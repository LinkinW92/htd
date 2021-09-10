package com.skeqi.mes.pojo.api;

public class CheckSnPT {

	//记录条数
	private Integer tempTrackingCount;
	//工位记录条数
	private Integer tempStationCount;
	//产品工艺路线记录条数
	private Integer tempPwayCount;
	//返修工艺路线记录条数
	private Integer tempReworkWayCount;
	//
	private Integer tempTrackingId;
	//产品是否返工
	private String tempTrackingEnginesn;
	//产品状态
	private String tempTrackingStatus;
	//产品id
	private Integer tempTrackingProductionId;
	//经过工位下标
	private String tempTrackingGearbosxn;
	//上一个工位
	private String tempTrackingStationName;
	//工位id
	private Integer tempStationId;
	//工位名称
	private String tempStationName;
	//工位下标
	private Integer tempStationIndex;
	//是否线外站
	private String tempStationType;
	//工位业务类型
	private String tempStationAutoornot;
	//产线id
	private Integer tempStationLineId;
	//是否末站
	private String tempStationEndornot;
	//返修工位
	private Integer tempReworkStationId;
	//返修工位顺序
	private Integer tempReworkStationSerialNo;
	//返修路线前一工位
	private String tempReworkBeforeName;
	//返修前一工位顺序
	private Integer tempReworkBeforeSerialNo;
	//配方数量
	private Integer tempRecipeCount;
	//工艺路线工位ID
	private Integer tempPwayStationId;
	//产品工艺路线顺序
	private Integer tempPwayStationSerialNo;
	//路线前工位名称
	private String tempPwayBeforeStationName;
	//产品工艺路线前工位顺序
	private Integer tempPwayBeforeSerialNo;
	//当前计划ID
	private Integer tempPlanId;
	private Integer tempOnlineNumber;
	private String tempTrackingReworkFlag;
	private Integer tempPTrackingCout;
	//产品在线、离线标记
	private String tempOnOff;
	private Integer tempWorkOrderId;
	private Integer tempWorkOrderOffline;
	//工艺路线工位末站
	private Integer tempProductionSerialMax;
	//工艺路线工位首站
	private Integer tempProductionSerialMin;


	public Integer getTempTrackingCount() {
		return tempTrackingCount;
	}
	public void setTempTrackingCount(Integer tempTrackingCount) {
		this.tempTrackingCount = tempTrackingCount;
	}
	public Integer getTempStationCount() {
		return tempStationCount;
	}
	public void setTempStationCount(Integer tempStationCount) {
		this.tempStationCount = tempStationCount;
	}
	public Integer getTempPwayCount() {
		return tempPwayCount;
	}
	public void setTempPwayCount(Integer tempPwayCount) {
		this.tempPwayCount = tempPwayCount;
	}
	public Integer getTempReworkWayCount() {
		return tempReworkWayCount;
	}
	public void setTempReworkWayCount(Integer tempReworkWayCount) {
		this.tempReworkWayCount = tempReworkWayCount;
	}
	public Integer getTempTrackingId() {
		return tempTrackingId;
	}
	public void setTempTrackingId(Integer tempTrackingId) {
		this.tempTrackingId = tempTrackingId;
	}
	public String getTempTrackingEnginesn() {
		return tempTrackingEnginesn;
	}
	public void setTempTrackingEnginesn(String tempTrackingEnginesn) {
		this.tempTrackingEnginesn = tempTrackingEnginesn;
	}
	public String getTempTrackingStatus() {
		return tempTrackingStatus;
	}
	public void setTempTrackingStatus(String tempTrackingStatus) {
		this.tempTrackingStatus = tempTrackingStatus;
	}
	public Integer getTempTrackingProductionId() {
		return tempTrackingProductionId;
	}
	public void setTempTrackingProductionId(Integer tempTrackingProductionId) {
		this.tempTrackingProductionId = tempTrackingProductionId;
	}

