package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @String 2021/6/10
 * @Classname CSrmMakeOutAnInvoice
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 开票申请表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmMakeOutAnInvoiceReq {

    /**
     * 开票申请单号
     */
    private String theNumberOfHeInvoiceApplication;


    /**
     * 送货单号
     */
    private String deliveryNumber;


    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 行项目号
     */
    private List<Object> lineItemNo;


    /**
     * 采购申请号
     */
    private String purchaseRequestNo;


    /**
     * 公司编码
     */
    private String companyCode;


    /**
     * 公司名称
     */
    private String companyName;


    /**
     * 供应商编码
     */
    private String supplierCode;


    /**
     * 供应商名称
     */
    private String supplierName;


    /**
     * 收货时间从
     */
    private String receivingGoodsDtStart;


    /**
     * 收货时间至
     */
    private String receivingGoodsDtStop;



    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;


    /**
     *  是否已创建应收/付发票(1.未创建2.已创建)
     */
     private Integer isCreated;

    /**
     * 收货时间
     */
    private String receivingGoodsDt;


    /**
     * 状态(1.新建2.待确认3.已完成)
     */
    private String status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 确认人
     */
    private String confirmationP;

    /**
     * 确认时间
     */
    private String confirmationTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 操作标识(1.创建2.修改3.变更状态)
     */
    private String operationSign;

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Object> getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(List<Object> lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getPurchaseRequestNo() {
        return purchaseRequestNo;
    }

    public void setPurchaseRequestNo(String purchaseRequestNo) {
        this.purchaseRequestNo = purchaseRequestNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getReceivingGoodsDtStart() {
        return receivingGoodsDtStart;
    }

    public void setReceivingGoodsDtStart(String receivingGoodsDtStart) {
        this.receivingGoodsDtStart = receivingGoodsDtStart;
    }

    public String getReceivingGoodsDtStop() {
        return receivingGoodsDtStop;
    }

    public void setReceivingGoodsDtStop(String receivingGoodsDtStop) {
        this.receivingGoodsDtStop = receivingGoodsDtStop;
    }

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

    public Integer getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(Integer isCreated) {
        this.isCreated = isCreated;
    }

    public String getReceivingGoodsDt() {
        return receivingGoodsDt;
    }

    public void setReceivingGoodsDt(String receivingGoodsDt) {
        this.receivingGoodsDt = receivingGoodsDt;
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

    public String getConfirmationP() {
        return confirmationP;
    }

    public void setConfirmationP(String confirmationP) {
        this.confirmationP = confirmationP;
    }

    public String getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(String confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    @Override
    public String toString() {
        return "CSrmMakeOutAnInvoiceReq{" +
                "theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", lineItemNo=" + lineItemNo +
                ", purchaseRequestNo='" + purchaseRequestNo + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", receivingGoodsDtStart='" + receivingGoodsDtStart + '\'' +
                ", receivingGoodsDtStop='" + receivingGoodsDtStop + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", isCreated=" + isCreated +
                ", receivingGoodsDt='" + receivingGoodsDt + '\'' +
                ", status='" + status + '\'' +
                ", creator='" + creator + '\'' +
                ", confirmationP='" + confirmationP + '\'' +
                ", confirmationTime='" + confirmationTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", operationSign='" + operationSign + '\'' +
                '}';
    }
}
