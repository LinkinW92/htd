package com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库盘点库存详情明细表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-LslCheckInventoryDetailedDetailT")
public class LslCheckInventoryDetailedDetailT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 明细编号
    */
    @ApiModelProperty(value="明细编号")
    private String number;

    /**
    * 行号
    */
    @ApiModelProperty(value="行号")
    private String detailedNumber;

    /**
    * 实际数量
    */
    @ApiModelProperty(value="实际数量")
    private Integer realQuantity;

    /**
    * 系统数量
    */
    @ApiModelProperty(value="系统数量")
    private Integer warehouseQuantity;

    /**
    * 差异数量
    */
    @ApiModelProperty(value="差异数量")
    private Integer discrepancyQuantity;

    /**
    * 物料总成号
    */
    @ApiModelProperty(value="物料总成号")
    private String materialSn;

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

    public String getDetailedNumber() {
        return detailedNumber;
    }

    public void setDetailedNumber(String detailedNumber) {
        this.detailedNumber = detailedNumber;
    }

    public Integer getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(Integer realQuantity) {
        this.realQuantity = realQuantity;
    }

    public Integer getWarehouseQuantity() {
        return warehouseQuantity;
    }

    public void setWarehouseQuantity(Integer warehouseQuantity) {
        this.warehouseQuantity = warehouseQuantity;
    }

    public Integer getDiscrepancyQuantity() {
        return discrepancyQuantity;
    }

    public void setDiscrepancyQuantity(Integer discrepancyQuantity) {
        this.discrepancyQuantity = discrepancyQuantity;
    }

    public String getMaterialSn() {
        return materialSn;
    }

    public void setMaterialSn(String materialNo) {
        this.materialSn = materialNo;
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
        sb.append(", detailedNumber=").append(detailedNumber);
        sb.append(", realQuantity=").append(realQuantity);
        sb.append(", warehouseQuantity=").append(warehouseQuantity);
        sb.append(", discrepancyQuantity=").append(discrepancyQuantity);
        sb.append(", materialSn=").append(materialSn);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
