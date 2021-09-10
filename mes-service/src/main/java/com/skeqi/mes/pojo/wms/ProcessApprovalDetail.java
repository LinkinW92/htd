package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.CMesUserT;

/**
 * 审批流程详情表
 *
 * @author Ryan
 *
 */
public class ProcessApprovalDetail {

	private Integer id;
	private Integer userId;// 用户id
	private Integer priorityLevel;// 审批优先级
	private String dt;
	private Integer processId;// 审批流程表id
	private String dis;

	private CMesUserT user;//用户
	private CWmsDepartmentT department;//部门
	private CMesRoleT role;//角色
	private ProcessType processType;//单据类型

	public ProcessType getProcessType() {
		return processType;
	}

	public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}

	public CMesUserT getUser() {
		return user;
	}

	public void setUser(CMesUserT user) {
		this.user = user;
	}

	public CWmsDepartmentT getDepartment() {
		return department;
	}

	public void setDepartment(CWmsDepartmentT department) {
		this.department = department;
	}

	public CMesRoleT getRole() {
		return role;
	}

	public void setRole(CMesRoleT role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
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

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	@Override
	public String toString() {
		return "ProcessApprovalDetail [id=" + id + ", userId=" + userId + ", priorityLevel=" + priorityLevel + ", dt="
				+ dt + ", processId=" + processId + ", dis=" + dis + "]";
	}

}
