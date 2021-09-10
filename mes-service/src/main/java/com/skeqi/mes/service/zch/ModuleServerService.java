package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

public interface ModuleServerService {

	List<Map<String, Object>> findDataConfList(Map<String, Object> map);

	Integer addDataConf(Map<String, Object> map);

	Integer editDataConf(Map<String, Object> map);

	Integer deleteDataConf(Map<String, Object> map);

	List<Map<String, Object>> findMesInterfaceTypeList(Map<String, Object> map);

	List<Map<String, Object>> findMesInterfaceParaList(Map<String, Object> map);

	Integer addMesInterfacePara(Map<String, Object> map);

	Integer editMesInterfacePara(Map<String, Object> map);

	Integer deleteMesInterfacePara(Map<String, Object> map);

	List<Map<String, Object>> findDataCollectList(Map<String, Object> map);

	List<Map<String, Object>> findDataCollectParaList(Map<String, Object> map);

	List<Map<String, Object>> findStationList(Map<String, Object> map);

	List<Map<String, Object>> exportDataCollectParaList(Map<String, Object> map);

}
