package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTR
 * @Description ${Description}
 */

/**
 * 询价/招标行表
 */
public class CSrmEnquiryInvitationForBidsTRRsp {

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
     * 需求日期--采购申请单中字段   requiredDate
     */
    private String requiredDate;

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
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 供应商编码
     */
    private String supplierCode;


    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 最小采购量
     */
    private String minimumOrder;


    /**
     * 最小包装量
     */
    private String minimumPackingQuantity;


    /**
     * 预计交货时间
     */
    private String estimatedTimeOfDelivery;


    /**
     * 报价有效期
     */
    private String offerPeriodOfValidity;


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

    public String getOfferPeriodOfValidity() {
        return offerPeriodOfValidity;
    }

    public void setOfferPeriodOfValidity(String offerPeriodOfValidity) {
        this.offerPeriodOfValidity = offerPeriodOfValidity;
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

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(String minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public String getMinimumPackingQuantity() {
        return minimumPackingQuantity;
    }

    public void setMinimumPackingQuantity(String minimumPackingQuantity) {
        this.minimumPackingQuantity = minimumPackingQuantity;
    }

    public String getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    public void setEstimatedTimeOfDelivery(String estimatedTimeOfDelivery) {
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
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

    public String getDemandedDate() {
        return demandedDate;
    }

    public void setDemandedDate(String demandedDate) {
        this.demandedDate = demandedDate;
        this.requiredDate = demandedDate;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTRRsp{" +
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
                ", requiredDate='" + requiredDate + '\'' +
                ", drawingFigureNo='" + drawingFigureNo + '\'' +
                ", purchaseRequest='" + purchaseRequest + '\'' +
                ", accessory='" + accessory + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", minimumOrder='" + minimumOrder + '\'' +
                ", minimumPackingQuantity='" + minimumPackingQuantity + '\'' +
                ", estimatedTimeOfDelivery='" + estimatedTimeOfDelivery + '\'' +
                ", offerPeriodOfValidity='" + offerPeriodOfValidity + '\'' +
                '}';
    }
}
