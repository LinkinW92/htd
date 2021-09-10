package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * 入库管理
 *
 * @author yinp
 * @date 2020年3月21日
 *
 */
public interface WarehousingService {

	//入库
	public boolean add(Integer userId,String listNo, Integer projectId, Integer warehouseId,Integer areaId,Integer resevoirAreaId,Integer locationId, Integer materialId,Integer materialNumber) throws Exception;

	//撤销
	public boolean revoke(String listNo,Integer userId) throws Exception;

	//立库.项目入库
	public boolean xiagmuAdd(String listNo, Integer projectId, Integer warehouseId,Integer areaId,Integer resevoirAreaId,Integer locationId, Integer materialId,Integer materialNumber) throws Exception;

	//查询入库信息
	public PageInfo<CWmsApprovalT> queryStockInInformation(JSONObject json) throws Exception;

	/**
	 * XT355_356_357 执行
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public boolean zhixingXT355_356_357(JSONObject  json) throws Exception;

	/**
	 * 查询库存详情P表
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findPStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 查询库存详情表
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 查询SUM库存详情P表
	 * @param storageDetail
	 * @return
	 */
	public List<Map<String,Object>> findPStorageDetailSum(String listNo);

	/**
	 * 查询SUM库存详情R表
	 * @param storageDetail
	 * @return
	 */
	public List<Map<String,Object>> findRStorageDetailSum(String listNo);

	/**
	 * 查询可入库的库位
	 * @return
	 */
	public List<JSONObject> findLocationIdAndName(Integer raId);

	/**
	 * 查询所有仓库
	 * @return
	 */
	public List<JSONObject> findWarehouse();

	/**
	 * 通过仓库id查询区域
	 * @param warehouseId
	 * @return
	 */
	public List<JSONObject> findArea(Integer warehouseId);

	/**
	 * 通过区域id查询库区
	 * @param areaId
	 * @return
	 */
	public List<JSONObject> findReservoirArea(Integer areaId);

	/**
	 * 通过库区id查询库位
	 * @param reservoirAreaId
	 * @return
	 */
	public List<JSONObject> findLocation(Integer reservoirAreaId);

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<JSONObject> findProject(String projectName);

	/**
	 * 查询所有物料
	 * @return
	 */
	public List<JSONObject> findMaterial(String materialName);

}
