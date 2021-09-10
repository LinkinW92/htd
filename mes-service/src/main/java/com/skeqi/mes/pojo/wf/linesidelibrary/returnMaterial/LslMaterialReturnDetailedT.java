package com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
    * 线边库退料详情表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-returnMaterial-LslMaterialReturnDetailedT")
public class LslMaterialReturnDetailedT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 详情单号
    */
    @ApiModelProperty(value="详情单号")
    private String number;

    /**
    * 退料单号
    */
    @ApiModelProperty(value="退料单号")
    private String returnNumber;

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
    * 物料编号
    */
    @ApiModelProperty(value="物料编号")
    private String materialCode;

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

    /**
    * 工单
    */
    @ApiModelProperty(value="工单")
    private String workOrder;

    /**
     * 详情明细集合
     */
    @ApiModelProperty(value="详情明细集合")
    private List<LslMaterialReturnDetailedDetailT> detailedDetailTList;

    public List<LslMaterialReturnDetailedDetailT> getDetailedDetailTList() {
        return detailedDetailTList;
    }

    public void setDetailedDetailTList(List<LslMaterialReturnDetailedDetailT> detailedDetailTList) {
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

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", returnNumber=").append(returnNumber);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append(", materialCode=").append(materialCode);
        sb.append(", quantity=").append(quantity);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", workOrder=").append(workOrder);
        sb.append("]");
        return sb.toString();
    }
}
