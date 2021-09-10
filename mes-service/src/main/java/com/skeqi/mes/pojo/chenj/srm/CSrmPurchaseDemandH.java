package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */

/**
 * 采购需求头表
 */
public class CSrmPurchaseDemandH {
    /**
     * 采购需求头id
     */
    private Integer id;

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 状态(1.新建2.审批中3.待分配4.已分配5.已删除)
     */
    private String status;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 申请日期
     */
    private Date applicationDate;

    /**
     * 项目号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 申请部门
     */
    private String applyForDepartment;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    /**
     * 业务类型
     */
    private String businessType;


    /**
     * 制单人
     */
    private String preparedBy;


    /**
     * 逻辑删除(0:未删除、1:已删除)
     */
    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getStatus() {
        return status;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposer() {
        return proposer;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplyForDepartment() {
        return applyForDepartment;
    }

    public void setApplyForDepartment(String applyForDepartment) {
        this.applyForDepartment = applyForDepartment;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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
        return "CSrmPurchaseDemandH{" +
                "id=" + id +
                ", requestCode='" + requestCode + '\'' +
                ", status='" + status + '\'' +
                ", proposer='" + proposer + '\'' +
                ", applicationDate=" + applicationDate +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", applyForDepartment='" + applyForDepartment + '\'' +
                ", buyer='" + buyer + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", businessType='" + businessType + '\'' +
                ", preparedBy='" + preparedBy + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
