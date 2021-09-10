package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 *
 * @author Yinp
 * @data 2020年01月09日16:01:21
 * @name 工位表
 *
 */
public class AssembleBoltPT implements Serializable{

	private Integer id;

	private String stationType;
	private String materialName;
	private String reworktimes;
	private String aLimit;
	private String tLimit;
	private String tempTLimitLower;
	private String tempTLimitUpper;
	private String tempALimitLower;
	private String tempALimitUpper;
	private String tempMinId;
	private String tempT;
	private String tempA;
	private String tempR;
	private String tempALimit;
	private String tempTLimit;
	private String tempBoltName;
	private String tempBoltNum;
	private String tempReworkFlag;
	private String tempReworkSt;
	private String snBarcode;
	private String stationBoltName;
	private String tValues;
	private String aValues;
	private String tempAllResult;
	private String emp;
	private String tempRemainBoltCount;
	private String boltName;
	private Integer Y;

	private Integer danQianBoltNum;
	private Integer MAXBoltNum;

	private Integer transfer;
	private String sn;
	private String st;
	private String t;
	private String a;
	private String r;
	private String c;
	private String eqs;
	private String wId;
	private String tId;
	private Integer boltNum;
	private String reworkFlag;
	private String reworkSt;
	private String gunNo;
	private String reasons;


	public Integer getTransfer() {
		return transfer;
	}

	public void setTransfer(Integer transfer) {
		this.transfer = transfer;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}


	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getEqs() {
		return eqs;
	}

	public void setEqs(String eqs) {
		this.eqs = eqs;
	}

	public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public Integer getBoltNum() {
		return boltNum;
	}

	public void setBoltNum(Integer boltNum) {
		this.boltNum = boltNum;
	}

	public String getReworkFlag() {
		return reworkFlag;
	}

	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}

	public String getReworkSt() {
		return reworkSt;
	}

	public void setReworkSt(String reworkSt) {
		this.reworkSt = reworkSt;
	}

	public String getGunNo() {
		return gunNo;
	}

	public void setGunNo(String gunNo) {
		this.gunNo = gunNo;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Integer getDanQianBoltNum() {
		return danQianBoltNum;
	}

	public void setDanQianBoltNum(Integer danQianBoltNum) {
		this.danQianBoltNum = danQianBoltNum;
	}

	public Integer getMAXBoltNum() {
		return MAXBoltNum;
	}

	public void setMAXBoltNum(Integer mAXBoltNum) {
		MAXBoltNum = mAXBoltNum;
	}

	public Integer getY() {
		return Y;
	}

	public void setY(Integer y) {
		Y = y;
	}

	public String getBoltName() {
		return boltName;
	}

	public void setBoltName(String boltName) {
		this.boltName = boltName;
	}

	public String getTempRemainBoltCount() {
		return tempRemainBoltCount;
	}

	public void setTempRemainBoltCount(String tempRemainBoltCount) {
		this.tempRemainBoltCount = tempRemainBoltCount;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getSnBarcode() {
		return snBarcode;
	}

	public void setSnBarcode(String snBarcode) {
		this.snBarcode = snBarcode;
	}

	public String getStationBoltName() {
		return stationBoltName;
	}

	public void setStationBoltName(String stationBoltName) {
		this.stationBoltName = stationBoltName;
	}

	public String gettValues() {
		return tValues;
	}

	public void settValues(String tValues) {
		this.tValues = tValues;
	}

	public String getaValues() {
		return aValues;
	}

	public void setaValues(String aValues) {
		this.aValues = aValues;
	}

	public String getTempAllResult() {
		return tempAllResult;
	}

	public void setTempAllResult(String tempAllResult) {
		this.tempAllResult = tempAllResult;
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

	public String getTempBoltNum() {
		return tempBoltNum;
	}

	public void setTempBoltNum(String tempBoltNum) {
		this.tempBoltNum = tempBoltNum;
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

	public String getTempMinId() {
		return tempMinId;
	}

	public void setTempMinId(String tempMinId) {
		this.tempMinId = tempMinId;
	}

	public String getTempALimitLower() {
		return tempALimitLower;
	}

	public void setTempALimitLower(String tempALimitLower) {
		this.tempALimitLower = tempALimitLower;
	}

	public String getTempALimitUpper() {
		return tempALimitUpper;
	}

	public void setTempALimitUpper(String tempALimitUpper) {
		this.tempALimitUpper = tempALimitUpper;
	}

	public String getTempTLimitLower() {
		return tempTLimitLower;
	}

	public void setTempTLimitLower(String tempTLimitLower) {
		this.tempTLimitLower = tempTLimitLower;
	}

	public String getTempTLimitUpper() {
		return tempTLimitUpper;
	}

	public void setTempTLimitUpper(String tempTLimitUpper) {
		this.tempTLimitUpper = tempTLimitUpper;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getReworktimes() {
		return reworktimes;
	}

	public void setReworktimes(String reworktimes) {
		this.reworktimes = reworktimes;
	}

	public String getaLimit() {
		return aLimit;
	}

	public void setaLimit(String aLimit) {
		this.aLimit = aLimit;
	}

	public String gettLimit() {
		return tLimit;
	}

	public void settLimit(String tLimit) {
		this.tLimit = tLimit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}


}
