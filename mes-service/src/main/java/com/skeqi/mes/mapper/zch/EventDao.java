package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EventDao {

	/**
	 * 事件列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findList(Map<String, Object> map);

	List<Map<String, Object>>  findEventIds(Map<String, Object> map);

	List<Map<String, Object>> findEventById(@Param("objectList") List<String> ids);

	/**
	 * 添加事件
	 * @param map
	 * @return
	 */
	Integer addEvent(Map<String, Object> map);

	/**
	 * 物料事件列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findMaterialEventList(Map<String, Object> map);

	List<Map<String, Object>> findOrderEventByOrderId(Map<String, Object> map);

	/**
	 * 获取产线外部id
	 * @param map
	 * @return
	 */
	Map<String, Object> getLineCode(Map<String, Object> map);

	/**
	 * 获取bolt表中的MATERIAL_INSTANCE_ID
	 * @param map
	 * @return
	 */
	Map<String, Object> getMaterialId(Map<String, Object> map);

	/**
	 *
	 * @param map
	 * @return
	 */
	Map<String, Object> getKeyPartMaterialId(Map<String, Object> map);

    List<Map<String, Object>> findWorkOrderByobjectId(@Param("objectList") List<String> sn);

	List<Map<String, Object>> findWorkOrderByWorkOrderId(int WORK_ORDER_ID);

	List<Map<String, Object>> findEventTimeShaft(Map<String, Object> map);

	List<Map<String, Object>> findMaterialEventLately();

	List<Map<String, Object>> findWarehousingLately();

	List<Map<String, Object>> findYieldLately();

    List<Map<String, Object>> findConsumeMaterial();

	List<Map<String, Object>> findMaterialEventListLike(Map<String, Object> map);


}
