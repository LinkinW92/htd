package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurveyDetails
 * @Description ${Description}
 */

/**
 * 调查表记录附属表
 */
public class CSrmSurveyDetails {
    /**
     * 调查表记录附属id
     */
    private Integer id;

    /**
     * 调查表单号
     */
    private String surveyFormNumber;

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
     * 是否答复
     */
    private String whetherAReply;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurveyFormNumber() {
        return surveyFormNumber;
    }

    public void setSurveyFormNumber(String surveyFormNumber) {
        this.surveyFormNumber = surveyFormNumber;
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

    public String getWhetherAReply() {
        return whetherAReply;
    }

    public void setWhetherAReply(String whetherAReply) {
        this.whetherAReply = whetherAReply;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", surveyFormNumber=").append(surveyFormNumber);
        sb.append(", auxiliaryType=").append(auxiliaryType);
        sb.append(", propertyField=").append(propertyField);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", whetherAReply=").append(whetherAReply);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
