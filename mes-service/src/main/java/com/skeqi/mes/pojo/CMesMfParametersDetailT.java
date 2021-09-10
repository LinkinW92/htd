package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesMfParametersDetailT {
	private Integer id;//                  INTEGER not null,
	private Date dt;//                  DATE,
	private String parameterNo;//       VARCHAR2(100),
	private String parameterName;//      VARCHAR2(200),
	private Integer parameterMainFlag;// INTEGER,
	private Integer parameterCheck;//     INTEGER,
	private String parameterReplace;//   VARCHAR2(150),
	private Integer screwNumber;//        INTEGER,
	private String normalT;//            VARCHAR2(50),
	private String tUpperLimit;//       VARCHAR2(50),
	private String tLowerLimit;//       VARCHAR2(50),
	private String normalA;//            VARCHAR2(50),
	private String aUpperLimit;//       VARCHAR2(50),
	private String aLowerLimit;//       VARCHAR2(50),
	private String otherMormalT;//      VARCHAR2(50),
	private String otherUpperLimit;//   VARCHAR2(50),
	private String otherLowerLimit;//   VARCHAR2(50),
	private Integer mfParametersId;//    INTEGER
	private String mfParametersName;
	private String stationName;
	private Integer stationId;


	public String getMfParametersName() {
		return mfParametersName;
	}
	public void setMfParametersName(String mfParametersName) {
		this.mfParametersName = mfParametersName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getParameterNo() {
		return parameterNo;
	}
	public void setParameterNo(String parameterNo) {
		this.parameterNo = parameterNo;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Integer getParameterMainFlag() {
		return parameterMainFlag;
	}
	public void setParameterMainFlag(Integer parameterMainFlag) {
		this.parameterMainFlag = parameterMainFlag;
	}
	public Integer getParameterCheck() {
		return parameterCheck;
	}
	public void setParameterCheck(Integer parameterCheck) {
		this.parameterCheck = parameterCheck;
	}
	public String getParameterReplace() {
		return parameterReplace;
	}
	public void setParameterReplace(String parameterReplace) {
		this.parameterReplace = parameterReplace;
	}
	public Integer getScrewNumber() {
		return screwNumber;
	}
	public void setScrewNumber(Integer screwNumber) {
		this.screwNumber = screwNumber;
	}
	public String getNormalT() {
		return normalT;
	}
	public void setNormalT(String normalT) {
		this.normalT = normalT;
	}
	public String gettUpperLimit() {
		return tUpperLimit;
	}
	public void settUpperLimit(String tUpperLimit) {
		this.tUpperLimit = tUpperLimit;
	}
	public String gettLowerLimit() {
		return tLowerLimit;
	}
	public void settLowerLimit(String tLowerLimit) {
		this.tLowerLimit = tLowerLimit;
	}
	public String getNormalA() {
		return normalA;
	}
	public void setNormalA(String normalA) {
		this.normalA = normalA;
	}
	public String getaUpperLimit() {
		return aUpperLimit;
	}
	public void setaUpperLimit(String aUpperLimit) {
		this.aUpperLimit = aUpperLimit;
	}
	public String getaLowerLimit() {
		return aLowerLimit;
	}
	public void setaLowerLimit(String aLowerLimit) {
		this.aLowerLimit = aLowerLimit;
	}
	public String getOtherMormalT() {
		return otherMormalT;
	}
	public void setOtherMormalT(String otherMormalT) {
		this.otherMormalT = otherMormalT;
	}
	public String getOtherUpperLimit() {
		return otherUpperLimit;
	}
	public void setOtherUpperLimit(String otherUpperLimit) {
		this.otherUpperLimit = otherUpperLimit;
	}
	public String getOtherLowerLimit() {
		return otherLowerLimit;
	}
	public void setOtherLowerLimit(String otherLowerLimit) {
		this.otherLowerLimit = otherLowerLimit;
	}
	public Integer getMfParametersId() {
		return mfParametersId;
	}
	public void setMfParametersId(Integer mfParametersId) {
		this.mfParametersId = mfParametersId;
	}
	@Override
	public String toString() {
		return "CMesMfParametersDetailT [id=" + id + ", dt=" + dt + ", parameterNo=" + parameterNo + ", parameterName="
				+ parameterName + ", parameterMainFlag=" + parameterMainFlag + ", parameterCheck=" + parameterCheck
				+ ", parameterReplace=" + parameterReplace + ", screwNumber=" + screwNumber + ", normalT=" + normalT
				+ ", tUpperLimit=" + tUpperLimit + ", tLowerLimit=" + tLowerLimit + ", normalA=" + normalA
				+ ", aUpperLimit=" + aUpperLimit + ", aLowerLimit=" + aLowerLimit + ", otherMormalT=" + otherMormalT
				+ ", otherUpperLimit=" + otherUpperLimit + ", otherLowerLimit=" + otherLowerLimit + ", mfParametersId="
				+ mfParametersId + ", mfParametersName=" + mfParametersName + ", stationName=" + stationName
				+ ", stationId=" + stationId + "]";
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public CMesMfParametersDetailT(Integer id, Date dt, String parameterNo, String parameterName,
			Integer parameterMainFlag, Integer parameterCheck, String parameterReplace, Integer screwNumber,
			String normalT, String tUpperLimit, String tLowerLimit, String normalA, String aUpperLimit,
			String aLowerLimit, String otherMormalT, String otherUpperLimit, String otherLowerLimit,
			Integer mfParametersId, String mfParametersName, String stationName, Integer stationId) {
		super();
		this.id = id;
		this.dt = dt;
		this.parameterNo = parameterNo;
		this.parameterName = parameterName;
		this.parameterMainFlag = parameterMainFlag;
		this.parameterCheck = parameterCheck;
		this.parameterReplace = parameterReplace;
		this.screwNumber = screwNumber;
		this.normalT = normalT;
		this.tUpperLimit = tUpperLimit;
		this.tLowerLimit = tLowerLimit;
		this.normalA = normalA;
		this.aUpperLimit = aUpperLimit;
		this.aLowerLimit = aLowerLimit;
		this.otherMormalT = otherMormalT;
		this.otherUpperLimit = otherUpperLimit;
		this.otherLowerLimit = otherLowerLimit;
		this.mfParametersId = mfParametersId;
		this.mfParametersName = mfParametersName;
		this.stationName = stationName;
		this.stationId = stationId;
	}
	public CMesMfParametersDetailT() {
		super();
	}




}
