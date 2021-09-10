package com.skeqi.mes.pojo.wf.productionQuality.dataCollection.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 数据收集组定义表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionQuality-dataCollection-MesDataCollectionGroupT")
public class MesDataCollectionGroupT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 组号
    */
    @ApiModelProperty(value="组号")
    private String number;

    /**
    * 组名称
    */
    @ApiModelProperty(value="组名称")
    private String name;

    /**
    * 适用范围
    */
    @ApiModelProperty(value="适用范围")
    private String range;

    /**
     * 适用范围名称
     */
    @ApiModelProperty(value="适用范围名称")
    private String rangeName;


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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
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
        sb.append(", range=").append(range);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
