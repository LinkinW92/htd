package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日
 * 审批记录
 */
public interface ApprovalService {
	/**
	 * 查询
	 * @param approval
	 * @return
	 */
	public PageInfo<CWmsApprovalT> findApprovalList(JSONObject json)throws Exception;

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
	public CWmsApprovalT findApprovalByListNo(String listNo);

	/**
	 * 通过id查询
	 * @param approvalId
	 * @return
	 */
	public CWmsApprovalT findApprovalById(Integer approvalId);

	/**
	 * 新增
	 * @param approval
	 * @return
	 */
	public boolean addApproval(CWmsApprovalT approval);

	/**
	 * 更新
	 * @param approval
	 * @return
	 */
	public boolean updateApproval(CWmsApprovalT approval);

	/**
	 * 删除
	 * @param approvalId
	 * @return
	 */
	public boolean deleteApproval(Integer approvalId);


	/**
	 * 审批驳回
	 * @param userId
	 * @param listNo
	 * @return
	 */
	public boolean approvalReject(int userId,String listNo) throws Exception;

	/**
	 * 审批同意
	 * @param userId
	 * @param listNo
	 * @return
	 */
	public boolean approvalAgree(int userId,String listNo) throws Exception;

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
	public List<JSONObject> findRStorageDetailSum(String listNo);

	/**
	 * 通过单号查询盘点详情
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findInventoryDetailByListNo(String listNo);

}
