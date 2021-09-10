package com.skeqi.mes.pojo.chenj.srm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbutting
 * @Description ${Description}
 */

/**
 * K3采购对接日志表
 */
@ApiModel(value = "com-skeqi-pojo-chenj-CSrmKThreePurchaseAbutting")
public class CSrmKThreePurchaseAbutting {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 单据号
     */
    @ApiModelProperty(value = "单据号")
    private String doNumber;

    /**
     * 采购需求申请号
     */
    @ApiModelProperty(value = "采购需求申请号")
    private String requestCode;

    /**
     * 采购订单号
     */
    @ApiModelProperty(value = "采购订单号")
    private String orderNumber;

    /**
     * 送货单单号
     */
    @ApiModelProperty(value = "送货单单号")
    private String deliveryNumber;


    /**
     * 单据类型((1.采购申请、2.采购订单、3.送货单))
     */
    @ApiModelProperty(value = "单据类型((1.采购申请、2.采购订单、3.送货单))")
    private String doType;

    /**
     * 变更类型(1.创建、2.修改、3.删除、4.入库(送货单))
     */
    @ApiModelProperty(value = "变更类型(1.创建、2.修改、3.删除、4.入库(送货单))")
    private String alterType;

    /**
     * 变更状态(1.待处理、2.已变更)
     */
    @ApiModelProperty(value = "变更状态(1.待处理、2.已变更)")
    private String alterStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoNumber() {
        return doNumber;
    }

    public void setDoNumber(String doNumber) {
        this.doNumber = doNumber;
        this.requestCode = doNumber;
        this.orderNumber = doNumber;
        this.deliveryNumber = doNumber;
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

    public String getAlterStatus() {
        return alterStatus;
    }

    public void setAlterStatus(String alterStatus) {
        this.alterStatus = alterStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    @Override
    public String toString() {
        return "CSrmKThreePurchaseAbutting{" +
                "id=" + id +
                ", doNumber='" + doNumber + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", doType='" + doType + '\'' +
                ", alterType='" + alterType + '\'' +
                ", alterStatus='" + alterStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
