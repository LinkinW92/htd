package com.skeqi.mes.pojo.wf.linesidelibrary.pack;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
    * 包装明细表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-pack-LslPackDetailT")
public class LslPackDetailT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 容器号
    */
    @ApiModelProperty(value="容器号")
    private String containerno;

    /**
    * 行号
    */
    @ApiModelProperty(value="行号")
    private String lineno;

    /**
    * 总成
    */
    @ApiModelProperty(value="总成")
    private String sn;

    /**
    * 数量
    */
    @ApiModelProperty(value="数量")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContainerno() {
        return containerno;
    }

    public void setContainerno(String containerno) {
        this.containerno = containerno;
    }

    public String getLineno() {
        return lineno;
    }

    public void setLineno(String lineno) {
        this.lineno = lineno;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", containerno=").append(containerno);
        sb.append(", lineno=").append(lineno);
        sb.append(", sn=").append(sn);
        sb.append(", quantity=").append(quantity);
        sb.append("]");
        return sb.toString();
    }
}
