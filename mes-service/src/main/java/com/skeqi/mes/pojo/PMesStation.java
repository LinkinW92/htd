package com.skeqi.mes.pojo;

public class PMesStation {

	private String st;//工位
	private String eqName;//设备名称
	private String stopTime;//停机时间
	private String lackTime ;//待料时间
	private String runTime ;//运行时间

	private String faultTime;//故障时间
	private String wallTime ;//堵料时间
	private String elseTime ;//其他时间
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getLackTime() {
		return lackTime;
	}
	public void setLackTime(String lackTime) {
		this.lackTime = lackTime;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getFaultTime() {
		return faultTime;
	}
	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}
	public String getWallTime() {
		return wallTime;
	}
	public void setWallTime(String wallTime) {
		this.wallTime = wallTime;
	}
	public String getElseTime() {
		return elseTime;
	}
	public void setElseTime(String elseTime) {
		this.elseTime = elseTime;
	}



}
