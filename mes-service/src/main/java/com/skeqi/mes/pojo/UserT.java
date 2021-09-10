package com.skeqi.mes.pojo;
/*用户表*/
public class UserT {
	private int id;//主键id
	private String userName;//用户名称
	private String userPwd;//用户密码
	private String permissions;
	private int status;
	private String department;
	private String security;
	private String userPower;
	private String roleId;//角色id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getUserPower() {
		return userPower;
	}
	public void setUserPower(String userPower) {
		this.userPower = userPower;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserT [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", permissions=" + permissions
				+ ", status=" + status + ", department=" + department + ", security=" + security + ", userPower="
				+ userPower + ", roleId=" + roleId + "]";
	}

}
