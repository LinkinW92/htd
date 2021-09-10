package com.skeqi.mes.pojo.qh;

import io.swagger.annotations.ApiModelProperty;

public class CMesDefectManagerL {
	@ApiModelProperty(value = "缺陷id")
	private Integer tid;
	@ApiModelProperty(value = "缺陷日期")
	private String dt;
	@ApiModelProperty(value = "缺陷编号")
	private Integer defectId;
	@ApiModelProperty(value = "缺陷名称")
	private String defectName;
	@ApiModelProperty(value = "缺陷等级名称")
    private String gradeName;
	@ApiModelProperty(value = "缺陷等级描述")
    private String mdis;
	@ApiModelProperty(value = "缺陷等级id")
    private Integer mid;

	@ApiModelProperty(value = "缺陷等级")
	private Integer defectGrade;

	public Integer getDefectGrade() {
		return defectGrade;
	}

	public void setDefectGrade(Integer defectGrade) {
		this.defectGrade = defectGrade;
	}

	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getDefectId() {
		return defectId;
	}
	public void setDefectId(Integer defectId) {
		this.defectId = defectId;
	}
	public String getDefectName() {
		return defectName;
	}
	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getMdis() {
		return mdis;
	}
	public void setMdis(String mdis) {
		this.mdis = mdis;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}


}
