package com.skeqi.mes.service.zch;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface LineSideLibraryService {

	List<Map<String, Object>> findRockList(Map<String, Object> map);

	Integer addRock(Map<String, Object> map);

	Integer editRock(Map<String, Object> map);

	Integer deleteRock(Map<String, Object> map);

	List<Map<String, Object>> findRockConfigList(Map<String, Object> map);

	Integer addRockConfig(Map<String, Object> map);

	Integer editRockConfig(Map<String, Object> map);

	Integer deleteRockConfig(Map<String, Object> map);

	List<Map<String, Object>> findInventoryList(Map<String, Object> map);

	Integer addInventory(Map<String, Object> map);

	Integer editInventory(Map<String, Object> map);

	List<Map<String, Object>> findRockConfigVersionList(Map<String, Object> map);

	Integer addRockConfigVersion(Map<String, Object> map);

	Integer editRockConfigVersion(Map<String, Object> map);

	Integer deleteRockConfigVersion(Map<String, Object> map);

	Integer enableRockConfigVersion(Map<String, Object> map);

	List<Map<String, Object>> findMaterialFuzzy(Map<String, Object> map);

	JSONObject findMaterialQuestKanban() throws ParseException;

	List<Map<String, Object>> reclaimingPTL(Map<String, Object> map) throws IOException;

	List<Map<String, Object>> findRockListFuzzy(Map<String, Object> map);

	Map<String, Object> findMateriaInstanceByMaterialNo(Map<String, Object> map);

}
