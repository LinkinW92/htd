package com.skeqi.mes.pojo.api;

public class ModuleUpdatestepPT {
	// 入参
	private String serialnumber;
	private String station;
	private String line;
	private Integer step;

	// 出参
	private Integer r;

	// 临时变量
	private Integer tempSteprecordCount;// ---记录条数
	private Integer tempStep;// ---步序
	private Integer maxid;// ---最大id
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
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
