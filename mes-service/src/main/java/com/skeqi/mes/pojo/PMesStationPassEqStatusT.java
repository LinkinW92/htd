package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesStationPassEqStatusT {

	private Integer id;//id
	private Date dt;//时间
	private String st;//工位
	private String eqName;//设备名
	private Integer eqStatusType;//状态
	private Integer rowNum;



	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
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
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	public Integer getEqStatusType() {
		return eqStatusType;
	}
	public void setEqStatusType(Integer eqStatusType) {
		this.eqStatusType = eqStatusType;
	}






}
