package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesShiftsTeamT {
	private Integer id;
	private String name;
	private String startTime;
	private String endTime;
	private String dis;
	private Integer lineId;
	private String lineName;
	private  Integer jumpTime;


	public Integer getJumpTime() {
		return jumpTime;
	}
	public void setJumpTime(Integer jumpTime) {
		this.jumpTime = jumpTime;
	}
	private String planTime;


	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public CMesShiftsTeamT(Integer id, String name, String startTime, String endTime, String dis, Integer lineId,
			String lineName, String planTime) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dis = dis;
		this.lineId = lineId;
		this.lineName = lineName;
		this.planTime = planTime;
	}
	public CMesShiftsTeamT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesShiftsTeamT [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", dis=" + dis + ", lineId=" + lineId + ", lineName=" + lineName + ", planTime=" + planTime + "]";
	}



}
