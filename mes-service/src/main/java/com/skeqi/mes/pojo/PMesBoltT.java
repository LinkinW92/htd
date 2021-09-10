package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesBoltT {
	private Integer id;//          INTEGER not null,
	private Date dt;//           DATE,
	private Integer transfer;
	private Integer boltMode;//     INTEGER,
	private String  sn;//            VARCHAR2(200) not null,
	private String  st   ;//         VARCHAR2(100),
	private String  t  ;//           VARCHAR2(50),
	private String  a  ;//           VARCHAR2(50),
	private String  p  ;//           VARCHAR2(50),
	private String  c  ;//            VARCHAR2(50),
	private String  r  ;//           VARCHAR2(50),
	private String  eqs     ;//      VARCHAR2(50),
	private String  tLimit  ;//     VARCHAR2(50),
	private String  aLimit ;//      VARCHAR2(50),
	private String  wid  ;//         VARCHAR2(50),
	private String  tid   ;//        VARCHAR2(50),
	private Integer  boltNum ;//     INTEGER,
	private String  boltName ;//    VARCHAR2(250),
	private String  reworkFlag ;//  VARCHAR2(10),
	private String  reworkSt ;//    VARCHAR2(100)
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
	public PMesBoltT(Integer id, String dt, Integer transfer, Integer boltMode, String sn, String st, String t, String a,
			String p, String c, String r, String eqs, String tLimit, String aLimit, String wid, String tid,
			Integer boltNum, String boltName, String reworkFlag, String reworkSt) {
		super();
		this.id = id;
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
	}
	public PMesBoltT() {
		super();
	}
	@Override
	public String toString() {
		return "PMesBoltT [id=" + id + ", dt=" + dt + ", transfer=" + transfer + ", boltMode=" + boltMode + ", sn=" + sn
				+ ", st=" + st + ", t=" + t + ", a=" + a + ", p=" + p + ", c=" + c + ", r=" + r + ", eqs=" + eqs
				+ ", tLimit=" + tLimit + ", aLimit=" + aLimit + ", wid=" + wid + ", tid=" + tid + ", boltNum=" + boltNum
				+ ", boltName=" + boltName + ", reworkFlag=" + reworkFlag + ", reworkSt=" + reworkSt + "]";
	}


}
