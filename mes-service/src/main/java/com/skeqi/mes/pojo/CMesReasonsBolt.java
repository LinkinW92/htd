package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesReasonsBolt {

	private Integer id;
	private Date dt;
	private Integer transfer;
	private Integer boltMode;
	private String sn;
	private String st;
	private String t;
	private String a;
	private String p;
	private String c;
	private String r;
	private String eqs;
	private String tLimit;
	private String aLimit;
	private String wid;
	private String tid;
	private Integer boltNum;
	private String boltName;
	private String reworkFlag;
	private String reworkSt;
	private String gunNo;
	private String reasons;
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
	public Integer getBoltMode() {
		return boltMode;
	}
	public void setBoltMode(Integer boltMode) {
		this.boltMode = boltMode;
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
	public Integer getBoltNum() {
		return boltNum;
	}
	public void setBoltNum(Integer boltNum) {
		this.boltNum = boltNum;
	}
	public String getBoltName() {
		return boltName;
	}
	public void setBoltName(String boltName) {
		this.boltName = boltName;
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
	public CMesReasonsBolt(Integer id, Date dt, Integer transfer, Integer boltMode, String sn, String st, String t,
			String a, String p, String c, String r, String eqs, String tLimit, String aLimit, String wid, String tid,
			Integer boltNum, String boltName, String reworkFlag, String reworkSt, String gunNo, String reasons) {
		super();
		this.id = id;
		this.dt = dt;
		this.transfer = transfer;
		this.boltMode = boltMode;
		this.sn = sn;
		this.st = st;
		this.t = t;
		this.a = a;
		this.p = p;
		this.c = c;
		this.r = r;
		this.eqs = eqs;
		this.tLimit = tLimit;
		this.aLimit = aLimit;
		this.wid = wid;
		this.tid = tid;
		this.boltNum = boltNum;
		this.boltName = boltName;
		this.reworkFlag = reworkFlag;
		this.reworkSt = reworkSt;
		this.gunNo = gunNo;
		this.reasons = reasons;
	}
	public CMesReasonsBolt() {
		super();
	}
	@Override
	public String toString() {
		return "CMesReasonsBolt [id=" + id + ", dt=" + dt + ", transfer=" + transfer + ", boltMode=" + boltMode
				+ ", sn=" + sn + ", st=" + st + ", t=" + t + ", a=" + a + ", p=" + p + ", c=" + c + ", r=" + r
				+ ", eqs=" + eqs + ", tLimit=" + tLimit + ", aLimit=" + aLimit + ", wid=" + wid + ", tid=" + tid
				+ ", boltNum=" + boltNum + ", boltName=" + boltName + ", reworkFlag=" + reworkFlag + ", reworkSt="
				+ reworkSt + ", gunNo=" + gunNo + ", reasons=" + reasons + "]";
	}


}
