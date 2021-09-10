package com.skeqi.mes.pojo;


import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesDefectManager {

	private Integer id;
	@ApiModelProperty(value = "修改时间",required = false)
	private String dt;
	@ApiModelProperty(value = "缺陷编号",required = true)
	private String defectId;
	@ApiModelProperty(value = "缺陷名称",required = true)
	private String defectName;
//
	@ApiModelProperty(value = "缺陷描述",required = false)
	private String defectDis;


	@ApiModelProperty(value = "6666缺陷等级",required = true)
	private int dengji;


	private CMesDefectGradeManagerT defectGrade;


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


	public String getDefectId() {
		return defectId;
	}


	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}


	public String getDefectName() {
		return defectName;
	}


	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}


	public String getDefectDis() {
		return defectDis;
	}


	public void setDefectDis(String defectDis) {
		this.defectDis = defectDis;
	}


	public int getDengji() {
		return dengji;
	}


	public void setDengji(int dengji) {
		this.dengji = dengji;
	}


	public CMesDefectGradeManagerT getDefectGrade() {
		return defectGrade;
	}


	public void setDefectGrade(CMesDefectGradeManagerT defectGrade) {
		this.defectGrade = defectGrade;
	}







}
