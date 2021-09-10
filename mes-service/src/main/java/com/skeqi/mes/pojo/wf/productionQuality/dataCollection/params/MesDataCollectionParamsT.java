package com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 数据收集组参数清单表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionQuality-dataCollection-MesDataCollectionParamsT")
public class MesDataCollectionParamsT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 编号
    */
    @ApiModelProperty(value="编号")
    private String number;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * 所属组号
    */
    @ApiModelProperty(value="所属组号")
    private String groupNumber;

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
    * 超限
    */
    @ApiModelProperty(value="超限")
    private String overrun;

    /**
    * 必收标记（0 true, 1 false）
    */
    @ApiModelProperty(value="必收标记（0 true, 1 false）")
    private Integer necessary;

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

    /**
     * 实际值
     */
    @ApiModelProperty(value="实际值")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

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

    public String getOverrun() {
        return overrun;
    }

    public void setOverrun(String overrun) {
        this.overrun = overrun;
    }

    public Integer getNecessary() {
        return necessary;
    }

    public void setNecessary(Integer necessary) {
        this.necessary = necessary;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", name=").append(name);
        sb.append(", groupNumber=").append(groupNumber);
        sb.append(", upperLimit=").append(upperLimit);
        sb.append(", lowerLimit=").append(lowerLimit);
        sb.append(", overrun=").append(overrun);
        sb.append(", necessary=").append(necessary);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
