package com.skeqi.mes.service.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.MaterialDao;
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesStationT;
@Service
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	MaterialDao materialDao;

	@Override
	public List<CMesAssemblyType> assemblyTypeList(Map<String, Object> map) {
		return materialDao.assemblyTypeList(map);
	}

	@Override
	public Integer countAssemblyTypeByAddAssemblyTypeNo(Map<String, Object> map) {
		return materialDao.countAssemblyTypeByAddAssemblyTypeNo(map);
	}

	@Transactional
	@Override
	public void addAssemblyType(Map<String, Object> map) {
		materialDao.addAssemblyType(map);
	}

	@Transactional
	@Override
	public void delAssemblyType(Map<String, Object> map) {
		materialDao.delAssemblyType(map);
	}

	@Transactional
	@Override
	public void editAssemblyType(Map<String, Object> map) {
		materialDao.editAssemblyType(map);
	}

	@Override
	public List<CMesMaterialT> materialList(Map<String, Object> map) {
		return materialDao.materialList(map);
	}

	@Transactional
	@Override
	public Integer addMaterial(Map<String, Object> map) {
		return materialDao.addMaterial(map);
	}

	@Transactional
	@Override
	public void editMaterial(Map<String, Object> map) {
		materialDao.editMaterial(map);

	}

	@Transactional
	@Override
	public void delMaterial(Map<String, Object> map) {
		materialDao.delMaterial(map);

	}

	@Override
	public List<CMesMaterialTypeT> materialTypeList(Map<String, Object> map) {
		return materialDao.materialTypeList(map);
	}

	@Transactional
	@Override
	public void addMaterialType(Map<String, Object> map) {
		materialDao.addMaterialType(map);
	}

	@Override
	public Integer countMaterialTypeByName(Map<String, Object> map) {
		return materialDao.countMaterialTypeByName(map);
	}

	@Transactional
	@Override
	public void delMaterialType(Map<String, Object> map) {
		materialDao.delMaterialType(map);
	}

	@Transactional
	@Override
	public void editMaterialType(Map<String, Object> map) {
		materialDao.editMaterialType(map);
	}

	@Override
	public Integer countMaterialByName(Map<String, Object> map) {
		return materialDao.countMaterialByName(map);
	}

	@Override
	public Integer countMaterialByNo(Map<String, Object> map) {
		return materialDao.countMaterialByNo(map);
	}

	@Override
	public List<CMesMaterialListT> materialLists(Map<String, Object> map) {
		return materialDao.materialLists(map);
	}

	@Override
	public List<CMesMaterialListDetailT> materialListDetails(Map<String, Object> map) {
		return materialDao.materialListDetails(map);
	}

	@Transactional
	@Override
	public void addMaterialList(HashMap<String, Object> map) {
		materialDao.addMaterialList(map);
	}

	@Override
	public Integer countMaterialListByNo(HashMap<String, Object> map) {
		return materialDao.countMaterialListByNo(map);
	}

	@Transactional
	@Override
	public void editMaterialList(HashMap<String, Object> map) {
		materialDao.editMaterialList(map);
	}

	@Transactional
	@Override
	public void delMaterialList(HashMap<String, Object> map) {
		materialDao.delMaterialList(map);
	}

	@Transactional
	@Override
	public void addMaterialDetail(HashMap<String, Object> map) {
		materialDao.addMaterialDetail(map);
	}

	@Transactional
	@Override
	public void editMaterialDetail(HashMap<String, Object> map) {
		materialDao.editMaterialDetail(map);
	}

	@Transactional
	@Override
	public void delMaterialDetail(HashMap<String, Object> map) {
		materialDao.delMaterialDetail(map);
	}

	@Override
	public List<CMesManufactureParametersT> manuParameterLists(Map<String, Object> map) {
		return materialDao.manuParameterLists(map);
	}

	@Transactional
	@Override
	public void addManuParameterList(HashMap<String, Object> map) {
		materialDao.addManuParameterList(map);
	}

	@Override
	public Integer countaddManuParameterListByNo(HashMap<String, Object> map) {
		return materialDao.countaddManuParameterListByNo(map);
	}

	@Transactional
	@Override
	public void editManuParameterList(HashMap<String, Object> map) {
		materialDao.editManuParameterList(map);
	}

	@Transactional
	@Override
	public void delManuParameterList(HashMap<String, Object> map) {
		materialDao.delManuParameterList(map);
	}

	@Override
	public List<CMesMfParametersDetailT> mfParametersDetailList(Map<String, Object> map) {
		return materialDao.mfParametersDetailList(map);
	}

	@Transactional
	@Override
	public void addManuParameterListDetail(HashMap<String, Object> map) {
		materialDao.addManuParameterListDetail(map);
	}

	@Transactional
	@Override
	public void editManuParameterListDetail(HashMap<String, Object> map) {
		materialDao.editManuParameterListDetail(map);
	}

	@Transactional
	@Override
	public void delManuParameterListDetail(HashMap<String, Object> map) {
		materialDao.delManuParameterListDetail(map);
	}

	@Override
	public List<CMesProductionProcessT> productionProcess(Map<String, Object> map) {
		return materialDao.productionProcess(map);
	}

	@Transactional
	@Override
	public void addProcessConfig(HashMap<String, Object> map) {
		materialDao.addProcessConfig(map);
	}

	@Override
	public int countProcessConfigByAll(HashMap<String, Object> map) {
		return materialDao.countProcessConfigByAll(map);
	}

	@Transactional
	@Override
	public void delProcessConfig(HashMap<String, Object> map) {
		materialDao.delProcessConfig(map);
	}

	@Transactional
	@Override
	public void editProcessConfig(HashMap<String, Object> map) {
		materialDao.editProcessConfig(map);
	}

	@Override
	public int countMaterialListDetailByMaterilaListId(HashMap<String, Object> map) {
		return materialDao.countMaterialListDetailByMaterilaListId(map);
	}

	@Override
	public int countMaterialMsgByMaterialType(HashMap<String, Object> map) {
		return materialDao.countMaterialMsgByMaterialType(map);
	}

	@Override
	public int countMaterialListByMaterialListId(HashMap<String, Object> map) {
		return materialDao.countMaterialListByMaterialListId(map);
	}

	@Override
	public int countProductionProcessByParameterListId(HashMap<String, Object> map) {
		return materialDao.countProductionProcessByParameterListId(map);
	}

	@Override
	public int countMaterialByAssemblyTypeId(HashMap<String, Object> map) {
		return materialDao.countMaterialByAssemblyTypeId(map);
	}

	@Override
	public Integer countMaterialListByName(HashMap<String, Object> map) {
		return materialDao.countMaterialListByName(map);
	}

	@Override
	public Integer countaddManuParameterListByName(HashMap<String, Object> map) {
		return materialDao.countaddManuParameterListByName(map);
	}

	@Override
	public Integer countAssemblyTypeByAddAssemblyTypeName(HashMap<String, Object> map) {
		return materialDao.countAssemblyTypeByAddAssemblyTypeName(map);
	}

	@Override
	public Integer del() {
		return materialDao.del();
	}

	@Override
	public Double findMaxVersion() {
		return materialDao.findMaxVersion();
	}

	@Override
	public void editstatus(String id) {
		materialDao.editstatustwo();
		materialDao.editstatus(id);
	}

	@Override
	public List<CMesStationT> findStation() {
		return materialDao.findStation();
	}

	@Override
	public Double findMaxmanu() {
		return materialDao.findMaxmanu();
	}

	@Override
	public String findNo(String name) {
		return materialDao.findNo(name);
	}

	@Override
	public List<Map<String, Object>> findMaterialTypeStatistics() {
		return materialDao.findMaterialTypeStatistics();
	}

	@Override
	public List<Map<String, Object>> findMaterialNumStatistics() {
		return materialDao.findMaterialNumStatistics();
	}

	@Override
	public List<Map<String, Object>> findMaterialList(Map<String, Object> map) {
		return materialDao.findMaterialList(map);
	}

}
