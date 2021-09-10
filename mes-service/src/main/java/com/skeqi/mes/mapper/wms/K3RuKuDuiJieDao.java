package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsAllMaterialBarcode;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;
import com.skeqi.mes.pojo.wms.K3ImportNotifydetail;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.pojo.wms.WarehouseT;


/**
 * k3入库对接
 * @author yinp
 *
 */
public interface K3RuKuDuiJieDao {

	//新增k3数据
	public Integer addK3ImportNotifydetail(K3ImportNotifydetail dx);

	//通过id查询
	public K3ImportNotifydetail findK3ImportNotifydetail(@Param("id")Integer id);

	//查询k3入库数据
	public List<K3ImportNotifydetail> findK3ImportNotifydetailList(Map<String,Object> map);

	//查询库位
	public List<JSONObject> findLocationList();

	//新增库存详情
	public Integer addStorageDetail(CWmsStorageDetailT dx);

	//新增入库队列
	public Integer addInTaskqueue(CWmsInTaskqueueT dx);

	//更新已经入库数量
	public Integer updateK3ImportNotifydetail(@Param("id")Integer id,
			@Param("receivedNumber")Integer receivedNumber,
			@Param("result")Integer result);

	//查询库位
	public CWmsLocationT findLocation(@Param("id")Integer id);

	//通过托盘码查询
	public CWmsInTaskqueueT findInTaskqueueByTray(@Param("tray")String tray);

	/**
	 * 通过仓库名称查询仓库
	 * @param materialName
	 * @return
	 */
	public WarehouseT findWarehouse(String warehouseName);

	/**
	 * 通过项目名称查询项目
	 * @param materialName
	 * @return
	 */
	public CWmsProject findProject(String projectName);

	/**
	 * 新增项目信息
	 * @param dx
	 * @return
	 */
	public Integer addProject(CWmsProject dx);

	/**
	 * 通过物料名称查询物料
	 * @param materialName
	 * @return
	 */
	public CMesJlMaterialT findMtaerialName(String materialName);

	/**
	 * 新增物料信息
	 * @param dx
	 * @return
	 */
	public Integer addMaterial(CMesJlMaterialT dx);

	/**
	 * 通过库位id查询是加料操作还是入库操作
	 * @param locationId
	 * @return
	 */
	public Integer findMaterialNumberCount(@Param("locationId")Integer locationId);

	/**
	 * 通过库位id查询  物料库存
	 * @param locationId
	 * @return
	 */
	public CWmsMaterialNumberT findMaterialNumber(@Param("locationId")Integer locationId);

	/**
	 * 删除K3入库信息
	 * @param object
	 * @return
	 */
	public Integer deleteK3Number(@Param("id")Integer id);

	/**
	 * 查询所有物料条码规则
	 * @return
	 */
	public List<CWmsMaterialBarcodeRuleT> findMaterialBarcodeRuleList();

	/**
	 * 查询所有物料条码规则属性
	 * @return
	 */
	public List<CWmsMaterialRuleAttributeT> findMaterialRuleAttributeList();

	/**
	 * 查询物料信息
	 * @param listNo
	 * @return
	 */
	public CMesJlMaterialT findMaterial(@Param("id")Integer id);

	/**
	 * 通过
	 * @param materialBarcodeRuleId
	 * @return
	 */
	public CWmsAllMaterialBarcode findAllMaterialBarcodeMax(
			@Param("materialBarcodeRuleId")Integer materialBarcodeRuleId,
			@Param("tiaomaType")String tiaomaType);

	/**
	 * 更新库存详情表条码
	 * @param barCode
	 * @param materialId
	 * @param listNo
	 * @return
	 */
	public Integer updateStorageDetailT(
			@Param("barCode")String barCode,
			@Param("materialId")Integer materialId,
			@Param("listNo")String listNo);

	/**
	 * 新增所有物料条码表数据
	 * @param dx
	 * @return
	 */
	public Integer addAllMaterialBarcode(CWmsAllMaterialBarcode dx);

	/**
	 * 查询审批流程
	 *
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 查询审批流程详情
	 *
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 新增审批记录
	 *
	 * @param approval
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT approval);

	/**
	 * 通过用户id查询用户
	 * @param userId
	 * @return
	 */
	public CMesUserT findUserById(@Param("userId")int userId);

	/**
	 * 检查出入库队列
	 * @param tray
	 * @return
	 */
	public JSONObject checkOutAndReceiptQueue(@Param("tray")String tray);

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<JSONObject> projectList();

	/**
	 * 新
	 * 查询是否有库存
	 * @param json
	 * @return
	 */
	public JSONObject findStockMaterialNumber(JSONObject json);

}
