package com.skeqi.mes.pojo.api;

public class ModuleGetcurrentempPT {
	//入参
	private String snBarcode;
	private String stationEmpName;
	private String 	lineEmpName;

	//出参
	private String eName;
	private Integer rEmp;

	//临时变量
	private Integer tempSteprecordCount;//记录条数
	private String tmepEmpName;
	public String getSnBarcode() {
		return snBarcode;
	}
	public void setSnBarcode(String snBarcode) {
		this.snBarcode = snBarcode;
	}
	public String getStationEmpName() {
		return stationEmpName;
	}
	public void setStationEmpName(String stationEmpName) {
		this.stationEmpName = stationEmpName;
	}
	public String getLineEmpName() {
		return lineEmpName;
	}
	public void setLineEmpName(String lineEmpName) {
		this.lineEmpName = lineEmpName;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Integer getrEmp() {
		return rEmp;
	}
	public void setrEmp(Integer rEmp) {
		this.rEmp = rEmp;
	}
	public Integer getTempSteprecordCount() {
		return tempSteprecordCount;
	}
	public void setTempSteprecordCount(Integer tempSteprecordCount) {
		this.tempSteprecordCount = tempSteprecordCount;
	}
	public String getTmepEmpName() {
		return tmepEmpName;
	}
	public void setTmepEmpName(String tmepEmpName) {
		this.tmepEmpName = tmepEmpName;
	}
}
