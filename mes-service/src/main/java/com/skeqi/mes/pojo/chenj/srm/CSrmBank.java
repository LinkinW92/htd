package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmBank
 * @Description ${Description}
 */

/**
 * 供应商银行信息表
 */
public class CSrmBank {
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



    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCollectorTelephoneNumber(String collectorTelephoneNumber) {
        this.collectorTelephoneNumber = collectorTelephoneNumber;
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

    @Override
    public String toString() {
        return "CSrmBank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", accountTitle='" + accountTitle + '\'' +
                ", receiverMailbox='" + receiverMailbox + '\'' +
                ", collectorTelephoneNumber='" + collectorTelephoneNumber + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
