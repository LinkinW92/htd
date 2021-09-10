package com.skeqi.mes.pojo;
/*班组明细表*/
public class TeamDetailT {
	private int id;//主键
	private int stationId;//工位id
	private String empIcd;//员工工号
	private int empTeamId;//班组id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getEmpIcd() {
		return empIcd;
	}
	public void setEmpIcd(String empIcd) {
		this.empIcd = empIcd;
	}
	public int getEmpTeamId() {
		return empTeamId;
	}
	public void setEmpTeamId(int empTeamId) {
		this.empTeamId = empTeamId;
	}

}
