package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryForBidsTSupplier
 * @Description ${Description}
 */

/**
 * 询价/招标供应商表出参数
 */
public class CSrmEnquiryForBidsTSupplierRsp {

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 物料编码
     */
    private String materialCode;


    /**
     * 物料名称
     */
    private String materialName;


    /**
     * 报价有效期
     */
    private String offerPeriodOfValidity;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 是否运输费
     */
    private String freightOrNot;

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
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商名称
     */
    private String name;

    /**
     *
     * 报价人
     */
    private String offerEr;

    /**
     *
     * 报价时间
     */
    private String offerTime;

    /**
     * 合作次数
     */
    private String cooperationCount;



    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
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

    public String getOfferPeriodOfValidity() {
        return offerPeriodOfValidity;
    }

    public void setOfferPeriodOfValidity(String offerPeriodOfValidity) {
        this.offerPeriodOfValidity = offerPeriodOfValidity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfferEr() {
        return offerEr;
    }

    public void setOfferEr(String offerEr) {
        this.offerEr = offerEr;
    }

    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getFreightOrNot() {
        return freightOrNot;
    }

    public void setFreightOrNot(String freightOrNot) {
        this.freightOrNot = freightOrNot;
    }

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(String minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public String getCooperationCount() {
        return cooperationCount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setCooperationCount(String cooperationCount) {
        this.cooperationCount = cooperationCount;
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

    @Override
    public String toString() {
        return "CSrmEnquiryForBidsTSupplierRsp{" +
                "lineNumber='" + lineNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", offerPeriodOfValidity='" + offerPeriodOfValidity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", freightOrNot='" + freightOrNot + '\'' +
                ", minimumOrder='" + minimumOrder + '\'' +
                ", minimumPackingQuantity='" + minimumPackingQuantity + '\'' +
                ", estimatedTimeOfDelivery='" + estimatedTimeOfDelivery + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", name='" + name + '\'' +
                ", offerEr='" + offerEr + '\'' +
                ", offerTime='" + offerTime + '\'' +
                ", cooperationCount='" + cooperationCount + '\'' +
                '}';
    }
}
