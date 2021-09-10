package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditRRsp;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditH
 * @Description ${Description}
 */

/**
 * 开票申请头 入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmTheNumberAuditHReq {


    /**
     * 对账申请号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 开票申请单号集合
     */
    private List<String> theNumberOfHeInvoiceApplicationList;



    /**
     * 开票申请单号集合--更新是否创建应收应付单号集合
     */
    private List<CSrmTheNumberAuditRRsp> updateList;


    /**
     * 应收发票编号
     */
    private String invoiceReceivableNo;


    /**
     * 送货单号
     */
    private String deliveryNumber;

    /**
     * 总税额
     */
    private String sumTax;


    /**
     * 不含税总金额
     */
    private String noTaxMoneyCountMoney;

    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 币种
     */
    private String currency;

    /**
     * 状态
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
     * 公司
     */
    private String companyName;

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
     * 提交日期
     */
    private String updateTime;

    /**
     *  申请行数据
     */
    private List<CSrmTheNumberAuditRReq> reqList;


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
     *  是否已创建应收/付发票(1.未创建2.已创建)
     */
    private Integer isCreated;


    /**
     *  是否已创建开票申请单(0.未创建1.已创建)
     */
    private Integer isOpenTicket;

    /**
     * 收货时间
     */
    private String receivingGoodsDt;


    /**
     * 确认时间
     */
    private String confirmationTime;





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

    public String getSumTax() {
        return sumTax;
    }

    public void setSumTax(String sumTax) {
        this.sumTax = sumTax;
    }

    public String getNoTaxMoneyCountMoney() {
        return noTaxMoneyCountMoney;
    }

    public Integer getIsOpenTicket() {
        return isOpenTicket;
    }

    public List<CSrmTheNumberAuditRRsp> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<CSrmTheNumberAuditRRsp> updateList) {
        this.updateList = updateList;
    }

    public void setIsOpenTicket(Integer isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
    }

    public void setNoTaxMoneyCountMoney(String noTaxMoneyCountMoney) {
        this.noTaxMoneyCountMoney = noTaxMoneyCountMoney;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
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

    public List<CSrmTheNumberAuditRReq> getReqList() {
        return reqList;
    }

    @Override
    public String toString() {
        return "CSrmTheNumberAuditHReq{" +
                "theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", theNumberOfHeInvoiceApplicationList=" + theNumberOfHeInvoiceApplicationList +
                ", updateList=" + updateList +
                ", invoiceReceivableNo='" + invoiceReceivableNo + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", sumTax='" + sumTax + '\'' +
                ", noTaxMoneyCountMoney='" + noTaxMoneyCountMoney + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", taxMoneyCountMoney='" + taxMoneyCountMoney + '\'' +
                ", companyName='" + companyName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", reqList=" + reqList +
                ", operationSign='" + operationSign + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", lineItemNo=" + lineItemNo +
                ", purchaseRequestNo='" + purchaseRequestNo + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", receivingGoodsDtStart='" + receivingGoodsDtStart + '\'' +
                ", receivingGoodsDtStop='" + receivingGoodsDtStop + '\'' +
                ", isCreated=" + isCreated +
                ", isOpenTicket=" + isOpenTicket +
                ", receivingGoodsDt='" + receivingGoodsDt + '\'' +
                ", confirmationTime='" + confirmationTime + '\'' +
                '}';
    }

    public String getInvoiceReceivableNo() {
        return invoiceReceivableNo;
    }

    public void setInvoiceReceivableNo(String invoiceReceivableNo) {
        this.invoiceReceivableNo = invoiceReceivableNo;
    }

    public void setReqList(List<CSrmTheNumberAuditRReq> reqList) {
        this.reqList = reqList;
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

    public List<String> getTheNumberOfHeInvoiceApplicationList() {
        return theNumberOfHeInvoiceApplicationList;
    }

    public void setTheNumberOfHeInvoiceApplicationList(List<String> theNumberOfHeInvoiceApplicationList) {
        this.theNumberOfHeInvoiceApplicationList = theNumberOfHeInvoiceApplicationList;
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


    public String getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(String confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
