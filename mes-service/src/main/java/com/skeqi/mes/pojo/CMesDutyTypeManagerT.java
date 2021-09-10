package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesDutyTypeManagerT {

	@ApiModelProperty(value = "id")
	private Integer id;
	private String dt;
	@ApiModelProperty(value = "责任类型编号")
	private String dutyTypeId;
	@ApiModelProperty(value = "责任类型名称")
	private String dutyTypeName;
	@ApiModelProperty(value = "责任类型描述")
	private String dutyTypeDis;




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
	public String getDutyTypeId() {
		return dutyTypeId;
	}
	public void setDutyTypeId(String dutyTypeId) {
		this.dutyTypeId = dutyTypeId;
	}
	public String getDutyTypeName() {
		return dutyTypeName;
	}
	public void setDutyTypeName(String dutyTypeName) {
		this.dutyTypeName = dutyTypeName;
	}
	public String getDutyTypeDis() {
		return dutyTypeDis;
	}
	public void setDutyTypeDis(String dutyTypeDis) {
		this.dutyTypeDis = dutyTypeDis;
	}




}
