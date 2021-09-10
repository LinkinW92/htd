package com.skeqi.mes.pojo.zch;

public class MesPrintT {
	private Integer ID;
	private String DT;
	private String SN;
	private Integer planId;
	private Integer lineId;
	private Integer productionId;
	private Integer serialNo;
	private Integer printFlag;
	private Integer workOrderId;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getDT() {
		return DT;
	}
	public void setDT(String dT) {
		DT = dT;
	}
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductingId(Integer productionId) {
		this.productionId = productionId;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Integer getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(Integer printFlag) {
		this.printFlag = printFlag;
	}
	public Integer getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Integer workOrderId) {
		this.workOrderId = workOrderId;
	}

}
