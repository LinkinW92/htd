package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSourcingTemplate
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 寻源模板表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSourcingTemplateReq {

    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 类型(1.询报价2.招投标)
     */
    private String type;

    /**
     * 状态(1.已禁用2.已启用)
     */
    private String status;

    /**
     * 版本
     */
    private String version;

    /**
     * 附属类别(1.银行信息2.产品信息)
     */
    private String accessoryCategories;

    /**
     * 属性字段
     */
    private String propertyField;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段值
     */
    private String fieldValue;

    /**
     * 操作标识 (1.新增2.修改3.新增版本)
     */
    private String operationSign;

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    @Override
    public String toString() {
        return "CSrmSourcingTemplateReq{" +
                "templateCode='" + templateCode + '\'' +
                ", templateName='" + templateName + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", accessoryCategories='" + accessoryCategories + '\'' +
                ", propertyField='" + propertyField + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", operationSign='" + operationSign + '\'' +
                '}';
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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

    public String getAccessoryCategories() {
        return accessoryCategories;
    }

    public void setAccessoryCategories(String accessoryCategories) {
        this.accessoryCategories = accessoryCategories;
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

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
