package com.skeqi.mes.pojo;

import java.util.Date;

//订单计划实绩表
public class CMesOrderPlanPerformanceSheet {
	private Integer id;

	private String orderNumber;// 订单编号
	private String nameGoods;// 物品名称
	private String plannedPerformance;// 计划实绩
	private Integer quantityProduction;// 生产数量
	private Date startDate;// 着手日
	private Date materialArrivalDate;// 物料到位日
	private Date productionStartDate;// 生产开始日
	private Date productionEndDate;// 生产结束日
	private Date shipmentDate;// 出货日
	private Double currentCompletionRate;// 现完成率
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getPlannedPerformance() {
		return plannedPerformance;
	}

	public void setPlannedPerformance(String plannedPerformance) {
		this.plannedPerformance = plannedPerformance;
	}

	public Integer getQuantityProduction() {
		return quantityProduction;
	}

	public void setQuantityProduction(Integer quantityProduction) {
		this.quantityProduction = quantityProduction;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getMaterialArrivalDate() {
		return materialArrivalDate;
	}

	public void setMaterialArrivalDate(Date materialArrivalDate) {
		this.materialArrivalDate = materialArrivalDate;
	}

	public Date getProductionStartDate() {
		return productionStartDate;
	}

	public void setProductionStartDate(Date productionStartDate) {
		this.productionStartDate = productionStartDate;
	}

	public Date getProductionEndDate() {
		return productionEndDate;
	}

	public void setProductionEndDate(Date productionEndDate) {
		this.productionEndDate = productionEndDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Double getCurrentCompletionRate() {
		return currentCompletionRate;
	}

	public void setCurrentCompletionRate(Double currentCompletionRate) {
		this.currentCompletionRate = currentCompletionRate;
	}

	@Override
	public String toString() {
		return "CMesOrderPlanPerformanceSheet [id=" + id + ", orderNumber=" + orderNumber + ", nameGoods=" + nameGoods
				+ ", plannedPerformance=" + plannedPerformance + ", quantityProduction=" + quantityProduction
				+ ", startDate=" + startDate + ", materialArrivalDate=" + materialArrivalDate + ", productionStartDate="
				+ productionStartDate + ", productionEndDate=" + productionEndDate + ", shipmentDate=" + shipmentDate
				+ ", currentCompletionRate=" + currentCompletionRate + "]";
	}

}
