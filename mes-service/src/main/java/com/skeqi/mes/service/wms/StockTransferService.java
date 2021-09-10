package com.skeqi.mes.service.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 库存转移
 *
 * @author yinp
 * @date 2021年4月28日
 *
 */
public interface StockTransferService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询队列
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findTaskqueue(JSONObject json);

	/**
	 * 查询库存
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findStock(JSONObject json);

	/**
	 * 出库
	 *
	 * @param listNo
	 * @param locationId
	 */
	public void chuku(String listNo, int locationId) throws Exception;

	/**
	 * 更新托盘码
	 *
	 * @param id
	 * @param tray
	 * @throws Exception
	 */
	public void updateTray(int id, String tray) throws Exception;

	/**
	 * 改入库队列动作标记
	 *
	 * @param id
	 */
	public void updateInTaskqueueFlag(@Param("id") int id) throws Exception;

	/**
	 * 改出库队列动作标记
	 *
	 * @param id
	 */
	public void updateOutTaskqueueFlag(@Param("id") int id) throws Exception;

	/**
	 * 直接入库
	 * @param json
	 * @throws Exception
	 */
	public void directWarehousing(JSONObject json) throws Exception;

	/**
	 * 直接出库
	 * @param json
	 * @throws Exception
	 */
	public void directDelivery(JSONObject json) throws Exception;

}
