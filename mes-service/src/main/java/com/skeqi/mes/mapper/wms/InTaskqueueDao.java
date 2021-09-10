package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月18日
 * @author yp 入库队列
 *
 */
public interface InTaskqueueDao {

	/**
	 * 查询
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public List<CWmsInTaskqueueT> findInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 通过id查询临时入库队列
	 * @param id
	 * @return
	 */
	public JSONObject findRIntaskqueueById(@Param("id")int id);

	//通过托盘码查询库位坐标跟单号
	public CWmsInTaskqueueT findZuoBiaoAndListNo(@Param("tray")String tray);

	/**
	 * 新增
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public Integer addInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 新增P 表
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public Integer addPInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 更新
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public Integer updateInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 删除
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	public Integer deleteInTaskqueue(Integer inTaskqueueId);

	// 查询入库队列里的单据
	public List<CWmsApprovalT> findApproval(Map<String, Object> map);

	//查询物料库存
	public List<Map<String,Object>> findStorageDetail(
			@Param("listNo")String listNo,
			@Param("locationId")Integer locationId);

	//通过id查询库位
	public CWmsLocationT findLocation(@Param("locationId")Integer locationId);

	//通过库位id查询出库队列
	public CWmsOutTaskqueueT findOutTaskqueue(@Param("locationId")Integer locationId);

	//查询入库队列里的单据XT355_356_357
	public List<CWmsApprovalT> findApprovalXT355_356_357(JSONObject json);

	//查询物料库存
	public List<CWmsStorageDetailT> findPStorageDetail(JSONObject json);

	/**
	 * 查询托盘码是否存在
	 * @param tray
	 * @return
	 */
	public Integer findMaterialNumberCount(@Param("tray")String tray);

	/**
	 * 查询物料库存
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(JSONObject json);

	/**
	 * 新增物料库存
	 * @param MaterialNumber
	 * @return
	 */
	public Integer addMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 更新物料库存
	 * @param MaterialNumber
	 * @return
	 */
	public Integer updateMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 更新
	 * @param location
	 * @return
	 */
	public Integer updateLocation(CWmsLocationT location);

	/**
	 * 打印条码（更新条码打印时间）
	 * @param id
	 * @return
	 */
	public Integer updataAllMaterialBarcode(@Param("id")Integer id);

	/**
	 * 查询打印表最小的id（即将打印）
	 * @param listNo
	 * @return
	 */
	public Map<String,Object> findAllMaterialBarcodeIdAndBarCode(@Param("listNo")String listNo);

	/**
	 * 查询条码
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> findBarCode(
			@Param("listNo")String listNo,
			@Param("locationId")Integer locationId,
			@Param("materialId")Integer materialId)throws Exception;

	/**
	 * 查询条码的conut
	 * @param barCode
	 * @return
	 */
	public Integer findBarCodeCount(@Param("barCode")String barCode);

	/**
	 * 修改所有物料条码表里的条码
	 * @param sourceBarCode
	 * @param presentBarCode
	 * @return
	 */
	public Integer updateAllMaterialBarCode(@Param("sourceBarCode")String sourceBarCode,@Param("presentBarCode")String presentBarCode);

	/**
	 * 修改临时物料库存表里的条码
	 * @param sourceBarCode
	 * @param presentBarCode
	 * @return
	 */
	public  Integer updateStorageDetailBarCode(@Param("sourceBarCode")String sourceBarCode,@Param("presentBarCode")String presentBarCode);

	/**
	 * 查询临时库存详情
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findRStorageDetail(@Param("listNo")String listNo, @Param("locationId")int locationId);

	/**
	 * 新增永久库存详情
	 * @param sql
	 * @return
	 */
	public int addPStorageDetail(@Param("sql")String sql);

	/**
	 * 删除临时库存详情
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public int deleteRStorageDetail(@Param("listNo")String listNo, @Param("locationId")int locationId);

	/**
	 * 新增物料库存
	 * @param json
	 * @return
	 */
	public int addMaterialNumberSql(@Param("sql")String sql);

	/**
	 * 修改库位状态
	 * @param id
	 * @param state
	 * @return
	 */
	public int updateLocationState(@Param("id")int id, @Param("state")int state);

	/**
	 * 修改库位托盘码
	 * @param id
	 * @param trayCode
	 * @return
	 */
	public int updateLocationTrayCode(@Param("id")int id, @Param("trayCode")String trayCode);

	/**
	 * 通过库位id查询动作标记count
	 * @param locationId
	 * @return
	 */
	public int findRInTaskqueueFlagCountByLocationId(@Param("locationId")int locationId);

	/**
	 * 修改临时队列表动作标记
	 * @param id
	 * @return
	 */
	public int updateRInTaskqueueFlag(@Param("id")int id);

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	public int updateLocationStatus(@Param("locationId") int locationId, @Param("locationStatus") int locationStatus);

}
