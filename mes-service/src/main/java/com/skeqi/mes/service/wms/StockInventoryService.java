package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.InventoryDetailT;
import com.skeqi.mes.pojo.wms.InventoryT;

/**
 * 库存盘点
 * @author yinp
 *
 */
public interface StockInventoryService {
	/**
	 * 查询库存
	 * @param locationId
	 * @param materialId
	 * @param projectId
	 * @return
	 */
	public Integer findMaterialNumber(Integer locationId,Integer materialId,Integer projectId);

	/**
	 * 新增盘点单
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public boolean addInventory(JSONObject json) throws Exception;

	/**
	 * 查询盘点单据
	 * @param map
	 * @return
	 */
	public List<InventoryT> findInventoryList(Map<String,Object> map);

	/**
	 * 查询盘点单据详情
	 * @param map
	 * @return
	 */
	public List<InventoryDetailT> findInventoryDetailList(Map<String,Object> map);
	/**
	 * 执行
	 * @param id
	 * @return
	 */
	public boolean implement(@Param("id")Integer id)throws Exception;

	/**
	 * 查询是否有盘点记录未审批或者未执行
	 * @return
	 */
	public boolean queryWhetherThereIsInventory();

	/**
	 * 查询是否还有未完成的出库队列
	 * @return
	 */
	public boolean findOutgoingQueue();

	/**
	 * 查询是否还有未完成的入库队列
	 * @return
	 */
	public boolean findWarehousingQueue();

	/**
	 * 查询所有仓库
	 * @return
	 */
	public List<JSONObject> findWarehouseAll();

	/**
	 * 通过仓库id查询区域
	 * @return
	 */
	public List<JSONObject> findAreaAllByWarehouseId(int warehouseId);

	/**
	 * 通过区域id查询库区
	 * @return
	 */
	public List<JSONObject> findReservoirAreaAllByAeraId(int areaId);

	/**
	 * 通过库区id查询库位
	 * @param reservoirAreaId
	 * @return
	 */
	public List<JSONObject> findLocationAllByReservoirAreaId(int reservoirAreaId);

	/**
	 * 查询所有物料
	 * @return
	 */
	public List<JSONObject> findMaterialAll(String materialName);

	/**
	 * 查询所有项目
	 * @return
	 */
	public List<JSONObject> findProjectAll(String projectName);

	/**
	 * 通过单号查询盘点详情
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findInventoryDetailByListNo(String listNo);

}
