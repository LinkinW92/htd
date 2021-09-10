package com.skeqi.mes.mapper.wms;

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
public interface StockTransferDao {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询出库队列
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findOutTaskqueue(@Param("listNo") String listNo);

	/**
	 * 查询入库队列
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findInTaskqueue(@Param("listNo") String listNo);

	/**
	 * 查询临时库存
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRStock(@Param("listNo") String listNo, @Param("locationId") Integer locationId);

	/**
	 * 查询永久库存
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findPStock(@Param("listNo") String listNo);

	/**
	 * 查询库位坐标
	 *
	 * @param locationId
	 * @return
	 */
	public JSONObject findLocationCoordinate(@Param("listNo") String listNo, @Param("locationId") int locationId);

	/**
	 * 修改出库队列动作标记
	 *
	 * @param id
	 * @return
	 */
	public int updateOutTaskqueueFlag(@Param("id") int id);

	/**
	 * 删除出库队列
	 *
	 * @param id
	 * @return
	 */
	public int deleteOutTaskqueue(@Param("id") int id);

	/**
	 * 更新托盘码
	 *
	 * @param id
	 * @param tray
	 * @throws Exception
	 */
	public int updateTray(@Param("id") int id, @Param("tray") String tray);

	/**
	 * 修改入库队列动作标记
	 *
	 * @param id
	 * @return
	 */
	public int updateInTaskqueueFlag(@Param("id") int id);

	/**
	 * 通过单号查询出库队列的数量
	 *
	 * @param listNo
	 * @return
	 */
	public int findInTaskqueueByListNo(@Param("listNo") String listNo);

	/**
	 * 通过单号查询入库队列的数量
	 *
	 * @param listNo
	 * @return
	 */
	public int findOutTaskqueueByListNo(@Param("listNo") String listNo);

	/**
	 * 更新库存转移表状态
	 *
	 * @param listNo
	 * @return
	 */
	public int updateStockTransferState(@Param("listNo") String listNo);

	/**
	 * 查询托盘码是否存在
	 *
	 * @param tray
	 * @return
	 */
	public int findTrayCount(@Param("tray") String tray);

	/**
	 * 查询临时库存详情
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> queryTemporaryInventoryDetails(JSONObject json);

	/**
	 * 查询物料库存
	 *
	 * @param json
	 * @return
	 */
	public JSONObject queryMaterialInventory(JSONObject json);

	/**
	 * 通过id查询物料库存
	 *
	 * @param json
	 * @return
	 */
	public JSONObject queryMaterialInventoryById(@Param("id")int id);

	/**
	 * 新增物料库存
	 *
	 * @param json
	 * @return
	 */
	public int newMaterialInventory(JSONObject json);

	/**
	 * 更新物料库存
	 *
	 * @param json
	 * @return
	 */
	public int updateMaterialInventory(JSONObject json);

	/**
	 * 删除临时物料库存
	 *
	 * @param id
	 * @return
	 */
	public int deleteTemporaryMaterialInventory(@Param("id") int id);

	/**
	 * 新增永久库存详情
	 *
	 * @param id
	 * @return
	 */
	public int newPermanentInventoryDetails(JSONObject json);

	/**
	 * 删除临时入库队列
	 *
	 * @param id
	 * @return
	 */
	public int deleteTemporaryStorageQueue(@Param("id") int id);

	/**
	 * 新增永久入库队列
	 *
	 * @param id
	 * @return
	 */
	public int addPermanentStorageQueue(JSONObject json);

	/**
	 * 修改库位状态以及托盘码
	 * @param id
	 * @param state
	 * @param tray
	 * @return
	 */
	public int modifyTheStatusOfTheLocationAndTheTrayCode(@Param("id") int id, @Param("state") int state,
			@Param("tray") String tray);

	/**
	 * 出库更新库存
	 * @param id
	 * @param materialNumber
	 * @return
	 */
	public int issueUpdateInventory(@Param("id")int id,@Param("materialNumber")int materialNumber);

	/**
	 * 删除库存记录
	 * @param id
	 * @return
	 */
	public int deleteInventoryRecord(@Param("id")int id);

	/**
	 * 删除临时出库队列
	 * @param id
	 * @return
	 */
	public int deleteTemporaryIssueQueue(@Param("id")int id);

	/**
	 * 新增永久出库队列
	 * @param json
	 * @return
	 */
	public int addPermanentIssueQueue(JSONObject json);

	/**
	 * 通过库位id查询库存
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findMaterialNumberByLocationId(@Param("locationId")int locationId);
}
