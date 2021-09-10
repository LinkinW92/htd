package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSourcingTemplateAffiliate
 * @Description ${Description}
 */

/**
 * 寻源模板附属信息表
 */
public class CSrmSourcingTemplateAffiliate {
    /**
     * 寻源模板附属信息id
     */
    private Integer id;

    /**
     * 模板编号
     */
    private String templateCode;

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

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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
        sb.append(", templateCode=").append(templateCode);
        sb.append(", accessoryCategories=").append(accessoryCategories);
        sb.append(", propertyField=").append(propertyField);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
