package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryForBidsTSupplier
 * @Description ${Description}
 */

/**
 * 询价/招标供应商表
 */
public class CSrmEnquiryForBidsTSupplier {
    /**
     * 询价/招标供应商id
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
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 是否报价(1.已报价2.未报价)
     */
    private String whetherOffer;

    /**
     * 报价人
     */
    private String offerEr;

    /**
     * 报价时间
     */
    private Date offerTime;

    /**
     * 报价有效期
     */
    private Date offerPeriodOfValidity;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 是否运输费(1.是2.否)
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
    private Date estimatedTimeOfDelivery;

    /**
     * 分配数量
     */
    private String quantityAllotted;

    /**
     * 选用理由
     */
    private String chooseReason;

    /**
     * 评分人/评分专家
     */
    private String scorer;

    /**
     * 评分时间
     */
    private Date markingTime;

    /**
     * 评分要素
     */
    private String scoreElementsAndScores;

    /**
     * 分值
     */
    private String score;

    /**
     * 是否推荐(1.是2.否)
     */
    private String whetherToRecommend;

    /**
     * 推荐意见
     */
    private String recommendations;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getWhetherOffer() {
        return whetherOffer;
    }

    public void setWhetherOffer(String whetherOffer) {
        this.whetherOffer = whetherOffer;
    }

    public String getOfferEr() {
        return offerEr;
    }

    public void setOfferEr(String offerEr) {
        this.offerEr = offerEr;
    }

    public Date getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(Date offerTime) {
        this.offerTime = offerTime;
    }

    public Date getOfferPeriodOfValidity() {
        return offerPeriodOfValidity;
    }

    public void setOfferPeriodOfValidity(Date offerPeriodOfValidity) {
        this.offerPeriodOfValidity = offerPeriodOfValidity;
    }

    public String getUnitPrice() {
        return unitPrice;
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

    public String getMinimumPackingQuantity() {
        return minimumPackingQuantity;
    }

    public void setMinimumPackingQuantity(String minimumPackingQuantity) {
        this.minimumPackingQuantity = minimumPackingQuantity;
    }

    public Date getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    public void setEstimatedTimeOfDelivery(Date estimatedTimeOfDelivery) {
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }

    public String getQuantityAllotted() {
        return quantityAllotted;
    }

    public void setQuantityAllotted(String quantityAllotted) {
        this.quantityAllotted = quantityAllotted;
    }

    public String getChooseReason() {
        return chooseReason;
    }

    public void setChooseReason(String chooseReason) {
        this.chooseReason = chooseReason;
    }

    public String getScorer() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer = scorer;
    }

    public Date getMarkingTime() {
        return markingTime;
    }

    public void setMarkingTime(Date markingTime) {
        this.markingTime = markingTime;
    }

    public String getScoreElementsAndScores() {
        return scoreElementsAndScores;
    }

    public void setScoreElementsAndScores(String scoreElementsAndScores) {
        this.scoreElementsAndScores = scoreElementsAndScores;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWhetherToRecommend() {
        return whetherToRecommend;
    }

    public void setWhetherToRecommend(String whetherToRecommend) {
        this.whetherToRecommend = whetherToRecommend;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", materialCode=").append(materialCode);
        sb.append(", materialName=").append(materialName);
        sb.append(", whetherOffer=").append(whetherOffer);
        sb.append(", offerEr=").append(offerEr);
        sb.append(", offerTime=").append(offerTime);
        sb.append(", offerPeriodOfValidity=").append(offerPeriodOfValidity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", freightOrNot=").append(freightOrNot);
        sb.append(", minimumOrder=").append(minimumOrder);
        sb.append(", minimumPackingQuantity=").append(minimumPackingQuantity);
        sb.append(", estimatedTimeOfDelivery=").append(estimatedTimeOfDelivery);
        sb.append(", quantityAllotted=").append(quantityAllotted);
        sb.append(", chooseReason=").append(chooseReason);
        sb.append(", scorer=").append(scorer);
        sb.append(", markingTime=").append(markingTime);
        sb.append(", scoreElementsAndScores=").append(scoreElementsAndScores);
        sb.append(", score=").append(score);
        sb.append(", whetherToRecommend=").append(whetherToRecommend);
        sb.append(", recommendations=").append(recommendations);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}
