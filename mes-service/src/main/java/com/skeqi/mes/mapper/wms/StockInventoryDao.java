package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.InventoryDetailT;
import com.skeqi.mes.pojo.wms.InventoryT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * 库存盘点
 * @author yinp
 *
 */
public interface StockInventoryDao {

	/**
	 * 查询盘点单据详情
	 * @param map
	 * @return
	 */
	public List<InventoryDetailT> findInventoryDetailList(Map<String,Object> map);

	/**
	 * 查询盘点单据
	 * @param map
	 * @return
	 */
	public List<InventoryT> findInventoryList(Map<String,Object> map);

	/**
	 * 新增审批单据
	 * @param dx
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT dx);

	/**
	 * 新增盘点单据
	 * @param dx
	 * @return
	 */
	public Integer addInventory(InventoryT dx);

	/**
	 * 新增盘点单据详情
	 * @param dx
	 * @return
	 */
	public Integer addInventoryDetail(InventoryDetailT dx);

	/**
	 * 查询库存
	 * @param locationId
	 * @param materialId
	 * @param projectId
	 * @return
	 */
	public Integer findMaterialNumber(
			@Param("locationId")Integer locationId,
			@Param("materialId")Integer materialId,
			@Param("projectId")Integer projectId);

	/**
	 * 更新库存盘点表
	 * @param dx
	 * @return
	 */
	public Integer updtaeInventory(JSONObject json);

	/**
	 * 修改物料库存表记录
	 * @param dx
	 * @return
	 */
	public Integer updatematerialNumber(CWmsMaterialNumberT dx);

	/**
	 * 查询是否有盘点记录未审批或者未执行
	 * @return
	 */
	public Integer queryWhetherThereIsInventory();

	/**
	 * 查询是否还有未完成的出库队列
	 * @return
	 */
	public Integer findOutgoingQueue();

	/**
	 * 查询是否还有未完成的入库队列
	 * @return
	 */
	public Integer findWarehousingQueue();

	/**
	 * 通过用户id查询用户信息
	 * @param userId
	 * @return
	 */
	public CMesUserT findUserById(@Param("userId")int userId);

	/**
	 * 查询审批流程
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 通过单号查询审批记录
	 * @param listNo
	 * @return
	 */
	public CWmsApprovalT findApprovalByListNo(@Param("listNo")String listNo);

	/**
	 * 新增审批详情数据
	 * @param approvalDetails
	 * @return
	 */
	public Integer addApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 查询流程详情
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 查询所有仓库
	 * @return
	 */
	public List<JSONObject> findWarehouseAll();

	/**
	 * 通过仓库id查询区域
	 * @return
	 */
	public List<JSONObject> findAreaAllByWarehouseId(@Param("warehouseId")int warehouseId);

	/**
	 * 通过区域id查询库区
	 * @return
	 */
	public List<JSONObject> findReservoirAreaAllByAeraId(@Param("areaId")int areaId);

	/**
	 * 通过库区id查询库位
	 * @param reservoirAreaId
	 * @return
	 */
	public List<JSONObject> findLocationAllByReservoirAreaId(@Param("reservoirAreaId")int reservoirAreaId);

	/**
	 * 查询所有物料
	 * @return
	 */
	public List<JSONObject> findMaterialAll(@Param("materialName")String materialName);

	/**
	 * 查询所有项目
	 * @return
	 */
	public List<JSONObject> findProjectAll(@Param("projectName")String projectName);

	/**
	 * 通过单号查询盘点详情
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findInventoryDetailByListNo(@Param("listNo")String listNo);

	/**
	 * 通过id查询盘点单信息
	 * @param id
	 * @return
	 */
	public JSONObject findInventoryById(@Param("id")int id);

	/**
	 * 修改盘点表状态
	 * @param id
	 * @param state
	 * @return
	 */
	public int updateInventoryState(@Param("id")int id, @Param("state")int state);

	/**
	 * 新增永久库存详情表信息
	 * @param json
	 * @return
	 */
	public int addPInventoryDetails(@Param("sql")String sql);

	/**
	 * 新增物料库存
	 * @param json
	 * @return
	 */
	public int addMaterialNumber(@Param("sql")String sql);

	/**
	 * 通过库位id查询物料库存信息
	 * @param locationId
	 * @return
	 */
	public JSONObject findMaterialNumberByLocationId(@Param("locationId")int locationId);

	/**
	 * 通过库位id查询 库区、区域、仓库id
	 * @param locationId
	 * @return
	 */
	public JSONObject findWARByLocationId(@Param("locationId")int locationId);

	/**
	 * 通过物料id查询物料部分信息
	 * @param materialId
	 * @return
	 */
	public JSONObject findMaterialById(@Param("materialId")int materialId);

	/**
	 * 查过库位查询物料库存id
	 * @param locationId
	 * @return
	 */
	public List<Integer> findMaterialNumberIdByLocationId(@Param("locationId")int locationId);

	/**
	 * 删除物料库存
	 * @param id
	 * @return
	 */
	public int deleteMaterialNumber(@Param("id")int id);

	/**
	 * 更新库位状态
	 * @param id
	 * @param state
	 * @return
	 */
	public int updateLocationState(@Param("id")int id,@Param("state")int state);

	/**
	 * 更新库位托盘码
	 * @param id
	 * @param trayCode
	 * @return
	 */
	public int updateLocationTrayCode(@Param("id")int id,@Param("trayCode")String trayCode);

	/**
	 * 新
	 * 查询盘点详情
	 * @param INVENTORY_ID
	 * @return
	 */
	public List<JSONObject> findInventoryDetail(@Param("INVENTORY_ID")int INVENTORY_ID);

	/**
	 * 新
	 * 新增永久库存详情表记录
	 * @param sql
	 * @return
	 */
	public int addRStorageDetail(@Param("sql")String sql);

	/**
	 * 新
	 * 更新物料库存
	 * @param json
	 * @return
	 */
	public int updateStockMaterialNumber(JSONObject json);

	/**
	 * 新
	 * 删除物料库存
	 * @param json
	 * @return
	 */
	public int deleteStockMaterialNumber(JSONObject json);

	/**
	 * 新
	 * 通过库位查询是否还有物料库存
	 * @param locationId
	 * @return
	 */
	public int findStockMaterialNumberByLocationId(@Param("locationId")int locationId);

	/**
	 * 新
	 * 更新库位状态跟托盘码
	 * @param json
	 * @return
	 */
	public int updateLocationStateAndTrayCode(JSONObject json);

	/**
	 * 新
	 * 更新盘点主表状态
	 * @param id
	 * @return
	 */
	public int newUpdateInventoryState(@Param("id")int id);


}
