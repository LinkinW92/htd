package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAlteration
 * @Description ${Description}
 */

/**
 * 变更记录表
 */
public class CSrmAlteration {
    /**
     * 变更记录id
     */
    private Integer id;

    /**
     * 变更对象类型
     */
    private String changingObjectTypes;

    /**
     * 变更对象值
     */
    private String changingObjectValues;

    /**
     * 变更属性
     */
    private String propertyChanges;

    /**
     * 属性旧值
     */
    private String theOldAttributeValues;

    /**
     * 属性新值
     */
    private String theNewAttributeValues;

    /**
     * 变更人
     */
    private String changeAPerson;

    /**
     * 变更时间
     */
    private Date alterationTime;

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

    public String getChangingObjectTypes() {
        return changingObjectTypes;
    }

    public void setChangingObjectTypes(String changingObjectTypes) {
        this.changingObjectTypes = changingObjectTypes;
    }

    public String getChangingObjectValues() {
        return changingObjectValues;
    }

    public void setChangingObjectValues(String changingObjectValues) {
        this.changingObjectValues = changingObjectValues;
    }

    public String getPropertyChanges() {
        return propertyChanges;
    }

    public void setPropertyChanges(String propertyChanges) {
        this.propertyChanges = propertyChanges;
    }

    public String getTheOldAttributeValues() {
        return theOldAttributeValues;
    }

    public void setTheOldAttributeValues(String theOldAttributeValues) {
        this.theOldAttributeValues = theOldAttributeValues;
    }

    public String getTheNewAttributeValues() {
        return theNewAttributeValues;
    }

    public void setTheNewAttributeValues(String theNewAttributeValues) {
        this.theNewAttributeValues = theNewAttributeValues;
    }

    public String getChangeAPerson() {
        return changeAPerson;
    }

    public void setChangeAPerson(String changeAPerson) {
        this.changeAPerson = changeAPerson;
    }

    public Date getAlterationTime() {
        return alterationTime;
    }

    public void setAlterationTime(Date alterationTime) {
        this.alterationTime = alterationTime;
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
        sb.append(", changingObjectTypes=").append(changingObjectTypes);
        sb.append(", changingObjectValues=").append(changingObjectValues);
        sb.append(", propertyChanges=").append(propertyChanges);
        sb.append(", theOldAttributeValues=").append(theOldAttributeValues);
        sb.append(", theNewAttributeValues=").append(theNewAttributeValues);
        sb.append(", changeAPerson=").append(changeAPerson);
        sb.append(", alterationTime=").append(alterationTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
