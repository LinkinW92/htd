package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

public interface ModuleApiDao {

	Integer writeLog(Map<String, Object> map);

	List<Map<String, Object>> findLog(Map<String, Object> map);

	List<Map<String, Object>> findConfList(Map<String, Object> map);

	Map<String, Object> getProductionByName(Map<String, Object> map);

	Integer insertDataCollect(Map<String, Object> map);

	Integer insertDataCollectDetailBatch(List<Map<String, Object>> confList);

	Map<String, Object> getProduct(Map<String, Object> workorderMap);

	Integer getNgNum(Map<String, Object> map);

	Map<String, Object> getEmp(Map<String, Object> map);

	Map<String, Object> getLastDeviceState(Map<String, Object> map);

	Integer insertDeviceState(Map<String, Object> map);

	List<Map<String, Object>> findMaterialList(Map<String, Object> map);

	Integer updateDataCollectValid(Map<String, Object> map);

	List<Map<String, Object>> findParaList(Map<String, Object> map);

}
