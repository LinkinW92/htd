package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
    * 工厂表
    */
@ApiModel(value="com-skeqi-pojo-qh-CMesFactoryT")
public class CMesFactoryT implements Serializable {
    /**
    * 工厂id
    */
    @ApiModelProperty(value="工厂id")
    private Integer id;

    /**
    * 工厂编号
    */
    @ApiModelProperty(value="工厂编号")
    private String factoryCode;

    /**
    * 工厂名称
    */
    @ApiModelProperty(value="工厂名称")
    private String factoryName;

    /**
    * 工厂描述
    */
    @ApiModelProperty(value="工厂描述")
    private String describe;

    /**
     * 所属公司
     */
    @ApiModelProperty(value="所属公司")
    private String companyCode;

    /**
     * 所属公司名称
     */
    @ApiModelProperty(value="所属公司名称")
    private String companyName;

    /**
     * 自定义属性
     */
    @ApiModelProperty(value="自定义属性")
    private Object custom;

    public Object getCustom() {
        return custom;
    }

    public void setCustom(Object custom) {
        this.custom = custom;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private String alterDt;

    public String getAlterDt() {
        return alterDt;
    }

    public void setAlterDt(String alterDt) {
        this.alterDt = alterDt;
    }

    private static final long serialVersionUID = 8128632510761684210L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", factorycode=").append(factoryCode);
        sb.append(", factoryname=").append(factoryName);
        sb.append(", describe=").append(describe);
        sb.append("]");
        return sb.toString();
    }
}
