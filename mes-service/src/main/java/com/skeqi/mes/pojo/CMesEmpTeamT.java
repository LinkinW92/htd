package com.skeqi.mes.pojo;

public class CMesEmpTeamT {
	private Integer id;
	private String name;
	private String dis;
	private Integer lineId;
	private String lineName;
	private Integer shiftsTeamId;
	private String shiftsTeamName;
	private String emps;

	public Integer getShiftsTeamId() {
		return shiftsTeamId;
	}
	public void setShiftsTeamId(Integer shiftsTeamId) {
		this.shiftsTeamId = shiftsTeamId;
	}
	public String getShiftsTeamName() {
		return shiftsTeamName;
	}
	public void setShiftsTeamName(String shiftsTeamName) {
		this.shiftsTeamName = shiftsTeamName;
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
	public String getEmps() {
		return emps;
	}
	public void setEmps(String emps) {
		this.emps = emps;
	}
	public CMesEmpTeamT(Integer id, String name, String dis, Integer lineId, String emps) {
		super();
		this.id = id;
		this.name = name;
		this.dis = dis;
		this.lineId = lineId;
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "CMesEmpTeamT [id=" + id + ", name=" + name + ", dis=" + dis + ", lineId=" + lineId + ", emps=" + emps
				+ "]";
	}
	public CMesEmpTeamT() {
		super();
	}


}
