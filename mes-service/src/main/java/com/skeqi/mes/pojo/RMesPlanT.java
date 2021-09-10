package com.skeqi.mes.pojo;

import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;

import io.swagger.annotations.ApiModelProperty;

public class RMesPlanT {
	private Integer id;
	@ApiModelProperty(value="时间",required=false)
	private String dt;
	@ApiModelProperty(value="计划名称",required=false)
	private String planName;
	@ApiModelProperty(value="产品id",required=false)
	private Integer productionId;
	@ApiModelProperty(value="计划数量",required=false)
	private Integer planNumber;
	@ApiModelProperty(value="完成数量",required=false)
	private Integer completeNumber;
	@ApiModelProperty(value="剩余数量",required=false)
	private Integer remaindNumber;
	@ApiModelProperty(value="合格数量",required=false)
	private Integer okNumber;
	@ApiModelProperty(value="不合格数量",required=false)
	private Integer ngNumber;
	@ApiModelProperty(value="产线id",required=false)
	private Integer lineId;
	@ApiModelProperty(value="产线名称",required=false)
	private String lineName;
	@ApiModelProperty(value="计划优先级",required=false)
	private String planLevel;
	@ApiModelProperty(value="完成标记",required=false)
	private String completeFlag;
	@ApiModelProperty(value="操作人员",required=false)
	private String opreationUser;
	@ApiModelProperty(value="是否生成条码",required=false)
	private String createBarcodeFlag;
//	private CMesLineT line;//产线
	private String name;
	@ApiModelProperty(value="产品类别",required=false)
	private String productionType;//产品类别
	@ApiModelProperty(value="产品名称",required=false)
	private String productionName;//产品名称
	@ApiModelProperty(value="计划编号",required=false)
	private String planSerialno;  //计划编号
	private String onlineNumber;
	@ApiModelProperty(value="工艺路线id",required=false)
	private Integer routingId;  //工艺路线id
	@ApiModelProperty(value="工艺路线名称",required=false)
	private String routingName;
	@ApiModelProperty(value="总配方id",required=false)
	private Integer totalRecipeId;  //总配方id
	@ApiModelProperty(value="总配方名称",required=false)
	private String totalRecipeName;   //总配方名称
	@ApiModelProperty(value="计划开始时间",required=false)
	private String planStartTime;   //计划开始时间
	@ApiModelProperty(value="计划完成时间",required=false)
	private String planEndTime;   //计划完成时间
	@ApiModelProperty(value="实际开始时间",required=false)
	private String actualStartTime;   //实际开始时间
	@ApiModelProperty(value="实际完毕时间",required=false)
	private String actualEndTime;  //实际完毕时间
	private String productMark;
	@ApiModelProperty(value="当前状态",required=false)
	private String planStatus;//当前状态
	@ApiModelProperty(value="关闭时间",required=false)
	private String planDate;//关闭时间
	@ApiModelProperty(value="关闭人",required=false)
	private String  userName;//关闭人
	@ApiModelProperty(value="计划类型id",required=false)
	private Integer  planTypeId;//计划类型id
	@ApiModelProperty(value="计划类型名称",required=false)
	private String  planTypeName;//计划类型名称

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getProductMark() {
		return productMark;
	}
	public void setProductMark(String productMark) {
		this.productMark = productMark;
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
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
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
	public String getPlanLevel() {
		return planLevel;
	}
	public void setPlanLevel(String planLevel) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductionType() {
		return productionType;
	}
	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getPlanSerialno() {
		return planSerialno;
	}
	public void setPlanSerialno(String planSerialno) {
		this.planSerialno = planSerialno;
	}
	public String getOnlineNumber() {
		return onlineNumber;
	}
	public void setOnlineNumber(String onlineNumber) {
		this.onlineNumber = onlineNumber;
	}
	public Integer getRoutingId() {
		return routingId;
	}
	public void setRoutingId(Integer routingId) {
		this.routingId = routingId;
	}
	public String getRoutingName() {
		return routingName;
	}
	public void setRoutingName(String routingName) {
		this.routingName = routingName;
	}
	public Integer getTotalRecipeId() {
		return totalRecipeId;
	}
	public void setTotalRecipeId(Integer totalRecipeId) {
		this.totalRecipeId = totalRecipeId;
	}
	public String getTotalRecipeName() {
		return totalRecipeName;
	}
	public void setTotalRecipeName(String totalRecipeName) {
		this.totalRecipeName = totalRecipeName;
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
	public Integer getPlanTypeId() {
		return planTypeId;
	}
	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}
	public String getPlanTypeName() {
		return planTypeName;
	}
	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}
	public RMesPlanT(Integer id, String dt, String planName, Integer productionId, Integer planNumber,
			Integer completeNumber, Integer remaindNumber, Integer okNumber, Integer ngNumber, Integer lineId,
			String lineName, String planLevel, String completeFlag, String opreationUser, String createBarcodeFlag,
			String name, String productionType, String productionName, String planSerialno, String onlineNumber,
			Integer routingId, String routingName, Integer totalRecipeId, String totalRecipeName, String planStartTime,
			String planEndTime, String actualStartTime, String actualEndTime, String productMark, String planStatus,
			String planDate, String userName, Integer planTypeId, String planTypeName) {
		super();
		this.id = id;
		this.dt = dt;
		this.planName = planName;
		this.productionId = productionId;
		this.planNumber = planNumber;
		this.completeNumber = completeNumber;
		this.remaindNumber = remaindNumber;
		this.okNumber = okNumber;
		this.ngNumber = ngNumber;
		this.lineId = lineId;
		this.lineName = lineName;
		this.planLevel = planLevel;
		this.completeFlag = completeFlag;
		this.opreationUser = opreationUser;
		this.createBarcodeFlag = createBarcodeFlag;
		this.name = name;
		this.productionType = productionType;
		this.productionName = productionName;
		this.planSerialno = planSerialno;
		this.onlineNumber = onlineNumber;
		this.routingId = routingId;
		this.routingName = routingName;
		this.totalRecipeId = totalRecipeId;
		this.totalRecipeName = totalRecipeName;
		this.planStartTime = planStartTime;
		this.planEndTime = planEndTime;
		this.actualStartTime = actualStartTime;
		this.actualEndTime = actualEndTime;
		this.productMark = productMark;
		this.planStatus = planStatus;
		this.planDate = planDate;
		this.userName = userName;
		this.planTypeId = planTypeId;
		this.planTypeName = planTypeName;
	}
	public RMesPlanT() {
		super();
	}
	@Override
	public String toString() {
		return "RMesPlanT [id=" + id + ", dt=" + dt + ", planName=" + planName + ", productionId=" + productionId
				+ ", planNumber=" + planNumber + ", completeNumber=" + completeNumber + ", remaindNumber="
				+ remaindNumber + ", okNumber=" + okNumber + ", ngNumber=" + ngNumber + ", lineId=" + lineId
				+ ", lineName=" + lineName + ", planLevel=" + planLevel + ", completeFlag=" + completeFlag
				+ ", opreationUser=" + opreationUser + ", createBarcodeFlag=" + createBarcodeFlag + ", name=" + name
				+ ", productionType=" + productionType + ", productionName=" + productionName + ", planSerialno="
				+ planSerialno + ", onlineNumber=" + onlineNumber + ", routingId=" + routingId + ", routingName="
				+ routingName + ", totalRecipeId=" + totalRecipeId + ", totalRecipeName=" + totalRecipeName
				+ ", planStartTime=" + planStartTime + ", planEndTime=" + planEndTime + ", actualStartTime="
				+ actualStartTime + ", actualEndTime=" + actualEndTime + ", productMark=" + productMark
				+ ", planStatus=" + planStatus + ", planDate=" + planDate + ", userName=" + userName + ", planTypeId="
				+ planTypeId + ", planTypeName=" + planTypeName + "]";
	}




}
