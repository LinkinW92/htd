package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

//采购订单表
public class CMesPurchaseOrderForm {
	private Integer id;
	@ApiModelProperty(value="采购订单号",required=false)
	  private String purchaseOrderNo;//采购订单号
	@ApiModelProperty(value="相关销售单",required=false)
	  private String relatedSalesOrder;//相关销售单
	@ApiModelProperty(value="项目代号",required=false)
	  private String itemCode;//项目代号
	@ApiModelProperty(value="供应商",required=false)
	  private String supplier;// 供应商
	@ApiModelProperty(value="客户订单号",required=false)
	  private String customerOrderNumber;// 客户订单号
	@ApiModelProperty(value="是否共享订单,0.是、1.否",required=false)
	  private Integer shareOrderOrNot;// 是否共享订单：0.是、1.否
	@ApiModelProperty(value="联系人",required=false)
	  private String contacts;// 联系人
	@ApiModelProperty(value="联系电话",required=false)
	  private String managerTele;// 联系电话
	@ApiModelProperty(value="传真号",required=false)
	  private String faxNumber;// 传真号
	@ApiModelProperty(value="订单日期",required=false)
	  private Date orderDate;//订单日期
	@ApiModelProperty(value="应进货日",required=false)
	  private Date dueDate;//应进货日
	@ApiModelProperty(value="采购原因",required=false)
	  private String purchasingReason;//采购原因
	@ApiModelProperty(value="采购员",required=false)
	  private String buyer;// 采购员
	@ApiModelProperty(value="付款方式：0.到付、1.先付款",required=false)
	  private Integer paymentMethod;// 付款方式：0.到付、1.先付款
	@ApiModelProperty(value="付款日",required=false)
	  private Date paymentDate;//付款日
	@ApiModelProperty(value="订单说明",required=false)
	  private String orderDescription;// 订单说明
	@ApiModelProperty(value="货物寄送地址",required=false)
	  private String deliveryAddress;// 货物寄送地址
	@ApiModelProperty(value="相关条款",required=false)
	  private String relatedProvisions;//相关条款
	@ApiModelProperty(value="审核意见",required=false)
	  private String auditOpinion;//审核意见
	@ApiModelProperty(value="订单总金额",required=false)
	  private Integer totalOrderAmount;// 订单总金额
	@ApiModelProperty(value="币种：0.人名币、1.非人民币",required=false)
	  private Integer currency;// 币种：0.人名币、1.非人民币
	@ApiModelProperty(value=" 图片路径",required=false)
	  private String picturnPath;// 图片路径
	@ApiModelProperty(value="未付款",required=false)
	  private Integer unpaid;// 未付款
	@ApiModelProperty(value="未收发票",required=false)
	  private Integer unpaidInvoices;// 未收发票
	@ApiModelProperty(value="是否开发票：0.需要、1.不需要",required=false)
	  private Integer invoiceStatus;//是否开发票：0.需要、1.不需要
	@ApiModelProperty(value=" 订单状态：0.未入库、1.已入库",required=false)
	  private Integer orderStatus;// 订单状态：0.未入库、1.已入库
	  @ApiModelProperty(value="审核状态：0.未审批、1.审批中、2.已审批、3.驳回、4撤销",required=false)
	  private Integer state;// 审核状态：0.未审批、1.审批中、2.已审批、3.驳回、4撤销
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}
	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}
	public String getRelatedSalesOrder() {
		return relatedSalesOrder;
	}
	public void setRelatedSalesOrder(String relatedSalesOrder) {
		this.relatedSalesOrder = relatedSalesOrder;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}
	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}
	public Integer getShareOrderOrNot() {
		return shareOrderOrNot;
	}
	public void setShareOrderOrNot(Integer shareOrderOrNot) {
		this.shareOrderOrNot = shareOrderOrNot;
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
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getPurchasingReason() {
		return purchasingReason;
	}
	public void setPurchasingReason(String purchasingReason) {
		this.purchasingReason = purchasingReason;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getRelatedProvisions() {
		return relatedProvisions;
	}
	public void setRelatedProvisions(String relatedProvisions) {
		this.relatedProvisions = relatedProvisions;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public Integer getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(Integer totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public String getPicturnPath() {
		return picturnPath;
	}
	public void setPicturnPath(String picturnPath) {
		this.picturnPath = picturnPath;
	}
	public Integer getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(Integer unpaid) {
		this.unpaid = unpaid;
	}
	public Integer getUnpaidInvoices() {
		return unpaidInvoices;
	}
	public void setUnpaidInvoices(Integer unpaidInvoices) {
		this.unpaidInvoices = unpaidInvoices;
	}
	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "CMesPurchaseOrderForm [id=" + id + ", purchaseOrderNo=" + purchaseOrderNo + ", relatedSalesOrder="
				+ relatedSalesOrder + ", itemCode=" + itemCode + ", supplier=" + supplier + ", customerOrderNumber="
				+ customerOrderNumber + ", shareOrderOrNot=" + shareOrderOrNot + ", contacts=" + contacts
				+ ", managerTele=" + managerTele + ", faxNumber=" + faxNumber + ", orderDate=" + orderDate
				+ ", dueDate=" + dueDate + ", purchasingReason=" + purchasingReason + ", buyer=" + buyer
				+ ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate + ", orderDescription="
				+ orderDescription + ", deliveryAddress=" + deliveryAddress + ", relatedProvisions=" + relatedProvisions
				+ ", auditOpinion=" + auditOpinion + ", totalOrderAmount=" + totalOrderAmount + ", currency=" + currency
				+ ", picturnPath=" + picturnPath + ", unpaid=" + unpaid + ", unpaidInvoices=" + unpaidInvoices
				+ ", invoiceStatus=" + invoiceStatus + ", orderStatus=" + orderStatus + ", state=" + state + "]";
	}


}
