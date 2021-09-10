package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * @package com.skeqi.mes.service.wms
 * @author Yinp
 * @2020年2月15日
 * 审批流程详情
 *
 */
public interface ProcessApprovalDetailService {
	/**
	 * 查询
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 通过id查询
	 * @param processApprovalDetailId
	 * @return
	 */
	public ProcessApprovalDetail findProcessApprovalDetailById(Integer processApprovalDetailId);

	/**
	 * 新增
	 * @param processApprovalDetail
	 * @return
	 */
	public boolean addProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail);

	/**
	 * 更新
	 * @param processApprovalDetail
	 * @return
	 */
	public boolean updateProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail);

	/**
	 * 删除
	 * @param processApprovalDetailId
	 * @return
	 */
	public boolean deleteProcessApprovalDetail(Integer processApprovalDetailId);

	/**
	 * 查询最大优先级
	 * @param dmId
	 * @param roleId
	 * @param typeId
	 * @return
	 */
	public Integer findMaxPriorityLevel(Integer dmId,Integer roleId,Integer typeId);

	/**
	 * 更新审批详情的优先级
	 * @param priorityLevel
	 * @param processId
	 * @return
	 */
	public Integer updateApprovaDetailsPriorityLevel(Integer priorityLevel,Integer processId,Integer str);


}
