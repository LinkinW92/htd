package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityH
 * @Description ${Description}
 */

/**
 * 物料行入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialLineReq {


    @ApiModelProperty(value = "物料号")
    private String materialNumber;

    @ApiModelProperty(value = "行号")
    private String lineNumber;

    @ApiModelProperty(value = "实收数")
    private String paNumber;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getPaNumber() {
        return paNumber;
    }

    public void setPaNumber(String paNumber) {
        this.paNumber = paNumber;
    }

    @Override
    public String toString() {
        return "MaterialLineReq{" +
                "materialNumber='" + materialNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", paNumber='" + paNumber + '\'' +
                '}';
    }
}
