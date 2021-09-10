package com.skeqi.mes.pojo.api;

public class RMesNextBarcodePEcT {
	Integer id;
	Integer lineInId;
	Integer rPrint;// 0：成功  >1 失败
    String nextBarcode;
    String nextOneBarcode;
    String labelName;
    Integer tempPlanCount;//产线计划数量
    Integer tempPlanLevel_max;//最大优先级
    Integer tempPlanLevel;
    Integer tempPlanComditionCount;
    Integer tempPlanNumber;
    Integer tempPlanOnlineNumber;
    Integer tempCurrentPlanId;//工单id
    Integer tempPlanId;//计划id
    Integer tempPlanPrintCount;
    Integer tempAlarmMark;//没有计划标记
    String tempLabelRules;//条码标签
    String tempLabelVr;//条码日期和流水号格式
    Integer tempLabelTypeId;//条码类型id gb/t 34014 强制规定为id=1
    String tempLabelHead;//条码标签‘#’之前部分
    String tempLabelEnd;//条码标签‘#’之后部分
    String tempLabelYmd;//条码类型中月日年
    String tempLabelCode;//条码类型中流水号
    Integer tmepCodeSerialMax;
    Integer tempProductionId;
    String tempOldSnCode;
    Integer tempOldSnNum;
    String tempCurrentCreateYear;//处理后的年份
    String tmepCurrent_month;//当前月份
    String tempCurrentCreateDay;//处理后的天数
    String tempPrintFlag;//是否打印的标记
    String tempShift;//班次
    Integer tempMark;//标记
    String tempExceptionMsg;
    Integer tempDay;
    Integer tempYear;
    String tempLabelName;//标签名称
    String tempDate;
    String dt;
    String planName;
    String productionId;
    Integer planNumber;
    Integer completeNumber;
    Integer remaindNumber;
    Integer okNumber;
    Integer ngNumber;
    Integer lineId;
    Integer planLevel;
    String completeFlag;
    String opreationUser;
    String createBarcodeFlag;
    String planSerialno;
    Integer onlineNumber;

