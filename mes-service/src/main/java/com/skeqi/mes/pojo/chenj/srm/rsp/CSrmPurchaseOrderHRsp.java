package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmPurchaseOrderH
 * @Description ${Description}
 */

import com.github.pagehelper.PageInfo;

/**
 * 采购订单头表
 */
public class CSrmPurchaseOrderHRsp {


    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单类型(1.办公用品采购单)
     */
    private String orderType;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 采购员名称
     */
    private String buyerName;

    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;

    /**
     * 公司
     */
    private String company;

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
     * 币种(1.CNY2.USD)
     */
    private String currency;

    /**
     * 状态(1.新建2.待审批3.待确认4.已确认)
     */
    private String status;

    /**
     * 付款方式(1.银行卡支付2.现金支付)
     */
    private String paymentMethod;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 付款条件
     */
    private String paymentClause;

    /**
     * 备注
     */
    private String remark;
    /**
     * 合同编码
     */
    private String contractNo;

    /**
     * 部门名称
     */
    private String dmName;

    /**
     * 行数据
     */
    PageInfo<CSrmPurchaseOrderRRsp> lineItemNoList;

    /**
     * 操作标识(1.创建2.修改3.状态变更)
     */
    private String operationSign;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 交货地址
     */
    private String deliveryAddress;

    public String getOrderNumber() {
        return orderNumber;
    }

    public PageInfo<CSrmPurchaseOrderRRsp> getLineItemNoList() {
        return lineItemNoList;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setLineItemNoList(PageInfo<CSrmPurchaseOrderRRsp> lineItemNoList) {
        this.lineItemNoList = lineItemNoList;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
        this.creator = buyer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentClause() {
        return paymentClause;
    }

    public void setPaymentClause(String paymentClause) {
        this.paymentClause = paymentClause;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderHRsp{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderType='" + orderType + '\'' +
                ", creator='" + creator + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", company='" + company + '\'' +
                ", companyName='" + companyName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", paymentClause='" + paymentClause + '\'' +
                ", remark='" + remark + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", dmName='" + dmName + '\'' +
                ", lineItemNoList=" + lineItemNoList +
                ", operationSign='" + operationSign + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
