package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * @package com.skeqi.mapper.wms
 * @author Yinp
 * @2020年2月15日
 * 审批流程详情
 *
 */
public interface ProcessApprovalDetailDao {

	/**
	 * 查询
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 新增
	 * @param processApprovalDetail
	 * @return
	 */
	public Integer addProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail);

	/**
	 * 更新
	 * @param processApprovalDetail
	 * @return
	 */
	public Integer updateProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail);

	/**
	 * 删除
	 * @param processApprovalDetailId
	 * @return
	 */
	public Integer deleteProcessApprovalDetail(Integer processApprovalDetailId);

	/**
	 * 查询最大优先级
	 * @param dmId
	 * @param roleId
	 * @param typeId
	 * @return
	 */
	public Integer findMaxPriorityLevel(@Param("dmId")Integer dmId,
			@Param("roleId")Integer roleId,
			@Param("typeId")Integer typeId);


	/**
	 * 更新审批详情的优先级
	 * @param priorityLevel
	 * @param processId
	 * @return
	 */
	public Integer updateApprovaDetailsPriorityLevel(@Param("priorityLevel")Integer priorityLevel,
			@Param("processId")Integer processId,@Param("str")Integer str);

	/**
	 * 更新后更新审批详情的优先级
	 * @param priorityLevel
	 * @param processId
	 * @return
	 */
	public Integer updateUpdateApprovaDetailsPriorityLevel(
			@Param("qpriorityLevel")Integer qpriorityLevel,
			@Param("priorityLevel")Integer priorityLevel,
			@Param("processId")Integer processId,
			@Param("str")Integer str);

}
