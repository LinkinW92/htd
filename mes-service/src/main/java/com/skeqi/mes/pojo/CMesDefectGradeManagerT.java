package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesDefectGradeManagerT {
	@ApiModelProperty(value = "缺陷等级id",required = false)
	private Integer id;
	private String dt;
	@ApiModelProperty(value = "缺陷等级编号",required = false)
	private String defectGradeId;//缺陷等级编号
	@ApiModelProperty(value = "缺陷等级名称",required = false)
	private String defectGradeName;
	@ApiModelProperty(value = "缺陷等级描述",required = false)
	private String defectGradeDis;


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
	public String getDefectGradeId() {
		return defectGradeId;
	}
	public void setDefectGradeId(String defectGradeId) {
		this.defectGradeId = defectGradeId;
	}
	public String getDefectGradeName() {
		return defectGradeName;
	}
	public void setDefectGradeName(String defectGradeName) {
		this.defectGradeName = defectGradeName;
	}
	public String getDefectGradeDis() {
		return defectGradeDis;
	}
	public void setDefectGradeDis(String defectGradeDis) {
		this.defectGradeDis = defectGradeDis;
	}






}
