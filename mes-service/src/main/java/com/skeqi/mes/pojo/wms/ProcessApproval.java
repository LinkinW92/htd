package com.skeqi.mes.pojo.wms;

/**
 * \审批流程表
 * @author Ryan
 *
 */
public class ProcessApproval {
	private Integer id;
	private Integer deptId;//部门id
	private Integer roleId;//角色id
	private Integer typeId;//审批类型id
	private String dt;
	private String dis;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	@Override
	public String toString() {
		return "ProcessApproval [id=" + id + ", deptId=" + deptId + ", roleId=" + roleId + ", typeId=" + typeId
				+ ", dt=" + dt + ", dis=" + dis + "]";
	}

}
