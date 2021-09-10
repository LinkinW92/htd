package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesStationT {
    // (#{id},#{stationIndex},#{stationName},#{stationProcessok},#{stationDataok},
    // #{stationType},#{stationRecipeornot},#{stationAgvornot},#{stationRequstoutline},#{stationLightornot},
    // #{stationRequstin},#{stationReviewornot},#{stationPrintornot},#{stationUploadmes},#{stationEndornot},
    // #{stationGunornot},#{stationAutoornot},#{stationTime},'',#{lineId},#{stationScanderFlag},#{stationCcdFlag},#{stationRubberFlag},
    // #{stationLeakageFlag},#{stationEolFlag},#{stationAdamFlag},#{stationPlcFlag}

    // 28个字段
    @ApiModelProperty(value = "产线名称", required = false)
    private String lineName;
    // 27个字段
    private Integer id;
    @ApiModelProperty(value = "所属产线ID", required = false)
    private Integer lineId;
    @ApiModelProperty(value = "工位下标", required = false)
    private Integer stationIndex;
    @ApiModelProperty(value = "工位名称", required = false)
    private String stationName;
    @ApiModelProperty(value = "流程检查", required = false)
    private Integer stationProcessok;
    @ApiModelProperty(value = "数据检查", required = false)
    private Integer stationDataok;
    @ApiModelProperty(value = "工位类型", required = false)
    private Integer stationType;
    @ApiModelProperty(value = "是否需要配方", required = false)
    private Integer stationRecipeornot;
    @ApiModelProperty(value = "是否需要AGV", required = false)
    private Integer stationAgvornot;
    @ApiModelProperty(value = "是否出站效验", required = false)
    private Integer stationRequstoutline;
    @ApiModelProperty(value = "是否点亮放行灯", required = false)
    private Integer stationLightornot;
    @ApiModelProperty(value = "是否请求进站", required = false)
    private Integer stationRequstin;
    @ApiModelProperty(value = "是否追溯", required = false)
    private Integer stationReviewornot;
    @ApiModelProperty(value = "是否打印", required = false)
    private Integer stationPrintornot;
    @ApiModelProperty(value = "是否上传MES", required = false)
    private Integer stationUploadmes;
    @ApiModelProperty(value = "是否末站", required = false)
    private Integer stationEndornot;
    @ApiModelProperty(value = "是否有拧紧枪", required = false)
    private Integer stationGunornot;
    @ApiModelProperty(value = "站业务属性", required = false)
    private Integer stationAutoornot;
    @ApiModelProperty(value = "工位节拍", required = false)
    private Integer stationTime;
    @ApiModelProperty(value = "")
    private String stationFlowornot;
    @ApiModelProperty(value = "是否有扫描枪", required = false)
    private Integer stationScanderFlag;
    @ApiModelProperty(value = "是否有视觉系统", required = false)
    private Integer stationCcdFlag;
    @ApiModelProperty(value = "是否有涂胶系统", required = false)
    private Integer stationRubberFlag;
    @ApiModelProperty(value = "是否有气密测试", required = false)
    private Integer stationLeakageFlag;
    @ApiModelProperty(value = "是否有EOL设备", required = false)
    private Integer stationEolFlag;
    @ApiModelProperty(value = "是否办卡", required = false)
    private Integer stationAdamFlag;
    @ApiModelProperty(value = "是否有PLC", required = false)
    private Integer stationPlcFlag;
    @ApiModelProperty(value = "ip", required = false)
    private String ip;
    @ApiModelProperty(value = "userName", required = false)
    private String userName;// VARCHAR2(5)
    @ApiModelProperty(value = "password", required = false)
    private String password;// VARCHAR2(5)

    @ApiModelProperty(value = "工位喇叭唯一标识", required = false)
    private String trumpet;

    @ApiModelProperty(value = "工位喇叭播放次数", required = false)
    private String loopSum;

    @ApiModelProperty(value = "是否开启缺料自动轮询", required = false)
    private String automaticPolling;

    public String getAutomaticPolling() {
        return automaticPolling;
    }

    public void setAutomaticPolling(String automaticPolling) {
        this.automaticPolling = automaticPolling;
    }

    public String getLoopSum() {
        return loopSum;
    }

    public void setLoopSum(String loopSum) {
        this.loopSum = loopSum;
    }

    public String getTrumpet() {
        return trumpet;
    }

    public void setTrumpet(String trumpet) {
        this.trumpet = trumpet;
    }

    private String stationName1;
    private Integer stationIndex1;
    // API脚本专用渠道
    @ApiModelProperty(value = "所属产线外部ID", required = false)
    private String lineCode;


    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(Integer stationIndex) {
        this.stationIndex = stationIndex;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStationProcessok() {
        return stationProcessok;
    }

    public void setStationProcessok(Integer stationProcessok) {
        this.stationProcessok = stationProcessok;
    }

    public Integer getStationDataok() {
        return stationDataok;
    }

    public void setStationDataok(Integer stationDataok) {
        this.stationDataok = stationDataok;
    }

    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    public Integer getStationRecipeornot() {
        return stationRecipeornot;
    }

    public void setStationRecipeornot(Integer stationRecipeornot) {
        this.stationRecipeornot = stationRecipeornot;
    }

    public Integer getStationAgvornot() {
        return stationAgvornot;
    }

    public void setStationAgvornot(Integer stationAgvornot) {
        this.stationAgvornot = stationAgvornot;
    }

    public Integer getStationRequstoutline() {
        return stationRequstoutline;
    }

    public void setStationRequstoutline(Integer stationRequstoutline) {
        this.stationRequstoutline = stationRequstoutline;
    }

    public Integer getStationLightornot() {
        return stationLightornot;
    }

    public void setStationLightornot(Integer stationLightornot) {
        this.stationLightornot = stationLightornot;
    }

    public Integer getStationRequstin() {
        return stationRequstin;
    }

    public void setStationRequstin(Integer stationRequstin) {
        this.stationRequstin = stationRequstin;
    }

    public Integer getStationReviewornot() {
        return stationReviewornot;
    }

    public void setStationReviewornot(Integer stationReviewornot) {
        this.stationReviewornot = stationReviewornot;
    }

    public Integer getStationPrintornot() {
        return stationPrintornot;
    }

    public void setStationPrintornot(Integer stationPrintornot) {
        this.stationPrintornot = stationPrintornot;
    }

    public Integer getStationUploadmes() {
        return stationUploadmes;
    }

    public void setStationUploadmes(Integer stationUploadmes) {
        this.stationUploadmes = stationUploadmes;
    }

    public Integer getStationEndornot() {
        return stationEndornot;
    }

    public void setStationEndornot(Integer stationEndornot) {
        this.stationEndornot = stationEndornot;
    }

    public Integer getStationGunornot() {
        return stationGunornot;
    }

    public void setStationGunornot(Integer stationGunornot) {
        this.stationGunornot = stationGunornot;
    }

    public Integer getStationAutoornot() {
        return stationAutoornot;
    }

    public void setStationAutoornot(Integer stationAutoornot) {
        this.stationAutoornot = stationAutoornot;
    }

    public Integer getStationTime() {
        return stationTime;
    }

    public void setStationTime(Integer stationTime) {
        this.stationTime = stationTime;
    }

    public String getStationFlowornot() {
        return stationFlowornot;
    }

    public void setStationFlowornot(String stationFlowornot) {
        this.stationFlowornot = stationFlowornot;
    }

    public Integer getStationScanderFlag() {
        return stationScanderFlag;
    }

    public void setStationScanderFlag(Integer stationScanderFlag) {
        this.stationScanderFlag = stationScanderFlag;
    }

    public Integer getStationCcdFlag() {
        return stationCcdFlag;
    }

    public void setStationCcdFlag(Integer stationCcdFlag) {
        this.stationCcdFlag = stationCcdFlag;
    }

    public Integer getStationRubberFlag() {
        return stationRubberFlag;
    }

    public void setStationRubberFlag(Integer stationRubberFlag) {
        this.stationRubberFlag = stationRubberFlag;
    }

    public Integer getStationLeakageFlag() {
        return stationLeakageFlag;
    }

    public void setStationLeakageFlag(Integer stationLeakageFlag) {
        this.stationLeakageFlag = stationLeakageFlag;
    }

    public Integer getStationEolFlag() {
        return stationEolFlag;
    }

    public void setStationEolFlag(Integer stationEolFlag) {
        this.stationEolFlag = stationEolFlag;
    }

    public Integer getStationAdamFlag() {
        return stationAdamFlag;
    }

    public void setStationAdamFlag(Integer stationAdamFlag) {
        this.stationAdamFlag = stationAdamFlag;
    }

    public Integer getStationPlcFlag() {
        return stationPlcFlag;
    }

    public void setStationPlcFlag(Integer stationPlcFlag) {
        this.stationPlcFlag = stationPlcFlag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }


    public CMesStationT() {
        super();
    }

    @Override
    public String toString() {
        return "CMesStationT [id=" + id + ", lineId=" + lineId + ", stationIndex=" + stationIndex + ", stationName="
                + stationName + ", stationProcessok=" + stationProcessok + ", stationDataok=" + stationDataok
                + ", stationType=" + stationType + ", stationRecipeornot=" + stationRecipeornot + ", stationAgvornot="
                + stationAgvornot + ", stationRequstoutline=" + stationRequstoutline + ", stationLightornot="
                + stationLightornot + ", stationRequstin=" + stationRequstin + ", stationReviewornot="
                + stationReviewornot + ", stationPrintornot=" + stationPrintornot + ", stationUploadmes="
                + stationUploadmes + ", stationEndornot=" + stationEndornot + ", stationGunornot=" + stationGunornot
                + ", stationAutoornot=" + stationAutoornot + ", stationTime=" + stationTime + ", stationFlowornot="
                + stationFlowornot + ", lineName=" + lineName + ", stationScanderFlag=" + stationScanderFlag
                + ", stationCcdFlag=" + stationCcdFlag + ", stationRubberFlag=" + stationRubberFlag
                + ", stationLeakageFlag=" + stationLeakageFlag + ", stationEolFlag=" + stationEolFlag
                + ", stationAdamFlag=" + stationAdamFlag + ", stationPlcFlag=" + stationPlcFlag + ", lineCode=" + lineCode + "]";
    }

    public String getStationName1() {
        return stationName1;
    }

    public void setStationName1(String stationName1) {
        this.stationName1 = stationName1;
    }

    public Integer getStationIndex1() {
        return stationIndex1;
    }

    public void setStationIndex1(Integer stationIndex1) {
        this.stationIndex1 = stationIndex1;
    }

}
