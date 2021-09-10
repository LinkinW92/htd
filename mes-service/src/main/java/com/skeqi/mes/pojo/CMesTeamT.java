package com.skeqi.mes.pojo;

public class CMesTeamT {

	private Integer id;
	private String name;
	private String dis;
	private Integer shiftId;
	private String shiftName;
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
	public CMesTeamT(Integer id, String name, String dis, Integer shiftId, String shiftName) {
		super();
		this.id = id;
		this.name = name;
		this.dis = dis;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
	}
	public CMesTeamT() {
		super();
	}


}
