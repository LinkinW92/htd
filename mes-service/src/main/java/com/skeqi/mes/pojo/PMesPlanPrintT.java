package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesPlanPrintT {

	private Integer id;
	private Date dt;
	private String sn;
	private Integer planId;
	private String planName;
	private Integer lineId;
	private String lineName;
	private Integer productionId;
	private String productionName;
	private Integer serialNo;
	private String printFlag;
	private Integer workOrderId;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
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
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	public Integer getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Integer workOrderId) {
		this.workOrderId = workOrderId;
	}
	public PMesPlanPrintT(Integer id, Date dt, String sn, Integer planId, String planName, Integer lineId,
			String lineName, Integer productionId, String productionName, Integer serialNo, String printFlag,
			Integer workOrderId) {
		super();
		this.id = id;
		this.dt = dt;
		this.sn = sn;
		this.planId = planId;
		this.planName = planName;
		this.lineId = lineId;
		this.lineName = lineName;
		this.productionId = productionId;
		this.productionName = productionName;
		this.serialNo = serialNo;
		this.printFlag = printFlag;
		this.workOrderId = workOrderId;
	}
	@Override
	public String toString() {
		return "PMesPlanPrintT [id=" + id + ", dt=" + dt + ", sn=" + sn + ", planId=" + planId + ", planName="
				+ planName + ", lineId=" + lineId + ", lineName=" + lineName + ", productionId=" + productionId
				+ ", productionName=" + productionName + ", serialNo=" + serialNo + ", printFlag=" + printFlag
				+ ", workOrderId=" + workOrderId + "]";
	}
	public PMesPlanPrintT() {
		super();
	}


}
