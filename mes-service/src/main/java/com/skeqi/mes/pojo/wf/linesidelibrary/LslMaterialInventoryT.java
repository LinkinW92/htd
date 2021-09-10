package com.skeqi.mes.pojo.wf.linesidelibrary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value = "com-skeqi-pojo-wf-linesidelibrary-LslMaterialInventoryT")
public class LslMaterialInventoryT {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date cdt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date udt;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Integer productId;

    /**
     * 产线id
     */
    @ApiModelProperty(value = "产线id")
    private Integer lineId;

    /**
     * 工位id
     */
    @ApiModelProperty(value = "工位id")
    private Integer stationId;

    /**
     * 批次条码
     */
    @ApiModelProperty(value = "批次条码")
    private String batchCode;

    /**
     * 物料条码
     */
    @ApiModelProperty(value = "物料条码")
    private String materialCode;

    /**
     * 物料编号
     */
    @ApiModelProperty(value = "物料编号")
    private String materialNo;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;

    /**
     * 物料类型 0 混合 1 批次 2 单个
     */
    @ApiModelProperty(value = "物料类型 0 混合 1 批次 2 单个")
    private Integer type;

    /**
     * 精确数量
     */
    @ApiModelProperty(value = "精确数量")
    private Integer quantity;

    /**
     * 状态 1 正常, 2 冻结
     */
    @ApiModelProperty(value = "状态 1 正常, 2 冻结")
    private Integer status;

    /**
     * 料架id
     */
    @ApiModelProperty(value = "料架id")
    private Integer rockId;

    /**
     * 项目号
     */
    @ApiModelProperty(value = "项目号")
    private String projectNo;

    /**
     * 冻结数量
     */
    @ApiModelProperty(value = "冻结数量")
    private Integer frozenQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRockId() {
        return rockId;
    }

    public void setRockId(Integer rockId) {
        this.rockId = rockId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Integer getFrozenQuantity() {
        return frozenQuantity;
    }

    public void setFrozenQuantity(Integer frozenQuantity) {
        this.frozenQuantity = frozenQuantity;
    }
}

