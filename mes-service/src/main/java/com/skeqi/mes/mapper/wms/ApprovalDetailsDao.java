package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月15日
 * 审批详情记录
 */
public interface ApprovalDetailsDao {

	/**
	 * 查询
	 * @param approvalDetails
	 * @return
	 */
	public List<CWmsApprovalDetailsT> findApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 新增
	 * @param approvalDetails
	 * @return
	 */
	public Integer addApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 更新
	 * @param approvalDetails
	 * @return
	 */
	public Integer updateApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 删除
	 * @param ApprovalDetailsId
	 * @return
	 */
	public Integer deleteApprovalDetails(Integer approvalDetailsId);

	/**
	 * 查新是否最后一个人审批
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public List<CWmsApprovalDetailsT> findShiFouZuiHouYiGeShenPiRen(@Param("listNo")String listNo,
			@Param("priorityLevel")Integer priorityLevel);

	/**
	 * 更新审批详表审批结果
	 * 更新审批详表ynApproved
	 * @param id
	 * @return
	 */
	public Integer updateApprovalResult(@Param("id")Integer id);

	/**
	 * 更新审批详表ynApproved
	 * @param id
	 * @return
	 */
	public Integer updateYnApproved(@Param("id")Integer id);


}
