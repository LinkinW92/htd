package com.skeqi.mes.service.zch;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ModuleApiService {

	JSONObject writeLog(Map<String, Object> map);

	JSONObject findLog(Map<String, Object> map);

	JSONObject dataCollect(Map<String, Object> map);

	JSONObject inStation(Map<String, Object> map);

	JSONObject outStation(Map<String, Object> map);

	JSONObject getProductionInformation(Map<String, Object> map);

	JSONObject login(Map<String, Object> map);

	JSONObject deviceState(Map<String, Object> map);

	JSONObject findStationMaterial(Map<String, Object> map);

	JSONObject unbundStationMaterial(Map<String, Object> map);

	JSONObject findDataCollectPara(Map<String, Object> map);

}
