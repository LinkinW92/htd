package com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库盘点库存表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-LslCheckInventoryT")
public class LslCheckInventoryT {
    @ApiModelProperty(value="")
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
    * 盘点单号
    */
    @ApiModelProperty(value="盘点单号")
    private String number;

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
    * 状态（0新建，1完成）
    */
    @ApiModelProperty(value="状态（0新建，1完成）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append(", number=").append(number);
        sb.append(", creator=").append(creator);
        sb.append(", mender=").append(mender);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
