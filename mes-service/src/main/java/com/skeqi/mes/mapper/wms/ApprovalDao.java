package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsAllMaterialBarcode;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月15日
 * 审批记录
 */
public interface ApprovalDao {

	/**
	 * 查询
	 * @param approval
	 * @return
	 */
	public List<CWmsApprovalT> findApprovalList(CWmsApprovalT approval);

	/**
	 * 通过审批类型查询
	 * @param approval
	 * @return
	 */
	public List<CWmsApprovalT> findApproval(CWmsApprovalT approval);

	/**
	 * 通过单号查询审批记录
	 * @param listNo
	 * @return
	 */
	public CWmsApprovalT findApprovalByListNo(@Param("listNo")String listNo);

	/**
	 * 新增
	 * @param approval
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT approval);

	/**
	 * 更新
	 * @param approval
	 * @return
	 */
	public Integer updateApproval(CWmsApprovalT approval);

	/**
	 * 删除
	 * @param approvalId
	 * @return
	 */
	public Integer deleteApproval(Integer approvalId);

	/**
	 * 查询库存详情R表
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 查询SUM库存详情R表
	 * @param storageDetail
	 * @return
	 */
	public List<JSONObject> findRStorageDetailSum(@Param("listNo")String listNo);

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
	 * 新增所有物料条码表数据
	 * @param dx
	 * @return
	 */
	public Integer addAllMaterialBarcode(@Param("sql")String sql);

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
	 * 查询单据是否可以审批通过
	 * @param listNo
	 * @return
	 */
	public Map<String,Object> findWhetherItCanBeApproved(@Param("listNo")String listNo);

	/**
	 * 通过userName查询user
	 * @param userName
	 * @return
	 */
	public CMesUserT findUserByName(@Param("userName")String userName);

	/**
	 * 通过单号查询盘点详情
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findInventoryDetailByListNo(@Param("listNo")String listNo);

	/**
	 * 查询出入库是否存在记录
	 * @param listNo
	 */
	public JSONObject findOutInCount(@Param("listNo")String listNo);

	/**
	 * 新
	 * 批量新增库存条码
	 * @param sql
	 * @return
	 */
	public int addStockBarcode(@Param("sql")String sql);

}
