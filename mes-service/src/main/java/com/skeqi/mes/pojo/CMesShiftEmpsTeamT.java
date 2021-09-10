package com.skeqi.mes.pojo;

public class CMesShiftEmpsTeamT {
	private Integer id;
	private Integer shiftsTeamId;//班次ID
	private Integer empTeamId;//班组ID
	private Integer lineId;//产线ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShiftsTeamId() {
		return shiftsTeamId;
	}
	public void setShiftsTeamId(Integer shiftsTeamId) {
		this.shiftsTeamId = shiftsTeamId;
	}
	public Integer getEmpTeamId() {
		return empTeamId;
	}
	public void setEmpTeamId(Integer empTeamId) {
		this.empTeamId = empTeamId;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public CMesShiftEmpsTeamT(Integer id, Integer shiftsTeamId, Integer empTeamId, Integer lineId) {
		super();
		this.id = id;
		this.shiftsTeamId = shiftsTeamId;
		this.empTeamId = empTeamId;
		this.lineId = lineId;
	}
	public CMesShiftEmpsTeamT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesShiftEmpsTeamT [id=" + id + ", shiftsTeamId=" + shiftsTeamId + ", empTeamId=" + empTeamId
				+ ", lineId=" + lineId + "]";
	}


}
