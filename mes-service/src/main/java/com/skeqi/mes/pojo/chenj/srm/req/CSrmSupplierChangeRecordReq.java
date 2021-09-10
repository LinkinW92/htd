package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierChangeRecord
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
    * 供应商变更记录表
    */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierChangeRecordReq {


    /**
     * 主键
     */
    private Integer id;

// ------------------------------------------供应商代码------------------------------------------------------------------
    /**
     * 供应商代码
     */
    private String supplierCode;

// ------------------------------------------公司基础信息----------------------------------------------------------------
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
    private String registerDate;
    /**
     * 营业执照上传
     */
    private String uploadOfBusinessLicense;




// ------------------------------------------联系人信息------------------------------------------------------------------
//    /**
//     * 姓名
//     */
//    private String name;
//    /**
//     * 性别(1.男2.女)
//     */
//    private String sex;
//    /**
//     * 电话
//     */
//    private String phone;
//    /**
//     * 邮箱
//     */
//    private String email;
//    /**
//     * 部门
//     */
//    private String department;
//    /**
//     * 职位
//     */
//    private String position;

    /**
     * 联系人行数据
     */
    private List<CSrmLinkmanChangeRecordReq> linkManList;

// ------------------------------------------财务信息--------------------------------------------------------------------
//    /**
//     * 年度
//     */
//    private String year;
//    /**
//     * 总资产(万元)
//     */
//    private String totalAssets;
//    /**
//     * 总负债(万元)
//     */
//    private String grossLiability;
//    /**
//     * 流动资产(万元)
//     */
//    private String currentAssets;
//    /**
//     * 流动负债(万元)
//     */
//    private String currentLiabilities;
//    /**
//     * 营业收入(万元)
//     */
//    private String operatingReceipt;
//    /**
//     * 净利润(万元)
//     */
//    private String retainedProfits;

    /**
     * 财务行数据
     */
    private List<CSrmFinanceChangeRecordReq> financeList;

// ------------------------------------------银行\交易信息---------------------------------------------------------------
//    /**
//     * 开户银行名称
//     */
//    private String bankName;
//    /**
//     * 银行账号
//     */
//    private String bankAccount;
//    /**
//     * 账户名称
//     */
//    private String accountTitle;
//    /**
//     * 收票人邮箱
//     */
//    private String receiverMailbox;
//    /**
//     * 收票人电话
//     */
//    private String collectorTelephoneNumber;


    /**
     * 银行\交易行数据
     */
    private List<CSrmBankChangeRecordReq> bankList;

// ------------------------------------------主要产品\服务信息------------------------------------------------------------
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类别(1.中草药材、2.棉花)
     */
    private String productType;
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


// ------------------------------------------附件信息--------------------------------------------------------------------
    /**
     * 附件描述
     */
    private String theAttachmentDescribe;

    /**
     * 附件上传
     */
    private String attachmentUploading;







//------------------------------------------其他参数--------------------------------------------------------------------
    /**
     * 公司名称
     */
    private String companyName;


    /**
     * 操作标识(1.保存 2.提交)
     */
    private String operationSign;
    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证)
     *
     */
    private String status;
    /**
     * 所处阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String inPhase;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 提交时间从
     */
    private String submitStart;
    /**
     * 提交时间至
     */
    private String submitStop;

    /**
     * 数据源(1.真实信息表2.变更信息表)
     */
    private String sourceType;


    public String getSubmitStart() {
        return submitStart;
    }

    public void setSubmitStart(String submitStart) {
        this.submitStart = submitStart;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSubmitStop() {
        return submitStop;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setSubmitStop(String submitStop) {
        this.submitStop = submitStop;
    }

    @Override
    public String toString() {
        return "CSrmSupplierChangeRecordReq{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", unifyCode='" + unifyCode + '\'' +
                ", enterpriseType='" + enterpriseType + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", particularAddress='" + particularAddress + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", registeredCapital='" + registeredCapital + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", uploadOfBusinessLicense='" + uploadOfBusinessLicense + '\'' +
                ", linkManList=" + linkManList +
                ", financeList=" + financeList +
                ", bankList=" + bankList +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", businessNature='" + businessNature + '\'' +
                ", productsOrServices='" + productsOrServices + '\'' +
                ", client='" + client + '\'' +
                ", theAttachmentDescribe='" + theAttachmentDescribe + '\'' +
                ", attachmentUploading='" + attachmentUploading + '\'' +
                ", companyName='" + companyName + '\'' +
                ", operationSign='" + operationSign + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", status='" + status + '\'' +
                ", inPhase='" + inPhase + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", submitStart='" + submitStart + '\'' +
                ", submitStop='" + submitStop + '\'' +
                ", sourceType='" + sourceType + '\'' +
                '}';
    }

    public Integer getPageSize() {
        return pageSize;
    }



    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public String getInPhase() {
        return inPhase;
    }

    public void setInPhase(String inPhase) {
        this.inPhase = inPhase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
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

    public String getTheAttachmentDescribe() {
        return theAttachmentDescribe;
    }

    public void setTheAttachmentDescribe(String theAttachmentDescribe) {
        this.theAttachmentDescribe = theAttachmentDescribe;
    }

    public String getAttachmentUploading() {
        return attachmentUploading;
    }

    public List<CSrmLinkmanChangeRecordReq> getLinkManList() {
        return linkManList;
    }

    public void setLinkManList(List<CSrmLinkmanChangeRecordReq> linkManList) {
        this.linkManList = linkManList;
    }

    public List<CSrmFinanceChangeRecordReq> getFinanceList() {
        return financeList;
    }

    public void setFinanceList(List<CSrmFinanceChangeRecordReq> financeList) {
        this.financeList = financeList;
    }

    public List<CSrmBankChangeRecordReq> getBankList() {
        return bankList;
    }

    public void setBankList(List<CSrmBankChangeRecordReq> bankList) {
        this.bankList = bankList;
    }

    public void setAttachmentUploading(String attachmentUploading) {
        this.attachmentUploading = attachmentUploading;
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

}
