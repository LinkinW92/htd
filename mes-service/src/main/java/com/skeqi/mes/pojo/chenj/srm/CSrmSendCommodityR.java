package com.skeqi.mes.pojo.chenj.srm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityR
 * @Description ${Description}
 */

/**
 * 送货单行表
 */
@ApiModel(value = "com-skeqi-pojo-chenj-CSrmSendCommodityR")
public class CSrmSendCommodityR {
    /**
     * 送货单行表id
     */
    @ApiModelProperty(value = "送货单行表id")
    private Integer id;

    /**
     * 送货单号
     */
    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;

    /**
     * 行项目号
     */
    @ApiModelProperty(value = "行项目号")
    private String lineItemNo;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private String count;

    /**
     * 实收数
     */
    @ApiModelProperty(value = "实收数")
    private String paidInNumber;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 采购订单号
     */
    @ApiModelProperty(value = "采购订单号")
    private String purchaseOrderNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

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

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPaidInNumber() {
        return paidInNumber;
    }

    public void setPaidInNumber(String paidInNumber) {
        this.paidInNumber = paidInNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
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
        return "CSrmSendCommodityR{" +
                "id=" + id +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", paidInNumber='" + paidInNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
