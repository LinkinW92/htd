package com.skeqi.mes.pojo.wf.linesidelibrary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 线边仓物料返回表
 * @author Lenovo
 */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-RLslMaterialResponseT")
public class RLslMaterialResponseT implements Serializable {
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
    * 要料请求详情Id
    */
    @ApiModelProperty(value="要料请求详情Id")
    private Integer requestDetailId;

    /**
     * 物料批次
     */
    @ApiModelProperty(value="物料批次")
    private String materialBatch;

    /**
    * 物料单个条码
    */
    @ApiModelProperty(value="物料单个条码")
    private String materialCode;

    /**
    * 实际数量
    */
    @ApiModelProperty(value="实际数量")
    private Integer quantity;


    /**
     * 物料条码集合
     */
    @ApiModelProperty(value="物料条码集合")
    private List<String> codeList;

    /**
     * 物料表的物料编号
     */
    @ApiModelProperty(value="物料表的物料编号")
    private String materialNo;

    /**
     * 物料实例表的自增id
     */
    @ApiModelProperty(value="物料实例表的自增id")
    private Integer materialId;

    /**
     * 物料实例表的剩余数量
     */
    @ApiModelProperty(value="物料实例表的剩余数量")
    private Integer numberRemaining;

    public Integer getNumberRemaining() {
        return numberRemaining;
    }

    public void setNumberRemaining(Integer numberRemaining) {
        this.numberRemaining = numberRemaining;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
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

    public Integer getRequestDetailId() {
        return requestDetailId;
    }

    public void setRequestDetailId(Integer requestId) {
        this.requestDetailId = requestId;
    }

    public String getMaterialBatch() {
        return materialBatch;
    }

    public void setMaterialBatch(String type) {
        this.materialBatch = type;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String code) {
        this.materialCode = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RLslMaterialResponseT{" +
                "id=" + id +
                ", cdt=" + cdt +
                ", udt=" + udt +
                ", requestDetailId=" + requestDetailId +
                ", materialBatch='" + materialBatch + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", quantity=" + quantity +
                ", codeList=" + codeList +
                ", materialNo='" + materialNo + '\'' +
                ", materialId=" + materialId +
                ", numberRemaining=" + numberRemaining +
                '}';
    }
}
