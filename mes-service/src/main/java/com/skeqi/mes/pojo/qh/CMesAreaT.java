package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 区域表
 */
@ApiModel(value="com-skeqi-pojo-qh-CMesAreaT")
public class CMesAreaT implements Serializable {
    /**
    * 区域id
    */
    @ApiModelProperty(value="区域id")
    private Integer id;

    /**
    * 区域编号
    */
    @ApiModelProperty(value="区域编号")
    private String areaCode;

    /**
    * 区域名称
    */
    @ApiModelProperty(value="区域名称")
    private String areaName;

    /**
    * 区域描述
    */
    @ApiModelProperty(value="区域描述")
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
    * 所属工厂
    */
    @ApiModelProperty(value="所属工厂")
    private String factoryCode;

    /**
     * 所属工厂名称
     */
    @ApiModelProperty(value="所属工厂名称")
    private String factoryName;

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

    private static final long serialVersionUID = 4925741264657227188L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    @Override
    public String toString() {
        return "CMesAreaT{" +
                "id=" + id +
                ", areaCode='" + areaCode + '\'' +
                ", areaCame='" + areaName + '\'' +
                ", describe='" + describe + '\'' +
                ", factoryCode='" + factoryCode + '\'' +
                '}';
    }
}
