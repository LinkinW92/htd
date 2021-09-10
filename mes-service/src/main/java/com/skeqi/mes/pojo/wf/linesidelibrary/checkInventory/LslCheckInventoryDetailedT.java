package com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
    * 线边库盘点库存详情表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-LslCheckInventoryDetailedT")
public class LslCheckInventoryDetailedT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 行号
    */
    @ApiModelProperty(value="行号")
    private String number;

    /**
    * 盘点单号
    */
    @ApiModelProperty(value="盘点单号")
    private String checkNumber;

    /**
    * 物料编码
    */
    @ApiModelProperty(value="物料编码")
    private String materialCode;

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
    * 工单
    */
    @ApiModelProperty(value="工单")
    private String workOrder;

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
     * 详情明细
     */
    @ApiModelProperty(value="详情明细")
    private List<LslCheckInventoryDetailedDetailT> detailedDetailTList;

    public List<LslCheckInventoryDetailedDetailT> getDetailedDetailTList() {
        return detailedDetailTList;
    }

    public void setDetailedDetailTList(List<LslCheckInventoryDetailedDetailT> detailedDetailTList) {
        this.detailedDetailTList = detailedDetailTList;
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

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode  = materialCode;
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

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
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
        sb.append(", checkNumber=").append(checkNumber);
        sb.append(", materialCode=").append(materialCode);
        sb.append(", realQuantity=").append(realQuantity);
        sb.append(", warehouseQuantity=").append(warehouseQuantity);
        sb.append(", discrepancyQuantity=").append(discrepancyQuantity);
        sb.append(", workOrder=").append(workOrder);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
