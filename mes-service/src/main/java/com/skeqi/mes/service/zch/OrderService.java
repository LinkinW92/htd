package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

public interface OrderService {

	List<Map<String, Object>> findOrderList(Map<String, Object> map);

	Integer addOrder(Map<String, Object> map) throws Exception;

	Integer updateOrder(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> findOrderrecondList(Map<String, Object> map);

	Integer addOrderrecord(Map<String, Object> map);

	Integer updateOrderrecord(Map<String, Object> map);

	List<Map<String, Object>> schedulingOrderList();

	List<Map<String, Object>> schedulingWorkorderList(Map<String, Object> map) throws Exception;

	Integer deleteOrder(Map<String, Object> map) throws Exception;

	Integer deleteOrderrecord(Map<String, Object> map);

	Map<String, Object> getByID(Map<String, Object> map);

	Map<String, Object> getOrderrecordByID(Map<String, Object> map);

	Map<String, Object> findDemandMaterial(Map<String, Object> map) throws Exception;


	Map<String, Object> findOrderByIdOrCode(Map<String, Object> map);

	void materialInventory();

	List<List<Map<String, Object>>> findMaterialInventory(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> findProductModelByName(Map<String, Object> map);

    List<Map<String, Object>> findListAll();
}
