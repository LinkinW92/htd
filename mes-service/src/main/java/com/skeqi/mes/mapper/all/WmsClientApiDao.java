package com.skeqi.mes.mapper.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * 立库客户端专用api
 *
 * @author yinp
 *
 */
public interface WmsClientApiDao {
	/**
	 * 查询出库队列
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsOutTaskqueueT> findOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 删除出库队列
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public Integer deleteOutTaskqueue(@Param("outTaskqueueId") Integer outTaskqueueId);

	/**
	 * 新增出库队列P表数据
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public Integer addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 查询物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map);

	/**
	 * 新增物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer addMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 更新物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer updateMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 删除物料库存
	 *
	 * @param MaterialNumberId
	 * @return
	 */
	public Integer deleteMaterialNumber(@Param("materialNumberId") Integer materialNumberId);

	/**
	 * 查询库位总物料库存
	 *
	 * @param locationId
	 * @return
	 */
	public Integer findLocationCount(@Param("locationId") Integer locationId);

	/**
	 * 查询库存详情R表
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 新增库存详情P表
	 *
	 * @param dx
	 * @return
	 */
	public Integer addPStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 删除库存详情R 表
	 *
	 * @param id
	 * @return
	 */
	public Integer deleteStorageDetail(@Param("id") Integer id);

	/**
	 * 查询已生成条码物料待出库库存
	 *
	 * @param locationId
	 * @param projectId
	 * @param materialId
	 * @return
	 */
	public CWmsMaterialNumberT findBarcodeGeneratedMaterialNumberList(@Param("locationId") Integer locationId,
			@Param("projectId") Integer projectId, @Param("materialId") Integer materialId);

	/**
	 * 修改库位状态
	 *
	 * @param locationId
	 * @param state
	 * @return
	 */
	public int updateLocationState(@Param("locationId") int locationId, @Param("state") int state);

	/**
	 * 修改库位托盘码
	 *
	 * @param locationId
	 * @param trayCode
	 * @return
	 */
	public int updateTrayCode(@Param("locationId") int locationId, @Param("trayCode") String trayCode);

	/**
	 * 通过托盘码查询临时入库队列信息
	 *
	 * @param trayCode
	 * @return
	 */
	public JSONObject findRInTaskqueueByTrayCode(@Param("trayCode") String trayCode);

	/**
	 * 新增永久入库队列信息
	 *
	 * @param json
	 * @return
	 */
	public int addPInTaskqueue(JSONObject json);

	/**
	 * 删除出库队列临时表数据
	 *
	 * @param id
	 * @return
	 */
	public int deleteRInTaskqueue(@Param("id") int id);

	/**
	 * 查询库位坐标
	 *
	 * @param locationId
	 * @return
	 */
	public JSONObject findlocationZuoBiaoByTayCode(@Param("trayCode") String trayCode);

	/**
	 * 新增临时入库队列表信息
	 *
	 * @param json
	 * @return
	 */
	public int addRInTaskqueue(JSONObject json);

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
	 * 新
	 *
	 * 出库更新库存
	 *
	 * @param materialNumber
	 * @param materialNumberId
	 * @return
	 */
	public int updateStockMaterialNumber(@Param("materialNumber") int materialNumber,
			@Param("materialNumberId") int materialNumberId);

	/**
	 * 新
	 *
	 * 删除空库存的记录
	 *
	 * @return
	 */
	public int deleteNullStock();

	/**
	 * 查询是否有库存
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public Integer findStockCount(@Param("materialId") int materialId, @Param("projectId") int projectId,
			@Param("locationId") int locationId);

	/**
	 * 更新库存数量
	 * @param id
	 * @param number
	 * @return
	 */
	public int updateStockNumber(@Param("id")int id,@Param("number")int number);

}
