package com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 数据收集记录表
    */
@ApiModel(value="com-skeqi-pojo-wf-productionQuality-dataCollection-record-MesDataCollectionRecordT")
public class MesDataCollectionRecordT {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 编号
    */
    @ApiModelProperty(value="编号")
    private String number;

    /**
    * 所属组号
    */
    @ApiModelProperty(value="所属组号")
    private String grouNumber;

    /**
    * 记录人员
    */
    @ApiModelProperty(value="记录人员")
    private String staff;

    /**
    * 工序
    */
    @ApiModelProperty(value="工序")
    private String process;

    /**
    * 设备
    */
    @ApiModelProperty(value="设备")
    private String equipment;

    /**
    * 总成号
    */
    @ApiModelProperty(value="总成号")
    private String sn;

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

    public String getGrouNumber() {
        return grouNumber;
    }

    public void setGrouNumber(String grouNumber) {
        this.grouNumber = grouNumber;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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
        sb.append(", grouNumber=").append(grouNumber);
        sb.append(", staff=").append(staff);
        sb.append(", process=").append(process);
        sb.append(", equipment=").append(equipment);
        sb.append(", sn=").append(sn);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
