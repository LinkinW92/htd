package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.*;

public interface ReportService {
	List<PTrackingT> ptrackingList(Map<String, Object> map);
	String getProductionName(int production_id);
	List<RTrackingT> rtrackingList(Map<String, Object> map);
	List<PMesBoltT> boltList(Map<String, Object> map);
	List<PMesKeypartT> keypartList(Map<String, Object> map);
	List<PMesLeakageT> leakageList(Map<String, Object> map);
	List<RMesBolt> rboltList(Map<String, Object> map);
	List<RMesKeypart> rkeypartList(Map<String, Object> map);
	List<RMesLeakage> rleakageList(Map<String, Object> map);
	List<RMesBolt> boltListR(Map<String, Object> map);
	List<RMesKeypart> keypartListR(Map<String, Object> map);
	List<RMesLeakage> leakageListR(Map<String, Object> map);
	List<CMesRecipeDatilT> recipeDatilLists(Map<String, Object> map);
	List<CMesProductionT> getProductionVr();
	void saveRecipeDatil(Map<String, Object> map);
	List<CMesRecipeT> getRecipeIdByName(Map<String, Object> map);
	List<CMesStationT> getStationByName(Map<String, Object> map);
	List<CMesProductionT> getProductionByName(Map<String, Object> returnMap);
	List<CMesProductionT> getProductionByPackPn(Map<String, Object> map);
	int countStation(String stationName);
	int countPackPn(String packPn);
	int countProductionName(String productionName);
	void saveProductionRecipe(Map<String, Object> map);
	List<CMesProductionRecipeT> productionRecipe(Map<String, Object> map);
	//	List<CMesProductionT> findProductionIdByName(Map<String, Object> map);
	//	List<CMesStationT> findStationIdByName(Map<String, Object> map);
	void createRecipe(Map<String, Object> map);
	void removeRecipeDatilByRecipeId(Map<String, Object> map);
	void createProduction(Map<String, Object> map);
	List<CMesRecipeTypeT> getRecipeTypeNameByStepCategory(Map<String, Object> map);
	List<RUploadDataT> uploadDataList(Map<String, Object> map);
	List<RAsmElectricDetection> rAsmElectricDetectionList(Map<String, Object> map);
	List<PChargedischarge> pChargedischargeList(Map<String, Object> map);
	List<RAsmEol> rAsmEolList(Map<String, Object> map);
	List<CMesXXT> getXXT(Map<String, Object> map);
	String getSn(String materialName);
	String getPSn(String materialName);
	List<PMesEolT> findAllEol(String sn);
	List<PMesDischargeT> findAllDischargeT(String sn);
	List<PMesWeightT> findAllWeight(String sn);
	List<PMesStationPassT> findAllStationPass(String sn);
	JSONObject firstSn();
}
