package com.skeqi.mes.service.zch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.qh.CMesAreaT;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.PurchaseOrderDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderDao dao;

	@Autowired
	private CustomPropertyDao cMesCustomPropertyMapper;

	@Override
	public List<Map<String, Object>> findPurchaseList(Map<String, Object> map) {
		return dao.findPurchaseList(map);
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
					object.put("objectCode", map.get("PURCHASE_NO"));
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
								hashMap.put("bindScopeValue",map.get("PURCHASE_NO"));
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

	@Override
	public Integer addPurchase(Map<String, Object> map) throws Exception {
		//新增自定义属性值（内容）
		handleCustomValue(map);
		return dao.addPurchase(map);
	}

	@Override
	public Integer addPurchaseDetails(Map<String, Object> map) {
		return dao.addPurchaseDetails(map);
	}

	@Override
	public Map<String, Object> getByID(Map<String, Object> map) {
		return dao.getByID(map);
	}

	@Override
	public Integer updatePurchase(Map<String, Object> map) throws Exception {
		//新增自定义属性值（内容）
		handleCustomValue(map);
		return dao.updatePurchase(map);
	}

	@Override
	public Integer updatePurchaseDetails(Map<String, Object> map) {
		return dao.updatePurchaseDetails(map);
	}

	@Override
	public Map<String, Object> getDetailsByID(Map<String, Object> map) {
		return dao.getDetailsByID(map);
	}

	@Override
	public List<Map<String, Object>> findPurchaseDetailsList(Map<String, Object> map) {
		return dao.findPurchaseDetailsList(map);
	}

	@Override
	public Integer deletePurchase(Map<String, Object> map) {
		return dao.deletePurchase(map);
	}

	@Override
	public Integer deletePurchaseDetails(Map<String, Object> map) {
		return dao.deletePurchaseDetails(map);
	}

}
