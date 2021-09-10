package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

public interface ChargingTaskqueueDao {

	// 查询审批记录表
	public List<CWmsApprovalT> findApproval(JSONObject json);

	// 查询入库队列
	public List<CWmsInTaskqueueT> findInTaskqueue(Map<String, Object> map);

	// 查询库存详情
	public List<JSONObject> findStorageDetail(Map<String, Object> map);

	// 新增入库队列永久表记录
	public Integer addPInTaskqueue(CWmsInTaskqueueT dx);

	// 删除入库队列临时表记录
	public Integer deleteInTaskqueue(@Param("inTaskqueueId") Integer inTaskqueueId);

	// 查询物料库存
	public CWmsMaterialNumberT findMaterialNumber(@Param("materialId") Integer materialId,
			@Param("projectId") Integer projectId, @Param("locationId") Integer locationId);

	// 更新物料库存
	public Integer updateMaterialNumber(@Param("materialNumberId") Integer materialNumberId,
			@Param("materialNumber") Integer materialNumber);

	/**
	 * 新增物料库存
	 *
	 * @param dx
	 * @return
	 */
	public Integer addMaterialNumber(JSONObject json);

	/**
	 * 查询打印表最小的id（即将打印）
	 *
	 * @param listNo
	 * @return
	 */
	public Map<String, Object> findAllMaterialBarcodeIdAndBarCode(@Param("listNo") String listNo);

	/**
	 * 打印条码（更新条码打印时间）
	 *
	 * @param id
	 * @return
	 */
	public Integer updataAllMaterialBarcode(@Param("id") Integer id);

	/**
	 * 通过用户名称查询用户
	 *
	 * @param userName
	 * @return
	 */
	public CMesUserT findUserNameByName(@Param("userName") String userName);

	/**
	 * 查询条码
	 *
	 * @param listNo
	 * @param materialId
	 * @return
	 */
	public List<JSONObject> findBarCode(@Param("listNo") String listNo, @Param("materialId") int materialId);

	/**
	 * 新增永久库存详情表数据
	 *
	 * @param json
	 * @return
	 */
	public int addPStorageDetail(@Param("sql") String sql);

	/**
	 * 删除临时库存详情表数据
	 *
	 * @param id
	 * @return
	 */
	public int deleteRStorageDetail(@Param("listNo") String listNo);

	/**
	 * 查询 临时库存详情表
	 *
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findRStorageDetail(@Param("listNo") String listNo, @Param("locationId") int locationId);

	/**
	 * 通过库位id查询动作标记count
	 *
	 * @param locationId
	 * @return
	 */
	public int findRInTaskqueueFlagCountByLocationId(@Param("locationId") int locationId);

	/**
	 * 修改临时队列表动作标记
	 *
	 * @param id
	 * @return
	 */
	public int updateRInTaskqueueFlag(@Param("id") int id);

	/**
	 * 更新库存时间
	 *
	 * @param projectId
	 * @param materialId
	 * @param locationId
	 */
	public void updateNumberDt(@Param("projectId") Integer projectId, @Param("materialId") Integer materialId,
			@Param("locationId") Integer locationId);

	/**
	 * 新 查询库存是否存在
	 *
	 * @param json
	 * @return
	 */
	public JSONObject findMaterialNumberJsonObject(JSONObject json);

	/**
	 * 新 更新库存
	 *
	 * @param json
	 * @return
	 */
	public int updateStockMaterialNumber(JSONObject json);

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	public int updateLocationStatus(@Param("locationId") int locationId, @Param("locationStatus") int locationStatus);

}
