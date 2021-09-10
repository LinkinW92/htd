package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @String 2021/6/10
 * @Classname CSrmMakeOutAnInvoice
 * @Description ${Description}
 */


/**
 * 开票申请表
 */
public class CSrmMakeOutAnInvoiceRsp {

    /**
     * 开票申请单号
     */
    private String theNumberOfHeInvoiceApplication;


    /**
     * 送货单号
     */
    private String deliveryNumber;


    /**
     * 供应商名称
     * @return
     */
    private String supplierName;

    /**
     * 供应商地址
     * @return
     */
    private String address;

    /**
     * 含税金额
     * @return
     */
    private String taxMoney;


    /**
     * 不含税金额
     * @return
     */
    private String noTaxMoney;

    public String getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(String taxMoney) {
        this.taxMoney = taxMoney;
    }

    public String getNoTaxMoney() {
        return noTaxMoney;
    }

    public void setNoTaxMoney(String noTaxMoney) {
        this.noTaxMoney = noTaxMoney;
    }

    /**
     * 公司名称
     * @return
     */
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    /**
     * 供应商编码
     */
    private String supplierCode;


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
     * 更新时间
     */
    private String updateTime;

    /**
     *  是否已创建应收/付发票(0.未创建 1.已创建)
     */
    private Integer isCreated;


    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public Integer getIsCreated() {
        return isCreated;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsCreated(Integer isCreated) {
        this.isCreated = isCreated;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CSrmMakeOutAnInvoiceRsp{" +
                "theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", taxMoney='" + taxMoney + '\'' +
                ", noTaxMoney='" + noTaxMoney + '\'' +
                ", companyName='" + companyName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", status='" + status + '\'' +
                ", creator='" + creator + '\'' +
                ", confirmationP='" + confirmationP + '\'' +
                ", confirmationTime='" + confirmationTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isCreated=" + isCreated +
                ", operationSign='" + operationSign + '\'' +
                '}';
    }
}
