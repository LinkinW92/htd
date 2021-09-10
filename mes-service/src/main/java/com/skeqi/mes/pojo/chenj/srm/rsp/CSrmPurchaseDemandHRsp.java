package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */

/**
 * 采购需求管理头查询
 */
public class CSrmPurchaseDemandHRsp {

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
    private String applicationDate;

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
     * 申请部门名称
     */
    private String applyForDepartmentName;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;

    /**
     * 采购员名称
     */
    private String buyerName;

    /**
     * 到货日期
     */
    private String deliveryDate;
    /**
     * 审核日期
     */
    private String updateTime;

    /**
     * 制单人
     */
    private String preparedBy;

    /**
     * 采购员名称
     */
    private String name;


    /**
     * 摘要 组成格式((物料名称,规格),(物料名称,规格))
     */
    private String digest;


    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplyForDepartment() {
        return applyForDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setApplyForDepartment(String applyForDepartment) {
        this.applyForDepartment = applyForDepartment;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getApplyForDepartmentName() {
        return applyForDepartmentName;
    }

    public void setApplyForDepartmentName(String applyForDepartmentName) {
        this.applyForDepartmentName = applyForDepartmentName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseDemandHRsp{" +
                "requestCode='" + requestCode + '\'' +
                ", status='" + status + '\'' +
                ", proposer='" + proposer + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", applyForDepartment='" + applyForDepartment + '\'' +
                ", applyForDepartmentName='" + applyForDepartmentName + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", preparedBy='" + preparedBy + '\'' +
                ", name='" + name + '\'' +
                ", digest='" + digest + '\'' +
                '}';
    }
}
