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
public class CSrmPurchaseOrderHFindReq {

    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 合同编码
     */
    private String contractNo;

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
     * 公司
     */
    private String company;

    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 状态(1.新建2.待审批3.待确认4.已确认5.已拒绝)
     */
    private List<String> status;

    /**
     * 付款方式
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
     * 部门
     */
    private String department;

    /**
     * 订单行数据
     *
     * @return
     */
    private List<CSrmPurchaseOrderHFindReq> orderNumberList;


    /**
     * 采购类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;


    /**
     * 是否已创建送货单(0.未创建1.已创建)
     */
    private Integer isOpenTicket;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
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

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
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

    public String getPaymentClause() {
        return paymentClause;
    }

    public void setPaymentClause(String paymentClause) {
        this.paymentClause = paymentClause;
    }

    public String getDepartment() {
        return department;
    }

    public List<CSrmPurchaseOrderHFindReq> getOrderNumberList() {
        return orderNumberList;
    }

    public void setOrderNumberList(List<CSrmPurchaseOrderHFindReq> orderNumberList) {
        this.orderNumberList = orderNumberList;
    }

    public Integer getIsOpenTicket() {
        return isOpenTicket;
    }

    public void setIsOpenTicket(Integer isOpenTicket) {
        this.isOpenTicket = isOpenTicket;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderHFindReq{" +
                "orderNumber='" + orderNumber + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", creator='" + creator + '\'' +
                ", buyer='" + buyer + '\'' +
                ", company='" + company + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", status=" + status +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", paymentClause='" + paymentClause + '\'' +
                ", department='" + department + '\'' +
                ", orderNumberList=" + orderNumberList +
                ", buyerType='" + buyerType + '\'' +
                ", isOpenTicket=" + isOpenTicket +
                '}';
    }
}
