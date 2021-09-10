package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

public interface ProductionManagementDao {

	List<Map<String, Object>> findPlanList(Map<String, Object> map);

	Map<String, Object> getPlanRepetition(Map<String, Object> map);

	Integer addPlan(Map<String, Object> map);

	Integer editPlan(Map<String, Object> map);

	Integer deletePlan(Map<String, Object> map);

	List<Map<String, Object>> findPlanListMrp();

	Map<String, Object> getMaterialByProductId(Map<String, Object> map);

	Map<String, Object> getMaterialInstanceByNo(Map<String, Object> map);

	Map<String, Object> getLslInstanceByNo(Map<String, Object> map);

	List<Map<String, Object>> findDemandMaterialList(Map<String, Object> map);

	List<Map<String, Object>> findMaterialInstance();

	List<Map<String, Object>> findLslInstance();

	Map<String, Object> getPurchaseMap(Map<String, Object> map);

	Map<String, Object> getPurchaseDemandMap(Map<String, Object> map);

	Map<String, Object> getWorkorderMap(Map<String, Object> map);

	Map<String, Object> getScheduledPlanMap(Map<String, Object> map);

	Map<String, Object> getWorkorderLockMap(Map<String, Object> map);

	Map<String, Object> getProductIdByMaterialNo(Map<String, Object> map);

	Map<String, Object> getLastPurchaseDemand();

	Integer insertPurchaseDemand(Map<String, Object> map);

	Integer insertPurchaseDemandR(Map<String, Object> map);

	List<Map<String, Object>> findSNList(Map<String, Object> map);

	Map<String, Object> getTrackingBySn(Map<String, Object> map);

	Integer getTrackingCountRBySn(Map<String, Object> map);

	Integer getTrackingCountPBySn(Map<String, Object> map);

	Integer insertTracking(Map<String, Object> printMap);

	Integer insertTranscodingRecord(Map<String, Object> map);

	Integer updateSnBinding(Map<String, Object> map);

	Integer updateOriginalSnState(Map<String, Object> map);

	Integer copySnBinding(Map<String, Object> map);

	List<Map<String, Object>> findContainerList(Map<String, Object> map);

	Map<String, Object> getContainerRepetition(Map<String, Object> map);

	Integer addContainer(Map<String, Object> map);

	Integer editContainer(Map<String, Object> map);

	Integer deleteContainer(Map<String, Object> map);

	List<Map<String, Object>> findPackList(Map<String, Object> map);

	Map<String, Object> getPackRepetition(Map<String, Object> map);

	Integer addPack(Map<String, Object> packMap);

	Integer editPack(Map<String, Object> packMap);

	List<Map<String, Object>> findPackDetailListByPackId(Map<String, Object> map);

	Integer deletePack(Map<String, Object> map);

	List<Map<String, Object>> findPackDetailList(Map<String, Object> map);

	Map<String, Object> getPackDetailRepetition(Map<String, Object> map);

	Integer addPackDetail(Map<String, Object> packDetailMap);

	Integer editPackDetail(Map<String, Object> packDetailMap);

	Integer deletePackDetail(Map<String, Object> map);

}
