package com.skeqi.mes.pojo.chenj.srm.rsp;

import java.math.BigDecimal;

/**
 * @author ChenJ
 * @date 2021/7/2
 * @Classname NoConsignmentInvoiceH
 * @Description 非寄售开票单明细查询  头展示数据
 */
public class NoConsignmentInvoiceRRsp {
   // 返回结果 行
   // 订单号 orderNumber
   // 物料编码  materialCode
   // 物料名称  materialName
   // 单位  unit
   // 可开票数量 count
   // 不含税单价 noUnitPrice
   // 不含税金额 noTaxMoneyMoney
   // 税率 taxRate
   // 税额 tax
   // 含税单价 unitPrice
   // 含税金额 unitMoney





    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 送货单号
     */
    private String deliveryNumber;

    /**
     * 可开票数量
     */
    private String paidInNumber;
    /**
     * 不含税单价
     */
    private String noTaxMoney;


    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司编名称
     */
    private String companyName;


    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 物料名称
     */
    private String materialName;
    /**
     * 单位
     */
    private String unit;
    /**
     * 可开票数量
     */
    private String count;

    /**
     * 不含税单价
     */
    private String noUnitPrice;

    /**
     * 不含税金额
     */
    private BigDecimal noTaxMoneyMoney;
    /**
     * 税率
     */
    private String taxRate;
    /**
     * 税额
     */
    private BigDecimal tax;
    /**
     * 含税单价
     */
    private String unitPrice;
    /**
     * 含税金额
     */
    private BigDecimal unitMoney;

    /**
     * 行号
     * @return
     */
    private String lineItemNo;

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
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

    public void setNoUnitPrice(String noUnitPrice) {
        this.noUnitPrice = noUnitPrice;
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

    public BigDecimal getNoTaxMoneyMoney() {
        return noTaxMoneyMoney;
    }

    public void setNoTaxMoneyMoney(BigDecimal noTaxMoneyMoney) {
        this.noTaxMoneyMoney = noTaxMoneyMoney;
    }

    @Override
    public String toString() {
        return "NoConsignmentInvoiceRRsp{" +
                "orderNumber='" + orderNumber + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", paidInNumber='" + paidInNumber + '\'' +
                ", noTaxMoney='" + noTaxMoney + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", unit='" + unit + '\'' +
                ", count='" + count + '\'' +
                ", noUnitPrice='" + noUnitPrice + '\'' +
                ", noTaxMoneyMoney=" + noTaxMoneyMoney +
                ", taxRate='" + taxRate + '\'' +
                ", tax=" + tax +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitMoney=" + unitMoney +
                ", lineItemNo='" + lineItemNo + '\'' +
                '}';
    }
}
