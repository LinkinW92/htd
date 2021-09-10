package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesReasonsKeypart {

	private Integer id;
	private String reasons;
	private Date dt;
	private Integer transfer;
	private Integer keypartMode;
	private String st;
	private String sn;
	private String wid;
	private String tid;
	private String secondNum;
	private Integer keypartId;
	private String keypartName;
	private String keypartPn;
	private String keypartNum;
	private String keypartModule;
	private String keypartReworkFg;
	private String keypartReworkSt;
	private Integer materialId;
	private String materialNumber;
	private String userName;
	private Date updateDate;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
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
	public CMesReasonsKeypart(Integer id, String reasons, Date dt, Integer transfer, Integer keypartMode, String st,
			String sn, String wid, String tid, String secondNum, Integer keypartId, String keypartName,
			String keypartPn, String keypartNum, String keypartModule, String keypartReworkFg, String keypartReworkSt,
			Integer materialId, String materialNumber) {
		super();
		this.id = id;
		this.reasons = reasons;
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
	}
	public CMesReasonsKeypart() {
		super();
	}
	@Override
	public String toString() {
		return "CMesReasonsKeypart [id=" + id + ", reasons=" + reasons + ", dt=" + dt + ", transfer=" + transfer
				+ ", keypartMode=" + keypartMode + ", st=" + st + ", sn=" + sn + ", wid=" + wid + ", tid=" + tid
				+ ", secondNum=" + secondNum + ", keypartId=" + keypartId + ", keypartName=" + keypartName
				+ ", keypartPn=" + keypartPn + ", keypartNum=" + keypartNum + ", keypartModule=" + keypartModule
				+ ", keypartReworkFg=" + keypartReworkFg + ", keypartReworkSt=" + keypartReworkSt + ", materialId="
				+ materialId + ", materialNumber=" + materialNumber + "]";
	}


}
