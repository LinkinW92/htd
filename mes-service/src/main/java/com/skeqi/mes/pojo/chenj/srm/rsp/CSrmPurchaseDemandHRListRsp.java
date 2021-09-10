package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */

/**
 * 采购需求单转换出参
 */
public class CSrmPurchaseDemandHRListRsp {


    /**
     * 申请单号
     */
    private String purchaseRequest;

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
     * 采购员
     */
    private String buyer;

    /**
     * 采购员名称
     */
    private String buyerName;

    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType;


    /**
     * 行项目号
     */
    private String rowProjectNumber;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 数量
     */
    private String count;

    /**
     * 需求数量
     */
    private String quantityRequired;


    /**
     * 单位
     */
    private String unit;

    /**
     * 需求日期
     */
    private String requiredDate;

    /**
     * 收货地点
     */
    private String placeOfReceipt;



//    /**
//     * 操作标识(1.采购需求单转询价)
//     */
//    private String operationSign;


//    /**
//     * 当前页码
//     */
//    private Integer pageNum;
//    /**
//     * 条数
//     */
//    private Integer pageSize;


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

    public String getProjectCode() {
        return projectCode;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
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

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
        this.quantityRequired = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getPurchaseRequest() {
        return purchaseRequest;
    }

    public String getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(String quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public void setPurchaseRequest(String purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseDemandHRListRsp{" +
                "purchaseRequest='" + purchaseRequest + '\'' +
                ", status='" + status + '\'' +
                ", proposer='" + proposer + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", applyForDepartment='" + applyForDepartment + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", count='" + count + '\'' +
                ", quantityRequired='" + quantityRequired + '\'' +
                ", unit='" + unit + '\'' +
                ", requiredDate='" + requiredDate + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                '}';
    }
}
