package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class PMesDefectReasonT {
	@ApiModelProperty(value="",required=false)
	private Integer id;//            INTEGER not null,
	@ApiModelProperty(value="总成",required=false)
	private String sn;//             VARCHAR2(200),
	@ApiModelProperty(value="时间",required=false)
	private Date dt;//             DATE,
	@ApiModelProperty(value="缺陷类型(1:维修2.报废3.返工)",required=false)
	private String defectType;//    VARCHAR2(10),
	@ApiModelProperty(value="详细原因",required=false)
	private String reasonDetail;//  VARCHAR2(500
	@ApiModelProperty(value="责任管理ID",required=false)
	private Integer dutyId;
	@ApiModelProperty(value="缺陷ID",required=false)
	private Integer defectId;
	@ApiModelProperty(value="原因ID",required=false)
	private Integer resionId;
	@ApiModelProperty(value="责任管理名称",required=false)
	private String dutyName;
	@ApiModelProperty(value="缺陷名称",required=false)
	private String defectName;
	@ApiModelProperty(value="原因介绍",required=false)
	private String resionDis;
	@ApiModelProperty(value="产线id",required=false)
	private Integer lineId;
	private PTrackingT tracking;//总成状态永久性表
	private CMesDutyManagerT getDuty;
	private CMesDefectManager defect;
	private CMesDefectResionT getResion;
	private String menuIds;




	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getDefectName() {
		return defectName;
	}
	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}
	public String getResionDis() {
		return resionDis;
	}
	public void setResionDis(String resionDis) {
		this.resionDis = resionDis;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public CMesDutyManagerT getGetDuty() {
		return getDuty;
	}
	public void setGetDuty(CMesDutyManagerT getDuty) {
		this.getDuty = getDuty;
	}


	public CMesDefectManager getDefect() {
		return defect;
	}
	public void setDefect(CMesDefectManager defect) {
		this.defect = defect;
	}
	public CMesDefectResionT getGetResion() {
		return getResion;
	}
	public void setGetResion(CMesDefectResionT getResion) {
		this.getResion = getResion;
	}

	public PTrackingT getTracking() {
		return tracking;
	}
	public void setTracking(PTrackingT tracking) {
		this.tracking = tracking;
	}
	public Integer getDutyId() {
		return dutyId;
	}
	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}
	public Integer getDefectId() {
		return defectId;
	}
	public void setDefectId(Integer defectId) {
		this.defectId = defectId;
	}
	public Integer getResionId() {
		return resionId;
	}
	public void setResionId(Integer resionId) {
		this.resionId = resionId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getDefectType() {
		return defectType;
	}
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}
	public String getReasonDetail() {
		return reasonDetail;
	}
	public void setReasonDetail(String reasonDetail) {
		this.reasonDetail = reasonDetail;
	}
	public PMesDefectReasonT(Integer id, String sn, Date dt, String defectType, String reasonDetail) {
		super();
		this.id = id;
		this.sn = sn;
		this.dt = dt;
		this.defectType = defectType;
		this.reasonDetail = reasonDetail;
	}
	public PMesDefectReasonT() {
		super();
	}


}
