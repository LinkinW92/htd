package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日
 * 审批详情记录
 */
public interface ApprovalDetailsService {
	/**
	 * 查询
	 * @param approvalDetails
	 * @return
	 */
	public List<CWmsApprovalDetailsT> findApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 通过id查询
	 * @param approvalDetailsId
	 * @return
	 */
	public CWmsApprovalDetailsT findApprovalDetailsById(Integer approvalDetailsId);

	/**
	 * 新增
	 * @param approvalDetails
	 * @return
	 */
	public boolean addApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 更新
	 * @param approvalDetails
	 * @return
	 */
	public boolean updateApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 删除
	 * @param ApprovalDetailsId
	 * @return
	 */
	public boolean deleteApprovalDetails(Integer approvalDetailsId);

	/**
	 * 查新是否最后一个人审批
	 * @param listNo
	 * @param priorityLevel
	 * @return
	 */
	public List<CWmsApprovalDetailsT> findShiFouZuiHouYiGeShenPiRen(String listNo,Integer priorityLevel);

	/**
	 * 更新审批详表审批结果
	 * 更新审批详表ynApproved
	 * @param id
	 * @return
	 */
	public boolean updateApprovalResult(Integer id);

	/**
	 * 更新审批详表ynApproved
	 * @param id
	 * @return
	 */
	public boolean updateYnApproved(Integer id);

}
