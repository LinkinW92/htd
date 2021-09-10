package com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
    * 数据收集记录明细表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionQuality-dataCollection-record-MesDataCollectionRecordDetailT")
public class MesDataCollectionRecordDetailT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 数据收集记录编号
    */
    @ApiModelProperty(value="数据收集记录编号")
    private String recordNumber;

    /**
    * 参数编号
    */
    @ApiModelProperty(value="参数编号")
    private String paramsNumber;

    /**
    * 参数名称
    */
    @ApiModelProperty(value="参数名称")
    private String paramsName;

    /**
    * 实际值
    */
    @ApiModelProperty(value="实际值")
    private String value;

    /**
     * 上限
     */
    @ApiModelProperty(value="上限")
    private String upperLimit;

    /**
     * 下限
     */
    @ApiModelProperty(value="下限")
    private String lowerLimit;


    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date cdt;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date udt;

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getParamsNumber() {
        return paramsNumber;
    }

    public void setParamsNumber(String paramsNumber) {
        this.paramsNumber = paramsNumber;
    }

    public String getParamsName() {
        return paramsName;
    }

    public void setParamsName(String paramsName) {
        this.paramsName = paramsName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCdt() {
        return cdt;
    }

    public void setCdt(Date cdt) {
        this.cdt = cdt;
    }

    public Date getUdt() {
        return udt;
    }

    public void setUdt(Date udt) {
        this.udt = udt;
    }

    @Override
    public String toString() {
        return "MesDataCollectionRecordDetailT{" +
                "id=" + id +
                ", recordNumber='" + recordNumber + '\'' +
                ", paramsNumber='" + paramsNumber + '\'' +
                ", paramsName='" + paramsName + '\'' +
                ", value='" + value + '\'' +
                ", cdt=" + cdt +
                ", udt=" + udt +
                '}';
    }
}
