package com.skeqi.mes.pojo.api;

public class ModUpdatecurrentempP {

	// 入参
	private String serialnumber;
	private String stationName;
	private String line;
	private String empname;

	// 出参
	private Integer r;

	// 临时变量
	private Integer tempSteprecordCount;// ---记录条数
	private Integer tempStep;// --步序
	private Integer maxid;// ----最大id
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Integer getR() {
		return r;
	}
	public void setR(Integer r) {
		this.r = r;
	}
	public Integer getTempSteprecordCount() {
		return tempSteprecordCount;
	}
	public void setTempSteprecordCount(Integer tempSteprecordCount) {
		this.tempSteprecordCount = tempSteprecordCount;
	}
	public Integer getTempStep() {
		return tempStep;
	}
	public void setTempStep(Integer tempStep) {
		this.tempStep = tempStep;
	}
	public Integer getMaxid() {
		return maxid;
	}
	public void setMaxid(Integer maxid) {
		this.maxid = maxid;
	}



}
