package com.skeqi.mes.pojo.chenj.srm.rsp;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompany
 * @Description ${Description}
 */

/**
 * 响应企业认证数据
 */
public class CSrmSupplierEnterpriseRsp {

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 开户银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类别(1.中草药材、2.棉花)
     */
    private String productType;

    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证)
     */
    private String status;
    /**
     * 所处阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String inPhase;

    /**
     * 账号
     * @return
     */
   private String account;




    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInPhase() {
        return inPhase;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setInPhase(String inPhase) {
        this.inPhase = inPhase;
    }

    @Override
    public String toString() {
        return "CSrmSupplierEnterpriseRsp{" +
                "supplierCode='" + supplierCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", status='" + status + '\'' +
                ", inPhase='" + inPhase + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
