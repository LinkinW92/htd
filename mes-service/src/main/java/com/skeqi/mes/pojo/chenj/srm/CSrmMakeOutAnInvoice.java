package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmMakeOutAnInvoice
 * @Description ${Description}
 */

/**
 * 开票申请表
 */
public class CSrmMakeOutAnInvoice {
    /**
     * 开票申请表id
     */
    private Integer id;

    /**
     * 开票申请单号
     */
    private String theNumberOfHeInvoiceApplication;

    /**
     * 送货单号
     */
    private String deliveryNumber;

    /**
     * 状态(1.新建2.待确认3.已完成)
     */
    private String status;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 公司编码
     */
    private String companyCode;


    /**
     * 确认人
     */
    private String confirmationP;

    /**
     * 确认时间
     */
    private Date confirmationTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否已创建应收/付发票(0.未创建 1.已创建)
     */
    private Integer isCreated;

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

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getConfirmationP() {
        return confirmationP;
    }

    public void setConfirmationP(String confirmationP) {
        this.confirmationP = confirmationP;
    }

    public Date getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(Date confirmationTime) {
        this.confirmationTime = confirmationTime;
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
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(Integer isCreated) {
        this.isCreated = isCreated;
    }

    @Override
    public String toString() {
        return "CSrmMakeOutAnInvoice{" +
                "id=" + id +
                ", theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", status='" + status + '\'' +
                ", supplier='" + supplier + '\'' +
                ", creator='" + creator + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", confirmationP='" + confirmationP + '\'' +
                ", confirmationTime=" + confirmationTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isCreated=" + isCreated +
                '}';
    }
}
