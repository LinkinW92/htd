package com.skeqi.mes.pojo.project;

import java.util.List;

import com.skeqi.mes.mapper.qh.CmesScheduling;

public class Scheduling {

	private Integer id;
	private Integer shiftId;//班次ID
	private String shift;//班次名称
	private Integer teamId;//班组ID
	private String team;//班组
	private String dt;//日期
	private Integer lineId;//产线id
	private String line;//产线名称
	private Integer planNumber;//计划生成数量
	private Integer planId;//计划ID
	private String planName;//计划ID
	private Integer  planrealNumber;//实际生成数量
	private List<CmesScheduling> plans;
	public Scheduling() {
		// TODO Auto-generated constructor stub
	}
	public Scheduling(Integer id, Integer shiftId, String shift, Integer teamId, String team, String dt, Integer lineId,
			String line, Integer planNumber, Integer planId, String planName, Integer planrealNumber,
			List<CmesScheduling> plans) {
		this.id = id;
		this.shiftId = shiftId;
		this.shift = shift;
		this.teamId = teamId;
		this.team = team;
		this.dt = dt;
		this.lineId = lineId;
		this.line = line;
		this.planNumber = planNumber;
		this.planId = planId;
		this.planName = planName;
		this.planrealNumber = planrealNumber;
		this.plans = plans;
	}
	@Override
	public String toString() {
		return "Scheduling [id=" + id + ", shiftId=" + shiftId + ", shift=" + shift + ", teamId=" + teamId + ", team="
				+ team + ", dt=" + dt + ", lineId=" + lineId + ", line=" + line + ", planNumber=" + planNumber
				+ ", planId=" + planId + ", planName=" + planName + ", planrealNumber=" + planrealNumber + ", plans="
				+ plans + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShiftId() {
		return shiftId;
	}
	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public Integer getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Integer getPlanrealNumber() {
		return planrealNumber;
	}
	public void setPlanrealNumber(Integer planrealNumber) {
		this.planrealNumber = planrealNumber;
	}
	public List<CmesScheduling> getPlans() {
		return plans;
	}
	public void setPlans(List<CmesScheduling> plans) {
		this.plans = plans;
	}

}
