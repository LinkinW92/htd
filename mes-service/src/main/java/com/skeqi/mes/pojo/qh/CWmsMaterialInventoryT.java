package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
    * 物料库存数量记录表
    */
@ApiModel(value="com-skeqi-pojo-qh-CWmsMaterialInventoryT")
public class CWmsMaterialInventoryT implements Serializable {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 物料编号
    */
    @ApiModelProperty(value="物料编号")
    private String materialNo;

    /**
    * 物料名称
    */
    @ApiModelProperty(value="物料名称")
    private String materialName;

    /**
    * 物料数量
    */
    @ApiModelProperty(value="物料数量")
    private Integer materialNumber;

    /**
    * 记录日期
    */
    @ApiModelProperty(value="记录日期")
    private Date inventoryTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public Date getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(Date inventoryTime) {
        this.inventoryTime = inventoryTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", materialNo=").append(materialNo);
        sb.append(", materialName=").append(materialName);
        sb.append(", materialNumber=").append(materialNumber);
        sb.append(", inventoryTime=").append(inventoryTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CWmsMaterialInventoryT other = (CWmsMaterialInventoryT) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMaterialNo() == null ? other.getMaterialNo() == null : this.getMaterialNo().equals(other.getMaterialNo()))
            && (this.getMaterialName() == null ? other.getMaterialName() == null : this.getMaterialName().equals(other.getMaterialName()))
            && (this.getMaterialNumber() == null ? other.getMaterialNumber() == null : this.getMaterialNumber().equals(other.getMaterialNumber()))
            && (this.getInventoryTime() == null ? other.getInventoryTime() == null : this.getInventoryTime().equals(other.getInventoryTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMaterialNo() == null) ? 0 : getMaterialNo().hashCode());
        result = prime * result + ((getMaterialName() == null) ? 0 : getMaterialName().hashCode());
        result = prime * result + ((getMaterialNumber() == null) ? 0 : getMaterialNumber().hashCode());
        result = prime * result + ((getInventoryTime() == null) ? 0 : getInventoryTime().hashCode());
        return result;
    }
}
