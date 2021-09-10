package com.skeqi.mes.pojo;

import java.util.Date;

public class RMesPlanPrintT {

	private Integer id;
	private Date dt;
	private String sn;
	private Integer planId;
	private Integer lineId;
	private Integer productionId;
	private Integer serialNO;
	private String printFlag;
	public RMesPlanPrintT(Integer id, Date dt, String sn, Integer planId, Integer lineId, Integer productionId,
			Integer serialNO, String printFlag) {
		super();
		this.id = id;
		this.dt = dt;
		this.sn = sn;
		this.planId = planId;
		this.lineId = lineId;
		this.productionId = productionId;
		this.serialNO = serialNO;
		this.printFlag = printFlag;
	}
	public RMesPlanPrintT() {
		super();
	}
	@Override
	public String toString() {
		return "RMesPlanPrintT [id=" + id + ", dt=" + dt + ", sn=" + sn + ", planId=" + planId + ", lineId=" + lineId
				+ ", productionId=" + productionId + ", serialNO=" + serialNO + ", printFlag=" + printFlag + "]";
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
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public Integer getSerialNO() {
		return serialNO;
	}
	public void setSerialNO(Integer serialNO) {
		this.serialNO = serialNO;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}


}
