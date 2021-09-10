package com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库退料表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-returnMaterial-LslMaterialReturnT")
public class LslMaterialReturnT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 退料单号
    */
    @ApiModelProperty(value="退料单号")
    private String number;

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
    * 退料类型（0 余料退料，1 不良退料）
    */
    @ApiModelProperty(value="退料类型（0 余料退料，1 不良退料）")
    private Integer type;

    /**
    * 状态(0 待处理，1已处理 )
    */
    @ApiModelProperty(value="状态(0 待处理，1已处理 )")
    private Integer status;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String creator;

    /**
    * 修改者
    */
    @ApiModelProperty(value="修改者")
    private String mender;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remarks;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", mender=").append(mender);
        sb.append(", remarks=").append(remarks);
        sb.append("]");
        return sb.toString();
    }
}
