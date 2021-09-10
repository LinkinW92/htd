package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface CustomPanelDao {

	List<Map<String, Object>> snDetailedList(JSONObject query);

	List<Map<String, Object>> findLoadingMaterialList(JSONObject json);

	Map<String, Object> getLoadingMaterial(JSONObject json);

	void insertLoadingMaterial(JSONObject json);

	List<Map<String, Object>> findRecipeMaterialList(JSONObject json);

	Map<String, Object> getLoadingMap(@Param("stationId") Object stationId, @Param("lineId") Object lineId, @Param("workorderId") Object workorderId, @Param("materialNo") Object mATERIALPN);

	List<Map<String, Object>> findNcCodeConfigList(JSONObject json);

	List<Map<String, Object>> findNcCodeRecordList(JSONObject json);

	void addNcCodeRecord(JSONObject json);

	Map<String, Object> getTrumpetByStation(JSONObject json);

	String getLoadingCode(JSONObject json);

	List<Map<String, Object>> findDataCollectionRecordList(JSONObject json);

	List<Map<String, Object>> findDataCollectionParams(JSONObject json);

	void addDataCollectionRecord(JSONObject json);

	List<Map<String, Object>> findDataCollectionParamsAll(JSONObject json);

	void addDataCollectionRecordDetail(Map<String, Object> map);

}
