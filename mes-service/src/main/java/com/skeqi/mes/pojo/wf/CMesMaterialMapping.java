package com.skeqi.mes.pojo.wf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
    * 物料映射表
    */
@ApiModel(value="com-skeqi-pojo-wf-CMesMaterialMapping")
public class CMesMaterialMapping implements Serializable {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 供应商名称
    */
    @ApiModelProperty(value="供应商名称")
    private String supplierName;

    /**
     * 供应商物料编码
     */
    @ApiModelProperty(value="供应商物料编码")
    private String supplierMaterialCode;

    /**
     * 物料编码
     */
    @ApiModelProperty(value="物料编码")
    private String materialCode;

    /**
    * 供应商批次号
    */
    @ApiModelProperty(value="供应商批次号")
    private String supplierBatch;

    /**
    * 批次号
    */
    @ApiModelProperty(value="批次号")
    private String batch;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierMaterialCode() {
        return supplierMaterialCode;
    }

    public void setSupplierMaterialCode(String supplierMaterialCode) {
        this.supplierMaterialCode = supplierMaterialCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getSupplierBatch() {
        return supplierBatch;
    }

    public void setSupplierBatch(String supplierBatch) {
        this.supplierBatch = supplierBatch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", supplierBatch=").append(supplierBatch);
        sb.append(", batch=").append(batch);
        sb.append("]");
        return sb.toString();
    }
}
