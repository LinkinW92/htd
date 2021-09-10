package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ChenJ
 * @date 2021/6/23
 * @Classname partnerOrClauseReq
 * @Description 合作伙伴与条款入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerOrClauseReq {


    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 前端增删改查标识
     */
    private String indexInfos;

    /**
     * 行号
     */
    private String lineItemNo;

    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 合同条款对象及对象值
     */
    private String objectOfContractOrValue;
    /**
     * 业务条款编码
     */
    private String codeOfBusinessTerms;
    /**
     * 业务条框名称
     */
    private String businessBoxName;
    /**
     * 业务条款内容
     */
    private String contentOfBusinessTerms;
    /**
     * 业务条款说明
     */
    private String statementOfBusinessTerms;

    public String getObjectOfContractOrValue() {
        return objectOfContractOrValue;
    }

    public void setObjectOfContractOrValue(String objectOfContractOrValue) {
        this.objectOfContractOrValue = objectOfContractOrValue;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCodeOfBusinessTerms() {
        return codeOfBusinessTerms;
    }

    public void setCodeOfBusinessTerms(String codeOfBusinessTerms) {
        this.codeOfBusinessTerms = codeOfBusinessTerms;
    }

    public String getBusinessBoxName() {
        return businessBoxName;
    }

    public void setBusinessBoxName(String businessBoxName) {
        this.businessBoxName = businessBoxName;
    }

    public String getContentOfBusinessTerms() {
        return contentOfBusinessTerms;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public String getIndexInfos() {
        return indexInfos;
    }

    public void setIndexInfos(String indexInfos) {
        this.indexInfos = indexInfos;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public void setContentOfBusinessTerms(String contentOfBusinessTerms) {
        this.contentOfBusinessTerms = contentOfBusinessTerms;
    }

    public String getStatementOfBusinessTerms() {
        return statementOfBusinessTerms;
    }

    public void setStatementOfBusinessTerms(String statementOfBusinessTerms) {
        this.statementOfBusinessTerms = statementOfBusinessTerms;
    }

    @Override
    public String toString() {
        return "PartnerOrClauseReq{" +
                "contractNo='" + contractNo + '\'' +
                ", indexInfos='" + indexInfos + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", objectOfContractOrValue='" + objectOfContractOrValue + '\'' +
                ", codeOfBusinessTerms='" + codeOfBusinessTerms + '\'' +
                ", businessBoxName='" + businessBoxName + '\'' +
                ", contentOfBusinessTerms='" + contentOfBusinessTerms + '\'' +
                ", statementOfBusinessTerms='" + statementOfBusinessTerms + '\'' +
                '}';
    }
}
