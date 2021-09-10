package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RMesWorkorderDetailT {
	private Integer id;//              INTEGER not null,
	@ApiModelProperty(value="时间",required=false)
	private String dt;//              DATE,
	@ApiModelProperty(value="产品id",required=false)
	private String productionId;//   NUMBER not null,
	@ApiModelProperty(value="订单数量",required=false)
	private Integer orderNumber;//    NUMBER not null,
	@ApiModelProperty(value="上线数量",required=false)
	private Integer onlineNumber;//   NUMBER,
	@ApiModelProperty(value="下线数量",required=false)
	private Integer offlineNumber;//  NUMBER,
	@ApiModelProperty(value="缺陷数量",required=false)
	private Integer deffectNumber;//  NUMBER,
	@ApiModelProperty(value="产线id",required=false)
	private Integer lineId;//         NUMBER,
	@ApiModelProperty(value="班次id",required=false)
	private Integer teamId;//         NUMBER,
	@ApiModelProperty(value="优先级",required=false)
	private Integer levelNo;//        NUMBER,
	@ApiModelProperty(value="状态",required=false)
	private Integer status;//          NUMBER,
	@ApiModelProperty(value="修改日期",required=false)
	private String alterDt;//        DATE,
	@ApiModelProperty(value="计划id",required=false)
	private Integer planId;//         NUMBER not null
	@ApiModelProperty(value="产品名称",required=false)
	private String productionName;// VARCHAR2(200),
	@ApiModelProperty(value="班次名称",required=false)
	private String teamName;// VARCHAR2(200),
//	@ApiModelProperty(value="条形码？",required=false)
	private String createBarcodeFlag;
	@ApiModelProperty(value="产线名称",required=false)
	private String lineName;// VARCHAR2(200),


	public String getCreateBarcodeFlag() {
		return createBarcodeFlag;
	}
	public void setCreateBarcodeFlag(String createBarcodeFlag) {
		this.createBarcodeFlag = createBarcodeFlag;
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
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getOnlineNumber() {
		return onlineNumber;
	}
	public void setOnlineNumber(Integer onlineNumber) {
		this.onlineNumber = onlineNumber;
	}
	public Integer getOfflineNumber() {
		return offlineNumber;
	}
	public void setOfflineNumber(Integer offlineNumber) {
		this.offlineNumber = offlineNumber;
	}
	public Integer getDeffectNumber() {
		return deffectNumber;
	}
	public void setDeffectNumber(Integer deffectNumber) {
		this.deffectNumber = deffectNumber;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlterDt() {
		return alterDt;
	}
	public void setAlterDt(String alterDt) {
		this.alterDt = alterDt;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
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
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public RMesWorkorderDetailT(Integer id, String dt, String productionId, Integer orderNumber, Integer onlineNumber,
			Integer offlineNumber, Integer deffectNumber, Integer lineId, Integer teamId, Integer levelNo,
			Integer status, String alterDt, Integer planId, String productionName, String teamName,
			String createBarcodeFlag, String lineName) {
		super();
		this.id = id;
		this.dt = dt;
		this.productionId = productionId;
		this.orderNumber = orderNumber;
		this.onlineNumber = onlineNumber;
		this.offlineNumber = offlineNumber;
		this.deffectNumber = deffectNumber;
		this.lineId = lineId;
		this.teamId = teamId;
		this.levelNo = levelNo;
		this.status = status;
		this.alterDt = alterDt;
		this.planId = planId;
		this.productionName = productionName;
		this.teamName = teamName;
		this.createBarcodeFlag = createBarcodeFlag;
		this.lineName = lineName;
	}
	@Override
	public String toString() {
		return "RMesWorkorderDetailT [id=" + id + ", dt=" + dt + ", productionId=" + productionId + ", orderNumber="
				+ orderNumber + ", onlineNumber=" + onlineNumber + ", offlineNumber=" + offlineNumber
				+ ", deffectNumber=" + deffectNumber + ", lineId=" + lineId + ", teamId=" + teamId + ", levelNo="
				+ levelNo + ", status=" + status + ", alterDt=" + alterDt + ", planId=" + planId + ", productionName="
				+ productionName + ", teamName=" + teamName + ", createBarcodeFlag=" + createBarcodeFlag + ", lineName="
				+ lineName + "]";
	}
	public RMesWorkorderDetailT() {
		super();
	}




}
