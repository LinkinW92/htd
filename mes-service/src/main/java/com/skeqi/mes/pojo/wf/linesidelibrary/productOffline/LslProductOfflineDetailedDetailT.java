package com.skeqi.mes.pojo.wf.linesidelibrary.productOffline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 线边库产品下线详情明细表
 */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-productOffline-LslProductOfflineDetailedDetailT")
public class LslProductOfflineDetailedDetailT {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 明细单号
     */
    @ApiModelProperty(value="明细单号")
    private String number;

    /**
     * 详情编号
     */
    @ApiModelProperty(value="详情编号")
    private String detailedNumber;

    /**
     * 下线数量
     */
    @ApiModelProperty(value="下线数量")
    private Integer quantity;

    /**
     * 包装号
     */
    @ApiModelProperty(value="包装号")
    private String packNo;

    /**
     * 总成号
     */
    @ApiModelProperty(value="总成号")
    private String materialSn;

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
     * 物料编码
     */
    @ApiModelProperty(value="物料编码")
    private String materialCode;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetailedNumber() {
        return detailedNumber;
    }

    public void setDetailedNumber(String detailedNumber) {
        this.detailedNumber = detailedNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPackNo() {
        return packNo;
    }

    public void setPackNo(String packNo) {
        this.packNo = packNo;
    }

    public String getMaterialSn() {
        return materialSn;
    }

    public void setMaterialSn(String materialSn) {
        this.materialSn = materialSn;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", detailedNumber=").append(detailedNumber);
        sb.append(", quantity=").append(quantity);
        sb.append(", packNo=").append(packNo);
        sb.append(", materialSn=").append(materialSn);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
