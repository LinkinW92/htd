package com.skeqi.mes.pojo;

public class CMesCheckoutEnterT {

	private Integer id;
	private String sn;
	private String record;
	private Integer productionId;
	private Integer status;
	private Integer orderNumber;
	private String projectName;
	private String method;
	private String quailty;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getQuailty() {
		return quailty;
	}
	public void setQuailty(String quailty) {
		this.quailty = quailty;
	}
	@Override
	public String toString() {
		return "CMesCheckoutEnterT [id=" + id + ", sn=" + sn + ", record=" + record + ", productionId=" + productionId
				+ ", status=" + status + ", orderNumber=" + orderNumber + ", projectName=" + projectName + ", method="
				+ method + ", quailty=" + quailty + "]";
	}
	public CMesCheckoutEnterT(Integer id, String sn, String record, Integer productionId, Integer status,
			Integer orderNumber, String projectName, String method, String quailty) {
		super();
		this.id = id;
		this.sn = sn;
		this.record = record;
		this.productionId = productionId;
		this.status = status;
		this.orderNumber = orderNumber;
		this.projectName = projectName;
		this.method = method;
		this.quailty = quailty;
	}
	public CMesCheckoutEnterT() {
		super();
	}



}
