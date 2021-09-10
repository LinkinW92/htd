package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityR;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityH
 * @Description ${Description}
 */

/**
 * 收货单入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiveTheActualReceiptReq {


    @ApiModelProperty(value = "入库单")
    private String deliveryNumber;

    @ApiModelProperty(value = "收货人名称")
    private String consigneeName;

    @ApiModelProperty(value = "处理标记(1、已完成2、拒绝)")
    private String disposeSign;

    @ApiModelProperty(value = "物料行信息")
    private List<CSrmSendCommodityR> materialLine;

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }
    public String getDisposeSign() {
        return disposeSign;
    }

    public void setDisposeSign(String disposeSign) {
        this.disposeSign = disposeSign;
    }

    public List<CSrmSendCommodityR> getMaterialLine() {
        return materialLine;
    }

    public void setMaterialLine(List<CSrmSendCommodityR> materialLine) {
        this.materialLine = materialLine;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    @Override
    public String toString() {
        return "ReceiveTheActualReceiptReq{" +
                "deliveryNumber='" + deliveryNumber + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", disposeSign='" + disposeSign + '\'' +
                ", materialLine=" + materialLine +
                '}';
    }
}
