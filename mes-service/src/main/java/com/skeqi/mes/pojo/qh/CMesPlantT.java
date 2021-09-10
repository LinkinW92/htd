package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 车间表
 */
@ApiModel(value="com-skeqi-pojo-qh-CMesPlantT")
public class CMesPlantT implements Serializable {
    /**
    * 车间id
    */
    @ApiModelProperty(value="车间id")
    private Integer id;

    /**
    * 车间编号
    */
    @ApiModelProperty(value="车间编号")
    private String plantCode;

    /**
    * 车间名称
    */
    @ApiModelProperty(value="车间名称")
    private String plantName;

    /**
    * 车间描述
    */
    @ApiModelProperty(value="车间描述")
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
    * 所属区域
    */
    @ApiModelProperty(value="所属区域")
    private String areaCode;

    /**
     * 所属区域名称
     */
    @ApiModelProperty(value="所属区域名称")
    private String areaName;

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

    private static final long serialVersionUID = 5736908812311935208L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "CMesPlantT{" +
                "id=" + id +
                ", plantCode='" + plantCode + '\'' +
                ", plantName='" + plantName + '\'' +
                ", describe='" + describe + '\'' +
                ", factoryCode='" + factoryCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
