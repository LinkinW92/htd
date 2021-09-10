package com.skeqi.mes.pojo.chenj.srm.rsp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditR
 * @Description ${Description}
 */

/**
 * 开票申请审核行表(存储提交非寄售开票单维护绑定数据) 出参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmTheNumberAuditRRsp {


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

    /**
     * 通用名
     */
    @ApiModelProperty(value = "通用名")
    private String commonName;

    /**
     * 供应商料号
     */
    @ApiModelProperty(value = "供应商料号")
    private String supplierNumber;

    /**
     * 供应商料号描述
     */
    @ApiModelProperty(value = "供应商料号描述")
    private String supplierNumberDescribe;

    /**
     * 规则型号
     */
    @ApiModelProperty(value = "规则型号")
    private String rulesOfTheModel;

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
    private BigDecimal tax;

    /**
     * 含税单价
     */
    @ApiModelProperty(value = "含税单价")
    private String unitPrice;

    /**
     * 含税金额
     */
    @ApiModelProperty(value = "含税金额")
    private BigDecimal unitMoney;

    /**
     * 行备注
     */
    @ApiModelProperty(value = "行备注")
    private String rowRemark;

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
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;


    /**
     * 价税合计
     */
    @ApiModelProperty(value = "价税合计")
    private String priceTaxSum;


    /**
     * 订单行号
     */
    @ApiModelProperty(value = "订单行号")
    private String purLineItemNo;

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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierNumberDescribe() {
        return supplierNumberDescribe;
    }

    public void setSupplierNumberDescribe(String supplierNumberDescribe) {
        this.supplierNumberDescribe = supplierNumberDescribe;
    }

    public String getRulesOfTheModel() {
        return rulesOfTheModel;
    }

    public void setRulesOfTheModel(String rulesOfTheModel) {
        this.rulesOfTheModel = rulesOfTheModel;
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

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitMoney() {
        return unitMoney;
    }

    public void setUnitMoney(BigDecimal unitMoney) {
        this.unitMoney = unitMoney;
    }

    public String getRowRemark() {
        return rowRemark;
    }

    public void setRowRemark(String rowRemark) {
        this.rowRemark = rowRemark;
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

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPriceTaxSum() {
        return priceTaxSum;
    }

    public void setPriceTaxSum(String priceTaxSum) {
        this.priceTaxSum = priceTaxSum;
    }

    public String getPurLineItemNo() {
        return purLineItemNo;
    }

    public void setPurLineItemNo(String purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
    }

    @Override
    public String toString() {
        return "CSrmTheNumberAuditRRsp{" +
                "lineItemNo='" + lineItemNo + '\'' +
                ", theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", commonName='" + commonName + '\'' +
                ", supplierNumber='" + supplierNumber + '\'' +
                ", supplierNumberDescribe='" + supplierNumberDescribe + '\'' +
                ", rulesOfTheModel='" + rulesOfTheModel + '\'' +
                ", unit='" + unit + '\'' +
                ", count='" + count + '\'' +
                ", noUnitPrice='" + noUnitPrice + '\'' +
                ", noTaxMoneyMoney='" + noTaxMoneyMoney + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", tax=" + tax +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitMoney=" + unitMoney +
                ", rowRemark='" + rowRemark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", specification='" + specification + '\'' +
                ", priceTaxSum='" + priceTaxSum + '\'' +
                ", purLineItemNo='" + purLineItemNo + '\'' +
                '}';
    }
}
