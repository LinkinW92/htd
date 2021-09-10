package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSendCommodityH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageInfo;

/**
 * 送货单头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSendCommodityHRsp {

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
    private String supplier;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 当前页码-行
     */
    private Integer pageNumR;
    /**
     * 条数-行
     */
    private Integer pageSizeR;

    /**
     * 当前页码-物流
     */
    private Integer pageNumW;
    /**
     * 条数-物流
     */
    private Integer pageSizeW;



    /**
     *  创建日期
     */

    private String createTime;


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
     *  送货方式 ( 1.人工,2.物流)
     */
    private String shippingMethod;






    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getReceivingOrganizationName() {
        return receivingOrganizationName;
    }

    public void setReceivingOrganizationName(String receivingOrganizationName) {
        this.receivingOrganizationName = receivingOrganizationName;
    }

    @Override
    public String toString() {
        return "CSrmSendCommodityHRsp{" +
                "deliveryNumber='" + deliveryNumber + '\'' +
                ", typeOfDeliveryNote='" + typeOfDeliveryNote + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", expectedDateOfArrival='" + expectedDateOfArrival + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", receivingOrganization='" + receivingOrganization + '\'' +
                ", receivingOrganizationName='" + receivingOrganizationName + '\'' +
                ", client='" + client + '\'' +
                ", supplier='" + supplier + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", creator='" + creator + '\'' +
                ", pageNumR=" + pageNumR +
                ", pageSizeR=" + pageSizeR +
                ", pageNumW=" + pageNumW +
                ", pageSizeW=" + pageSizeW +
                ", createTime='" + createTime + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", logisticsCompanyInformation='" + logisticsCompanyInformation + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", status='" + status + '\'' +
                ", deList=" + deList +
                ", wlList=" + wlList +
                '}';
    }

    /**
     * 状态(1.新建2.待确认3.待发货4.待收货5.已完成)
     */
    private String status;


    /**
     * 行数据
     */
    private PageInfo<CSrmSendCommodityRReq> deList;


    /**
     *  物流行数据
     */
    private PageInfo<CSrmSendCommodityLogisticsReq> wlList;



    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public PageInfo<CSrmSendCommodityRReq> getDeList() {
        return deList;
    }

    public void setDeList(PageInfo<CSrmSendCommodityRReq> deList) {
        this.deList = deList;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getTypeOfDeliveryNote() {
        return typeOfDeliveryNote;
    }

    public Integer getPageSizeR() {
        return pageSizeR;
    }

    public void setPageSizeR(Integer pageSizeR) {
        this.pageSizeR = pageSizeR;
    }

    public Integer getPageNumW() {
        return pageNumW;
    }

    public void setPageNumW(Integer pageNumW) {
        this.pageNumW = pageNumW;
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

    public Integer getPageSizeW() {
        return pageSizeW;
    }

    public void setPageSizeW(Integer pageSizeW) {
        this.pageSizeW = pageSizeW;
    }

    public Integer getPageNumR() {
        return pageNumR;
    }

    public void setPageNumR(Integer pageNumR) {
        this.pageNumR = pageNumR;
    }

    public void setTypeOfDeliveryNote(String typeOfDeliveryNote) {
        this.typeOfDeliveryNote = typeOfDeliveryNote;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public PageInfo<CSrmSendCommodityLogisticsReq> getWlList() {
        return wlList;
    }

    public void setWlList(PageInfo<CSrmSendCommodityLogisticsReq> wlList) {
        this.wlList = wlList;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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


}
