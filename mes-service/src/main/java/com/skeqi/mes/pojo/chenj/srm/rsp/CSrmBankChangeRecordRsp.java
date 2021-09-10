package com.skeqi.mes.pojo.chenj.srm.rsp;

 /**
 * @author ChenJ
 * @date 2021/7/21
 * @Classname CSrmBankChangeRecord
 * @Description ${Description}
 */

/**
    * 供应商银行信息变更记录表
    */
public class CSrmBankChangeRecordRsp {
    /**
    * 供应商银行信息id
    */
    private Integer id;

    /**
    * 开户银行名称
    */
    private String bankName;

    /**
    * 银行账号
    */
    private String bankAccount;

    /**
    * 供应商代码
    */
    private String supplierCode;

    /**
    * 状态(0.已保存1.变更中2.已变更3.变更失败)
    */
    private String status;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 修改时间
    */
    private String updateTime;

    /**
    * 供应商id
    */
    private Integer supplierId;

    /**
    * 逻辑删除(0.未删除1.已删除)
    */
    private Boolean isDelete;

    /**
    * 账户名称
    */
    private String accountTitle;

    /**
    * 收票人邮箱
    */
    private String receiverMailbox;

    /**
    * 收票人电话
    */
    private String collectorTelephoneNumber;

    /**
    * 公司编码
    */
    private String companyCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getReceiverMailbox() {
        return receiverMailbox;
    }

    public void setReceiverMailbox(String receiverMailbox) {
        this.receiverMailbox = receiverMailbox;
    }

    public String getCollectorTelephoneNumber() {
        return collectorTelephoneNumber;
    }

    public void setCollectorTelephoneNumber(String collectorTelephoneNumber) {
        this.collectorTelephoneNumber = collectorTelephoneNumber;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", accountTitle=").append(accountTitle);
        sb.append(", receiverMailbox=").append(receiverMailbox);
        sb.append(", collectorTelephoneNumber=").append(collectorTelephoneNumber);
        sb.append(", companyCode=").append(companyCode);
        sb.append("]");
        return sb.toString();
    }
}
