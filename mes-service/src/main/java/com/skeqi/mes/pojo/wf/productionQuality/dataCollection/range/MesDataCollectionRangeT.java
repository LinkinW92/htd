package com.skeqi.mes.pojo.wf.productionQuality.dataCollection.range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 数据收集组适用范围表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionQuality-dataCollection-MesDataCollectionRangeT")
public class MesDataCollectionRangeT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 范围名称
     */
    @ApiModelProperty(value="范围名称")
    private String name;

    /**
    * 编号
    */
    @ApiModelProperty(value="编号")
    private String number;

    /**
    * 工序（* 所有）
    */
    @ApiModelProperty(value="工序（* 所有）")
    private String process;

    /**
    * 设备（* 所有）
    */
    @ApiModelProperty(value="设备（* 所有）")
    private String equipment;

    /**
    * 物料（* 所有）
    */
    @ApiModelProperty(value="物料（* 所有）")
    private String material;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        sb.append(", process=").append(process);
        sb.append(", equipment=").append(equipment);
        sb.append(", material=").append(material);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
