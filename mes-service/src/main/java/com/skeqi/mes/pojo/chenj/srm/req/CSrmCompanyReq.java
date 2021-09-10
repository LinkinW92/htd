package com.skeqi.mes.pojo.chenj.srm.req;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompany
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 公司信息表入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmCompanyReq {

    /**
     * 公司代码
     */
    private String companyCode;


    /**
     * 公司名称
     */
    private String companyName;


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




    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    public Integer getPageNum() {
        return pageNum;
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

    public String getCompanyCode() {
        return companyCode;
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

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CSrmCompanyReq{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", unifyCode='" + unifyCode + '\'' +
                ", enterpriseType='" + enterpriseType + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", particularAddress='" + particularAddress + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", registeredCapital='" + registeredCapital + '\'' +
                ", registerDate=" + registerDate +
                ", uploadOfBusinessLicense='" + uploadOfBusinessLicense + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
