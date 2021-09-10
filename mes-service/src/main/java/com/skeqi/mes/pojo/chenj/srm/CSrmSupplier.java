package com.skeqi.mes.pojo.chenj.srm;

import java.util.Arrays;
import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplier
 * @Description ${Description}
 */

/**
 * 供应商信息表
 */
public class CSrmSupplier {
    /**
     * 供应商id
     */
    private Integer id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商代码集合
     */
    private String[] supplierCodeList;


    /**
     * 所处阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String inPhase;

    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证4.认证失败5.企业信息变更失败)
     */
    private Integer status;


    /**
     * 权限id
     */
    private String roleId;


    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private Date tokenCreateTime;

    @Override
    public String toString() {
        return "CSrmSupplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierCodeList=" + Arrays.toString(supplierCodeList) +
                ", inPhase='" + inPhase + '\'' +
                ", status=" + status +
                ", roleId='" + roleId + '\'' +
                ", isDelete=" + isDelete +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tokenCreateTime=" + tokenCreateTime +
                '}';
    }

    public Date getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Date tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String[] getSupplierCodeList() {
        return supplierCodeList;
    }

    public void setSupplierCodeList(String[] supplierCodeList) {
        this.supplierCodeList = supplierCodeList;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getInPhase() {
        return inPhase;
    }

    public void setInPhase(String inPhase) {
        this.inPhase = inPhase;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

}
