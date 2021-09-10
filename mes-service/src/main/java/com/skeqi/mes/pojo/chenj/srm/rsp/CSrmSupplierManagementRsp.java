package com.skeqi.mes.pojo.chenj.srm.rsp;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompany
 * @Description ${Description}
 */

/**
 * 供应商生命周期查询出参
 */
public class CSrmSupplierManagementRsp {
    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 企业名称/公司名称
     */
    private String companyName;


    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证)
     *
     * @return
     */
    private String status;
    /**
     * 所处阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String inPhase;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系方式
     */
    private String contactNumber;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 供应商名称
     */
    private String name;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public void setInPhase(String inPhase) {
        this.inPhase = inPhase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CSrmSupplierManagementRsp{" +
                "supplierCode='" + supplierCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", status='" + status + '\'' +
                ", inPhase='" + inPhase + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
