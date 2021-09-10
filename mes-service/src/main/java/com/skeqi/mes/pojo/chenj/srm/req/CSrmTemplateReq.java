package com.skeqi.mes.pojo.chenj.srm.req;




 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmTemplate
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 调查表模板主表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmTemplateReq {


    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 版本
     */
    private String version;


    /**
     * 附属类别(1.银行信息2.产品信息)
     */
    private String auxiliaryType;

    /**
     * 属性字段
     */
    private String propertyField;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private String fieldType;



    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "CSrmTemplateReq{" +
                "templateName='" + templateName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", auxiliaryType='" + auxiliaryType + '\'' +
                ", propertyField='" + propertyField + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
