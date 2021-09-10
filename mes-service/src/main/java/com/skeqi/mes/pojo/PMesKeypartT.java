package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesKeypartT {
	private Integer id;//             INTEGER not null,
	private Date  dt  ;//            DATE,
	private Integer  transfer    ;//    INTEGER,
	private Integer keypartMode ;//   INTEGER,
	private String st       ;//       VARCHAR2(100),
	private String sn       ;//       VARCHAR2(200) not null,
	private String wid     ;//        VARCHAR2(100),
	private String tid     ;//        VARCHAR2(50),
	private String secondNum  ;//    VARCHAR2(200),
	private Integer  keypartId  ;//    INTEGER,
	private String keypartName ;//   VARCHAR2(200),
	private String keypartPn  ;//    VARCHAR2(200),
	private String  keypartNum ;//    VARCHAR2(200),
	private String  keypartModule;//  VARCHAR2(200)
	private String keypartReworkFg;

	public String getKeypartReworkFg() {
		return keypartReworkFg;
	}
	public void setKeypartReworkFg(String keypartReworkFg) {
		this.keypartReworkFg = keypartReworkFg;
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
	public Integer getTransfer() {
		return transfer;
	}
	public void setTransfer(Integer transfer) {
		this.transfer = transfer;
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

	public Integer getKeypartMode() {
		return keypartMode;
	}
	public void setKeypartMode(Integer keypartMode) {
		this.keypartMode = keypartMode;
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
	public PMesKeypartT(Integer id, String dt, Integer transfer, Integer keypartMode, String st, String sn, String wid,
			String tid, String secondNum, Integer keypartId, String keypartName, String keypartPn, String keypartNum,
			String keypartModule) {
		super();
		this.id = id;
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
	}
	public PMesKeypartT() {
		super();
	}
	@Override
	public String toString() {
		return "PMesKeypartT [id=" + id + ", dt=" + dt + ", transfer=" + transfer + ", keypart_mode=" + keypartMode
				+ ", st=" + st + ", sn=" + sn + ", wid=" + wid + ", tid=" + tid + ", second_num=" + secondNum
				+ ", keypartId=" + keypartId + ", keypartName=" + keypartName + ", keypartPn=" + keypartPn
				+ ", keypartNum=" + keypartNum + ", keypartModule=" + keypartModule + "]";
	}


}
