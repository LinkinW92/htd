package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesUserT;

/**
 * @date 2020年2月15日
 * @author Yinp
 * 审批记录
 */
public class CWmsApprovalT {

	private Integer id;
	private String listNo;//外键：单号
	private String dt;//生成记录的时间
	private Integer processId;//外键：流程表id
	private Integer userId;//申请人id
	private Integer state;//状态：0.未审批、1.审批中、2.审批成功、3.驳回、4撤销
	private String note;//备注

	private ProcessApproval processApproval;//流程表
	private CMesUserT user;//用户表
	private ProcessType processType;//单据类型
	private CWmsDepartmentT department;//部门
	private CWmsApprovalDetailsT approvalDetails;//审批详情


	public CWmsApprovalDetailsT getApprovalDetails() {
		return approvalDetails;
	}
	public void setApprovalDetails(CWmsApprovalDetailsT approvalDetails) {
		this.approvalDetails = approvalDetails;
	}
	public CWmsDepartmentT getDepartment() {
		return department;
	}
	public void setDepartment(CWmsDepartmentT department) {
		this.department = department;
	}
	public ProcessType getProcessType() {
		return processType;
	}
	public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}
	public ProcessApproval getProcessApproval() {
		return processApproval;
	}
	public void setProcessApproval(ProcessApproval processApproval) {
		this.processApproval = processApproval;
	}
	public CMesUserT getUser() {
		return user;
	}
	public void setUser(CMesUserT user) {
		this.user = user;
	}
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
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "CWmsApprovalT [id=" + id + ", listNo=" + listNo + ", dt=" + dt + ", processId=" + processId
				+ ", userId=" + userId + ", state=" + state + ", note=" + note + "]";
	}

}
