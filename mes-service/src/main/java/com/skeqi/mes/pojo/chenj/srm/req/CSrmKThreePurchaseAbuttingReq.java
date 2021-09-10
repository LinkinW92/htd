package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbutting
 * @Description ${Description}
 */

/**
    * K3采购对接日志表(入参)
    */
@ApiModel(value="K3采购对接日志入参")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmKThreePurchaseAbuttingReq {

    /**
    * 单据号
    */
    @ApiModelProperty(value="单据号")
    private String doNumber;

    /**
    * 单据类型((0.采购申请、1.采购订单、2.送货单))
    */
    @ApiModelProperty(value="单据类型((0.采购申请、1.采购订单、2.送货单))")
    private String doType;

    /**
    * 变更类型(1.创建、2.修改、3.删除、4.入库(送货单))
    */
    @ApiModelProperty(value="变更类型(1.创建、2.修改、3.删除、4.入库(送货单))")
    private String alterType;


    public String getDoNumber() {
        return doNumber;
    }

    public void setDoNumber(String doNumber) {
        this.doNumber = doNumber;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getAlterType() {
        return alterType;
    }

    public void setAlterType(String alterType) {
        this.alterType = alterType;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", doNumber=").append(doNumber);
        sb.append(", doType=").append(doType);
        sb.append(", alterType=").append(alterType);
        sb.append("]");
        return sb.toString();
    }
}
