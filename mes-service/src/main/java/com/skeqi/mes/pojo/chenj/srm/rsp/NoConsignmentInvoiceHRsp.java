package com.skeqi.mes.pojo.chenj.srm.rsp;

import com.github.pagehelper.PageInfo;


/**
 * @author ChenJ
 * @date 2021/7/2
 * @Classname NoConsignmentInvoiceH
 * @Description 非寄售开票单明细查询  头展示数据
 */
public class NoConsignmentInvoiceHRsp {

    // 返回结果 头
    // 供应商名称 name
    // 不含税总金额  noTaxMoneyCountMoney(查询行数据出来后一条一条计算，将不含税数据统一加起来)
    // 币种  currency
    // 供应商编码 supplier
    // 总税额 sumTax, //
    // 状态 status,
    // 地址 address,
    // 含税总金额  taxMoneyCountMoney //
    // 公司  companyName
    // 库存组织 inventoryOrganization
    // 采购员 buyer
    // 创建人 creator
    // 创建时间 createTime
    // 提交时间 updateTime


    /**
     * 供应商名称
     */
    private String supplierName;



    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 不含税总金额
     */
    private String noTaxMoneyCountMoney;
    /**
     * 币种
     */
    private String currency;
    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 总金额
     */
    private String sumTax;

    /**
     * 状态
     */
    private String status;
    /**
     * 地址
     */
    private String address;
    /**
     * 含税总金额
     */
    private String taxMoneyCountMoney;
    /**
     * 公司
     */
    private String companyName;
    /**
     * 公司编码
     */
    private String companyCode;



    /**
     * 库存组织
     */
    private String shippingAddress;
    /**
     * 采购员
     */
    private String buyer;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 提交时间
     */
    private String updateTime;


    /**
     * 送货单号
     */
    private String deliveryNumber;
    /**
     * 开票申请单号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 行数据
     * @return
     */
    private PageInfo<NoConsignmentInvoiceRRsp> rspList;

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getNoTaxMoneyCountMoney() {
        return noTaxMoneyCountMoney;
    }

    public void setNoTaxMoneyCountMoney(String noTaxMoneyCountMoney) {
        this.noTaxMoneyCountMoney = noTaxMoneyCountMoney;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getSumTax() {
        return sumTax;
    }

    public void setSumTax(String sumTax) {
        this.sumTax = sumTax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxMoneyCountMoney() {
        return taxMoneyCountMoney;
    }

    public void setTaxMoneyCountMoney(String taxMoneyCountMoney) {
        this.taxMoneyCountMoney = taxMoneyCountMoney;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public PageInfo<NoConsignmentInvoiceRRsp> getRspList() {
        return rspList;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setRspList(PageInfo<NoConsignmentInvoiceRRsp> rspList) {
        this.rspList = rspList;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public String toString() {
        return "NoConsignmentInvoiceHRsp{" +
                "supplierName='" + supplierName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", noTaxMoneyCountMoney='" + noTaxMoneyCountMoney + '\'' +
                ", currency='" + currency + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", sumTax='" + sumTax + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", taxMoneyCountMoney='" + taxMoneyCountMoney + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", rspList=" + rspList +
                '}';
    }
}
