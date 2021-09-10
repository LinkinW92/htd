package com.skeqi.mes.pojo.wf.linesidelibrary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 物料请求记录明细详情表
 * @author Lenovo
 */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-CLslMaterialRequestDetailT")
public class CLslMaterialRequestDetailT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

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
    * 料架id
    */
    @ApiModelProperty(value="料架id")
    private Integer rockId;

    /**
     * 料格号
     */
    @ApiModelProperty(value="料格号")
    private Integer rockNo;

    /**
     * 料架号
     */
    @ApiModelProperty(value="料架号")
    private Integer rackNo;


    /**
    * 物料编码
    */
    @ApiModelProperty(value="物料编码")
    private String materialNo;

    /**
    * 物料名称
    */
    @ApiModelProperty(value="物料名称")
    private String materialName;

    /**
    * 需求数量
    */
    @ApiModelProperty(value="需求数量")
    private Integer requiredQuantity;

    /**
    * 要料请求单据号
    */
    @ApiModelProperty(value="要料请求单据号")
    private String billNo;

    /**
     * 追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)
     */
    @ApiModelProperty(value = "追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)")
    private String tracesType;

    /**
     * 实际总数
     */
    @ApiModelProperty(value = "实际总数")
    private Integer sumQuantity;

    public Integer getRockNo() {
        return rockNo;
    }

    public void setRockNo(Integer rockNo) {
        this.rockNo = rockNo;
    }

    public Integer getRackNo() {
        return rackNo;
    }

    public void setRackNo(Integer rackNo) {
        this.rackNo = rackNo;
    }

    public Integer getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Integer sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

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



    public Integer getRockId() {
        return rockId;
    }

    public void setRockId(Integer rockId) {
        this.rockId = rockId;
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

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTracesType() {
        return tracesType;
    }

    public void setTracesType(String tracesType) {
        this.tracesType = tracesType;
    }
}
