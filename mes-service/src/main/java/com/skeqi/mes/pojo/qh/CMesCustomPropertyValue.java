package com.skeqi.mes.pojo.qh;

/**
 * 自定义属性Value表
 * @author Lenovo
 */
public class CMesCustomPropertyValue {
   private Integer id;
   private String value;
   private Integer propertyId;
   private String objectType;
   private String objectCode;

    public String getBindScopeKey() {
        return bindScopeKey;
    }

    public void setBindScopeKey(String bindScopeKey) {
        this.bindScopeKey = bindScopeKey;
    }

    private String bindScopeKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
}
