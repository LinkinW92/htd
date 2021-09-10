package com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库退料详情明细表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-returnMaterial-LslMaterialReturnDetailedDetailT")
public class LslMaterialReturnDetailedDetailT {
    @ApiModelProperty(value="")
    private Integer id;

    /**
    * 明细单号
    */
    @ApiModelProperty(value="明细单号")
    private String number;

    /**
    * 详情单号
    */
    @ApiModelProperty(value="详情单号")
    private String detailedNumber;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date cdt;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date udt;

    /**
    * 物料条码
    */
    @ApiModelProperty(value="物料条码")
    private String materialSn;

    /**
    * 退料数量
    */
    @ApiModelProperty(value="退料数量")
    private Integer quantity;

    /**
    * 库存数量
    */
    @ApiModelProperty(value="库存数量")
    private Integer stockQuantity;

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

    public String getDetailedNumber() {
        return detailedNumber;
    }

    public void setDetailedNumber(String detailedNumber) {
        this.detailedNumber = detailedNumber;
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

    public String getMaterialSn() {
        return materialSn;
    }

    public void setMaterialSn(String materialSn) {
        this.materialSn = materialSn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", detailedNumber=").append(detailedNumber);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append(", materialSn=").append(materialSn);
        sb.append(", quantity=").append(quantity);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append("]");
        return sb.toString();
    }
}
