package com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * SN不合格记录
    */
@ApiModel(value="com-skeqi-pojo-wf-productionquality-nccodemanager-record-MesNcCodeRecordT")
public class MesNcCodeRecordT {
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private String number;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date cdt;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Date udt;

    /**
    * 总成号
    */
    @ApiModelProperty(value="总成号")
    private String sn;

    /**
    * 不合格编码
    */
    @ApiModelProperty(value="不合格编码")
    private String ncCode;

    /**
    * 记录人员
    */
    @ApiModelProperty(value="记录人员")
    private String staff;

    /**
    * 状态（0 开启，1关闭）
    */
    @ApiModelProperty(value="状态（0 开启，1关闭）")
    private Integer status;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getNcCode() {
        return ncCode;
    }

    public void setNcCode(String ncCode) {
        this.ncCode = ncCode;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
