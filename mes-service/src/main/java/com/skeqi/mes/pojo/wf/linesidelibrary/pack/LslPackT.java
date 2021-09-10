package com.skeqi.mes.pojo.wf.linesidelibrary.pack;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 包装表
    */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-pack-LslPackT")
public class LslPackT {
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
    * 容器类型id
    */
    @ApiModelProperty(value="容器类型id")
    private Integer containerId;

    /**
    * 容器号
    */
    @ApiModelProperty(value="容器号")
    private String containerNo;

    /**
    * 打包者
    */
    @ApiModelProperty(value="打包者")
    private String packager;

    /**
    * 打包产线
    */
    @ApiModelProperty(value="打包产线")
    private Integer lineId;

    /**
    * 状态 0 新建 1 已完成
    */
    @ApiModelProperty(value="状态 0 新建 1 已完成")
    private Integer status;

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

    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getPackager() {
        return packager;
    }

    public void setPackager(String packager) {
        this.packager = packager;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
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
        sb.append(", containerId=").append(containerId);
        sb.append(", containerNo=").append(containerNo);
        sb.append(", packager=").append(packager);
        sb.append(", lineId=").append(lineId);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
