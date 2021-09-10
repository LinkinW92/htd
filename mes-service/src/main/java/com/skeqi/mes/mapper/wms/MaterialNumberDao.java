package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月17日
 * @author Yinp 物料库存
 */
public interface MaterialNumberDao {

	/**
	 * 查询
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map);

	/**
	 * 查询导出
	 *
	 * @return
	 */
	public List<JSONObject> exportExcel(JSONObject json);

	/**
	 * 查询库位总库存
	 *
	 * @param locationId
	 * @return
	 */
	public Integer findLocationCount(@Param("locationId") Integer locationId);

	/**
	 * 新增
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer addMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 更新
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer updateMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 删除
	 *
	 * @param MaterialNumberId
	 * @return
	 */
	public Integer deleteMaterialNumber(@Param("id") Integer id);

	/**
	 * 更新库存即将出库数量
	 *
	 * @param dx
	 * @param str
	 * @return
	 */
	public Integer updateLmminentRelease(@Param("dx") CWmsMaterialNumberT dx, @Param("str") Integer str);

	/**
	 * 查询总库存
	 *
	 * @return
	 */
	public Integer findTotal(@Param("projectId") Integer projectId, @Param("materialId") Integer materialId,
			@Param("warehouseId") Integer warehouseId);

	/**
	 * 通过策列查询库存
	 *
	 * @param projectId
	 * @param materialId
	 * @param warehouseId
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumber(@Param("strategy") Integer strategy,
			@Param("projectId") Integer projectId, @Param("materialId") Integer materialId,
			@Param("warehouseId") Integer warehouseId);

	/**
	 * 更新库位状态以及托盘码
	 *
	 * @param locationId
	 * @return
	 */
	public Integer updateLocationStateAndTray(@Param("locationId") Integer locationId);

	/**
	 * 查询所有项目ID、NAME
	 *
	 * @return
	 */
	public List<CWmsProject> findProjectAll(@Param("projectName") String projectName);

	/**
	 * 查询所有物料ID、NAME
	 *
	 * @return
	 */
	public List<JSONObject> findMaterialAll(@Param("materialName") String materialName);

	/**
	 * 查询所有库位ID、NAME
	 *
	 * @return
	 */
	public List<CWmsLocationT> findLocationAll();

	/**
	 * 查询条码
	 *
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public List<Map<String, Object>> findBarCode(@Param("materialId") Integer materialId,
			@Param("projectId") Integer projectId, @Param("locationId") Integer locationId);

	/**
	 * 查询条码的conut
	 *
	 * @param barCode
	 * @return
	 */
	public Integer findBarCodeCount(@Param("barCode") String barCode);

	/**
	 * 修改所有物料条码表里的条码
	 *
	 * @param sourceBarCode
	 * @param presentBarCode
	 * @return
	 */
	public Integer updateAllMaterialBarCode(@Param("id") int id, @Param("presentBarCode") String presentBarCode);

	/**
	 * 修改临时物料库存表里的条码
	 *
	 * @param sourceBarCode
	 * @param presentBarCode
	 * @return
	 */
	public Integer updateStorageDetailBarCode(@Param("sourceBarCode") String sourceBarCode,
			@Param("presentBarCode") String presentBarCode);

	/**
	 * 修改物料库存条码
	 *
	 * @param sourceBarCode
	 * @param presentBarCode
	 * @return
	 */
	public Integer updateMaterialNumberBarCode(@Param("id") int id, @Param("presentBarCode") String presentBarCode);

	/**
	 * 新增临时库存表记录
	 *
	 * @param sql
	 * @return
	 */
	public int addRStock(JSONObject json);

	/**
	 * 新增审批表记录
	 *
	 * @param json
	 * @return
	 */
	public int addApproval(JSONObject json);

	/**
	 * 更新库存数量
	 *
	 * @param id
	 * @param number
	 * @return
	 */
	public int updateStockNumber(@Param("id") int id, @Param("number") int number);

	/**
	 * 新增出库队列
	 * @param sql
	 * @return
	 */
	public int addOutTaskqueue(@Param("sql") String sql);

	/**
	 * 通过库位id查询库位
	 * @param locationId
	 * @return
	 */
	public JSONObject findLocationById(@Param("id")int id);

	/**
	 * 新增入库队列
	 * @param json
	 * @return
	 */
	public int addInTaskqueue(JSONObject json);

	/**
	 * 通过id查询库位
	 * @param id
	 * @return
	 */
	public JSONObject findStockById(@Param("id")int id);

	/**
	 * 新增转移表
	 * @param json
	 * @return
	 */
	public int addStockTransfer(JSONObject json);

}
