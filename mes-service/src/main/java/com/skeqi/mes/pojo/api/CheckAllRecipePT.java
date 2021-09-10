package com.skeqi.mes.pojo.api;

import java.util.Date;

public class CheckAllRecipePT {

	private Integer id;
	private Date dt;
	private String productionName;
	private Integer productionId;
	private String stName;
	private Integer stId;
	private Integer serialNo;
	private Integer lineId;
	private Integer routingId;
	private Integer totalRecipeId;




	public Integer getRoutingId() {
		return routingId;
	}
	public void setRoutingId(Integer routingId) {
		this.routingId = routingId;
	}
	public Integer getTotalRecipeId() {
		return totalRecipeId;
	}
	public void setTotalRecipeId(Integer totalRecipeId) {
		this.totalRecipeId = totalRecipeId;
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
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public Integer getStId() {
		return stId;
	}
	public void setStId(Integer stId) {
		this.stId = stId;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	@Override
	public String toString() {
		return "CheckAllRecipePT [id=" + id + ", dt=" + dt + ", productionName=" + productionName + ", productionId="
				+ productionId + ", stName=" + stName + ", stId=" + stId + ", serialNo=" + serialNo + ", lineId="
				+ lineId + ", routingId=" + routingId + ", totalRecipeId=" + totalRecipeId + "]";
	}




}
