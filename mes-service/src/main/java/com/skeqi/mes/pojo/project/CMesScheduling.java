package com.skeqi.mes.pojo.project;

public class CMesScheduling {

	private Integer id;
	private Integer shiftId;
	private String shiftName;
	private Integer teamId;
	private String teamName;
	private String dt;
	private Integer workId;
	private String workName;
	private Integer lineId;
	private String lineName;
	private Integer planNumber;
	private Integer actualNumber;
	private Integer completeNumber;
	private Integer planTime;



	public Integer getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Integer planTime) {
		this.planTime = planTime;
	}
	public Integer getCompleteNumber() {
		return completeNumber;
	}
	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
	}
	public Integer getActualNumber() {
		return actualNumber;
	}
	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
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
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Integer getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}
	public CMesScheduling(Integer id, Integer shiftId, String shiftName, Integer teamId, String teamName, String dt,
			Integer workId, String workName, Integer lineId, String lineName, Integer planNumber, Integer actualNumber,
			Integer completeNumber, Integer planTime) {
		super();
		this.id = id;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.teamId = teamId;
		this.teamName = teamName;
		this.dt = dt;
		this.workId = workId;
		this.workName = workName;
		this.lineId = lineId;
		this.lineName = lineName;
		this.planNumber = planNumber;
		this.actualNumber = actualNumber;
		this.completeNumber = completeNumber;
		this.planTime = planTime;
	}
	public CMesScheduling() {
		super();
	}




}
