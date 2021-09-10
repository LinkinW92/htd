package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

public interface LineSideLibraryDao {

	List<Map<String, Object>> findRockList(Map<String, Object> map);

	Map<String, Object> getRock(Map<String, Object> map);

	Integer insertRock(Map<String, Object> map);

	Integer editRock(Map<String, Object> map);

	Integer deleteRock(Map<String, Object> map);

	List<Map<String, Object>> findRockConfigList(Map<String, Object> map);

	Map<String, Object> getRockConfig(Map<String, Object> map);

	Integer updateRockConfig(Map<String, Object> map);

	Integer insertRockConfig(Map<String, Object> map);

	Integer editRockConfig(Map<String, Object> map);

	Integer deleteRockConfig(Map<String, Object> map);

	List<Map<String, Object>> findInventoryList(Map<String, Object> map);

	Integer insertInventory(Map<String, Object> map);

	Integer editInventory(Map<String, Object> map);

	List<Map<String, Object>> findRockConfigVersionList(Map<String, Object> map);

	Map<String, Object> getRockConfigVersionByVersion(Map<String, Object> map);

	Integer insertRockConfigVersion(Map<String, Object> map);

	Integer editRockConfigVersion(Map<String, Object> map);

	Integer deleteRockConfigVersion(Map<String, Object> map);

	Map<String, Object> getRockConfigVersion(Map<String, Object> map);

	Integer disableRockConfigVersion(Map<String, Object> rockMap);

	Integer enableRockConfigVersion(Map<String, Object> map);

	Integer deleteRockConfigByVersionId(Map<String, Object> map);

	List<Map<String, Object>> findMaterialFuzzy(Map<String, Object> map);

	List<Map<String, Object>> findMaterialQuestKanban();

	List<Map<String, Object>> findInventoryListOrderByQuantity(Map<String, Object> responseMap);

	List<Map<String, Object>> findMaterialRequestListByBillNo(Map<String, Object> map);

	Integer updateRequestPicker(Map<String, Object> map);

	Map<String, Object> getRockByPtlNo(Map<String, Object> map);

	List<Map<String, Object>> findRockListFuzzy(Map<String, Object> map);

	List<Map<String, Object>> findMateriaInstanceByMaterialNo(Map<String, Object> map);

	List<Map<String, Object>> findMateriaInstanceBatchByMaterialNo(Map<String, Object> map);

	List<Map<String, Object>> findMateriaInstanceSingleByMaterialNo(Map<String, Object> map);

	Map<String, Object> getRockConfigByRockId(Map<String, Object> map);

}
