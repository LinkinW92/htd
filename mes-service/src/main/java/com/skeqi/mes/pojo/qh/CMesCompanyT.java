package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
    * 公司表
    */
@ApiModel(value="com-skeqi-pojo-qh-CMesCompanyT")
public class CMesCompanyT implements Serializable {
    /**
    * 公司id
    */
    @ApiModelProperty(value="公司id")
    private Integer id;

    /**
    * 公司编号
    */
    @ApiModelProperty(value="公司编号")
    private String companyCode;

    /**
    * 公司名称
    */
    @ApiModelProperty(value="公司名称")
    private String companyName;

    /**
     * 公司地址
     */
    @ApiModelProperty(value="公司地址")
    private String companyAddress;

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
    * 公司描述
    */
    @ApiModelProperty(value="公司描述")
    private String describe;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private String dateTime;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private String alterDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String datetime) {
        this.dateTime = datetime;
    }

    public String getAlterDt() {
        return alterDt;
    }

    public void setAlterDt(String alterDt) {
        this.alterDt = alterDt;
    }

    @Override
    public String toString() {
        return "CMesCompanyT{" +
                "id=" + id +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", describe='" + describe + '\'' +
                ", datetime=" + dateTime +
                ", alterDt=" + alterDt +
                '}';
    }
}
