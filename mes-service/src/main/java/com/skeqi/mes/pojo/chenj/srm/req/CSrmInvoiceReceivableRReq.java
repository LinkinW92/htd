package com.skeqi.mes.pojo.chenj.srm.req;

/**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmInvoiceReceivableR
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 应收/应付发票行表入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmInvoiceReceivableRReq {
    /**
     * 应收发票行表id
     */
    private Integer id;

    /**
     * 应收发票编号
     */
    private String invoiceReceivableNo;

    /**
     * 行号
     */
    private String lineItemNo;

    /**
     * 开票申请单号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 通用名
     */
    private String commonName;

    /**
     * 供应商料号
     */
    private String supplierNumber;

    /**
     * 供应商料号描述
     */
    private String supplierNumberDescribe;

    /**
     * 规则型号
     */
    private String rulesOfTheModel;

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
    private String noTaxMoneyMoney;

    /**
     * 税率(%)
     */
    private String taxRate;

    /**
     * 税额
     */
    private String tax;

    /**
     * 含税单价
     */
    private String unitPrice;

    /**
     * 价税合计
     */
    private String priceTaxSum;


    /**
     * 含税金额
     */
    private String unitMoney;

    /**
     * 行备注
     */
    private String rowRemark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 采购订单号
     */
    private String orderNumber;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 送货单号
     */
    private String deliveryNumber;

    /**
     * 实收数(可开票数量)
     */
    private String paidInNumber;

    /**
     * 不含税金额
     */
    private String noTaxMoney;

    /**
     * 订单行号
     */
    private String purLineItemNo;

    /**
     * 规格
     */
    private String specification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceReceivableNo() {
        return invoiceReceivableNo;
    }

    public void setInvoiceReceivableNo(String invoiceReceivableNo) {
        this.invoiceReceivableNo = invoiceReceivableNo;
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getPurLineItemNo() {
        return purLineItemNo;
    }

    public void setPurLineItemNo(String purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public String getTax() {
        return tax;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public String getPriceTaxSum() {
        return priceTaxSum;
    }

    public void setPriceTaxSum(String priceTaxSum) {
        this.priceTaxSum = priceTaxSum;
    }

    @Override
    public String toString() {
        return "CSrmInvoiceReceivableRReq{" +
                "id=" + id +
                ", invoiceReceivableNo='" + invoiceReceivableNo + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
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
                ", tax='" + tax + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", priceTaxSum='" + priceTaxSum + '\'' +
                ", unitMoney='" + unitMoney + '\'' +
                ", rowRemark='" + rowRemark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", isDelete=" + isDelete +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", paidInNumber='" + paidInNumber + '\'' +
                ", noTaxMoney='" + noTaxMoney + '\'' +
                ", purLineItemNo='" + purLineItemNo + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
