package com.skeqi.mes.pojo.chenj.srm.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityH
 * @Description ${Description}
 */

/**
 * 送货单头表
 */
@ApiModel(value = "com-skeqi-pojo-chenj-CSrmSendCommodityH")
public class CSrmSendCommodityHRspD {

    /**
     * 送货单号
     */
    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;

    /**
     * 送货单类型(1.标准送货单)
     */
    @ApiModelProperty(value = "送货单类型(1.标准送货单)")
    private String typeOfDeliveryNote;

    /**
     * 发货日期
     */
    @ApiModelProperty(value = "发货日期")
    private String deliveryDate;

    /**
     * 预计到货日期
     */
    @ApiModelProperty(value = "预计到货日期")
    private String expectedDateOfArrival;

    /**
     * 收货地点
     */
    @ApiModelProperty(value = "收货地点")
    private String placeOfReceipt;

    /**
     * 发货地点
     */
    @ApiModelProperty(value = "发货地点")
    private String pointOfDeparture;

    /**
     * 收货组织
     */
    @ApiModelProperty(value = "收货组织")
    private String receivingOrganization;

    /**
     * 收货组织名称
     */
    @ApiModelProperty(value = "收货组织名称")
    private String receivingOrganizationName;

    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    private String client;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String consignee;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;

    /**
     * 状态(1.新建2.待确认3.待发货4.待收货5.已完成)
     */
    @ApiModelProperty(value = "状态(1.新建2.待确认3.待发货4.待收货5.已完成)")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updateTime;

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

    /**
     * 送货方式 ( 1.人工,2.物流)
     */
    private Integer shippingMethod;

    public String getDeliveryNumber() {
        return deliveryNumber;
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

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
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

    public String getSupplierName() {
        return supplierName;
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

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setReceivingOrganization(String receivingOrganization) {
        this.receivingOrganization = receivingOrganization;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getReceivingOrganizationName() {
        return receivingOrganizationName;
    }

    public void setReceivingOrganizationName(String receivingOrganizationName) {
        this.receivingOrganizationName = receivingOrganizationName;
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

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public Integer getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmSendCommodityHRspD{" +
                "deliveryNumber='" + deliveryNumber + '\'' +
                ", typeOfDeliveryNote='" + typeOfDeliveryNote + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", expectedDateOfArrival='" + expectedDateOfArrival + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", receivingOrganization='" + receivingOrganization + '\'' +
                ", receivingOrganizationName='" + receivingOrganizationName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", client='" + client + '\'' +
                ", consignee='" + consignee + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", creator='" + creator + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", logisticsCompanyInformation='" + logisticsCompanyInformation + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", shippingMethod=" + shippingMethod +
                '}';
    }
}
