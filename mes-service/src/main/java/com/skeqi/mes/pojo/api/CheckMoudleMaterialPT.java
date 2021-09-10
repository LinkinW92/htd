package com.skeqi.mes.pojo.api;

public class CheckMoudleMaterialPT {
	// 入参
	private String materialBarcode;
	private String stationInName;
	private Integer stepNo;
	private String lineName;
	private String snMaterial;
	// 出参
	private Integer rMaterial;
	// 临时变量
	private String tempMaterialVr; // ------物料校验规则
	private String tempMaterialCheckFlag; // -----是否校验标记
	private String tempMaterialName;// -----物料名称
	private String tempMaterialNumR;// -----物料批次号
	private String tempExceptionMsg;// ----错误信息
	private String tempExactorno;// ----是否 精追
	private Integer tempKepartcountP;
	private String tempMaterialScdType;// ----物料扫描类别
	private Integer tempKepartcountR;
	private String tempMaterialSecondNum;// ---物料唯一码
	private String tempTrackingEnginesn;
	private String stationType;
	public String getMaterialBarcode() {
		return materialBarcode;
	}
	public void setMaterialBarcode(String materialBarcode) {
		this.materialBarcode = materialBarcode;
	}
	public String getStationInName() {
		return stationInName;
	}
	public void setStationInName(String stationInName) {
		this.stationInName = stationInName;
	}
	public Integer getStepNo() {
		return stepNo;
	}
	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getSnMaterial() {
		return snMaterial;
	}
	public void setSnMaterial(String snMaterial) {
		this.snMaterial = snMaterial;
	}
	public Integer getrMaterial() {
		return rMaterial;
	}
	public void setrMaterial(Integer rMaterial) {
		this.rMaterial = rMaterial;
	}
	public String getTempMaterialVr() {
		return tempMaterialVr;
	}
	public void setTempMaterialVr(String tempMaterialVr) {
		this.tempMaterialVr = tempMaterialVr;
	}
	public String getTempMaterialCheckFlag() {
		return tempMaterialCheckFlag;
	}
	public void setTempMaterialCheckFlag(String tempMaterialCheckFlag) {
		this.tempMaterialCheckFlag = tempMaterialCheckFlag;
	}
	public String getTempMaterialName() {
		return tempMaterialName;
	}
	public void setTempMaterialName(String tempMaterialName) {
		this.tempMaterialName = tempMaterialName;
	}
	public String getTempMaterialNumR() {
		return tempMaterialNumR;
	}
	public void setTempMaterialNumR(String tempMaterialNumR) {
		this.tempMaterialNumR = tempMaterialNumR;
	}
	public String getTempExceptionMsg() {
		return tempExceptionMsg;
	}
	public void setTempExceptionMsg(String tempExceptionMsg) {
		this.tempExceptionMsg = tempExceptionMsg;
	}
	public String getTempExactorno() {
		return tempExactorno;
	}
	public void setTempExactorno(String tempExactorno) {
		this.tempExactorno = tempExactorno;
	}
	public Integer getTempKepartcountP() {
		return tempKepartcountP;
	}
	public void setTempKepartcountP(Integer tempKepartcountP) {
		this.tempKepartcountP = tempKepartcountP;
	}
	public String getTempMaterialScdType() {
		return tempMaterialScdType;
	}
	public void setTempMaterialScdType(String tempMaterialScdType) {
		this.tempMaterialScdType = tempMaterialScdType;
	}
	public Integer getTempKepartcountR() {
		return tempKepartcountR;
	}
	public void setTempKepartcountR(Integer tempKepartcountR) {
		this.tempKepartcountR = tempKepartcountR;
	}
	public String getTempMaterialSecondNum() {
		return tempMaterialSecondNum;
	}
	public void setTempMaterialSecondNum(String tempMaterialSecondNum) {
		this.tempMaterialSecondNum = tempMaterialSecondNum;
	}
	public String getTempTrackingEnginesn() {
		return tempTrackingEnginesn;
	}
	public void setTempTrackingEnginesn(String tempTrackingEnginesn) {
		this.tempTrackingEnginesn = tempTrackingEnginesn;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
}
