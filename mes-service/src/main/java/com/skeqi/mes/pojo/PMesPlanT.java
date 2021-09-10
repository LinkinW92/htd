package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesPlanT {
	private Integer id;
	private Date dt;//时间
	private String planName;//工单名称
		private Integer productionId;//产品类别ID
	private CMesProductionT production;//产品类别
	private String productionType;//产品类别
	private Integer planNumber;//数量
	private Integer completeNumber;//完成数量
	private Integer remaindNumber;//未完成数量
	private Integer okNumber;//成品数量
	private Integer ngNumber;//未成品数量
	private Integer lineId;//产线ID
	private CMesLineT line;//产线
	private String name;
	private Integer planLevel;//计划优先级
	private String completeFlag;//完成标记
	private String opreationUser;//操作人姓名
	private String createBarcodeFlag;//是否生成条码
	private String planSerialno;
	private String onlineNumber;

	public String getOnlineNumber() {
		return onlineNumber;
	}
	public void setOnlineNumber(String onlineNumber) {
		this.onlineNumber = onlineNumber;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getPlanSerialno() {
		return planSerialno;
	}
	public void setPlanSerialno(String planSerialno) {
		this.planSerialno = planSerialno;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
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
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public CMesProductionT getProduction() {
		return production;
	}
	public void setProduction(CMesProductionT production) {
		this.production = production;
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
	public CMesLineT getLine() {
		return line;
	}
	public void setLine(CMesLineT line) {
		this.line = line;
	}
	public Integer getPlanLevel() {
		return planLevel;
	}
	public void setPlanLevel(Integer planLevel) {
		this.planLevel = planLevel;
	}
	public String getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}
	public String getOpreationUser() {
		return opreationUser;
	}
	public void setOpreationUser(String opreationUser) {
		this.opreationUser = opreationUser;
	}
	public String getCreateBarcodeFlag() {
		return createBarcodeFlag;
	}
	public void setCreateBarcodeFlag(String createBarcodeFlag) {
		this.createBarcodeFlag = createBarcodeFlag;
	}

	public String getProductionType() {
		return productionType;
	}
	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public PMesPlanT(Integer id, Date dt, String planName, CMesProductionT production, Integer planNumber,
			Integer completeNumber, Integer remaindNumber, Integer okNumber, Integer ngNumber, CMesLineT line,
			Integer planLevel, String completeFlag, String opreationUser, String createBarcodeFlag) {
		super();
		this.id = id;
		this.dt = dt;
		this.planName = planName;
		this.production = production;
		this.planNumber = planNumber;
		this.completeNumber = completeNumber;
		this.remaindNumber = remaindNumber;
		this.okNumber = okNumber;
		this.ngNumber = ngNumber;
		this.line = line;
		this.planLevel = planLevel;
		this.completeFlag = completeFlag;
		this.opreationUser = opreationUser;
		this.createBarcodeFlag = createBarcodeFlag;
	}
	public PMesPlanT() {
		super();
	}
	@Override
	public String toString() {
		return "PMesPlanT [id=" + id + ", dt=" + dt + ", planName=" + planName + ", production=" + production
				+ ", planNumber=" + planNumber + ", completeNumber=" + completeNumber + ", remaindNumber="
				+ remaindNumber + ", okNumber=" + okNumber + ", ngNumber=" + ngNumber + ", line=" + line
				+ ", planLevel=" + planLevel + ", completeFlag=" + completeFlag + ", opreationUser=" + opreationUser
				+ ", createBarcodeFlag=" + createBarcodeFlag + "]";
	}




}