	public String getTempTrackingGearbosxn() {
		return tempTrackingGearbosxn;
	}
	public void setTempTrackingGearbosxn(String tempTrackingGearbosxn) {
		this.tempTrackingGearbosxn = tempTrackingGearbosxn;
	}
	public String getTempTrackingStationName() {
		return tempTrackingStationName;
	}
	public void setTempTrackingStationName(String tempTrackingStationName) {
		this.tempTrackingStationName = tempTrackingStationName;
	}
	public Integer getTempStationId() {
		return tempStationId;
	}
	public void setTempStationId(Integer tempStationId) {
		this.tempStationId = tempStationId;
	}
	public String getTempStationName() {
		return tempStationName;
	}
	public void setTempStationName(String tempStationName) {
		this.tempStationName = tempStationName;
	}
	public Integer getTempStationIndex() {
		return tempStationIndex;
	}
	public void setTempStationIndex(Integer tempStationIndex) {
		this.tempStationIndex = tempStationIndex;
	}
	public String getTempStationType() {
		return tempStationType;
	}
	public void setTempStationType(String tempStationType) {
		this.tempStationType = tempStationType;
	}
	public String getTempStationAutoornot() {
		return tempStationAutoornot;
	}
	public void setTempStationAutoornot(String tempStationAutoornot) {
		this.tempStationAutoornot = tempStationAutoornot;
	}
	public Integer getTempStationLineId() {
		return tempStationLineId;
	}
	public void setTempStationLineId(Integer tempStationLineId) {
		this.tempStationLineId = tempStationLineId;
	}
	public String getTempStationEndornot() {
		return tempStationEndornot;
	}
	public void setTempStationEndornot(String tempStationEndornot) {
		this.tempStationEndornot = tempStationEndornot;
	}
	public Integer getTempReworkStationId() {
		return tempReworkStationId;
	}
	public void setTempReworkStationId(Integer tempReworkStationId) {
		this.tempReworkStationId = tempReworkStationId;
	}
	public Integer getTempReworkStationSerialNo() {
		return tempReworkStationSerialNo;
	}
	public void setTempReworkStationSerialNo(Integer tempReworkStationSerialNo) {
		this.tempReworkStationSerialNo = tempReworkStationSerialNo;
	}
	public String getTempReworkBeforeName() {
		return tempReworkBeforeName;
	}
	public void setTempReworkBeforeName(String tempReworkBeforeName) {
		this.tempReworkBeforeName = tempReworkBeforeName;
	}
	public Integer getTempReworkBeforeSerialNo() {
		return tempReworkBeforeSerialNo;
	}
	public void setTempReworkBeforeSerialNo(Integer tempReworkBeforeSerialNo) {
		this.tempReworkBeforeSerialNo = tempReworkBeforeSerialNo;
	}
	public Integer getTempRecipeCount() {
		return tempRecipeCount;
	}
	public void setTempRecipeCount(Integer tempRecipeCount) {
		this.tempRecipeCount = tempRecipeCount;
	}
	public Integer getTempPwayStationId() {
		return tempPwayStationId;
	}
	public void setTempPwayStationId(Integer tempPwayStationId) {
		this.tempPwayStationId = tempPwayStationId;
	}
	public Integer getTempPwayStationSerialNo() {
		return tempPwayStationSerialNo;
	}
	public void setTempPwayStationSerialNo(Integer tempPwayStationSerialNo) {
		this.tempPwayStationSerialNo = tempPwayStationSerialNo;
	}
	public String getTempPwayBeforeStationName() {
		return tempPwayBeforeStationName;
	}
	public void setTempPwayBeforeStationName(String tempPwayBeforeStationName) {
		this.tempPwayBeforeStationName = tempPwayBeforeStationName;
	}
	public Integer getTempPwayBeforeSerialNo() {
		return tempPwayBeforeSerialNo;
	}
	public void setTempPwayBeforeSerialNo(Integer tempPwayBeforeSerialNo) {
		this.tempPwayBeforeSerialNo = tempPwayBeforeSerialNo;
	}
	public Integer getTempPlanId() {
		return tempPlanId;
	}
	public void setTempPlanId(Integer tempPlanId) {
		this.tempPlanId = tempPlanId;
	}
	public Integer getTempOnlineNumber() {
		return tempOnlineNumber;
	}
	public void setTempOnlineNumber(Integer tempOnlineNumber) {
		this.tempOnlineNumber = tempOnlineNumber;
	}
	public String getTempTrackingReworkFlag() {
		return tempTrackingReworkFlag;
	}
	public void setTempTrackingReworkFlag(String tempTrackingReworkFlag) {
		this.tempTrackingReworkFlag = tempTrackingReworkFlag;
	}
	public Integer getTempPTrackingCout() {
		return tempPTrackingCout;
	}
	public void setTempPTrackingCout(Integer tempPTrackingCout) {
		this.tempPTrackingCout = tempPTrackingCout;
	}
	public String getTempOnOff() {
		return tempOnOff;
	}
	public void setTempOnOff(String tempOnOff) {
		this.tempOnOff = tempOnOff;
	}
	public Integer getTempWorkOrderId() {
		return tempWorkOrderId;
	}
	public void setTempWorkOrderId(Integer tempWorkOrderId) {
		this.tempWorkOrderId = tempWorkOrderId;
	}
	public Integer getTempWorkOrderOffline() {
		return tempWorkOrderOffline;
	}
	public void setTempWorkOrderOffline(Integer tempWorkOrderOffline) {
		this.tempWorkOrderOffline = tempWorkOrderOffline;
	}
	public Integer getTempProductionSerialMax() {
		return tempProductionSerialMax;
	}
	public void setTempProductionSerialMax(Integer tempProductionSerialMax) {
		this.tempProductionSerialMax = tempProductionSerialMax;
	}
	public Integer getTempProductionSerialMin() {
		return tempProductionSerialMin;
	}
	public void setTempProductionSerialMin(Integer tempProductionSerialMin) {
		this.tempProductionSerialMin = tempProductionSerialMin;
	}
	@Override
	public String toString() {
		return "CheckSnPT [tempTrackingCount=" + tempTrackingCount + ", tempStationCount=" + tempStationCount
				+ ", tempPwayCount=" + tempPwayCount + ", tempReworkWayCount=" + tempReworkWayCount
				+ ", tempTrackingId=" + tempTrackingId + ", tempTrackingEnginesn=" + tempTrackingEnginesn
				+ ", tempTrackingStatus=" + tempTrackingStatus + ", tempTrackingProductionId="
				+ tempTrackingProductionId + ", tempTrackingGearbosxn=" + tempTrackingGearbosxn
				+ ", tempTrackingStationName=" + tempTrackingStationName + ", tempStationId=" + tempStationId
				+ ", tempStationName=" + tempStationName + ", tempStationIndex=" + tempStationIndex
				+ ", tempStationType=" + tempStationType + ", tempStationAutoornot=" + tempStationAutoornot
				+ ", tempStationLineId=" + tempStationLineId + ", tempStationEndornot=" + tempStationEndornot
				+ ", tempReworkStationId=" + tempReworkStationId + ", tempReworkStationSerialNo="
				+ tempReworkStationSerialNo + ", tempReworkBeforeName=" + tempReworkBeforeName
				+ ", tempReworkBeforeSerialNo=" + tempReworkBeforeSerialNo + ", tempRecipeCount=" + tempRecipeCount
				+ ", tempPwayStationId=" + tempPwayStationId + ", tempPwayStationSerialNo=" + tempPwayStationSerialNo
				+ ", tempPwayBeforeStationName=" + tempPwayBeforeStationName + ", tempPwayBeforeSerialNo="
				+ tempPwayBeforeSerialNo + ", tempPlanId=" + tempPlanId + ", tempOnlineNumber=" + tempOnlineNumber
				+ ", tempTrackingReworkFlag=" + tempTrackingReworkFlag + ", tempPTrackingCout=" + tempPTrackingCout
				+ ", tempOnOff=" + tempOnOff + ", tempWorkOrderId=" + tempWorkOrderId + ", tempWorkOrderOffline="
				+ tempWorkOrderOffline + ", tempProductionSerialMax=" + tempProductionSerialMax
				+ ", tempProductionSerialMin=" + tempProductionSerialMin + "]";
	}
	public CheckSnPT() {
		super();
	}
	public CheckSnPT(Integer tempTrackingCount, Integer tempStationCount, Integer tempPwayCount,
			Integer tempReworkWayCount, Integer tempTrackingId, String tempTrackingEnginesn, String tempTrackingStatus,
			Integer tempTrackingProductionId, String tempTrackingGearbosxn, String tempTrackingStationName,
			Integer tempStationId, String tempStationName, Integer tempStationIndex, String tempStationType,
			String tempStationAutoornot, Integer tempStationLineId, String tempStationEndornot,
			Integer tempReworkStationId, Integer tempReworkStationSerialNo, String tempReworkBeforeName,
			Integer tempReworkBeforeSerialNo, Integer tempRecipeCount, Integer tempPwayStationId,
			Integer tempPwayStationSerialNo, String tempPwayBeforeStationName, Integer tempPwayBeforeSerialNo,
			Integer tempPlanId, Integer tempOnlineNumber, String tempTrackingReworkFlag, Integer tempPTrackingCout,
			String tempOnOff, Integer tempWorkOrderId, Integer tempWorkOrderOffline, Integer tempProductionSerialMax,
			Integer tempProductionSerialMin) {
		super();
		this.tempTrackingCount = tempTrackingCount;
		this.tempStationCount = tempStationCount;
		this.tempPwayCount = tempPwayCount;
		this.tempReworkWayCount = tempReworkWayCount;
		this.tempTrackingId = tempTrackingId;
		this.tempTrackingEnginesn = tempTrackingEnginesn;
		this.tempTrackingStatus = tempTrackingStatus;
		this.tempTrackingProductionId = tempTrackingProductionId;
		this.tempTrackingGearbosxn = tempTrackingGearbosxn;
		this.tempTrackingStationName = tempTrackingStationName;
		this.tempStationId = tempStationId;
		this.tempStationName = tempStationName;
		this.tempStationIndex = tempStationIndex;
		this.tempStationType = tempStationType;
		this.tempStationAutoornot = tempStationAutoornot;
		this.tempStationLineId = tempStationLineId;
		this.tempStationEndornot = tempStationEndornot;
		this.tempReworkStationId = tempReworkStationId;
		this.tempReworkStationSerialNo = tempReworkStationSerialNo;
		this.tempReworkBeforeName = tempReworkBeforeName;
		this.tempReworkBeforeSerialNo = tempReworkBeforeSerialNo;
		this.tempRecipeCount = tempRecipeCount;
		this.tempPwayStationId = tempPwayStationId;
		this.tempPwayStationSerialNo = tempPwayStationSerialNo;
		this.tempPwayBeforeStationName = tempPwayBeforeStationName;
		this.tempPwayBeforeSerialNo = tempPwayBeforeSerialNo;
		this.tempPlanId = tempPlanId;
		this.tempOnlineNumber = tempOnlineNumber;
		this.tempTrackingReworkFlag = tempTrackingReworkFlag;
		this.tempPTrackingCout = tempPTrackingCout;
		this.tempOnOff = tempOnOff;
		this.tempWorkOrderId = tempWorkOrderId;
		this.tempWorkOrderOffline = tempWorkOrderOffline;
		this.tempProductionSerialMax = tempProductionSerialMax;
		this.tempProductionSerialMin = tempProductionSerialMin;
	}



}
