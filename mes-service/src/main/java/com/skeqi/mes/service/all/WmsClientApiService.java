package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

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
public interface WmsClientApiService {
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
	public int deleteOutTaskqueue(Integer outTaskqueueId) throws Exception;

	/**
	 * 新增出库队列P表数据
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public int addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) throws Exception;

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
	public int addMaterialNumber(CWmsMaterialNumberT materialNumber) throws Exception;

	/**
	 * 更新物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public int updateMaterialNumber(CWmsMaterialNumberT materialNumber) throws Exception;

	/**
	 * 删除物料库存
	 *
	 * @param MaterialNumberId
	 * @return
	 */
	public int deleteMaterialNumber(Integer materialNumberId) throws Exception;

	/**
	 * 查询库位总物料库存
	 *
	 * @param locationId
	 * @return
	 */
	public Integer findLocationCount(Integer locationId);

	/**
	 * 查询库存详情P表
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
	public int addPStorageDetail(CWmsStorageDetailT dx) throws Exception;

	/**
	 * 删除库存详情R 表
	 *
	 * @param id
	 * @return
	 */
	public int deleteStorageDetail(Integer id) throws Exception;

	/**
	 * 查询已生成条码物料待出库库存
	 *
	 * @param locationId
	 * @param projectId
	 * @param materialId
	 * @return
	 */
	public CWmsMaterialNumberT findBarcodeGeneratedMaterialNumberList(Integer locationId, Integer projectId,
			Integer materialId);

	/**
	 * 修改库位状态
	 *
	 * @param locationId
	 * @param state
	 * @return
	 */
	public int updateLocationState(int locationId, int state) throws Exception;

	/**
	 * 修改库位托盘码
	 *
	 * @param locationId
	 * @param trayCode
	 * @return
	 */
	public int updateTrayCode(int locationId, String trayCode) throws Exception;

	/**
	 * 通过托盘码查询临时入库队列信息
	 *
	 * @param trayCode
	 * @return
	 */
	public JSONObject findRInTaskqueueByTrayCode(String trayCode);

	/**
	 * 新增永久入库队列信息
	 *
	 * @param json
	 * @return
	 */
	public int addPInTaskqueue(JSONObject json) throws Exception;

	/**
	 * 删除出库队列临时表数据
	 *
	 * @param id
	 * @return
	 */
	public int deleteRInTaskqueue(int id) throws Exception;

	/**
	 * 查询库位坐标
	 *
	 * @param locationId
	 * @return
	 */
	public JSONObject findlocationZuoBiaoByTayCode(String trayCode);

	/**
	 * 新增临时入库队列表信息
	 *
	 * @param json
	 * @return
	 */
	public int addRInTaskqueue(JSONObject json) throws Exception;

	/**
	 * 更新库存时间
	 *
	 * @param projectId
	 * @param materialId
	 * @param locationId
	 */
	public void updateNumberDt(Integer projectId, Integer materialId, Integer locationId);

	/**
	 * 新
	 *
	 * 出库更新库存
	 *
	 * @param materialNumber
	 * @param materialNumberId
	 * @return
	 */
	public int updateStockMaterialNumber(int materialNumber, int materialNumberId);

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
	 *
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public Integer findStockCount(int materialId, int projectId, int locationId);

	/**
	 * 更新库存数量
	 *
	 * @param id
	 * @param number
	 * @return
	 */
	public int updateStockNumber(int id, int number);

}
