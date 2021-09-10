package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmPurchaseOrderH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 采购订单头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmPurchaseOrderHReq {

    /**
     * 采购订单头表id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 送货单
     */
    private String deliveryNumber;

    /**
     * 订单号集合
     */
    private List<CSrmSendCommodityRReq> orderNumberList;

    /**
     * 合同编码
     */
    private String contractNo;

    /**
     * 订单类型(1.办公用品采购单)
     */
    private String orderType;

    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;

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
     * 地址
     */
    private String deliveryAddress;


    /**
     * 币种(1.CNY2.USD)
     */
    private String currency;

    /**
     * 状态(1.新建2.待审批3.待确认4.已确认5.已拒绝)
     */
    private String status;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


    /**
     * 付款条件
     */
    private String paymentClause;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门
     */
    private String department;

    /**
     *  采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;


    /**
     * 采购订单行入参
     */
    List<CSrmPurchaseOrderRReq> purList;


    /**
     * 操作标识(1.创建2.修改3.状态变更)
     */
    private String operationSign;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderType() {
        return orderType;
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

    public List<CSrmSendCommodityRReq> getOrderNumberList() {
        return orderNumberList;
    }

    public void setOrderNumberList(List<CSrmSendCommodityRReq> orderNumberList) {
        this.orderNumberList = orderNumberList;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCurrency() {
        return currency;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
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

    public List<CSrmPurchaseOrderRReq> getPurList() {
        return purList;
    }

    public void setPurList(List<CSrmPurchaseOrderRReq> purList) {
        this.purList = purList;
    }


    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
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

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderHReq{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", orderNumberList=" + orderNumberList +
                ", contractNo='" + contractNo + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", creator='" + creator + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", company='" + company + '\'' +
                ", companyName='" + companyName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", paymentClause='" + paymentClause + '\'' +
                ", remark='" + remark + '\'' +
                ", department='" + department + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", purList=" + purList +
                ", operationSign='" + operationSign + '\'' +
                '}';
    }
}
