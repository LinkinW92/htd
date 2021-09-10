package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

public interface ProductionManagementService {

	List<Map<String, Object>> findPlanList(Map<String, Object> map);

	Integer addPlan(Map<String, Object> map);

	Integer editPlan(Map<String, Object> map);

	Integer deletePlan(Map<String, Object> map);

	void mrpExecute() throws Exception;

	List<Map<String, Object>> findSNList(Map<String, Object> map);

	Integer transcodingSN(Map<String, Object> map);

	Integer splitSN(Map<String, Object> map);

	List<Map<String, Object>> findContainerList(Map<String, Object> map);

	Integer addContainer(Map<String, Object> map);

	Integer editContainer(Map<String, Object> map);

	Integer deleteContainer(Map<String, Object> map);

	List<Map<String, Object>> findPackList(Map<String, Object> map);

	List<Map<String, Object>> findPackDetailList(Map<String, Object> map);

	Integer addPack(Map<String, Object> map);

	Integer editPack(Map<String, Object> map);

	Integer deletePack(Map<String, Object> map);

	Integer addPackDetail(Map<String, Object> map);

	Integer editPackDetail(Map<String, Object> map);

	Integer deletePackDetail(Map<String, Object> map);

}
