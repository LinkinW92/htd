package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/21
 * @Classname CSrmSupplierChangeRecord
 * @Description ${Description}
 */

/**
 * 供应商变更记录表
 */
public class CSrmSupplierChangeRecord {
    /**
     * 供应商变更记录表id
     */
    private Integer id;

    /**
     * 供应商代码
     */
    private String supplierCode;

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
     * 状态(0.已保存1.变更中2.已变更3.变更失败)
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 统一社会信用代码
     */
    private String unifyCode;

    /**
     * 企业类型(1.国有企业 2.集体所有制企业 3.私营企 4.股份制企业 5.有限合伙企业 6.联营企业 7.外商投资企业 8.个人独资企业 9.港澳台企业 10.股份合作企业)
     */
    private String enterpriseType;

    /**
     * 注册地址
     */
    private String registeredAddress;

    /**
     * 详细地址
     */
    private String particularAddress;

    /**
     * 法定代表人
     */
    private String legalRepresentative;

    /**
     * 注册资本
     */
    private String registeredCapital;

    /**
     * 成立日期
     */
    private Date registerDate;

    /**
     * 营业执照上传
     */
    private String uploadOfBusinessLicense;

    /**
     * 经营性质(1.制造商 2.贸易商 3.服务商)
     */
    private String businessNature;

    /**
     * 产品/服务
     */
    private String productsOrServices;

    /**
     * 客户
     */
    private String client;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司名称
     */
    private String companyName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getUnifyCode() {
        return unifyCode;
    }

    public void setUnifyCode(String unifyCode) {
        this.unifyCode = unifyCode;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getParticularAddress() {
        return particularAddress;
    }

    public void setParticularAddress(String particularAddress) {
        this.particularAddress = particularAddress;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getUploadOfBusinessLicense() {
        return uploadOfBusinessLicense;
    }

    public void setUploadOfBusinessLicense(String uploadOfBusinessLicense) {
        this.uploadOfBusinessLicense = uploadOfBusinessLicense;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    public String getProductsOrServices() {
        return productsOrServices;
    }

    public void setProductsOrServices(String productsOrServices) {
        this.productsOrServices = productsOrServices;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        return "CSrmSupplierChangeRecord{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", unifyCode='" + unifyCode + '\'' +
                ", enterpriseType='" + enterpriseType + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", particularAddress='" + particularAddress + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", registeredCapital='" + registeredCapital + '\'' +
                ", registerDate=" + registerDate +
                ", uploadOfBusinessLicense='" + uploadOfBusinessLicense + '\'' +
                ", businessNature='" + businessNature + '\'' +
                ", productsOrServices='" + productsOrServices + '\'' +
                ", client='" + client + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
