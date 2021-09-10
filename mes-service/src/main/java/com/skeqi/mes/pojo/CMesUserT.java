package com.skeqi.mes.pojo;

import java.util.HashSet;
import java.util.Set;

import com.skeqi.mes.pojo.wms.CWmsDepartmentT;

import io.swagger.annotations.ApiModelProperty;

public class CMesUserT {

	private Integer id;
	@ApiModelProperty(value = "角色ID", required = true)
	private Integer roleId;
	private Integer userNumber;
	@ApiModelProperty(value = "名称", required = true)
	private String userName;
	@ApiModelProperty(value = "密码", required = true)
	private String userPwd;
	@ApiModelProperty(value = "身份", required = true)
	private String status;
	@ApiModelProperty(value = "部门", required = true)
	private String department;
	@ApiModelProperty(value = "保密", required = true)
	private String security;
	private String userPower;
	@ApiModelProperty(value = "角色名称", required = false)
	private String roleName;
	private String position;// 职位
	private String positionName;// 职位name
	private String organizationalPath;// 组织路径
	private Set<CMesRoleT> setRole = new HashSet<>();
	private Set<CMesMenuT> setMenu = new HashSet<>();
	private CWmsDepartmentT dep;// 部门
	private String loginStatus;
	private String rank;// 职级
	private String reportsToName;// 直属主管
	private String name;//姓名
	// 供应商代码
	private String supplierCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReportsToName() {
		return reportsToName;
	}

	public void setReportsToName(String reportsToName) {
		this.reportsToName = reportsToName;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	// 令牌
	private String token;

	private CMesRoleT role;
	private CWmsDepartmentT departmentT;

	public String getRank() {
		return rank;
	}

	public void setRankID(String rank) {
		this.rank = rank;
	}

	public String getOrganizationalPath() {
		return organizationalPath;
	}

	public void setOrganizationalPath(String organizationalPath) {
		this.organizationalPath = organizationalPath;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public CMesRoleT getRole() {
		return role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRole(CMesRoleT role) {
		this.role = role;
	}

	public CWmsDepartmentT getDepartmentT() {
		return departmentT;
	}

	public void setDepartmentT(CWmsDepartmentT departmentT) {
		this.departmentT = departmentT;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public CWmsDepartmentT getDep() {
		return dep;
	}

	public void setDep(CWmsDepartmentT dep) {
		this.dep = dep;
	}

	public Set<CMesMenuT> getSetMenu() {
		return setMenu;
	}

	public void setSetMenu(Set<CMesMenuT> setMenu) {
		this.setMenu = setMenu;
	}

	public Set<CMesRoleT> getSetRole() {
		return setRole;
	}

	public void setSetRole(Set<CMesRoleT> setRole) {
		this.setRole = setRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserPower() {
		return userPower;
	}

	public void setUserPower(String userPower) {
		this.userPower = userPower;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	// 封装用户名密码
	public String UserInfoToString() {
		return "C_MES_USER_T [id=" + id.toString() + ", UserName=" + userName + ", UserPwd=" + userPwd + "]";
	}

	@Override
	public String toString() {
		return "CMesUserT [id=" + id + ", roleId=" + roleId + ", userNumber=" + userNumber + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", status=" + status + ", department=" + department + ", security="
				+ security + ", userPower=" + userPower + ", roleName=" + roleName + ", setRole=" + setRole
				+ ", setMenu=" + setMenu + "]";
	}

}
