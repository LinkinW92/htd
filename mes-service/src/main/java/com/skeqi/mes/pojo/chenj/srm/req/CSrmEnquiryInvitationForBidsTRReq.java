package com.skeqi.mes.pojo.chenj.srm.req;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTR
 * @Description ${Description}
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 询价/招标行表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmEnquiryInvitationForBidsTRReq {


    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 公司
     */
    private String companyCode;

    /**
     * 库存组织
     */
    private String inventoryOrganization;

    /**
     * 物料编码(支持无编码情况)
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 需求数量
     */
    private String quantityRequired;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 需求日期
     */
    private String demandedDate;

    /**
     * 图纸图号
     */
    private String drawingFigureNo;

    /**
     * 原采购申请单号
     */
    private String purchaseRequest;

    /**
     * 附件
     */
    private String accessory;

    /**
     * 业务实体
     */
    private String businessEntity;

    /**
     * 物料类别
     */
    private String materialType;


    /**
     * 采购需求单的状态 (1.新建2.审批中3.待分配4.已分配5.已删除)---不入库，只做前端映射处理
     */
    private String status;


    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


    /**
     * 申请人
     */
    private String proposer;

    /**
     * 申请日期
     */
    private String applicationDate;

    /**
     *项目号
     */
    private String projectCode;

    /**
     *项目名称
     */
    private String projectName;


    /**
     *申请部门
     */
    private String applyForDepartment;


    /**
     *采购员
     */
    private String buyer;


    /**
     * 原单行号(采购申请单号)
     */
    private String rowProjectNumber;

    /**
     *数量
     */
    private String count;

    /**
     *需求日期
     */
    private String requiredDate;

    /**
     *收货地点
     */
    private String placeOfReceipt;



    public String getRfqOrTenderFormCode() {
        return rfqOrTenderFormCode;
    }

    public void setRfqOrTenderFormCode(String rfqOrTenderFormCode) {
        this.rfqOrTenderFormCode = rfqOrTenderFormCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }


    public String getInventoryOrganization() {
        return inventoryOrganization;
    }

    public void setInventoryOrganization(String inventoryOrganization) {
        this.inventoryOrganization = inventoryOrganization;
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

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getQuantityRequired() {
        return quantityRequired;
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

    public void setQuantityRequired(String quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDemandedDate() {
        return demandedDate;
    }

    public void setDemandedDate(String demandedDate) {
        this.demandedDate = demandedDate;
    }

    public String getDrawingFigureNo() {
        return drawingFigureNo;
    }

    public void setDrawingFigureNo(String drawingFigureNo) {
        this.drawingFigureNo = drawingFigureNo;
    }

    public String getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(String purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public String getAccessory() {
        return accessory;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTRReq{" +
                "rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", inventoryOrganization='" + inventoryOrganization + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantityRequired='" + quantityRequired + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", demandedDate='" + demandedDate + '\'' +
                ", drawingFigureNo='" + drawingFigureNo + '\'' +
                ", purchaseRequest='" + purchaseRequest + '\'' +
                ", accessory='" + accessory + '\'' +
                ", businessEntity='" + businessEntity + '\'' +
                ", materialType='" + materialType + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", proposer='" + proposer + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", applyForDepartment='" + applyForDepartment + '\'' +
                ", buyer='" + buyer + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", count='" + count + '\'' +
                ", requiredDate='" + requiredDate + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                '}';
    }
}
