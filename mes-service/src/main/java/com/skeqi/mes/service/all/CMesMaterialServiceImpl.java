package com.skeqi.mes.service.all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.chenj.srm.req.CMesJlMaterialTReq;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesMaterialTDAO;
import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesSystem;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class CMesMaterialServiceImpl implements CMesMaterialService{

	@Autowired
	CMesSystemService sService;

	@Autowired
	private CMesMaterialTDAO dao;

	@Autowired
	private CustomPropertyDao cMesCustomPropertyMapper;

	@Resource
	private CodeRuleService codeRuleService;

	@Autowired
	CMesCustomPropertyService customPropertyService;

	@Override
	public List<CMesMaterialTypeT> findAllMaterialType(CMesMaterialTypeT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMaterialType(t);
	}

	@Override
	public CMesMaterialTypeT findMaterialTypeByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialTypeByid(id);
	}

	@Override
	public Integer addMaterialType(CMesMaterialTypeT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialType()==null || t.getMaterialType()==""){
			throw new ParameterNullException("物料类型不能为空",200);
		}
		List<CMesMaterialTypeT> findAllMaterialType = dao.findAllMaterialType(t);
		if(findAllMaterialType.size()>0){
			throw new NameRepeatException("物料类型重复",100);
		}
		return dao.addMaterialType(t);
	}

	@Override
	public Integer updateMaterialType(CMesMaterialTypeT t) throws ServicesException {


		// TODO Auto-generated method stub
		if(t.getMaterialType()==null || t.getMaterialType()==""){
			throw new ParameterNullException("物料类型不能为空",200);
		}
		CMesMaterialTypeT findMaterialTypeByid = dao.findMaterialTypeByid(t.getId());
		if(!findMaterialTypeByid.getMaterialType().equals(t.getMaterialType())){
			List<CMesMaterialTypeT> findAllMaterialType = dao.findAllMaterialType(t);
			if(findAllMaterialType.size()>0){
				throw new NameRepeatException("物料类型重复",100);
			}
		}
		return dao.updateMaterialType(t);
	}

	@Override
	public Integer delMaterialType(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterialType(id);
	}

	@Override
	public List<CMesJlMaterialT> findAllMaterial(CMesJlMaterialT t){
		// TODO Auto-generated method stub
		return dao.findAllMaterial(t);
	}

	@Override
	public CMesJlMaterialT findMaterialByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialByid(id);
	}
	/**
	 * 操作自定义属性值（内容）
	 * @param t
	 * @throws Exception
	 */
	private void handleCustomValue(CMesJlMaterialT t) throws Exception {
		List<Map<String, Object>> editList = new ArrayList<>();
		List<Map<String, Object>> addList = new ArrayList<>();
		List<Map<String, Object>> list = (List<Map<String, Object>>) t.getCustom();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> object : list) {
				if (!StringUtils.isEmpty(object)) {
					System.out.println(object);
					object.put("objectCode", t.getBomId());
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
								hashMap.put("bindScopeValue",t.getBomId());
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
	public Integer addMaterial(CMesJlMaterialT t) throws Exception {

		CMesJlMaterialT one = new CMesJlMaterialT();
		one.setBomId(t.getBomId());
		List<CMesJlMaterialT> findAllMaterial = dao.findAllMaterial(one);
		if(findAllMaterial.size()>0){
			throw new NameRepeatException("物料编号重复",100);
		}

		Integer findMaterialByName = dao.findMaterialByName(t.getMaterialName());
		if(findMaterialByName>0){
			throw new NameRepeatException("物料名称重复",100);
		}

		//新增自定义属性值（内容）
		handleCustomValue(t);

		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					Integer res = dao.addMaterial(t);
					return res;
				}
			}
		}
		// 结束

		return dao.addMaterial(t);
	}

	@Override
	public Integer updateMaterial(HttpServletRequest request) throws Exception {

		Integer id = EqualsUtil.integer(request, "id", "物料id", true);
		String  bomId = EqualsUtil.string(request, "bomId", "物料编码", true);
		String  materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
		String  description = EqualsUtil.string(request, "description", "描述", false);
		String  specifications = EqualsUtil.string(request, "specifications", "规格", false);
		String  materialGroup = EqualsUtil.string(request, "materialGroup", "物料组", false);
		String  materialType = EqualsUtil.string(request, "materialType", "物料类型", true);
		Integer tracesType = EqualsUtil.integer(request, "tracesType", "追溯方式", true);
		String  stockUnit = EqualsUtil.string(request, "stockUnit", "库存单位", false);
		String  inventoryModelGroup = EqualsUtil.string(request, "inventoryModelGroup", "库存模型组", false);
		String  inventoryDimensionGroup = EqualsUtil.string(request, "inventoryDimensionGroup", "库存维组", false);
		String  release = EqualsUtil.string(request, "release", "发放方式", true);
		String  inspection = EqualsUtil.string(request, "inspection", "检验等级", false);
		String  fictitious = EqualsUtil.string(request, "fictitious", "虚拟", false);
		String  salesUnit = EqualsUtil.string(request, "salesUnit", "销售单位", false);
		String  secrecy = EqualsUtil.string(request, "secrecy", "是否保密", false);
		String  purchasingUnit = EqualsUtil.string(request, "purchasingUnit", "采购单位", false);
		String  productionTeam = EqualsUtil.string(request, "productionTeam", "生产组", false);
		String  mininumberOfPackages = EqualsUtil.string(request, "mininumberOfPackages", "最小包装数量", false);
		String  termOfValidity = EqualsUtil.string(request, "termOfValidity", "有效期", false);
		String  typenum = EqualsUtil.string(request, "typenum", "型号", false);
		String  voltage = EqualsUtil.string(request, "voltage", "电压容量", false);
		String  partCounts = EqualsUtil.string(request, "partCounts", "子件数", false);
		String  cellCapacity = EqualsUtil.string(request, "cellCapacity", "电芯容量", false);
		String  scan = EqualsUtil.string(request, "scan", "是否扫描", true);
		String  cellSpecification = EqualsUtil.string(request, "cellSpecification", "电芯规格", false);
		String  customerPartCode = EqualsUtil.string(request, "customerPartCode", "客户零件编码", false);
//			String materialLength = EqualsUtil.string(request, "materialLength", "物料长度", false);
//			String materialWidth = EqualsUtil.string(request, "materialWidth", "物料宽度", false);
//			String materialHight = EqualsUtil.string(request, "materialHight", "物料高度", false);
//			String materialVolume = EqualsUtil.string(request, "materialVolume", "物料体积", false);
//			String materialWeight = EqualsUtil.string(request, "materialWeight", "物料重量", false);
//			String materialLt = EqualsUtil.string(request, "materialLt", "存放库位类型", true);
//			String materialIncomType = EqualsUtil.string(request, "materialIncomType", "来料方式", true);
//			String materialLowLimitmaterial = EqualsUtil.string(request, "materialLowLimitmaterial", "物料库存下限", false);
//			String materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次数量", false);
//			String daysOfFailure = EqualsUtil.string(request, "daysOfFailure", "失效天数", false);


		CMesJlMaterialT dx = new CMesJlMaterialT();
		dx.setId(id);
		// API外部ID标识
		HttpSession session=request.getSession();
		session.setAttribute("materialCode", materialName);
		dx.setBomId(bomId);
		dx.setMaterialName(materialName);
		dx.setDescription(description);
		dx.setSpecifications(specifications);
		dx.setMaterialGroup(materialGroup);
		dx.setMaterialType(materialType);
		dx.setTracesType(tracesType);
		dx.setStockUnit(stockUnit);
		dx.setInventoryModelGroup(inventoryModelGroup);
		dx.setInventoryDimensionGroup(inventoryDimensionGroup);
		dx.setRelease(release);
		dx.setInspection(inspection);
		dx.setFictitious(fictitious);
		dx.setSalesUnit(salesUnit);
		dx.setSecrecy(secrecy);
		dx.setPurchasingUnit(purchasingUnit);
		dx.setProductionTeam(productionTeam);
		dx.setMininumberOfPackages(mininumberOfPackages);
		dx.setTermOfValidity(termOfValidity);
		dx.setTypenum(typenum);
		dx.setVoltage(voltage);
		dx.setPartCounts(partCounts);
		dx.setCellCapacity(cellCapacity);
		dx.setScan(scan);
		dx.setCellSpecification(cellSpecification);
		dx.setCustomerPartCode(customerPartCode);
		//修改自定义属性值（内容）所需参数
		JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
		dx.setCustom(jsonArray);
		//修改自定义属性值（内容）
		handleCustomValue(dx);

		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					Integer res = dao.updateMaterialXT355_356_357(dx);
					return res;
				}
			}
		}
		Integer integer = dao.updateMaterial(dx);
		if (integer<1){
			throw new Exception("修改失败");
		}
		// 结束
		return integer;
	}

	@Override
	public Integer delMaterial(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterial(id);
	}

	@Override
	public List<CMESMaterialRepairT> findAllMaterialRepair(CMESMaterialRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMaterialRepair(t);
	}

	@Override
	public CMESMaterialRepairT findMaterialRepairByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialRepairByid(id);
	}

	@Override
	public Integer addMaterialRepair(CMESMaterialRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("发起人不能为空",200);
		}
		return dao.addMaterialRepair(t);
	}

	@Override
	public Integer updateMaterialRepair(CMESMaterialRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateMaterialRepair(t);
	}

	@Override
	public Integer delMaterialRepair(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterialRepair(id);
	}

	@Override
	public List<CMesProductionProcessT> findAllProcess(CMesProductionProcessT p) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllProcess(p);
	}

	@Override
	public CMesProductionProcessT findProcessByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findProcessByid(id);
	}

	@Override
	public Integer addProcess(CMesProductionProcessT p) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addProcess(p);
	}

	@Override
	public Integer updateProcess(CMesProductionProcessT p) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateProcess(p);
	}

	@Override
	public Integer delProcess(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delProcess(id);
	}

	@Override
	public List<CMesAssemblyType> findAllAssembly(CMesAssemblyType type) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllAssembly(type);
	}

	@Override
	public CMesAssemblyType findAssemblyByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findAssemblyByid(id);
	}

	@Override
	public Integer addAssmbly(CMesAssemblyType t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addAssmbly(t);
	}

	@Override
	public Integer updateAssmebly(CMesAssemblyType t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateAssmebly(t);
	}

	@Override
	public Integer delAssmebly(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.delAssmebly(id);
	}

	@Override
	public List<CMesJlMaterialT> findIdNameAll() {
		// TODO Auto-generated method stub
		return dao.findIdNameAll();
	}

	@Override
	public List<CMesJlMaterialT> findAllMaterialXT355_356_357(CMesJlMaterialT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMaterialXT355_356_357(t);
	}

	@Override
	public boolean checkMaterialNo(String materialNo) {
		Integer conut = dao.checkMaterialNo(materialNo);
		if(conut<=0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkMaterialName(String materialName) {
		// TODO Auto-generated method stub
		Integer conut = dao.checkMaterialName(materialName);
		if(conut<=0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteMaterial(Integer id) throws Exception {
		//查询当前将被删除的物料
		CMesJlMaterialT materialByid = dao.findMaterialByid(id);
		if (StringUtils.isEmpty(materialByid)) {
			throw new Exception("删除物料失败");
		}
		CMesCustomProperty customProperty = new CMesCustomProperty();
		customProperty.setBindScopeValue(materialByid.getBomId());
		customProperty.setObjectType(CustomAttributesConstant.materialManager);

		Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
		if (integer<1){
			throw new Exception("删除失败");
		}

		boolean delMaterial = true;
		Integer deleteMaterial = dao.deleteMaterial(id);
		if (deleteMaterial<1){
			delMaterial = false;
			throw new Exception("删除物料失败");
		}
		return delMaterial;
	}

    @Override
    public List<CMesJlMaterialT> findMaterialCodeList() {
        return dao.findMaterialCodeList();
    }

	/**
	 * 按物料编码查询物料列表
	 * @param cMesMaterialInstanceT
	 * @return
	 */
	@Override
	public List<CMesJlMaterialT> findProductMaterialList(CMesMaterialInstanceT cMesMaterialInstanceT) {
		return dao.findProductMaterialList(cMesMaterialInstanceT);
	}

	@Override
	public List<CMesMaterialT> findMaterialByMaterialCode(List<String> myList) {
		return dao.findMaterialByMaterialCode(myList);
	}

	@Override
	public CMesJlMaterialT findMaterialByidAndMaterialName(Integer id, String materialName) {
		return dao.findMaterialByidAndMaterialName(id,materialName);
	}
	@Override
	public Rjson findAllMaterialList(CMesJlMaterialTReq req) {
		return Rjson.success(new PageInfoUtil<>(dao.findAllMaterialList(req), 5, req.getPageSize(), req.getPageNum()));
	}
}
