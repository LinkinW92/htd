package com.skeqi.mes.pojo.wf.linesidelibrary.productOffline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 线边库产品下线表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-productOffline-LslProductOfflineT")
public class LslProductOfflineT {
    /**
    * id
    */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
    * 下线单号
    */
    @ApiModelProperty(value="下线单号")
    private String number;

    /**
    * 下线类型（0 批次，1 单件）
    */
    @ApiModelProperty(value="下线类型（0 批次，1 单件）")
    private Integer type;

    /**
    * 状态 1新建, 2 已提交, 3已过账, 4拒绝
    */
    @ApiModelProperty(value="状态 1新建, 2 已提交, 3已过账, 4拒绝")
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

    /**
    * 批次号
    */
    @ApiModelProperty(value="批次号")
    private String batch;

    /**
    * 下线产线
    */
    @ApiModelProperty(value="下线产线")
    private String offlineLine;

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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getOfflineLine() {
        return offlineLine;
    }

    public void setOfflineLine(String offlineLine) {
        this.offlineLine = offlineLine;
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
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", mender=").append(mender);
        sb.append(", remarks=").append(remarks);
        sb.append(", batch=").append(batch);
        sb.append(", offlineLine=").append(offlineLine);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
