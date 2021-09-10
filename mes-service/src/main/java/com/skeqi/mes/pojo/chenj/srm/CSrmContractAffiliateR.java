package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractAffiliateR
 * @Description ${Description}
 */

/**
 * 合同附属表
 */
public class CSrmContractAffiliateR {
    /**
     * 合同附属表id
     */
    private Integer id;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 甲方
     */
    private String firstParty;

    /**
     * 乙方
     */
    private String secondParty;

    /**
     * 合同条款对象及对象值
     */
    private String objectOfContractOrValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    /**
     * 业务条款编码
     */
    private String  codeOfBusinessTerms;
    /**
     * 业务条框名称
     */
    private String  businessBoxName;
    /**
     * 业务条款内容
     */
    private String  contentOfBusinessTerms;
    /**
     * 业务条款说明
     */
    private String  statementOfBusinessTerms;


    /**
     * 行项目号
     * @return
     */
    private String lineItemNo;


    public String getCodeOfBusinessTerms() {
        return codeOfBusinessTerms;
    }

    public void setCodeOfBusinessTerms(String codeOfBusinessTerms) {
        this.codeOfBusinessTerms = codeOfBusinessTerms;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
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

    public void setContentOfBusinessTerms(String contentOfBusinessTerms) {
        this.contentOfBusinessTerms = contentOfBusinessTerms;
    }

    public String getStatementOfBusinessTerms() {
        return statementOfBusinessTerms;
    }

    public void setStatementOfBusinessTerms(String statementOfBusinessTerms) {
        this.statementOfBusinessTerms = statementOfBusinessTerms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getObjectOfContractOrValue() {
        return objectOfContractOrValue;
    }

    public void setObjectOfContractOrValue(String objectOfContractOrValue) {
        this.objectOfContractOrValue = objectOfContractOrValue;
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
        return "CSrmContractAffiliateR{" +
                "id=" + id +
                ", contractNo='" + contractNo + '\'' +
                ", firstParty='" + firstParty + '\'' +
                ", secondParty='" + secondParty + '\'' +
                ", objectOfContractOrValue='" + objectOfContractOrValue + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", codeOfBusinessTerms='" + codeOfBusinessTerms + '\'' +
                ", businessBoxName='" + businessBoxName + '\'' +
                ", contentOfBusinessTerms='" + contentOfBusinessTerms + '\'' +
                ", statementOfBusinessTerms='" + statementOfBusinessTerms + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                '}';
    }
}
