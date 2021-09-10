package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesReasonsLeakage {
	private Integer id;
	private Date dt;
	private String st;
	private String sn;
	private String leakageName;
	private String leakagePv;
	private String leakageLv;
	private String leakageR;
	private String wid;
	private Integer transfer;
	private Integer leakageMode;
	private String reworkFlag;
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
	public Integer getLeakageMode() {
		return leakageMode;
	}
	public void setLeakageMode(Integer leakageMode) {
		this.leakageMode = leakageMode;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public CMesReasonsLeakage(Integer id, Date dt, String st, String sn, String leakageName, String leakagePv,
			String leakageLv, String leakageR, String wid, Integer transfer, Integer leakageMode, String reworkFlag,
			String reasons) {
		super();
		this.id = id;
		this.dt = dt;
		this.st = st;
		this.sn = sn;
		this.leakageName = leakageName;
		this.leakagePv = leakagePv;
		this.leakageLv = leakageLv;
		this.leakageR = leakageR;
		this.wid = wid;
		this.transfer = transfer;
		this.leakageMode = leakageMode;
		this.reworkFlag = reworkFlag;
		this.reasons = reasons;
	}
	public CMesReasonsLeakage() {
		super();
	}
	@Override
	public String toString() {
		return "CMesReasonsLeakage [id=" + id + ", dt=" + dt + ", st=" + st + ", sn=" + sn + ", leakageName="
				+ leakageName + ", leakagePv=" + leakagePv + ", leakageLv=" + leakageLv + ", leakageR=" + leakageR
				+ ", wid=" + wid + ", transfer=" + transfer + ", leakageMode=" + leakageMode + ", reworkFlag="
				+ reworkFlag + ", reasons=" + reasons + "]";
	}


}
