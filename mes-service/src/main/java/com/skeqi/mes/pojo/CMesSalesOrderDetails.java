package com.skeqi.mes.pojo;

import java.util.Date;

//销售订单明细表
public class CMesSalesOrderDetails {
	private Integer id;
	private String companyOrderNumber;// 公司订单号
	private String customerOrderNumber;// 客户订单号
	private String clientName;// 客户/代理
	private Date orderDate;// 订单日期
	private Date scheduledDeliveryDate;// 预定交货日
	private String salesman;// 业务员
	private String accountingDepartment;// 核算部门
	private Integer orderStatus;// 订单状态：0.已到货、1.未到货
	private Integer totalOrderAmount;// 订单总金额
	private Integer collectedStatus;// 是否收款：0.已到款、1.未到款
	private Integer invoiceStatus;// 是否开发票：0.需要、1.不需要
	private String note;// 订单说明
	private Integer orderMaterialCost;// 订单成本
	private Integer orderGrossProfit;// 订单毛利
	private String ultimateCustomer;// 最终客户
	private String contacts;// 联系人
	private String managerTele;// 负责人电话
	private String customerArea;// 客户地区
	private Integer currency;// 币种：0.人名币、1.非人民币
	private Integer shipmentAmount;// 出货金额
	private String deliveryAddress;// 货物寄送地址
	private String invoiceMailingAddress;// 货物寄送地址
	private String picturnPath;// 图片路径
	private Integer state;// 审核状态：0.未审批、1.审批中、2.审批成功、3.驳回、4撤销

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyOrderNumber() {
		return companyOrderNumber;
	}

	public void setCompanyOrderNumber(String companyOrderNumber) {
		this.companyOrderNumber = companyOrderNumber;
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getAccountingDepartment() {
		return accountingDepartment;
	}

	public void setAccountingDepartment(String accountingDepartment) {
		this.accountingDepartment = accountingDepartment;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Integer totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public Integer getCollectedStatus() {
		return collectedStatus;
	}

	public void setCollectedStatus(Integer collectedStatus) {
		this.collectedStatus = collectedStatus;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrderMaterialCost() {
		return orderMaterialCost;
	}

	public void setOrderMaterialCost(Integer orderMaterialCost) {
		this.orderMaterialCost = orderMaterialCost;
	}

	public Integer getOrderGrossProfit() {
		return orderGrossProfit;
	}

	public void setOrderGrossProfit(Integer orderGrossProfit) {
		this.orderGrossProfit = orderGrossProfit;
	}

	public String getUltimateCustomer() {
		return ultimateCustomer;
	}

	public void setUltimateCustomer(String ultimateCustomer) {
		this.ultimateCustomer = ultimateCustomer;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getManagerTele() {
		return managerTele;
	}

	public void setManagerTele(String managerTele) {
		this.managerTele = managerTele;
	}

	public String getCustomerArea() {
		return customerArea;
	}

	public void setCustomerArea(String customerArea) {
		this.customerArea = customerArea;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getShipmentAmount() {
		return shipmentAmount;
	}

	public void setShipmentAmount(Integer shipmentAmount) {
		this.shipmentAmount = shipmentAmount;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getInvoiceMailingAddress() {
		return invoiceMailingAddress;
	}

	public void setInvoiceMailingAddress(String invoiceMailingAddress) {
		this.invoiceMailingAddress = invoiceMailingAddress;
	}

	public String getPicturnPath() {
		return picturnPath;
	}

	public void setPicturnPath(String picturnPath) {
		this.picturnPath = picturnPath;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CMesSalesOrderDetails [id=" + id + ", companyOrderNumber=" + companyOrderNumber
				+ ", customerOrderNumber=" + customerOrderNumber + ", clientName=" + clientName + ", orderDate="
				+ orderDate + ", scheduledDeliveryDate=" + scheduledDeliveryDate + ", salesman=" + salesman
				+ ", accountingDepartment=" + accountingDepartment + ", orderStatus=" + orderStatus
				+ ", totalOrderAmount=" + totalOrderAmount + ", collectedStatus=" + collectedStatus + ", invoiceStatus="
				+ invoiceStatus + ", note=" + note + ", orderMaterialCost=" + orderMaterialCost + ", orderGrossProfit="
				+ orderGrossProfit + ", ultimateCustomer=" + ultimateCustomer + ", contacts=" + contacts
				+ ", managerTele=" + managerTele + ", customerArea=" + customerArea + ", currency=" + currency
				+ ", shipmentAmount=" + shipmentAmount + ", deliveryAddress=" + deliveryAddress
				+ ", invoiceMailingAddress=" + invoiceMailingAddress + ", picturnPath=" + picturnPath + ", state="
				+ state + "]";
	}

}
