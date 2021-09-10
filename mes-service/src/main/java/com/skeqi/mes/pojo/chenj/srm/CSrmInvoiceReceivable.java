package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmInvoiceReceivable
 * @Description ${Description}
 */

/**
 * 应收/付发票头表
 */
public class CSrmInvoiceReceivable {
    /**
     * 应收发票头表id
     */
    private Integer id;

    /**
     * 应收发票编号
     */
    private String invoiceReceivableNo;

    /**
     * 开票日期
     */
    private Date dateOfIssue;

    /**
     * 税务发票代码
     */
    private String taxInvoiceCode;

    /**
     * 发票总额
     */
    private String invoiceGrossAmount;

    /**
     * 发票税额
     */
    private String theInvoiceAmount;

    /**
     * 状态(1.新建2.待审核3.待付款4.待收款5.已完成)
     */
    private String status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 收款确认人
     */
    private String confirmationOfReceipt;

    /**
     * 确认时间
     */
    private Date acknowledgingTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 开票申请表id
     */
    private Integer theNumberId;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 备注
     */
    private String remark;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 含税总额(系统)
     */
    private String taxMoneyCountMoney;

    /**
     * 总税额(系统)
     */
    private String sumTax;

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

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getTaxInvoiceCode() {
        return taxInvoiceCode;
    }

    public void setTaxInvoiceCode(String taxInvoiceCode) {
        this.taxInvoiceCode = taxInvoiceCode;
    }

    public String getInvoiceGrossAmount() {
        return invoiceGrossAmount;
    }

    public void setInvoiceGrossAmount(String invoiceGrossAmount) {
        this.invoiceGrossAmount = invoiceGrossAmount;
    }

    public String getTheInvoiceAmount() {
        return theInvoiceAmount;
    }

    public void setTheInvoiceAmount(String theInvoiceAmount) {
        this.theInvoiceAmount = theInvoiceAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getConfirmationOfReceipt() {
        return confirmationOfReceipt;
    }

    public void setConfirmationOfReceipt(String confirmationOfReceipt) {
        this.confirmationOfReceipt = confirmationOfReceipt;
    }

    public Date getAcknowledgingTime() {
        return acknowledgingTime;
    }

    public void setAcknowledgingTime(Date acknowledgingTime) {
        this.acknowledgingTime = acknowledgingTime;
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

    public Integer getTheNumberId() {
        return theNumberId;
    }

    public void setTheNumberId(Integer theNumberId) {
        this.theNumberId = theNumberId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getSumTax() {
        return sumTax;
    }

    public void setSumTax(String sumTax) {
        this.sumTax = sumTax;
    }
}
