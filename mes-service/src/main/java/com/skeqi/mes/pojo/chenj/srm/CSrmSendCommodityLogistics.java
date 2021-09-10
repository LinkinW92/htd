package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityLogistics
 * @Description ${Description}
 */

/**
 * 送货单物流信息表
 */
public class CSrmSendCommodityLogistics {
    /**
     * 送货单物流信息表id
     */
    private Integer id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deliveryNumber=").append(deliveryNumber);
        sb.append(", trackingNumber=").append(trackingNumber);
        sb.append(", logisticsCompanyInformation=").append(logisticsCompanyInformation);
        sb.append(", contactWay=").append(contactWay);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
