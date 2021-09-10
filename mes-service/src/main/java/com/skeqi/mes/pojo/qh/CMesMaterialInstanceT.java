package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
    * 物料实例表
    */
@ApiModel(value="com-skeqi-pojo-qh-CMesMaterialInstanceT")
public class CMesMaterialInstanceT implements Serializable {
    public CMesMaterialInstanceT() {
    }
    /**
    * 实例id
    */
    @ApiModelProperty(value="实例id")
    private Integer id;

    /**
    * 物料编码
    */
    @ApiModelProperty(value="物料编码")
    private String materialCode;

    /**
    * 物料批次
    */
    @ApiModelProperty(value="物料批次")
    private String materialBatch;

    /**
    * 物料SN
    */
    @ApiModelProperty(value="物料SN")
    private String materialSn;

    /**
    * 实例名称
    */
    @ApiModelProperty(value="实例名称")
    private String materialName;

    /**
    * 物料类型
    */
    @ApiModelProperty(value="物料类型")
    private Integer materialType;
    /**
     * 物料类型名称
     */
    @ApiModelProperty(value="物料类型名称")
    private String materialTypeName;

    /**
    * 物料有效期
    */
    @ApiModelProperty(value="物料有效期")
    private String instanceValidity;

    /**
    * 实例描述
    */
    @ApiModelProperty(value="实例描述")
    private String instanceDescription;

    /**
    * 事件id
    */
    @ApiModelProperty(value="事件id")
    private Integer eventId;

    /**
    * 耗损状态（0未消耗，1已消耗）
    */
    @ApiModelProperty(value="耗损状态（0未消耗，1已消耗）")
    private String wearState;

    /**
    * 批次数量
    */
    @ApiModelProperty(value="批次数量")
    private Integer materialNumber;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date dt;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Date alterDt;

    /**
     * 工单编号
     * @return
     */
    private String workOrderId;


    /**
     * 剩余数量
     */
    @ApiModelProperty(value="剩余数量")
    private Integer numberRemaining;

    /**
     * 拣货锁定数
     */
    @ApiModelProperty(value="拣货锁定数")
    private Integer pickingLockNumber;

    /**
     * 质量冻结数
     */
    @ApiModelProperty(value="质量冻结数")
    private Integer frozenNumber;
    /**
     * 库位id
     */
    @ApiModelProperty(value="库位id")

    private Integer locationId;

    public Integer getPickingLockNumber() {
        return pickingLockNumber;
    }

    public void setPickingLockNumber(Integer pickingLockNumber) {
        this.pickingLockNumber = pickingLockNumber;
    }

    public Integer getFrozenNumber() {
        return frozenNumber;
    }

    public void setFrozenNumber(Integer frozenNumber) {
        this.frozenNumber = frozenNumber;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getNumberRemaining() {
        return numberRemaining;
    }

    public void setNumberRemaining(Integer numberRemaining) {
        this.numberRemaining = numberRemaining;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialBatch() {
        return materialBatch;
    }

    public void setMaterialBatch(String materialBatch) {
        this.materialBatch = materialBatch;
    }

    public String getMaterialSn() {
        return materialSn;
    }

    public void setMaterialSn(String materialSn) {
        this.materialSn = materialSn;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getInstanceValidity() {
        return instanceValidity;
    }

    public void setInstanceValidity(String instanceValidity) {
        this.instanceValidity = instanceValidity;
    }

    public String getInstanceDescription() {
        return instanceDescription;
    }

    public void setInstanceDescription(String instanceDescription) {
        this.instanceDescription = instanceDescription;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getWearState() {
        return wearState;
    }

    public void setWearState(String wearState) {
        this.wearState = wearState;
    }

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Date getAlterDt() {
        return alterDt;
    }

    public void setAlterDt(Date alterDt) {
        this.alterDt = alterDt;
    }

    @Override
    public String toString() {
        return "CMesMaterialInstanceT{" +
                "id=" + id +
                ", materialCode='" + materialCode + '\'' +
                ", materialBatch='" + materialBatch + '\'' +
                ", materialSn='" + materialSn + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialType=" + materialType +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", instanceValidity='" + instanceValidity + '\'' +
                ", instanceDescription='" + instanceDescription + '\'' +
                ", eventId=" + eventId +
                ", wearState='" + wearState + '\'' +
                ", materialNumber=" + materialNumber +
                ", dt=" + dt +
                ", alterDt=" + alterDt +
                ", workOrderId='" + workOrderId + '\'' +
                '}';
    }
}
