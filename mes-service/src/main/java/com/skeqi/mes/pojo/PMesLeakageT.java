package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesLeakageT {
	private Integer id;//           INTEGER not null,
	private Date dt ;//           DATE,
	private String st  ;//          VARCHAR2(100),
	private String sn  ;//          VARCHAR2(200) not null,
	private String leakageName;//  VARCHAR2(100),
	private String leakagePv ;//   VARCHAR2(50),
	private String leakageLv;//    VARCHAR2(50),
	private String leakageR;//    VARCHAR2(50),
	private String  wid     ;//      VARCHAR2(50),
	private Integer transfer ;//     INTEGER
	private String stationName;
	private Integer leakageMode;
	private String  reworkFlag;




	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public Integer getLeakageMode() {
		return leakageMode;
	}
	public void setLeakageMode(Integer leakageMode) {
		this.leakageMode = leakageMode;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public Integer getTransfer() {
		return transfer;
	}
	public void setTransfer(Integer transfer) {
		this.transfer = transfer;
	}
	public PMesLeakageT(Integer id, String dt, String st, String sn, String leakageName, String leakagePv,
			String leakageLv, String leakageR, String wid, Integer transfer) {
		super();
		this.id = id;
		this.st = st;
		this.sn = sn;
		this.leakageName = leakageName;
		this.leakagePv = leakagePv;
		this.leakageLv = leakageLv;
		this.leakageR = leakageR;
		this.wid = wid;
		this.transfer = transfer;
	}
	public PMesLeakageT() {
		super();
	}
	@Override
	public String toString() {
		return "PMesLeakageT [id=" + id + ", dt=" + dt + ", st=" + st + ", sn=" + sn + ", leakageName=" + leakageName
				+ ", leakagePv=" + leakagePv + ", leakageLv=" + leakageLv + ", leakageR=" + leakageR + ", wid=" + wid
				+ ", transfer=" + transfer + "]";
	}


}
