package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/7/19
 * @Classname CSrmEnquiryInvitationForBidsTR
 * @Description ${Description}
 */

/**
 * 询价/招标行表
 */
public class CSrmEnquiryInvitationForBidsTR {
    /**
     * 询价/招标行id
     */
    private Integer id;

    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 库存组织(工厂)
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
    private Date demandedDate;

    /**
     * 图纸图号
     */
    private String drawingFigureNo;

    /**
     * 采购申请单号
     */
    private String purchaseRequest;

    /**
     * 附件
     */
    private String accessory;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 询价/招标头id
     */
    private Integer enquiryId;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 业务实体
     */
    private String businessEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRfqOrTenderFormCode() {
        return rfqOrTenderFormCode;
    }

    public void setRfqOrTenderFormCode(String rfqOrTenderFormCode) {
        this.rfqOrTenderFormCode = rfqOrTenderFormCode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getQuantityRequired() {
        return quantityRequired;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDemandedDate() {
        return demandedDate;
    }

    public void setDemandedDate(Date demandedDate) {
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

    public void setAccessory(String accessory) {
        this.accessory = accessory;
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

    public Integer getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rfqOrTenderFormCode=").append(rfqOrTenderFormCode);
        sb.append(", lineNumber=").append(lineNumber);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", inventoryOrganization=").append(inventoryOrganization);
        sb.append(", materialCode=").append(materialCode);
        sb.append(", materialName=").append(materialName);
        sb.append(", quantityRequired=").append(quantityRequired);
        sb.append(", unit=").append(unit);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", demandedDate=").append(demandedDate);
        sb.append(", drawingFigureNo=").append(drawingFigureNo);
        sb.append(", purchaseRequest=").append(purchaseRequest);
        sb.append(", accessory=").append(accessory);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", enquiryId=").append(enquiryId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", businessEntity=").append(businessEntity);
        sb.append("]");
        return sb.toString();
    }
}
