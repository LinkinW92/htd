package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @String 2021/6/10
 * @Classname CSrmInvoiceReceivable
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 应收发票头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmInvoiceReceivableReq {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 采购订单号
     */
    private String orderNumber;


    /**
     * 采购订单行号
     */
    private List<String> lineItemNo;

    /**
     * 应收发票编号
     */
    private String invoiceReceivableNo;

    /**
     * 对账申请号
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
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 含税总额(系统)
     */
    private String taxMoneyCountMoney;
    /**
     * 供应商地址
     */
    private String address;
    /**
     * 税额(系统)
     */
    private String sumTax;
    /**
     * 备注
     */
    private String remark;


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
     * 修改时间
     */
    private String updateTime;

    /**
     * 操作标识(1.创建2.修改3.变更状态)
     */
    private String operationSign;


    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    /**
     * 行数据
     */
    private List<CSrmInvoiceReceivableRReq> invoiceReceivableNoList;


    /**
     * OA审批回调类型(通过,驳回,撤销)
     */
    private String name;

    /**
     * 服务类型(1.K3(10000))
     */
    private Integer serviceType;


	/**
	 * 是否推送服务开关(true：推送，false：不推送)
	 */
	private Boolean push;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
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

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getTaxInvoiceCode() {
        return taxInvoiceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(List<String> lineItemNo) {
        this.lineItemNo = lineItemNo;
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

    public String getTaxMoneyCountMoney() {
        return taxMoneyCountMoney;
    }

    public void setTaxMoneyCountMoney(String taxMoneyCountMoney) {
        this.taxMoneyCountMoney = taxMoneyCountMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSumTax() {
        return sumTax;
    }

    public void setSumTax(String sumTax) {
        this.sumTax = sumTax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<CSrmInvoiceReceivableRReq> getInvoiceReceivableNoList() {
        return invoiceReceivableNoList;
    }

    public void setInvoiceReceivableNoList(List<CSrmInvoiceReceivableRReq> invoiceReceivableNoList) {
        this.invoiceReceivableNoList = invoiceReceivableNoList;
    }

	public Boolean getPush() {
		return push;
	}

	public void setPush(Boolean push) {
		this.push = push;
	}

	@Override
	public String toString() {
		return "CSrmInvoiceReceivableReq{" +
			"id=" + id +
			", orderNumber='" + orderNumber + '\'' +
			", lineItemNo=" + lineItemNo +
			", invoiceReceivableNo='" + invoiceReceivableNo + '\'' +
			", invoiceApplicationNumber='" + invoiceApplicationNumber + '\'' +
			", dateOfIssue='" + dateOfIssue + '\'' +
			", taxInvoiceCode='" + taxInvoiceCode + '\'' +
			", invoiceGrossAmount='" + invoiceGrossAmount + '\'' +
			", theInvoiceAmount='" + theInvoiceAmount + '\'' +
			", status='" + status + '\'' +
			", supplierCode='" + supplierCode + '\'' +
			", supplierName='" + supplierName + '\'' +
			", taxMoneyCountMoney='" + taxMoneyCountMoney + '\'' +
			", address='" + address + '\'' +
			", sumTax='" + sumTax + '\'' +
			", remark='" + remark + '\'' +
			", creator='" + creator + '\'' +
			", auditor='" + auditor + '\'' +
			", auditTime='" + auditTime + '\'' +
			", payer='" + payer + '\'' +
			", payTime='" + payTime + '\'' +
			", confirmationOfReceipt='" + confirmationOfReceipt + '\'' +
			", acknowledgingTime='" + acknowledgingTime + '\'' +
			", createTime='" + createTime + '\'' +
			", updateTime='" + updateTime + '\'' +
			", operationSign='" + operationSign + '\'' +
			", pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", invoiceReceivableNoList=" + invoiceReceivableNoList +
			", name='" + name + '\'' +
			", serviceType=" + serviceType +
			", push=" + push +
			'}';
	}
}
