package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * 入库
 *
 * @author Administrator
 *
 */
public interface WarehousingDao {

	/**
	 * 查询物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map);

	/**
	 * 新增库存 详情
	 *
	 * @param storageDetail
	 * @return
	 */
	public Integer addStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 查询库存详情
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 新增库存详情P表
	 *
	 * @param storageDetail
	 * @return
	 */
	public Integer addPStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 删除库存详情
	 *
	 * @param storageDetailId
	 * @return
	 */
	public Integer deleteStorageDetail(Integer storageDetailId);

	/**
	 * 查询审批流程
	 *
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 新增审批记录
	 *
	 * @param approval
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT approval);

	/**
	 * 通过单号查询审批记录
	 *
	 * @param listNo
	 * @return
	 */
	public CWmsApprovalT findApprovalByListNo(@Param("listNo") String listNo);

	/**
	 * 通过审批类型查询
	 *
	 * @param approval
	 * @return
	 */
	public List<CWmsApprovalT> findApproval(CWmsApprovalT approval);

	/**
	 * 更新审批记录
	 *
	 * @param approval
	 * @return
	 */
	public Integer updateApproval(CWmsApprovalT approval);

	/**
	 * 新增审批记录详情
	 *
	 * @param approvalDetails
	 * @return
	 */
	public Integer addApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 查询审批流程详情
	 *
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 新增
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer addMaterialNumber(CWmsMaterialNumberT materialNumber);


	/**
	 * XT355_356_357需求
	 * @param json
	 * @return
	 */
	public List<CWmsApprovalT> queryStockInInformationXT355_356_357(JSONObject json);

	/**
	 * 新增临时入库队列表
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public Integer addInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 更新临时入库队列表
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public Integer updateInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 查询库存详情P表
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findPStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 查询SUM库存详情P表
	 * @param storageDetail
	 * @return
	 */
	public List<Map<String,Object>> findPStorageDetailSum(@Param("listNo")String listNo);

	/**
	 * 查询SUM库存详情R表
	 * @param storageDetail
	 * @return
	 */
	public List<Map<String,Object>> findRStorageDetailSum(@Param("listNo")String listNo);

	/**
	 * 查询可入库的库位
	 * @return
	 */
	public List<JSONObject> findLocationIdAndName(@Param("raId")Integer raId);

	/**
	 * 查询用户
	 * @param userName
	 * @return
	 */
	public CMesUserT findUserById(@Param("userId")Integer userId);

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
	public List<JSONObject> findArea(@Param("warehouseId")Integer warehouseId);

	/**
	 * 通过区域id查询库区
	 * @param areaId
	 * @return
	 */
	public List<JSONObject> findReservoirArea(@Param("areaId")Integer areaId);

	/**
	 * 通过库区id查询库位
	 * @param reservoirAreaId
	 * @return
	 */
	public List<JSONObject> findLocation(@Param("reservoirAreaId")Integer reservoirAreaId);

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<JSONObject> findProject(@Param("projectName")String projectName);

	/**
	 * 查询所有物料
	 * @return
	 */
	public List<JSONObject> findMaterial(@Param("materialName")String materialName);


}
