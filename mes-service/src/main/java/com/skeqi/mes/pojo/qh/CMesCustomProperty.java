package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
    * 自定义属性表
 * @author Lenovo
 */
public class CMesCustomProperty{
    /**
    * 属性id
    */
    @ApiModelProperty(value="属性id")
    private Integer id;

    /**
    * 属性名称
    */
    @ApiModelProperty(value="属性名称")
    private String propertyName;

    /**
    * 属性英文名称
    */
    @ApiModelProperty(value="属性英文名称")
    private String propertyEnglishName;

    /**
     * 属性值
     */
    @ApiModelProperty(value="属性表内容")
    private List<Object> valueList;

    /**
    * 属性类型
    */
    @ApiModelProperty(value="属性类型")
    private String propertyType;

    /**
    * 对象类型（物料，产品，订单，工单等）
    */
    @ApiModelProperty(value="对象类型（物料，产品，订单，工单等）")
    private String objectType;

    /**
    * 属性范围 （全部，某个字段）
    */
    @ApiModelProperty(value="属性范围 （全部，某个字段）")
    private String bindScopeKey;

    /**
    * 属性范围判断条件
    */
    @ApiModelProperty(value="属性范围判断条件")
    private String bindCondition;

    /**
    * 属性范围值
    */
    @ApiModelProperty(value="属性范围值")
    private String bindScopeValue;

    /**
    * 属性分组
    */
    @ApiModelProperty(value="属性分组")
    private String propertyGroup;

    /**
    * 属性说明
    */
    @ApiModelProperty(value="属性说明")
    private String propertyExplain;

    /**
     * 属性说明
     */
    @ApiModelProperty(value="默认值")
    private String defaults;

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public List<Object> getValue() {
        return valueList;
    }

    public void setValue(List<Object> value) {
        this.valueList = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyEnglishName() {
        return propertyEnglishName;
    }

    public void setPropertyEnglishName(String propertyEnglishName) {
        this.propertyEnglishName = propertyEnglishName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getBindScopeKey() {
        return bindScopeKey;
    }

    public void setBindScopeKey(String bindScopeKey) {
        this.bindScopeKey = bindScopeKey;
    }

    public String getBindCondition() {
        return bindCondition;
    }

    public void setBindCondition(String bindCondition) {
        this.bindCondition = bindCondition;
    }

    public String getBindScopeValue() {
        return bindScopeValue;
    }

    public void setBindScopeValue(String bindScopeValue) {
        this.bindScopeValue = bindScopeValue;
    }

    public String getPropertyGroup() {
        return propertyGroup;
    }

    public void setPropertyGroup(String propertyGroup) {
        this.propertyGroup = propertyGroup;
    }

    public String getPropertyExplain() {
        return propertyExplain;
    }

    public void setPropertyExplain(String propertyExplain) {
        this.propertyExplain = propertyExplain;
    }

    @Override
    public String toString() {
        return "CMesCustomProperty{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", propertyEnglishName='" + propertyEnglishName + '\'' +
                ", valueList=" + valueList +
                ", propertyType='" + propertyType + '\'' +
                ", objectType='" + objectType + '\'' +
                ", bindScopeKey='" + bindScopeKey + '\'' +
                ", bindCondition='" + bindCondition + '\'' +
                ", bindScopeValue='" + bindScopeValue + '\'' +
                ", propertyGroup='" + propertyGroup + '\'' +
                ", propertyExplain='" + propertyExplain + '\'' +
                ", defaults='" + defaults + '\'' +
                '}';
    }
}
