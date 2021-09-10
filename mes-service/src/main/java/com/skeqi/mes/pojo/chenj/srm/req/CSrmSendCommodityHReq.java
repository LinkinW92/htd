package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSendCommodityH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 送货单头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSendCommodityHReq {

    /**
     * 送货单号
     */
    private String deliveryNumber;
    /**
     * 送货单号集合
     */
    private List<CSrmSendCommodityRReq> deliveryNumberList;
    /**
     * 对账申请单号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 送货单类型(1.标准送货单)
     */
    private String typeOfDeliveryNote;

    /**
     * 发货日期
     */
    private String deliveryDate;

    /**
     * 预计到货日期
     */
    private String expectedDateOfArrival;

    /**
     * 收货地点
     */
    private String placeOfReceipt;

    /**
     * 发货地点
     */
    private String pointOfDeparture;

    /**
     * 收货组织
     */
    private String receivingOrganization;

    /**
     * 收货组织名称
     */
    private String receivingOrganizationName;
    /**
     * 客户
     */
    private String client;

    /**
     * 供应商
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;

    /**
     *  创建日期
     */

    private String createTime;

    /**
     *  修改日期
     */
    private String updateTime;


    /**
     *  送货单行数据
     */
    private List<CSrmSendCommodityRReq> deList;



    /**
     *  物流行数据
     */
//    private List<CSrmSendCommodityLogisticsReq> wlList;

    /**
     *  送货方式 ( 1.人工,2.物流)
     */

    private Integer shippingMethod;

    /**
     *  是否已创建送货单(0.未创建1.已创建)
     */
    private Integer  isOpenTicket;



    /**
     * 备注
     */
    private String dis;




    /**
     * 服务类型(1.K3(10000))
     */
    private Integer serviceType;


	/**
	 * 是否推送服务开关(true：推送，false：不推送)
	 */
	private Boolean push;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

	/**
     * 状态(1.新建2.待确认3.待发货4.待收货5.已完成)
     */
    private String status;

    /**
     * 操作标识(1.创建2.修改3.变更状态)
     */
    private String operationSign;


    /**
     * 物流单号
     */
    private String trackingNumber;

    /**
     * 物流公司信息
     */
    private String logisticsCompanyInformation;

    /**
     * 联系方式
     */
    private String contactWay;


    public String getOrderNumber() {
        return orderNumber;
    }

    public Integer getIsOpenTicket() {
        return isOpenTicket;
    }

    public void setIsOpenTicket(Integer isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public Integer getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public List<CSrmSendCommodityRReq> getDeList() {
        return deList;
    }


    public void setDeList(List<CSrmSendCommodityRReq> deList) {
        this.deList = deList;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getTypeOfDeliveryNote() {
        return typeOfDeliveryNote;
    }

    public void setTypeOfDeliveryNote(String typeOfDeliveryNote) {
        this.typeOfDeliveryNote = typeOfDeliveryNote;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }

    public String getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public void setExpectedDateOfArrival(String expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getReceivingOrganization() {
        return receivingOrganization;
    }

    public void setReceivingOrganization(String receivingOrganization) {
        this.receivingOrganization = receivingOrganization;
    }

    public List<CSrmSendCommodityRReq> getDeliveryNumberList() {
        return deliveryNumberList;
    }

    public void setDeliveryNumberList(List<CSrmSendCommodityRReq> deliveryNumberList) {
        this.deliveryNumberList = deliveryNumberList;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCreator() {
        return creator;
    }

    public String getSupplierName() {
        return supplierName;
    }


    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getReceivingOrganizationName() {
        return receivingOrganizationName;
    }

    public void setReceivingOrganizationName(String receivingOrganizationName) {
        this.receivingOrganizationName = receivingOrganizationName;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getLogisticsCompanyInformation() {
        return logisticsCompanyInformation;
    }

    public void setLogisticsCompanyInformation(String logisticsCompanyInformation) {
        this.logisticsCompanyInformation = logisticsCompanyInformation;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public Boolean getPush() {
		return push;
	}

	public void setPush(Boolean push) {
		this.push = push;
	}

	@Override
	public String toString() {
		return "CSrmSendCommodityHReq{" +
			"deliveryNumber='" + deliveryNumber + '\'' +
			", deliveryNumberList=" + deliveryNumberList +
			", theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
			", orderNumber='" + orderNumber + '\'' +
			", typeOfDeliveryNote='" + typeOfDeliveryNote + '\'' +
			", deliveryDate='" + deliveryDate + '\'' +
			", expectedDateOfArrival='" + expectedDateOfArrival + '\'' +
			", placeOfReceipt='" + placeOfReceipt + '\'' +
			", pointOfDeparture='" + pointOfDeparture + '\'' +
			", receivingOrganization='" + receivingOrganization + '\'' +
			", receivingOrganizationName='" + receivingOrganizationName + '\'' +
			", client='" + client + '\'' +
			", supplierCode='" + supplierCode + '\'' +
			", supplierName='" + supplierName + '\'' +
			", creator='" + creator + '\'' +
			", pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", createTime='" + createTime + '\'' +
			", updateTime='" + updateTime + '\'' +
			", deList=" + deList +
			", shippingMethod=" + shippingMethod +
			", isOpenTicket=" + isOpenTicket +
			", dis='" + dis + '\'' +
			", serviceType=" + serviceType +
			", push=" + push +
			", status='" + status + '\'' +
			", operationSign='" + operationSign + '\'' +
			", trackingNumber='" + trackingNumber + '\'' +
			", logisticsCompanyInformation='" + logisticsCompanyInformation + '\'' +
			", contactWay='" + contactWay + '\'' +
			'}';
	}
}
