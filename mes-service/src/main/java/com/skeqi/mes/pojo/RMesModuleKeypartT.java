package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * 模组物料记录表
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午11:18:58
 */
public class RMesModuleKeypartT {
	private Integer id;//                INTEGER not null,
	private Date dt;//                DATE,
	private Integer transfer;//          INTEGER,
	private Integer keypartMode;//      INTEGER,
	private String st;//                VARCHAR2(100),
	private String sn;//                VARCHAR2(200),
	private String wid;//               VARCHAR2(100),
	private String tid ;//              VARCHAR2(50),
	private String secondNum;//        VARCHAR2(200),
	private Integer keypartId;//       INTEGER,
	private String keypartName ;//     VARCHAR2(200),
	private String keypartPn  ;//      VARCHAR2(200),
	private String keypartNum  ;//     VARCHAR2(200),
	private String keypartModule ;//   VARCHAR2(200),
	private String keypartReworkFg ;//VARCHAR2(10),
	private String keypartReworkSt;// VARCHAR2(100)
	private Integer materialId;
	private String materialNumber;
	private String reasons;
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
	public Integer getTransfer() {
		return transfer;
	}
	public void setTransfer(Integer transfer) {
		this.transfer = transfer;
	}
	public Integer getKeypartMode() {
		return keypartMode;
	}
	public void setKeypartMode(Integer keypartMode) {
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
	public Integer getKeypartId() {
		return keypartId;
	}
	public void setKeypartId(Integer keypartId) {
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
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	@Override
	public String toString() {
		return "RMesModuleKeypartT [id=" + id + ", dt=" + dt + ", transfer=" + transfer + ", keypartMode=" + keypartMode
				+ ", st=" + st + ", sn=" + sn + ", wid=" + wid + ", tid=" + tid + ", secondNum=" + secondNum
				+ ", keypartId=" + keypartId + ", keypartName=" + keypartName + ", keypartPn=" + keypartPn
				+ ", keypartNum=" + keypartNum + ", keypartModule=" + keypartModule + ", keypartReworkFg="
				+ keypartReworkFg + ", keypartReworkSt=" + keypartReworkSt + ", materialId=" + materialId
				+ ", materialNumber=" + materialNumber + ", reasons=" + reasons + "]";
	}
	public RMesModuleKeypartT(Integer id, Date dt, Integer transfer, Integer keypartMode, String st, String sn,
			String wid, String tid, String secondNum, Integer keypartId, String keypartName, String keypartPn,
			String keypartNum, String keypartModule, String keypartReworkFg, String keypartReworkSt, Integer materialId,
			String materialNumber, String reasons) {
		super();
		this.id = id;
		this.dt = dt;
		this.transfer = transfer;
		this.keypartMode = keypartMode;
		this.st = st;
		this.sn = sn;
		this.wid = wid;
		this.tid = tid;
		this.secondNum = secondNum;
		this.keypartId = keypartId;
		this.keypartName = keypartName;
		this.keypartPn = keypartPn;
		this.keypartNum = keypartNum;
		this.keypartModule = keypartModule;
		this.keypartReworkFg = keypartReworkFg;
		this.keypartReworkSt = keypartReworkSt;
		this.materialId = materialId;
		this.materialNumber = materialNumber;
		this.reasons = reasons;
	}
	public RMesModuleKeypartT() {
		super();
	}


}