    Integer orderNumber;
    Integer offlineNumber;
    Integer deffectNumber;
    Integer teamId;
    Integer levelNo;
    Integer status;
    String alterDt;
    Integer planId;
    Integer printeNumber;
    String sfcCode;
    String lineCode;
    String sfcType;
    String oldSfcCode;
    String oldSnCode;
    String tempLabelTypeName;//规则类型名称
	public String getTempLabelTypeName() {
		return tempLabelTypeName;
	}
	public void setTempLabelTypeName(String tempLabelTypeName) {
		this.tempLabelTypeName = tempLabelTypeName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineInId() {
		return lineInId;
	}
	public void setLineInId(Integer lineInId) {
		this.lineInId = lineInId;
	}
	public Integer getrPrint() {
		return rPrint;
	}
	public void setrPrint(Integer rPrint) {
		this.rPrint = rPrint;
	}
	public String getNextBarcode() {
		return nextBarcode;
	}
	public void setNextBarcode(String nextBarcode) {
		this.nextBarcode = nextBarcode;
	}
	public String getNextOneBarcode() {
		return nextOneBarcode;
	}
	public void setNextOneBarcode(String nextOneBarcode) {
		this.nextOneBarcode = nextOneBarcode;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public Integer getTempPlanCount() {
		return tempPlanCount;
	}
	public void setTempPlanCount(Integer tempPlanCount) {
		this.tempPlanCount = tempPlanCount;
	}
	public Integer getTempPlanLevel_max() {
		return tempPlanLevel_max;
	}
	public void setTempPlanLevel_max(Integer tempPlanLevel_max) {
		this.tempPlanLevel_max = tempPlanLevel_max;
	}
	public Integer getTempPlanLevel() {
		return tempPlanLevel;
	}
	public void setTempPlanLevel(Integer tempPlanLevel) {
		this.tempPlanLevel = tempPlanLevel;
	}
	public Integer getTempPlanComditionCount() {
		return tempPlanComditionCount;
	}
	public void setTempPlanComditionCount(Integer tempPlanComditionCount) {
		this.tempPlanComditionCount = tempPlanComditionCount;
	}
	public Integer getTempPlanNumber() {
		return tempPlanNumber;
	}
	public void setTempPlanNumber(Integer tempPlanNumber) {
		this.tempPlanNumber = tempPlanNumber;
	}
	public Integer getTempPlanOnlineNumber() {
		return tempPlanOnlineNumber;
	}
	public void setTempPlanOnlineNumber(Integer tempPlanOnlineNumber) {
		this.tempPlanOnlineNumber = tempPlanOnlineNumber;
	}
	public Integer getTempCurrentPlanId() {
		return tempCurrentPlanId;
	}
	public void setTempCurrentPlanId(Integer tempCurrentPlanId) {
		this.tempCurrentPlanId = tempCurrentPlanId;
	}
	public Integer getTempPlanId() {
		return tempPlanId;
	}
	public void setTempPlanId(Integer tempPlanId) {
		this.tempPlanId = tempPlanId;
	}
	public Integer getTempPlanPrintCount() {
		return tempPlanPrintCount;
	}
	public void setTempPlanPrintCount(Integer tempPlanPrintCount) {
		this.tempPlanPrintCount = tempPlanPrintCount;
	}
	public Integer getTempAlarmMark() {
		return tempAlarmMark;
	}
	public void setTempAlarmMark(Integer tempAlarmMark) {
		this.tempAlarmMark = tempAlarmMark;
	}
	public String getTempLabelRules() {
		return tempLabelRules;
	}
	public void setTempLabelRules(String tempLabelRules) {
		this.tempLabelRules = tempLabelRules;
	}
	public String getTempLabelVr() {
		return tempLabelVr;
	}
	public void setTempLabelVr(String tempLabelVr) {
		this.tempLabelVr = tempLabelVr;
	}
	public Integer getTempLabelTypeId() {
		return tempLabelTypeId;
	}
	public void setTempLabelTypeId(Integer tempLabelTypeId) {
		this.tempLabelTypeId = tempLabelTypeId;
	}
	public String getTempLabelHead() {
		return tempLabelHead;
	}
	public void setTempLabelHead(String tempLabelHead) {
		this.tempLabelHead = tempLabelHead;
	}
	public String getTempLabelEnd() {
		return tempLabelEnd;
	}
	public void setTempLabelEnd(String tempLabelEnd) {
		this.tempLabelEnd = tempLabelEnd;
	}
	public String getTempLabelYmd() {
		return tempLabelYmd;
	}
	public void setTempLabelYmd(String tempLabelYmd) {
		this.tempLabelYmd = tempLabelYmd;
	}
	public String getTempLabelCode() {
		return tempLabelCode;
	}
	public void setTempLabelCode(String tempLabelCode) {
		this.tempLabelCode = tempLabelCode;
	}
	public Integer getTmepCodeSerialMax() {
		return tmepCodeSerialMax;
	}
	public void setTmepCodeSerialMax(Integer tmepCodeSerialMax) {
		this.tmepCodeSerialMax = tmepCodeSerialMax;
	}
	public Integer getTempProductionId() {
		return tempProductionId;
	}
	public void setTempProductionId(Integer tempProductionId) {
		this.tempProductionId = tempProductionId;
	}
	public String getTempOldSnCode() {
		return tempOldSnCode;
	}
	public void setTempOldSnCode(String tempOldSnCode) {
		this.tempOldSnCode = tempOldSnCode;
	}
	public Integer getTempOldSnNum() {
		return tempOldSnNum;
	}
	public void setTempOldSnNum(Integer tempOldSnNum) {
		this.tempOldSnNum = tempOldSnNum;
	}
	public String getTempCurrentCreateYear() {
		return tempCurrentCreateYear;
	}
	public void setTempCurrentCreateYear(String tempCurrentCreateYear) {
		this.tempCurrentCreateYear = tempCurrentCreateYear;
	}
	public String getTmepCurrent_month() {
		return tmepCurrent_month;
	}
	public void setTmepCurrent_month(String tmepCurrent_month) {
		this.tmepCurrent_month = tmepCurrent_month;
	}
	public String getTempCurrentCreateDay() {
		return tempCurrentCreateDay;
	}
	public void setTempCurrentCreateDay(String tempCurrentCreateDay) {
		this.tempCurrentCreateDay = tempCurrentCreateDay;
	}
	public String getTempPrintFlag() {
		return tempPrintFlag;
	}
	public void setTempPrintFlag(String tempPrintFlag) {
		this.tempPrintFlag = tempPrintFlag;
	}
	public String getTempShift() {
		return tempShift;
	}
	public void setTempShift(String tempShift) {
		this.tempShift = tempShift;
	}
	public Integer getTempMark() {
		return tempMark;
	}
	public void setTempMark(Integer tempMark) {
		this.tempMark = tempMark;
	}
	public String getTempExceptionMsg() {
		return tempExceptionMsg;
	}
	public void setTempExceptionMsg(String tempExceptionMsg) {
		this.tempExceptionMsg = tempExceptionMsg;
	}
	public Integer getTempDay() {
		return tempDay;
	}
	public void setTempDay(Integer tempDay) {
		this.tempDay = tempDay;
	}
	public Integer getTempYear() {
		return tempYear;
	}
	public void setTempYear(Integer tempYear) {
		this.tempYear = tempYear;
	}
	public String getTempLabelName() {
		return tempLabelName;
	}
	public void setTempLabelName(String tempLabelName) {
		this.tempLabelName = tempLabelName;
	}
	public String getTempDate() {
		return tempDate;
	}
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public Integer getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}
	public Integer getCompleteNumber() {
		return completeNumber;
	}
	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
	}
	public Integer getRemaindNumber() {
		return remaindNumber;
	}
	public void setRemaindNumber(Integer remaindNumber) {
		this.remaindNumber = remaindNumber;
	}
	public Integer getOkNumber() {
		return okNumber;
	}
	public void setOkNumber(Integer okNumber) {
		this.okNumber = okNumber;
	}
	public Integer getNgNumber() {
		return ngNumber;
	}
	public void setNgNumber(Integer ngNumber) {
		this.ngNumber = ngNumber;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getPlanLevel() {
		return planLevel;
	}
	public void setPlanLevel(Integer planLevel) {
		this.planLevel = planLevel;
	}
	public String getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}
	public String getOpreationUser() {
		return opreationUser;
	}
	public void setOpreationUser(String opreationUser) {
		this.opreationUser = opreationUser;
	}
	public String getCreateBarcodeFlag() {
		return createBarcodeFlag;
	}
	public void setCreateBarcodeFlag(String createBarcodeFlag) {
		this.createBarcodeFlag = createBarcodeFlag;
	}
	public String getPlanSerialno() {
		return planSerialno;
	}
	public void setPlanSerialno(String planSerialno) {
		this.planSerialno = planSerialno;
	}
	public Integer getOnlineNumber() {
		return onlineNumber;
	}
	public void setOnlineNumber(Integer onlineNumber) {
		this.onlineNumber = onlineNumber;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getOfflineNumber() {
		return offlineNumber;
	}
	public void setOfflineNumber(Integer offlineNumber) {
		this.offlineNumber = offlineNumber;
	}
	public Integer getDeffectNumber() {
		return deffectNumber;
	}
	public void setDeffectNumber(Integer deffectNumber) {
		this.deffectNumber = deffectNumber;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlterDt() {
		return alterDt;
	}
	public void setAlterDt(String alterDt) {
		this.alterDt = alterDt;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getPrinteNumber() {
		return printeNumber;
	}
	public void setPrinteNumber(Integer printeNumber) {
		this.printeNumber = printeNumber;
	}
	public String getSfcCode() {
		return sfcCode;
	}
	public void setSfcCode(String sfcCode) {
		this.sfcCode = sfcCode;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getSfcType() {
		return sfcType;
	}
	public void setSfcType(String sfcType) {
		this.sfcType = sfcType;
	}
	public String getOldSfcCode() {
		return oldSfcCode;
	}
	public void setOldSfcCode(String oldSfcCode) {
		this.oldSfcCode = oldSfcCode;
	}
	public String getOldSnCode() {
		return oldSnCode;
	}
	public void setOldSnCode(String oldSnCode) {
		this.oldSnCode = oldSnCode;
	}



}
