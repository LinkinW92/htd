package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmTheNumberAuditH
 * @Description ${Description}
 */

/**
 * 开票申请头表
 */
public class CSrmTheNumberAuditH {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 对账申请单号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 供应商名称
     */
    private String supplierName;

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
     * 总税额
     */
    private String sumTax;

    /**
     * 状态(1.新建2.提交3.完成)
     */
    private String status;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 含税总金额
     */
    private String taxMoneyCountMoney;

    /**
     * 供应商备注
     */
    private String supplierRemark;

    /**
     * 公司
     */
    private String companyName;

    /**
     * 业务实体
     */
    private String businessEntity;

    /**
     * 采购组织
     */
    private String purOrganization;

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
    private Date createTime;

    /**
     * 提交日期
     */
    private Date updateTime;

    /**
     * 出票方
     */
    private String theDrawerSide;

    /**
     * 开票主题
     */
    private String makeTheme;

    /**
     * 期初余额
     */
    private String theBalance;

    /**
     * 本期增加额
     */
    private String currentIncrease;

    /**
     * 本期减少额
     */
    private String currentReduction;

    /**
     * 期末余额
     */
    private String endingBalance;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 是否已创建应收/付发票(0.未创建1.已创建)
     */
    private Integer isCreated;

    /**
     * 送货单号
     */
    private String deliveryNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxMoneyCountMoney() {
        return taxMoneyCountMoney;
    }

    public void setTaxMoneyCountMoney(String taxMoneyCountMoney) {
        this.taxMoneyCountMoney = taxMoneyCountMoney;
    }

    public String getSupplierRemark() {
        return supplierRemark;
    }

    public void setSupplierRemark(String supplierRemark) {
        this.supplierRemark = supplierRemark;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getPurOrganization() {
        return purOrganization;
    }



    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setPurOrganization(String purOrganization) {
        this.purOrganization = purOrganization;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public String getTheDrawerSide() {
        return theDrawerSide;
    }

    public void setTheDrawerSide(String theDrawerSide) {
        this.theDrawerSide = theDrawerSide;
    }

    public String getMakeTheme() {
        return makeTheme;
    }

    public void setMakeTheme(String makeTheme) {
        this.makeTheme = makeTheme;
    }

    public String getTheBalance() {
        return theBalance;
    }

    public void setTheBalance(String theBalance) {
        this.theBalance = theBalance;
    }

    public String getCurrentIncrease() {
        return currentIncrease;
    }

    public void setCurrentIncrease(String currentIncrease) {
        this.currentIncrease = currentIncrease;
    }

    public String getCurrentReduction() {
        return currentReduction;
    }

    public void setCurrentReduction(String currentReduction) {
        this.currentReduction = currentReduction;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsCreated() {
        return isCreated;
    }

    public String getNoTaxMoneyCountMoney() {
        return noTaxMoneyCountMoney;
    }

    public void setNoTaxMoneyCountMoney(String noTaxMoneyCountMoney) {
        this.noTaxMoneyCountMoney = noTaxMoneyCountMoney;
    }

    public void setIsCreated(Integer isCreated) {
        this.isCreated = isCreated;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }
}
