package com.skeqi.mes.pojo.chenj.srm;

import io.swagger.annotations.ApiModel;

import java.util.Date;


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
public class CSrmSendCommodityH {
    /**
     * 送货单头表id
     */
    private Integer id;

    /**
     * 送货单号
     */
    private String deliveryNumber;

    /**
     * 送货单类型(1.标准送货单)
     */
    private String typeOfDeliveryNote;

    /**
     * 发货日期
     */
    private Date deliveryDate;

    /**
     * 预计到货日期
     */
    private Date expectedDateOfArrival;

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
     * 收货时间
     */
    private Date receivingGoodsDt;

    /**
     * 客户
     */
    private String client;

    /**
     * 收货人
     */
    private String consignee;


    /**
     * 收货人名称
     */
    private String consigneeName;


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
     * 供应商
     */
    private String supplierCode;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态(1.新建2.待确认3.待发货4.待收货5.已完成)
     */
    private String status;


    /**
     * 是否已创建开票申请单(0.未创建1.已创建)
     */
    private Integer isOpenTicket;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 送货方式 ( 1.人工,2.物流）
     */
    private Integer shippingMethod;

    /**
     * 逻辑删除(0:未删除、1:已删除)
     */
    private Boolean isDelete;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public void setExpectedDateOfArrival(Date expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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

    public Date getReceivingGoodsDt() {
        return receivingGoodsDt;
    }

    public void setReceivingGoodsDt(Date receivingGoodsDt) {
        this.receivingGoodsDt = receivingGoodsDt;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsOpenTicket() {
        return isOpenTicket;
    }

    public void setIsOpenTicket(Integer isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmSendCommodityH{" +
                "id=" + id +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", typeOfDeliveryNote='" + typeOfDeliveryNote + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", expectedDateOfArrival=" + expectedDateOfArrival +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", receivingOrganization='" + receivingOrganization + '\'' +
                ", receivingGoodsDt=" + receivingGoodsDt +
                ", client='" + client + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", logisticsCompanyInformation='" + logisticsCompanyInformation + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", creator='" + creator + '\'' +
                ", status='" + status + '\'' +
                ", isOpenTicket=" + isOpenTicket +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", shippingMethod=" + shippingMethod +
                ", isDelete=" + isDelete +
                '}';
    }
}
