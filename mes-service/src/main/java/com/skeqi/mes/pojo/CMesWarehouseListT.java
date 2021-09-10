package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesWarehouseListT {

	private Integer id;
	private Date dt;
	private String emp;
	private String sn;
	private Integer lineId;
	private String lineName;
	private Integer productionId;
	private String productionName;
	private Integer planId;
	private String planName;
	private Integer orderId;
	private String orderName;
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	@Override
	public String toString() {
		return "CMesWarehouseListT [id=" + id + ", dt=" + dt + ", emp=" + emp + ", sn=" + sn + ", lineId=" + lineId
				+ ", lineName=" + lineName + ", productionId=" + productionId + ", productionName=" + productionName
				+ ", planId=" + planId + ", planName=" + planName + ", orderId=" + orderId + ", orderName=" + orderName
				+ ", note=" + note + "]";
	}
	public CMesWarehouseListT() {
		super();
	}
	public CMesWarehouseListT(Integer id, Date dt, String emp, String sn, Integer lineId, String lineName,
			Integer productionId, String productionName, Integer planId, String planName, Integer orderId,
			String orderName, String note) {
		super();
		this.id = id;
		this.dt = dt;
		this.emp = emp;
		this.sn = sn;
		this.lineId = lineId;
		this.lineName = lineName;
		this.productionId = productionId;
		this.productionName = productionName;
		this.planId = planId;
		this.planName = planName;
		this.orderId = orderId;
		this.orderName = orderName;
		this.note = note;
	}


}
