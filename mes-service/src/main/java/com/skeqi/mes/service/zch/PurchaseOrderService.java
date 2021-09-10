package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderService {

	List<Map<String, Object>> findPurchaseList(Map<String, Object> map);

	Integer addPurchase(Map<String, Object> map) throws Exception;

	Integer addPurchaseDetails(Map<String, Object> map);

	Map<String, Object> getByID(Map<String, Object> map);

	Integer updatePurchase(Map<String, Object> map) throws Exception;

	Integer updatePurchaseDetails(Map<String, Object> map);

	Map<String, Object> getDetailsByID(Map<String, Object> map);

	List<Map<String, Object>> findPurchaseDetailsList(Map<String, Object> map);

	Integer deletePurchase(Map<String, Object> map);

	Integer deletePurchaseDetails(Map<String, Object> map);

}
