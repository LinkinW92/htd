package com.skeqi.mes.pojo.wms;

/**
 * @date 2020年2月15日
 * @author Yinp
 * 审批详情记录表
 */
public class CWmsApprovalDetailsT {

	private Integer id;
	private String listNo;
	private Integer approvalResult;
	private Integer userId;
	private String reason;
	private Integer approvalId;
	private String dt;
	private Integer priorityLevel;
	private Integer ynApproved;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public Integer getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public Integer getYnApproved() {
		return ynApproved;
	}
	public void setYnApproved(Integer ynApproved) {
		this.ynApproved = ynApproved;
	}
	@Override
	public String toString() {
		return "CWmsApprovalDetails [id=" + id + ", listNo=" + listNo + ", approvalResult=" + approvalResult
				+ ", userId=" + userId + ", reason=" + reason + ", approvalId=" + approvalId + ", dt=" + dt
				+ ", priorityLevel=" + priorityLevel + ", ynApproved=" + ynApproved + "]";
	}

}
