package com.skeqi.mes.service.zch;

import java.util.*;

import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Rjson;

import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.skeqi.mes.mapper.zch.WorkorderDao;

import javax.annotation.Resource;

@Service
public class WorkorderServiceImpl implements WorkorderService {

	@Autowired
	WorkorderDao dao;

	@Autowired
	private CustomPropertyDao cMesCustomPropertyMapper;

	@Resource
	private CodeRuleService codeRuleService;

	@Autowired
	CMesCustomPropertyService customPropertyService;
	/**
	 * 工单列表
	 */
	@Override
	public List<Map<String, Object>> findWorkorderList(Map<String, Object> map) {
		List<Map<String, Object>> list = dao.findWorkorderList(map);
		return list;
	}
	/**
	 * 操作自定义属性值（内容）
	 * @param map
	 * @throws Exception
	 */
	private void handleCustomValue(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> editList = new ArrayList<>();
		List<Map<String, Object>> addList = new ArrayList<>();
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("custom");
		if (list != null && list.size() > 0) {
			for (Map<String, Object> object : list) {
				if (!StringUtils.isEmpty(object)) {
					System.out.println(object);
					object.put("objectCode", map.get("workorderId"));
					if (object.get("bindScopeKey").equals("0")) {
						object.put("propertyId", object.get("id"));
						if (object.get("value") == null || "".equals(object.get("value"))) {
							object.put("value", object.get("defaults"));
						}
					}
				}
				CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
				cMesCustomProperty.setObjectType(object.get("objectType").toString());
				cMesCustomProperty.setBindScopeValue(object.get("objectCode").toString());
				List<Integer> customPropertyValueAll = cMesCustomPropertyMapper.selectCustomPropertyValueAll(cMesCustomProperty);
				if (customPropertyValueAll.size()>0){
					//编辑
					editList.add(object);
				}else {
					//新增
					addList.add(object);
				}
			}

			//新增操作
			if (addList.size()>0){
				Map<String, Object> map1 = new HashMap<>();
				map1.put("list", addList);
				Integer editCustomPropertyValue = cMesCustomPropertyMapper.addCustomPropertyValue(map1);
				if (editCustomPropertyValue < 1) {
					throw new Exception("添加自定义属性内容失败");
				}
			}

			//编辑操作
			if (editList.size()>0){
				for (Object object : editList) {
					if (!StringUtils.isEmpty(object)){
						Map<String, Object> hashMap = (Map<String, Object>) object;
						if (hashMap.get("bindScopeKey").equals("0")) {
							if (StringUtils.isEmpty(hashMap.get("bindScopeValue"))) {
								hashMap.put("bindScopeValue",map.get("workorderId"));
							}
						}
						Integer integer = cMesCustomPropertyMapper.editCustomPropertyValue(hashMap);
						if (integer < 1) {
							throw new Exception("编辑自定义属性内容失败");
						}
					}
				}
			}
		}
	}

	/**
	 * 添加工单
	 */
	@Override
	public Integer addWorkorder(Map<String, Object> map) throws Exception {
		//新增自定义属性值（内容）
		handleCustomValue(map);

//		mapper.updateLevelNoByLineId(map);
		Integer levelNo = dao.getMaxLevelNoByLine(map);
		if(levelNo == null){
			levelNo = 0;
		}
		map.put("levelNo", levelNo + 1);
		Integer num  = dao.addWorkorder(map);
		return num;
	}

	/**
	 * 修改工单
	 */
	@Override
	public Integer updateWorkorder(Map<String, Object> map) throws Exception {
		//修改自定义属性值（内容）
		handleCustomValue(map);

		//清空
		map.put("WORKORDER_ID","");

		Map<String, Object> mapOld = dao.getLevelNoStatusOldByID(map);
		String levelNoOld = mapOld.get("LEVEL_NO").toString();
		String statusOld = mapOld.get("STATUS").toString();

		// 状态值有修改
		if (!statusOld.equals(map.get("STATUS").toString()) && "0".equals(statusOld) && "1".equals(map.get("STATUS").toString())) {
			map.put("LEVEL_NO", 1);
			dao.updateLevelNoByLineId(map);
		} else if (!levelNoOld.equals(map.get("LEVEL_NO").toString())) { // 优先级修改
			dao.updateLevelNoByLineId(map);
		}
		Integer num = dao.updateWorkorder(map);
		return num;
	}

	/**
	 * 删除工单
	 */
	@Override
	public Integer deleteWorkorder(Map<String, Object> map) throws Exception {
		//查询当前将被删除的工单
		Map<String, Object> orderByIdOrCode = dao.getByID(map);
		if (StringUtils.isEmpty(orderByIdOrCode)) {
			throw new Exception("删除工单失败");
		}
		CMesCustomProperty customProperty = new CMesCustomProperty();
		customProperty.setBindScopeValue((String) orderByIdOrCode.get("WORKORDER_ID"));
		customProperty.setObjectType(CustomAttributesConstant.workOrderManagement);

		Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
		if (integer<1){
			throw new Exception("删除失败");
		}

		return dao.deleteWorkorder(map);
	}

	/**
	 * 根据id获取工单
	 */
	@Override
	public Map<String, Object> getByID(Map<String, Object> map) {
		return dao.getByID(map);
	}

	/**
	 * 根据工单内部id或者工单编号查工单
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findWorkorderByIdOrWID(Map<String, Object> map) {
		return dao.findWorkorderByIdOrWID(map);
	}

	@Override
	public Integer getCountByWorkorderId(Map<String, Object> map) {
		return dao.getCountByWorkorderId(map);
	}

	@Override
	public void moveWork(Integer id) {
		dao.insertBolt(id);
		dao.insertKeypart(id);
		dao.insertLeakage(id);
		dao.insertPrint(id);
		dao.insertWork(id);

		dao.deleteBolt(id);
		dao.deleteKeypart(id);
		dao.deleteLeakage(id);
		dao.deletePrint(id);
		dao.deleteWork(id);
	}

	@Override
	public List<Map<String, Object>> findPWorkorderList(Map<String, Object> map) {
		return dao.findPWorkorderList(map);
	}

	@Override
	public List<Map<String, Object>> findWorkSn(Integer id) {
		return dao.findWorkSn(id);
	}

	@Override
	public List<Map<String, Object>> findListAll() {
		return dao.findListAll();
	}

	@Override
	public Rjson splitWorkorder(Map<String, Object> map) {
		Map<String, Object> workMap = dao.getByID(map);

		if(!"0".equals(workMap.get("STATUS").toString())) {
			return Rjson.error("工单已经开始不能拆批");
		}

		Integer num1 = Integer.parseInt(map.get("num1").toString());
		Integer num2 = Integer.parseInt(map.get("num2").toString());

		if(num1 + num2 != Integer.parseInt(workMap.get("ORDER_NUMBER").toString())) {
			return Rjson.error("数量有误");
		}


		return null;
	}

	@Override
	public Integer jointWorkorder(Map<String, Object> map) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findRecipeList(Map<String, Object> map) {
		return dao.findRecipeDetailList(map);
	}

	@Override
	public Integer updateRecipe(Map<String, Object> map) {

		return null;
	}

}
