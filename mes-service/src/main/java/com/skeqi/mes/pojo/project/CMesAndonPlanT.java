package com.skeqi.mes.pojo.project;

public class CMesAndonPlanT {

	private Integer id;
	private String dt;
	private String planStartTime;
	private String planEndTime;
	private String actualStartTime;
	private String actualEndTime;
	private String planName;
	private Integer planNumber;
	private Integer completeNumber;
	private Integer remaindNumber;
	private Integer okNumber;
	private Integer ngNumber;
	private Integer lineId;
	private String lineName;
	private String productionName;
	private Integer productionId;
	private String productionMark;

	public String getProductionMark() {
		return productionMark;
	}
	public void setProductionMark(String productionMark) {
		this.productionMark = productionMark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public String getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Integer getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}
	public Integer getCompleteNumber() {
		return completeNumber;
	}
	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
	}
	public Integer getRemaindNumber() {
		return remaindNumber;
	}
	public void setRemaindNumber(Integer remaindNumber) {
		this.remaindNumber = remaindNumber;
	}
	public Integer getOkNumber() {
		return okNumber;
	}
	public void setOkNumber(Integer okNumber) {
		this.okNumber = okNumber;
	}
	public Integer getNgNumber() {
		return ngNumber;
	}
	public void setNgNumber(Integer ngNumber) {
		this.ngNumber = ngNumber;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public CMesAndonPlanT(Integer id, String dt, String planStartTime, String planEndTime, String actualStartTime,
			String actualEndTime, String planName, Integer planNumber, Integer completeNumber, Integer remaindNumber,
			Integer okNumber, Integer ngNumber, Integer lineId, String lineName, String productionName,
			Integer productionId) {
		super();
		this.id = id;
		this.dt = dt;
		this.planStartTime = planStartTime;
		this.planEndTime = planEndTime;
		this.actualStartTime = actualStartTime;
		this.actualEndTime = actualEndTime;
		this.planName = planName;
		this.planNumber = planNumber;
		this.completeNumber = completeNumber;
		this.remaindNumber = remaindNumber;
		this.okNumber = okNumber;
		this.ngNumber = ngNumber;
		this.lineId = lineId;
		this.lineName = lineName;
		this.productionName = productionName;
		this.productionId = productionId;
	}
	public CMesAndonPlanT() {
		super();
	}


}
