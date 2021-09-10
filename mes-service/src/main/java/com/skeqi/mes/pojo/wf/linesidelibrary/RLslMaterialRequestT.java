package com.skeqi.mes.pojo.wf.linesidelibrary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * 线边仓物料请求表
 */
@ApiModel(value = "com-skeqi-pojo-wf-linesidelibrary-RLslMaterialRequestT")
public class RLslMaterialRequestT {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date cdt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date udt;

    /**
     * 单据号
     */
    @ApiModelProperty(value = "单据号")
    private String billNo;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Integer productId;

    /**
     * 产线id
     */
    @ApiModelProperty(value = "产线id")
    private Integer lineId;

    /**
     * 工位id
     */
    @ApiModelProperty(value="工位id")
    private Integer stationId;

    /**
     * 状态 0 待处理、1 拣货中、2 已出库、3 已确认
     */
    @ApiModelProperty(value = "状态 0 待处理、1 拣货中、2 已出库、3 已确认")
    private Integer status;



    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String creator;

    /**
     * 捡料员
     */
    @ApiModelProperty(value = "捡料员")
    private String picker;

    /**
     * 捡料时间
     */
    @ApiModelProperty(value = "捡料时间")
    private String pickTime;

    /**
     * 开始捡料时间
     */
    @ApiModelProperty(value = "开始捡料时间")
    private String startPickTime;

    /**
     * 收料员
     */
    @ApiModelProperty(value = "收料员")
    private String collector;

    /**
     * 收料时间
     */
    @ApiModelProperty(value = "收料时间")
    private Date collectTime;

    /**
     * 单据流水号
     */
    @ApiModelProperty(value = "单据流水号")
    private String serialNumber;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String productionName;

    /**
     * 产线名称
     */
    @ApiModelProperty(value = "产线名称")
    private String lineName;

    /**
     * 工位名称
     */
    @ApiModelProperty(value = "工位名称")
    private String stationName;

    /**
     * 待拣明细统计
     */
    @ApiModelProperty(value = "待拣明细统计")
    private Integer detailCount;


    /**
     * 单据任务需求总数
     */
    @ApiModelProperty(value = "单据任务需求总数")
    private Integer sumRequiredQuantity;

    /**
     * 工位喇叭唯一标识
     */
    @ApiModelProperty(value="工位喇叭唯一标识")
    private String trumpet;

    /**
     * 工位喇叭播放次数
     */
    @ApiModelProperty(value="工位喇叭播放次数")
    private String loopSum;

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
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;


    /**
     * 拒绝人员
     */
    @ApiModelProperty(value="拒绝人员")
    private String rejecter;


    /**
     * 拒绝时间
     */
    @ApiModelProperty(value="拒绝时间")
    private Date refusedTime;

    /**
     * 拒绝描述
     */
    @ApiModelProperty(value="拒绝描述")
    private String refuseDescribe;


    public String getLoopSum() {
        return loopSum;
    }

    public void setLoopSum(String loopSum) {
        this.loopSum = loopSum;
    }

    public String getStartPickTime() {
        return startPickTime;
    }

    public void setStartPickTime(String startPickTime) {
        this.startPickTime = startPickTime;
    }

    public String getRejecter() {
        return rejecter;
    }

    public void setRejecter(String rejecter) {
        this.rejecter = rejecter;
    }

    public Date getRefusedTime() {
        return refusedTime;
    }

    public void setRefusedTime(Date refusedTime) {
        this.refusedTime = refusedTime;
    }

    public String getRefuseDescribe() {
        return refuseDescribe;
    }

    public void setRefuseDescribe(String refuseDescribe) {
        this.refuseDescribe = refuseDescribe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTrumpet() {
        return trumpet;
    }

    public void setTrumpet(String trumpet) {
        this.trumpet = trumpet;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    public Integer getSumRequiredQuantity() {
        return sumRequiredQuantity;
    }

    public void setSumRequiredQuantity(Integer sumRequiredQuantity) {
        this.sumRequiredQuantity = sumRequiredQuantity;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "RLslMaterialRequestT{" +
                "id=" + id +
                ", cdt=" + cdt +
                ", udt=" + udt +
                ", billNo='" + billNo + '\'' +
                ", productId=" + productId +
                ", lineId=" + lineId +
                ", status=" + status +
                ", creator='" + creator + '\'' +
                ", picker='" + picker + '\'' +
                ", pickTime=" + pickTime +
                ", collector='" + collector + '\'' +
                ", collectTime=" + collectTime +
                '}';
    }
}
