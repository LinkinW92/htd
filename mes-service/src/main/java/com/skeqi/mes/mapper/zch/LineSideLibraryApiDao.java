package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;

public interface LineSideLibraryApiDao {

	Map<String, Object> getProductByName(Map<String, Object> map);

	Map<String, Object> getInventory(Map<String, Object> map);

	Map<String, Object> getStorageDetail(Map<String, Object> map);

	Integer updateQuantity(Map<String, Object> map);

	Integer insertInventoryBatch(Map<String, Object> map);

	Integer insertInventorySingle(Map<String, Object> map);

	Integer materialDeduction(Map<String, Object> inventoryMap);

	Map<String, Object> getMaterialResponse(Map<String, Object> map);

	Integer updateResponseStatus(Map<String, Object> responseMap);

	Integer getResponseFinishCount(Map<String, Object> responseMap);

	Map<String, Object> getMaterialRequest(Map<String, Object> responseMap);

	Integer insertInventory(Map<String, Object> requestMap);

	Integer updateRequestStatus(Map<String, Object> requestMap);

	Map<String, Object> getRockConfig(Map<String, Object> map);

	Integer insertRequestManual(Map<String, Object> rockConfigMap);

	Map<String, Object> getInventoryByCode(Map<String, Object> map);

	Integer updateInventoryStatus(Map<String, Object> inventoryMap);

	List<Map<String, Object>> findRockConfigPTL(Map<String, Object> map);

	List<Map<String, Object>> findMaterialQuestListByStation(Map<String, Object> map);

	List<Map<String, Object>> findMaterialQuestDetailListByStation(Map<String, Object> map);

	Map<String, Object> getRequestByBillNo(Map<String, Object> map);

	List<Map<String, Object>> findMaterialResponseListByBillNo(Map<String, Object> map);

	Integer reducePickingLockNumber(Map<String, Object> responseMap);

	Map<String, Object> getMaterialRecipeDetail(@Param("totalRecipeId") Object totalRecipeId, @Param("stationId") Object stationId, @Param("stepNo") Integer stepNo);

	Integer inventoryDeductionBatch(@Param("batchCode") Object batchCode, @Param("num") int num);

	Integer inventoryDeductionSingle(@Param("barcode") String barcode);

	Map<String, Object> getRock(Map<String, Object> map);

	Map<String, Object> getLastQuest();

	Integer insertRequest(Map<String, Object> map);

	Integer insertRequestDetail(Map<String, Object> map);

	Map<String, Object> getMaterialByCode(Map<String, Object> map);

	Integer getRockRemainNum(Map<String, Object> map);

	List<RLslMaterialRequestT> findLastRequest();

	Map<String, Object> getInventoryByBatchCode(@Param("batchCode") Object batchCode);

	Integer updateRetrospect(@Param("materialNo") Object materialNo, @Param("SN") String snBarcode, @Param("stationId") Object stationId);

	Integer insertRetrospect(@Param("materialNo") Object materialNo, @Param("SN") String snBarcode, @Param("stationId") Object stationId, @Param("emp") String emp,
			@Param("rockId") Object rockId);

	Map<String, Object> getInventoryByMaterialCode(@Param("materialCode") String barcode);

}
