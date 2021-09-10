package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CMesUserTReq {

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
	private Set<CMesRoleT> setRole = new HashSet<>();
	private Set<CMesMenuT> setMenu = new HashSet<>();
	private CWmsDepartmentT dep;// 部门
	private String loginStatus;
	// 令牌
	private String token;

	private Date tokenCreateTime;

	private Integer pageNum;

	private Integer pageSize;


	/**
	 * 登录标识符
	 */
	private String loginstatus;


	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 钉钉appID
	 */
	private String appid;

	/**
	 * 职务
	 */
	private String position;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 直属主管
	 */
	private Integer reportsto;

	/**
	 * 姓名
	 */
	private String name;

	private Integer viewmode;

	/**
	 * 职级id
	 */
	private Integer rankid;

	/**
	 * 供应商代码
	 */
	private String supplierCode;


	@Override
	public String toString() {
		return "CMesUserTReq{" +
				"id=" + id +
				", roleId=" + roleId +
				", userNumber=" + userNumber +
				", userName='" + userName + '\'' +
				", userPwd='" + userPwd + '\'' +
				", status='" + status + '\'' +
				", department='" + department + '\'' +
				", security='" + security + '\'' +
				", userPower='" + userPower + '\'' +
				", roleName='" + roleName + '\'' +
				", setRole=" + setRole +
				", setMenu=" + setMenu +
				", dep=" + dep +
				", loginStatus='" + loginStatus + '\'' +
				", token='" + token + '\'' +
				", tokenCreateTime=" + tokenCreateTime +
				", pageNum=" + pageNum +
				", pageSize=" + pageSize +
				", loginstatus='" + loginstatus + '\'' +
				", mobile='" + mobile + '\'' +
				", appid='" + appid + '\'' +
				", position='" + position + '\'' +
				", email='" + email + '\'' +
				", reportsto=" + reportsto +
				", name='" + name + '\'' +
				", viewmode=" + viewmode +
				", rankid=" + rankid +
				", supplierCode='" + supplierCode + '\'' +
				", role=" + role +
				", departmentT=" + departmentT +
				'}';
	}

	public Date getTokenCreateTime() {
		return tokenCreateTime;
	}

	public void setTokenCreateTime(Date tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}

	private CMesRoleT role;
	private CWmsDepartmentT departmentT;

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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public String getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getReportsto() {
		return reportsto;
	}

	public void setReportsto(Integer reportsto) {
		this.reportsto = reportsto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getViewmode() {
		return viewmode;
	}

	public void setViewmode(Integer viewmode) {
		this.viewmode = viewmode;
	}

	public Integer getRankid() {
		return rankid;
	}

	public void setRankid(Integer rankid) {
		this.rankid = rankid;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	// 封装用户名密码
	public String UserInfoToString() {
		return "C_MES_USER_T [id=" + id.toString() + ", UserName=" + userName + ", UserPwd=" + userPwd + "]";
	}

}
