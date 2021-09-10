package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesDutyManagerT {


	private Integer id;
	private String dt;
	private String dutyId;
	private String dutyName;
	private Integer  dutyTypeId;
	private String dutyDis;
	private CMesDutyTypeManagerT dutyType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getDutyId() {
		return dutyId;
	}
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public Integer getDutyTypeId() {
		return dutyTypeId;
	}
	public void setDutyTypeId(Integer dutyTypeId) {
		this.dutyTypeId = dutyTypeId;
	}
	public String getDutyDis() {
		return dutyDis;
	}
	public void setDutyDis(String dutyDis) {
		this.dutyDis = dutyDis;
	}
	public CMesDutyTypeManagerT getDutyType() {
		return dutyType;
	}
	public void setDutyType(CMesDutyTypeManagerT dutyType) {
		this.dutyType = dutyType;
	}


}
