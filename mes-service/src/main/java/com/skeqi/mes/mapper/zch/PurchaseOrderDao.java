package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderDao {

	/**
	 * 获取采购单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findPurchaseList(Map<String, Object> map);

	/**
	 * 新增采购单
	 * @param map
	 * @return
	 */
	Integer addPurchase(Map<String, Object> map);

	/**
	 * 新增采购详情
	 * @param map
	 * @return
	 */
	Integer addPurchaseDetails(Map<String, Object> map);

	/**
	 * 获取采购
	 * @param map
	 * @return
	 */
	Map<String, Object> getByID(Map<String, Object> map);

	/**
	 * 修改采购单
	 * @param map
	 * @return
	 */
	Integer updatePurchase(Map<String, Object> map);

	/**
	 * 修改采购详情
	 * @param map
	 * @return
	 */
	Integer updatePurchaseDetails(Map<String, Object> map);

	/**
	 * 根据id获取采购详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getDetailsByID(Map<String, Object> map);

	/**
	 * 获取采购详情列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findPurchaseDetailsList(Map<String, Object> map);

	/**
	 * 删除采购单
	 * @param map
	 * @return
	 */
	Integer deletePurchase(Map<String, Object> map);

	/**
	 * 删除采购详情
	 * @param map
	 * @return
	 */
	Integer deletePurchaseDetails(Map<String, Object> map);

}
