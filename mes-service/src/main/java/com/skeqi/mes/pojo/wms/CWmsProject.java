package com.skeqi.mes.pojo.wms;

public class CWmsProject {
	private Integer id;// 项目id
	private String managementNo;// 管理编号
	private String contractNo;// 合同编号
	private String projectName;// 项目名称
	private Integer productNumber;// 产品数量
	private String startDate;// 指派时间
	private String endDate;// 交货日期
	private String projectNature;// 项目性质
	private String projectLeader;// 项目负责人
	private Integer projectType;// 项目类别
	private String internalProjectNo;// 内部项目号
	private String updateDate;// 修改时间
	private String dis;// 描述
	private Integer viewMode;

	private CWmsProjectType projectTypeT;// 项目类型

	public CWmsProject() {
		// TODO Auto-generated constructor stub
	}

	public CWmsProject(Integer id, String managementNo, String contractNo, String projectName, Integer productNumber,
			String startDate, String endDate, String projectNature, String projectLeader, Integer projectType,
			String internalProjectNo, String updateDate, String dis) {
		this.id = id;
		this.managementNo = managementNo;
		this.contractNo = contractNo;
		this.projectName = projectName;
		this.productNumber = productNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectNature = projectNature;
		this.projectLeader = projectLeader;
		this.projectType = projectType;
		this.internalProjectNo = internalProjectNo;
		this.updateDate = updateDate;
		this.dis = dis;
	}

	public CWmsProjectType getProjectTypeT() {
		return projectTypeT;
	}

	public void setProjectTypeT(CWmsProjectType projectTypeT) {
		this.projectTypeT = projectTypeT;
	}

	public Integer getViewMode() {
		return viewMode;
	}

	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagementNo() {
		return managementNo;
	}

	public void setManagementNo(String managementNo) {
		this.managementNo = managementNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProjectNature() {
		return projectNature;
	}

	public void setProjectNature(String projectNature) {
		this.projectNature = projectNature;
	}

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public String getInternalProjectNo() {
		return internalProjectNo;
	}

	public void setInternalProjectNo(String internalProjectNo) {
		this.internalProjectNo = internalProjectNo;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	@Override
	public String toString() {
		return "CWmsProject [id=" + id + ", managementNo=" + managementNo + ", contractNo=" + contractNo
				+ ", projectName=" + projectName + ", productNumber=" + productNumber + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", projectNature=" + projectNature + ", projectLeader=" + projectLeader
				+ ", projectType=" + projectType + ", internalProjectNo=" + internalProjectNo + ", updateDate="
				+ updateDate + ", dis=" + dis + "]";
	}

}
