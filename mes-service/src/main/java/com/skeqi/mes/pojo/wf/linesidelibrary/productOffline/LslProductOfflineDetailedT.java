package com.skeqi.mes.pojo.wf.linesidelibrary.productOffline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库产品下线详情表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-productOffline-LslProductOfflineDetailedT")
public class LslProductOfflineDetailedT {
    /**
    * id
    */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
    * 明细单号
    */
    @ApiModelProperty(value="明细单号")
    private String number;

    /**
    * 下线单号
    */
    @ApiModelProperty(value="下线单号")
    private String offlineNumber;

    /**
    * 下线数量
    */
    @ApiModelProperty(value="下线数量")
    private Integer quantity;

    /**
    * 物料编号
    */
    @ApiModelProperty(value="物料编号")
    private String materialCode;

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

    public String getOfflineNumber() {
        return offlineNumber;
    }

    public void setOfflineNumber(String offlineNumber) {
        this.offlineNumber = offlineNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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
        sb.append(", offlineNumber=").append(offlineNumber);
        sb.append(", quantity=").append(quantity);
        sb.append(", materialCode=").append(materialCode);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
