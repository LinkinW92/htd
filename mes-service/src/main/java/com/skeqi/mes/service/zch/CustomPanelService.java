package com.skeqi.mes.service.zch;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

public interface CustomPanelService {

	JSONObject snDetailedList(JSONObject json);

	JSONObject assemblyMaterial(JSONObject json);

	JSONObject splitSN(JSONObject json);

	JSONObject findLoadingMaterialList(JSONObject json);

	JSONObject addLoadingMaterial(JSONObject json);

	JSONObject findNcCodeConfigList(JSONObject json);

	JSONObject findNcCodeRecordList(JSONObject json);

	JSONObject addNcCodeRecord(JSONObject json);

	JSONObject hornAlarm(JSONObject json) throws IOException;

	JSONObject getLoadingCode(JSONObject json);

	JSONObject findDataCollectionRecordList(JSONObject json);

	JSONObject addDataCollection(JSONObject json);

	JSONObject findDataCollectionParams(JSONObject json);

}
