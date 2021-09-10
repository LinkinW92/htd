package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityR
 * @Description ${Description}
 */

/**
 * 送货单行入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSendCommodityRReq {

    /**
     * 行项目号
     */
    @ApiModelProperty(value = "行项目号")
    private String lineItemNo;

    /**
     * 送货单号
     */
    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;

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
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updateTime;


    /**
     * 采购单行号
     */
    @ApiModelProperty(value = "采购单行号")
    private String purLineItemNo;


    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;


    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;


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

    public void setPurchaseOrderNo(String purchaseOrderNo) {

        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPurLineItemNo() {
        return purLineItemNo;
    }

    public void setPurLineItemNo(String purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "CSrmSendCommodityRReq{" +
                "lineItemNo='" + lineItemNo + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", paidInNumber='" + paidInNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", purLineItemNo='" + purLineItemNo + '\'' +
                ", materialName='" + materialName + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
