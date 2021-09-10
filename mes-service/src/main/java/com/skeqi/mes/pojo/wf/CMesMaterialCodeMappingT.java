package com.skeqi.mes.pojo.wf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
    * 物料编号映射表
    */
@ApiModel(value="com-skeqi-pojo-wf-CMesMaterialCodeMappingT")
public class CMesMaterialCodeMappingT implements Serializable {
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

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", supplierMaterialCode=").append(supplierMaterialCode);
        sb.append(", materialCode=").append(materialCode);
        sb.append("]");
        return sb.toString();
    }
}
