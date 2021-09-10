package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @String 2021/6/10
 * @Classname CSrmInvoiceReceivable
 * @Description ${Description}
 */

import com.github.pagehelper.PageInfo;

/**
 * 应收发票表
 */
public class CSrmInvoiceReceivableRsp {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 应收发票编号
     */
    private String invoiceReceivableNo;

    /**
     * 开票申请号
     */
    private String invoiceApplicationNumber;

    /**
     * 开票日期
     */
    private String dateOfIssue;

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
    private String auditTime;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款时间
     */
    private String payTime;

    /**
     * 收款确认人
     */
    private String confirmationOfReceipt;

    /**
     * 确认时间
     */
    private String acknowledgingTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 操作标识(1.创建2.修改3.变更状态)
     */
    private String operationSign;


    /**
     * 行数据
     */
    private PageInfo<CSrmInvoiceReceivableRRsp> invoiceReceivableNoList;


    /**
     * 供应商代码
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
    /**
     * 备注
     */
    private String remark;


    @Override
    public String toString() {
        return "CSrmInvoiceReceivableRsp{" +
                "id=" + id +
                ", invoiceReceivableNo='" + invoiceReceivableNo + '\'' +
                ", invoiceApplicationNumber='" + invoiceApplicationNumber + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", taxInvoiceCode='" + taxInvoiceCode + '\'' +
                ", invoiceGrossAmount='" + invoiceGrossAmount + '\'' +
                ", theInvoiceAmount='" + theInvoiceAmount + '\'' +
                ", status='" + status + '\'' +
                ", creator='" + creator + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", payer='" + payer + '\'' +
                ", payTime='" + payTime + '\'' +
                ", confirmationOfReceipt='" + confirmationOfReceipt + '\'' +
                ", acknowledgingTime='" + acknowledgingTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", operationSign='" + operationSign + '\'' +
                ", invoiceReceivableNoList=" + invoiceReceivableNoList +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", taxMoneyCountMoney='" + taxMoneyCountMoney + '\'' +
                ", sumTax='" + sumTax + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public String getInvoiceReceivableNo() {
        return invoiceReceivableNo;
    }

    public void setInvoiceReceivableNo(String invoiceReceivableNo) {
        this.invoiceReceivableNo = invoiceReceivableNo;
    }

    public String getInvoiceApplicationNumber() {
        return invoiceApplicationNumber;
    }

    public void setInvoiceApplicationNumber(String invoiceApplicationNumber) {
        this.invoiceApplicationNumber = invoiceApplicationNumber;
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

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTheInvoiceAmount(String theInvoiceAmount) {
        this.theInvoiceAmount = theInvoiceAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getConfirmationOfReceipt() {
        return confirmationOfReceipt;
    }

    public void setConfirmationOfReceipt(String confirmationOfReceipt) {
        this.confirmationOfReceipt = confirmationOfReceipt;
    }

    public String getAcknowledgingTime() {
        return acknowledgingTime;
    }

    public void setAcknowledgingTime(String acknowledgingTime) {
        this.acknowledgingTime = acknowledgingTime;
    }

    public String getCreateTime() {
        return createTime;
    }


    public PageInfo<CSrmInvoiceReceivableRRsp> getInvoiceReceivableNoList() {
        return invoiceReceivableNoList;
    }

    public void setInvoiceReceivableNoList(PageInfo<CSrmInvoiceReceivableRRsp> invoiceReceivableNoList) {
        this.invoiceReceivableNoList = invoiceReceivableNoList;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
