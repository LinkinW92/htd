package com.skeqi.mes.pojo.chenj.srm;


import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmPurchaseOrderH
 * @Description ${Description}
 */

/**
 * 采购订单头表
 */
public class CSrmPurchaseOrderH {
    /**
     * 采购订单头表id
     */
    private Integer id;

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
     * 公司
     */
    private String company;

    /**
     * 供应商编码
     */
    private String supplierCode;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 采购部门
     */
    private String departmentCode;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 交货地址
     */
    private String deliveryAddress;

    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;


    /**
     * 是否已创建送货单(0.未创建1.已创建)
     */
    private Integer isOpenTicket;


    public Integer getId() {
        return id;
    }

    public Integer getIsOpenTicket() {
        return isOpenTicket;
    }

    public void setIsOpenTicket(Integer isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
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

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderH{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderType='" + orderType + '\'' +
                ", creator='" + creator + '\'' +
                ", buyer='" + buyer + '\'' +
                ", company='" + company + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentClause='" + paymentClause + '\'' +
                ", remark='" + remark + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", departmentCode='" + departmentCode + '\'' +
                ", isDelete=" + isDelete +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", isOpenTicket='" + isOpenTicket + '\'' +
                '}';
    }
}
