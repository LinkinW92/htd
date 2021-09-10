package com.skeqi.mes.pojo.api;

import java.io.Serializable;

public class GetcurrentBoltPT implements Serializable{

	private Integer id;
	private String tempStationType;
	private String tempMaterialName;
	private String tempReworkTimes;
	private String tempMinId;
	private String tempT;
	private String tempA;
	private String tempR;
	private String tempALimit;
	private String tempTLimit;
	private String tempBoltName;
	private String tempReworkFlag;
	private String tempReworkSt;
	private String tempBoltNum;
	private String tempRemainBoltCount;

	public String getTempRemainBoltCount() {
		return tempRemainBoltCount;
	}

	public void setTempRemainBoltCount(String tempRemainBoltCount) {
		this.tempRemainBoltCount = tempRemainBoltCount;
	}

	public String getTempT() {
		return tempT;
	}

	public void setTempT(String tempT) {
		this.tempT = tempT;
	}

	public String getTempA() {
		return tempA;
	}

	public void setTempA(String tempA) {
		this.tempA = tempA;
	}

	public String getTempR() {
		return tempR;
	}

	public void setTempR(String tempR) {
		this.tempR = tempR;
	}

	public String getTempALimit() {
		return tempALimit;
	}

	public void setTempALimit(String tempALimit) {
		this.tempALimit = tempALimit;
	}

	public String getTempTLimit() {
		return tempTLimit;
	}

	public void setTempTLimit(String tempTLimit) {
		this.tempTLimit = tempTLimit;
	}

	public String getTempBoltName() {
		return tempBoltName;
	}

	public void setTempBoltName(String tempBoltName) {
		this.tempBoltName = tempBoltName;
	}

	public String getTempReworkFlag() {
		return tempReworkFlag;
	}

	public void setTempReworkFlag(String tempReworkFlag) {
		this.tempReworkFlag = tempReworkFlag;
	}

	public String getTempReworkSt() {
		return tempReworkSt;
	}

	public void setTempReworkSt(String tempReworkSt) {
		this.tempReworkSt = tempReworkSt;
	}

	public String getTempBoltNum() {
		return tempBoltNum;
	}

	public void setTempBoltNum(String tempBoltNum) {
		this.tempBoltNum = tempBoltNum;
	}

	public String getTempMinId() {
		return tempMinId;
	}

	public void setTempMinId(String tempMinId) {
		this.tempMinId = tempMinId;
	}

	public String getTempMaterialName() {
		return tempMaterialName;
	}

	public void setTempMaterialName(String tempMaterialName) {
		this.tempMaterialName = tempMaterialName;
	}

	public String getTempReworkTimes() {
		return tempReworkTimes;
	}

	public void setTempReworkTimes(String tempReworkTimes) {
		this.tempReworkTimes = tempReworkTimes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTempStationType() {
		return tempStationType;
	}

	public void setTempStationType(String tempStationType) {
		this.tempStationType = tempStationType;
	}

}
