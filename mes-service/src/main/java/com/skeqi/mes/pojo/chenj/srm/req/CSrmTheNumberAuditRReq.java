package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditR
 * @Description ${Description}
 */

/**
 * 开票申请行表 入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmTheNumberAuditRReq {


    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private int id;


    /**
     * 采购订单号
     */
    @ApiModelProperty(value = "采购订单号")
    private String orderNumber;
    /**
     * 采购订单号
     */
    @ApiModelProperty(value = "可开票数量(实收数)")
    private String paidInNumber;
    /**
     * 采购订单号
     */
    @ApiModelProperty(value = "不含税金额")
    private String noTaxMoney;


    /**
     * 送货单号
     */
    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;


    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private String lineItemNo;

    /**
     * 开票单号
     */
    @ApiModelProperty(value = "开票单号")
    private String theNumberOfHeInvoiceApplication;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;

//    /**
//     * 通用名
//     */
//    @ApiModelProperty(value = "通用名")
//    private String commonName;
//
//    /**
//     * 供应商料号
//     */
//    @ApiModelProperty(value = "供应商料号")
//    private String supplierNumber;
//
//    /**
//     * 供应商料号描述
//     */
//    @ApiModelProperty(value = "供应商料号描述")
//    private String supplierNumberDescribe;
//
//    /**
//     * 规则型号
//     */
//    @ApiModelProperty(value = "规则型号")
//    private String rulesOfTheModel;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 可开票数量
     */
    @ApiModelProperty(value = "可开票数量")
    private String count;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private String noUnitPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private String noTaxMoneyMoney;

    /**
     * 税率(%)
     */
    @ApiModelProperty(value = "税率(%)")
    private String taxRate;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private String tax;

    /**
     * 含税单价
     */
    @ApiModelProperty(value = "含税单价")
    private String unitPrice;

    /**
     * 含税金额
     */
    @ApiModelProperty(value = "含税金额")
    private String unitMoney;

//    /**
//     * 行备注
//     */
//    @ApiModelProperty(value = "行备注")
//    private String rowRemark;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNoUnitPrice() {
        return noUnitPrice;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public void setNoUnitPrice(String noUnitPrice) {
        this.noUnitPrice = noUnitPrice;
    }

    public String getNoTaxMoneyMoney() {
        return noTaxMoneyMoney;
    }

    public void setNoTaxMoneyMoney(String noTaxMoneyMoney) {
        this.noTaxMoneyMoney = noTaxMoneyMoney;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTax() {
        return tax;
    }

    public String getPaidInNumber() {
        return paidInNumber;
    }

    public void setPaidInNumber(String paidInNumber) {
        this.paidInNumber = paidInNumber;
    }

    public String getNoTaxMoney() {
        return noTaxMoney;
    }

    public void setNoTaxMoney(String noTaxMoney) {
        this.noTaxMoney = noTaxMoney;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitMoney() {
        return unitMoney;
    }

    public void setUnitMoney(String unitMoney) {
        this.unitMoney = unitMoney;
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
        return "CSrmTheNumberAuditRReq{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", paidInNumber='" + paidInNumber + '\'' +
                ", noTaxMoney='" + noTaxMoney + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", unit='" + unit + '\'' +
                ", count='" + count + '\'' +
                ", noUnitPrice='" + noUnitPrice + '\'' +
                ", noTaxMoneyMoney='" + noTaxMoneyMoney + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", tax='" + tax + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitMoney='" + unitMoney + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
