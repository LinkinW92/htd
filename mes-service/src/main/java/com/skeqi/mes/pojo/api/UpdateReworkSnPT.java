package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @date 2020年1月13日
 * @author yinp
 *
 */
public class UpdateReworkSnPT implements Serializable {

	private Integer id;
	private String tempTrackingCount;
	private String tempTrackingId;
	private String tempTrackingStationName;
	private String tempTrackingEnginesn;
	private String tempTrackingGearbosxn;
	private String tempTrackingStatus;
	private String tempTrackingProductionId;
	private String tempStationLineId;
	private String tempPlanId;
	private String tempDt;
	private String tempReworkFlag;
	private String tempWorkOrderId;
	private String tempPTrackingCout;
	private String snBarconde;
	private String tempTrackingBst;
	private String stationname;
	/**
	 * keypart
	 */
	private String dt;
	private String transfer;
	private String keypartMode;
	private String st;
	private String sn;
	private String wid;
	private String tid;
	private String secondNum;
	private String keypartId;
	private String keypartName;
	private String keypartPn;
	private String keypartNum;
	private String keypartModule;
	private String keypartReworkFg;
	private String keypartReworkSt;
	private String materialId;
	private String materialNumber;
	// leakage
	private String leakageName;
	private String leakagePv;
	private String leakageLv;
	private String leakageR;
	private String wId;
	private String leakageMode;
	private String reworkFlag;
	private String reason;
	//bolt
	private String boltMode;
	private String t;
	private String a;
	private String p;
	private String c;
	private String r;
	private String eqs;
	private String tLimit;
	private String aLimit;
	private String boltNum;
	private String boltName;
	private String reworkSt;
	private String gunNo;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTempTrackingCount() {
		return tempTrackingCount;
	}
	public void setTempTrackingCount(String tempTrackingCount) {
		this.tempTrackingCount = tempTrackingCount;
	}
	public String getTempTrackingId() {
		return tempTrackingId;
	}
	public void setTempTrackingId(String tempTrackingId) {
		this.tempTrackingId = tempTrackingId;
	}
	public String getTempTrackingStationName() {
		return tempTrackingStationName;
	}
	public void setTempTrackingStationName(String tempTrackingStationName) {
		this.tempTrackingStationName = tempTrackingStationName;
	}
	public String getTempTrackingEnginesn() {
		return tempTrackingEnginesn;
	}
	public void setTempTrackingEnginesn(String tempTrackingEnginesn) {
		this.tempTrackingEnginesn = tempTrackingEnginesn;
	}
	public String getTempTrackingGearbosxn() {
		return tempTrackingGearbosxn;
	}
	public void setTempTrackingGearbosxn(String tempTrackingGearbosxn) {
		this.tempTrackingGearbosxn = tempTrackingGearbosxn;
	}
	public String getTempTrackingStatus() {
		return tempTrackingStatus;
	}
	public void setTempTrackingStatus(String tempTrackingStatus) {
		this.tempTrackingStatus = tempTrackingStatus;
	}
	public String getTempTrackingProductionId() {
		return tempTrackingProductionId;
	}
	public void setTempTrackingProductionId(String tempTrackingProductionId) {
		this.tempTrackingProductionId = tempTrackingProductionId;
	}
	public String getTempStationLineId() {
		return tempStationLineId;
	}
	public void setTempStationLineId(String tempStationLineId) {
		this.tempStationLineId = tempStationLineId;
	}
	public String getTempPlanId() {
		return tempPlanId;
	}
	public void setTempPlanId(String tempPlanId) {
		this.tempPlanId = tempPlanId;
	}
	public String getTempDt() {
		return tempDt;
	}
	public void setTempDt(String tempDt) {
		this.tempDt = tempDt;
	}
	public String getTempReworkFlag() {
		return tempReworkFlag;
	}
	public void setTempReworkFlag(String tempReworkFlag) {
		this.tempReworkFlag = tempReworkFlag;
	}
	public String getTempWorkOrderId() {
		return tempWorkOrderId;
	}
	public void setTempWorkOrderId(String tempWorkOrderId) {
		this.tempWorkOrderId = tempWorkOrderId;
	}
	public String getTempPTrackingCout() {
		return tempPTrackingCout;
	}
	public void setTempPTrackingCout(String tempPTrackingCout) {
		this.tempPTrackingCout = tempPTrackingCout;
	}
	public String getSnBarconde() {
		return snBarconde;
	}
	public void setSnBarconde(String snBarconde) {
		this.snBarconde = snBarconde;
	}
	public String getTempTrackingBst() {
		return tempTrackingBst;
	}
	public void setTempTrackingBst(String tempTrackingBst) {
		this.tempTrackingBst = tempTrackingBst;
	}
	public String getStationname() {
		return stationname;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getKeypartMode() {
		return keypartMode;
	}
	public void setKeypartMode(String keypartMode) {
		this.keypartMode = keypartMode;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getSecondNum() {
		return secondNum;
	}
	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}
	public String getKeypartId() {
		return keypartId;
	}
	public void setKeypartId(String keypartId) {
		this.keypartId = keypartId;
	}
	public String getKeypartName() {
		return keypartName;
	}
	public void setKeypartName(String keypartName) {
		this.keypartName = keypartName;
	}
	public String getKeypartPn() {
		return keypartPn;
	}
	public void setKeypartPn(String keypartPn) {
		this.keypartPn = keypartPn;
	}
	public String getKeypartNum() {
		return keypartNum;
	}
	public void setKeypartNum(String keypartNum) {
		this.keypartNum = keypartNum;
	}
	public String getKeypartModule() {
		return keypartModule;
	}
	public void setKeypartModule(String keypartModule) {
		this.keypartModule = keypartModule;
	}
	public String getKeypartReworkFg() {
		return keypartReworkFg;
	}
	public void setKeypartReworkFg(String keypartReworkFg) {
		this.keypartReworkFg = keypartReworkFg;
	}
	public String getKeypartReworkSt() {
		return keypartReworkSt;
	}
	public void setKeypartReworkSt(String keypartReworkSt) {
		this.keypartReworkSt = keypartReworkSt;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getLeakageName() {
		return leakageName;
	}
	public void setLeakageName(String leakageName) {
		this.leakageName = leakageName;
	}
	public String getLeakagePv() {
		return leakagePv;
	}
	public void setLeakagePv(String leakagePv) {
		this.leakagePv = leakagePv;
	}
	public String getLeakageLv() {
		return leakageLv;
	}
	public void setLeakageLv(String leakageLv) {
		this.leakageLv = leakageLv;
	}
	public String getLeakageR() {
		return leakageR;
	}
	public void setLeakageR(String leakageR) {
		this.leakageR = leakageR;
	}
	public String getwId() {
		return wId;
	}
	public void setwId(String wId) {
		this.wId = wId;
	}
	public String getLeakageMode() {
		return leakageMode;
	}
	public void setLeakageMode(String leakageMode) {
		this.leakageMode = leakageMode;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBoltMode() {
		return boltMode;
	}
	public void setBoltMode(String boltMode) {
		this.boltMode = boltMode;
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
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getEqs() {
		return eqs;
	}
	public void setEqs(String eqs) {
		this.eqs = eqs;
	}
	public String gettLimit() {
		return tLimit;
	}
	public void settLimit(String tLimit) {
		this.tLimit = tLimit;
	}
	public String getaLimit() {
		return aLimit;
	}
	public void setaLimit(String aLimit) {
		this.aLimit = aLimit;
	}
	public String getBoltNum() {
		return boltNum;
	}
	public void setBoltNum(String boltNum) {
		this.boltNum = boltNum;
	}
	public String getBoltName() {
		return boltName;
	}
	public void setBoltName(String boltName) {
		this.boltName = boltName;
	}

}
