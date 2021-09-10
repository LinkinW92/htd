package com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 不合格编码表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionquality-nccodemanager-config-MesNcCodeConfigT")
public class MesNcCodeConfigT {
    @ApiModelProperty(value="")
    private Integer id;

    /**
    * 不合格编码
    */
    @ApiModelProperty(value="不合格编码")
    private String code;

    /**
    * 编码描述
    */
    @ApiModelProperty(value="编码描述")
    private String describe;

    /**
    * 不合格编码类别
    */
    @ApiModelProperty(value="不合格编码类别")
    private String category;

    /**
    * 适用工序
    */
    @ApiModelProperty(value="适用工序")
    private Integer process;

    /**
    * 特殊管控(是否拦截 0 否，1 是)
    */
    @ApiModelProperty(value="特殊管控(是否拦截 0 否，1 是)")
    private Integer specialControl;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getSpecialControl() {
        return specialControl;
    }

    public void setSpecialControl(Integer specialControl) {
        this.specialControl = specialControl;
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
        sb.append(", code=").append(code);
        sb.append(", describe=").append(describe);
        sb.append(", category=").append(category);
        sb.append(", process=").append(process);
        sb.append(", specialControl=").append(specialControl);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
