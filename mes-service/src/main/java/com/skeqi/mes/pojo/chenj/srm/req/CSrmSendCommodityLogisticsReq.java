package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityLogistics
 * @Description ${Description}
 */

/**
 * 送货单物流信息入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSendCommodityLogisticsReq {

    /**
     * 送货单号
     */
    private String deliveryNumber;

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


    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deliveryNumber=").append(deliveryNumber);
        sb.append(", trackingNumber=").append(trackingNumber);
        sb.append(", logisticsCompanyInformation=").append(logisticsCompanyInformation);
        sb.append(", contactWay=").append(contactWay);
        sb.append("]");
        return sb.toString();
    }
}
