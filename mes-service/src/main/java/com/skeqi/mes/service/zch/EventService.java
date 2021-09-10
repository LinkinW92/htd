package com.skeqi.mes.service.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EventService {

	List<Map<String, Object>> findList(Map<String, Object> map);

	List<Map<String, Object>> findEventIds(Map<String, Object> map);

	List<Map<String, Object>> findEventById(List<String> ids);

	Integer addEvent(Map<String, Object> map);
	List<Map<String, Object>> findMaterialEventList(Map<String, Object> map);

	List<Map<String, Object>> findOrderEventByOrderId(Map<String, Object> map);

	List<Map<String, Object>> findWorkOrderByWorkOrderId(int WORK_ORDER_ID);

	Map<String, Object> getLineCode(Map<String, Object> map);
	Map<String, Object> getMaterialId(Map<String, Object> map);
	Map<String, Object> getKeyPartMaterialId(Map<String, Object> map);

    List<Map<String, Object>> findWorkOrderByobjectId(List<String> sn);

    List<Map<String, Object>> findEventTimeShaft(Map<String, Object> map);

	List<Map<String, Object>> findMaterialEventLately();

	Map<String, Object> findMaterialEventStatisticsLately();

	List<Map<String, Object>> findMaterialEventListLike(Map<String, Object> map);


}
