package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurvey
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 调查表记录主表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSurveyReq {


    /**
     * 调查表单号
     */
    private String surveyFormNumber;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 模板编号
     */
    private String templateCode;
    /**
     * 附属类别
     */
    private String auxiliaryType;

    /**
     * 属性字段
     */
    private String propertyField;

    /**
     * 字段值
     */
    private String fieldValue;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 审批标记
     * @return
     */
    private String examineSign;




    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    @Override
    public String toString() {
        return "CSrmSurveyReq{" +
                "surveyFormNumber='" + surveyFormNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", creator='" + creator + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", auxiliaryType='" + auxiliaryType + '\'' +
                ", propertyField='" + propertyField + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", examineSign='" + examineSign + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
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

    public String getExamineSign() {
        return examineSign;
    }

    public void setExamineSign(String examineSign) {
        this.examineSign = examineSign;
    }

    public String getSurveyFormNumber() {
        return surveyFormNumber;
    }

    public void setSurveyFormNumber(String surveyFormNumber) {
        this.surveyFormNumber = surveyFormNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getAuxiliaryType() {
        return auxiliaryType;
    }

    public void setAuxiliaryType(String auxiliaryType) {
        this.auxiliaryType = auxiliaryType;
    }

    public String getPropertyField() {
        return propertyField;
    }

    public void setPropertyField(String propertyField) {
        this.propertyField = propertyField;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

}
