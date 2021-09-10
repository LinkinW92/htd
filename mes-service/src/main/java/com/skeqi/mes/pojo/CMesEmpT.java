package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesEmpT {
	private Integer id;//             INTEGER not null,
	@ApiModelProperty(value="时间",required=false)
	private Date dt;//             DATE,
	@ApiModelProperty(value="员工编码",required=false)
	private String empNo;//         VARCHAR2(100),
	@ApiModelProperty(value="员工姓名",required=false)
	private String empName;//       VARCHAR2(100),
	@ApiModelProperty(value="员工性别",required=false)
	private Integer empSex;//        INTEGER,
	@ApiModelProperty(value="员工类型",required=false)
	private Integer empType;//       INTEGER,
	@ApiModelProperty(value="员工电话",required=false)
	private String empTp;//         VARCHAR2(30),
	@ApiModelProperty(value="员工部门",required=false)
	private String empDepartment;// VARCHAR2(150),
	@ApiModelProperty(value="班组id",required=false)
	private Integer empTeamId;//    INTEGER,

	@ApiModelProperty(value="产线id",required=false)
	private Integer lineId;//        INTEGER,
	@ApiModelProperty(value="角色id",required=false)
	private Integer roleId;//        INTEGER,
	private String empVr;//         VARCHAR2(100)
	@ApiModelProperty(value="班组名称",required=false)
	private String teamName;
	@ApiModelProperty(value="产线名称",required=false)
	private String lineName;
	private String empMail;
	@ApiModelProperty(value="工位id",required=false)
	private String stationId;
	@ApiModelProperty(value="工位名称",required=false)
	private String stationName;
	@ApiModelProperty(value="员工类型名称",required=false)
	private String empTypeName;
	@ApiModelProperty(value="角色名称",required=false)
	private String roleName;
	@ApiModelProperty(value = "是否全班",required = false)
	private String isWhole;
	@ApiModelProperty(value = "密码",required = false)
	private String password;


	public String getIsWhole() {
		return isWhole;
	}

	public void setIsWhole(String isWhole) {
		this.isWhole = isWhole;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public CMesEmpT(Integer id, Date dt, String empNo, String empName, Integer empSex, Integer empType, String empTp, String empDepartment, Integer empTeamId, Integer lineId, Integer roleId, String empVr, String teamName, String lineName, String empMail, String stationId, String stationName, String empTypeName, String roleName, String isWhole, String password) {
		this.id = id;
		this.dt = dt;
		this.empNo = empNo;
		this.empName = empName;
		this.empSex = empSex;
		this.empType = empType;
		this.empTp = empTp;
		this.empDepartment = empDepartment;
		this.empTeamId = empTeamId;
		this.lineId = lineId;
		this.roleId = roleId;
		this.empVr = empVr;
		this.teamName = teamName;
		this.lineName = lineName;
		this.empMail = empMail;
		this.stationId = stationId;
		this.stationName = stationName;
		this.empTypeName = empTypeName;
		this.roleName = roleName;
		this.isWhole = isWhole;
		this.password = password;
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
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getEmpSex() {
		return empSex;
	}
	public void setEmpSex(Integer empSex) {
		this.empSex = empSex;
	}
	public Integer getEmpType() {
		return empType;
	}
	public void setEmpType(Integer empType) {
		this.empType = empType;
	}
	public String getEmpTp() {
		return empTp;
	}
	public void setEmpTp(String empTp) {
		this.empTp = empTp;
	}
	public String getEmpDepartment() {
		return empDepartment;
	}
	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}
	public Integer getEmpTeamId() {
		return empTeamId;
	}
	public void setEmpTeamId(Integer empTeamId) {
		this.empTeamId = empTeamId;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getEmpVr() {
		return empVr;
	}
	public void setEmpVr(String empVr) {
		this.empVr = empVr;
	}
	public CMesEmpT() {
		super();
	}
	public String getEmpMail() {
		return empMail;
	}
	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


}
