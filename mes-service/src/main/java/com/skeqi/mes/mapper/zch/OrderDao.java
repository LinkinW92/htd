package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderDao {

	/**
	 * 获取订单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findOrderList(Map<String, Object> map);

	/**
	 * 新增订单
	 * @param map
	 * @return
	 */
	Integer addOrder(Map<String, Object> map);

	/**
	 * 修改订单
	 * @param map
	 * @return
	 */
	Integer updateOrder(Map<String, Object> map);

	/**
	 * 获取订单记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findOrderrecondList(Map<String, Object> map);

	/**
	 * 新增订单记录
	 * @param map
	 * @return
	 */
	Integer addOrderrecord(Map<String, Object> map);

	/**
	 * 修改订单记录
	 * @param map
	 * @return
	 */
	Integer updateOrderrecord(Map<String, Object> map);

	/**
	 * 获取排产里订单列表
	 * @return
	 */
	List<Map<String, Object>> findOrderListOnScheduling();

	/**
	 * 根据产线id和日期获取工单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findWorkorderByLineId(Map<String, Object> map);

	/**
	 * 删除订单
	 * @param map
	 * @return
	 */
	Integer deleteOrder(Map<String, Object> map);

	/**
	 * 删除订单记录
	 * @param map
	 * @return
	 */
	Integer deleteOrderrecord(Map<String, Object> map);

	/**
	 * 根据订单id查询订单数量和已排产数量
	 * @param map
	 * @return
	 */
	Map<String, Object> getSchedulingNum(Map<String, Object> map);

	/**
	 * 根据id获取订单
	 * @param map
	 * @return
	 */
	Map<String, Object> getByID(Map<String, Object> map);

	/**
	 * 根据id获取订单详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getOrderrecordByID(Map<String, Object> map);

	/**
	 * 查询工单需求物料
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findDemandMaterial(Map<String, Object> map);

	/**
	 * 获取物料库存数量
	 * @param mapMa
	 * @return
	 */
	List<Map<String, Object>> getMaterialStock(Map<String, Object> mapMa);

	/**
	 * 按id或按code查询订单信息
	 * @param map
	 * @return
	 */
    Map<String, Object> findOrderByIdOrCode(Map<String, Object> map);

    /**
	 * 获取当前所有物料库存数量
	 * @return
	 */
	List<Map<String, Object>> findAllInventory();

	/**
	 * 插入信息到记录表
	 * @param list
	 */
	void InsertInventory(@Param("list") List<Map<String, Object>> list);

	/**
	 * 获取物料指定日期物料记录
	 * @param mapMa
	 * @return
	 */
	Map<String, Object> getInventoryByNo(Map<String, Object> mapMa);

	/**
	 * 获取历史记录数量（查重用）
	 * @param map
	 * @return
	 */
	Integer getInventoryCount(Map<String, Object> map);

	/**
	 * 根据物料code和时间区间获取物料库存记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMaterialInventoryList(Map<String, Object> map);

	/**
	 * 获取消耗列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findAllConsume(Map<String, Object> map);

	/**
	 * 获取采购列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findAllPurchase(Map<String, Object> map);

	/**
	 * 获取产品产量
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findAllYield(Map<String, Object> map);

	/**
	 * 根据产品名称查产品型号
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findProductModelByName(Map<String, Object> map);

    List<Map<String, Object>> findListAll();
}
