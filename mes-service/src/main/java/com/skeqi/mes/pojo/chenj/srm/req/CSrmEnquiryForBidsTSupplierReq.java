package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryForBidsTSupplier
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 询价/招标供应商表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmEnquiryForBidsTSupplierReq {

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
     * 物料编码集合
     */
    private List<String> materialCodeList;


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
    private String offerTime;

    /**
     * 报价有效期
     */
    private String offerPeriodOfValidity;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 是否运输费(1.是,2.否)
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
     * 分值
     */
    private String score;


    /**
     * 评分时间
     */
    private String markingTime;

    /**
     * 评分要素
     */
    private String scoreElementsAndScores;

    /**
     * 是否推荐
     */
    private String whetherToRecommend;

    /**
     * 推荐意见
     */
    private String recommendations;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 当前页码
     *
     * @return
     */
    private Integer pageNum;

    /**
     * 条数
     *
     * @return
     */
    private Integer pageSize;


    public String getRfqOrTenderFormCode() {
        return rfqOrTenderFormCode;
    }

    public void setRfqOrTenderFormCode(String rfqOrTenderFormCode) {
        this.rfqOrTenderFormCode = rfqOrTenderFormCode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getMaterialCodeList() {
        return materialCodeList;
    }

    public void setMaterialCodeList(List<String> materialCodeList) {
        this.materialCodeList = materialCodeList;
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

    public String getWhetherOffer() {
        return whetherOffer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setWhetherOffer(String whetherOffer) {
        this.whetherOffer = whetherOffer;
    }


    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
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

    public String getOfferEr() {
        return offerEr;
    }

    public void setOfferEr(String offerEr) {
        this.offerEr = offerEr;
    }

    public void setOfferPeriodOfValidity(String offerPeriodOfValidity) {
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

    public String getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    public void setEstimatedTimeOfDelivery(String estimatedTimeOfDelivery) {
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

    public String getMarkingTime() {
        return markingTime;
    }

    public void setMarkingTime(String markingTime) {
        this.markingTime = markingTime;
    }

    public String getScoreElementsAndScores() {
        return scoreElementsAndScores;
    }

    public void setScoreElementsAndScores(String scoreElementsAndScores) {
        this.scoreElementsAndScores = scoreElementsAndScores;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryForBidsTSupplierReq{" +
                "rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialCodeList='" + materialCodeList + '\'' +
                ", materialName='" + materialName + '\'' +
                ", whetherOffer='" + whetherOffer + '\'' +
                ", offerEr='" + offerEr + '\'' +
                ", offerTime='" + offerTime + '\'' +
                ", offerPeriodOfValidity='" + offerPeriodOfValidity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", freightOrNot='" + freightOrNot + '\'' +
                ", minimumOrder='" + minimumOrder + '\'' +
                ", minimumPackingQuantity='" + minimumPackingQuantity + '\'' +
                ", estimatedTimeOfDelivery='" + estimatedTimeOfDelivery + '\'' +
                ", quantityAllotted='" + quantityAllotted + '\'' +
                ", chooseReason='" + chooseReason + '\'' +
                ", scorer='" + scorer + '\'' +
                ", score='" + score + '\'' +
                ", markingTime='" + markingTime + '\'' +
                ", scoreElementsAndScores='" + scoreElementsAndScores + '\'' +
                ", whetherToRecommend='" + whetherToRecommend + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
