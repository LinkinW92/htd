package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesDeviceRepairT {

	private Integer id;
	private Date dt;
	private String deviceName;
	private String repairPerson;
	private String emp;
	private String reason;
	private String note;
	private Integer lineId;
	private String lineName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getRepairPerson() {
		return repairPerson;
	}
	public void setRepairPerson(String repairPerson) {
		this.repairPerson = repairPerson;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	@Override
	public String toString() {
		return "CMesDeviceRepairT [id=" + id + ", dt=" + dt + ", deviceName=" + deviceName + ", repairPerson="
				+ repairPerson + ", emp=" + emp + ", reason=" + reason + ", note=" + note + ", lineId=" + lineId
				+ ", lineName=" + lineName + "]";
	}
	public CMesDeviceRepairT(Integer id, Date dt, String deviceName, String repairPerson, String emp, String reason,
			String note, Integer lineId, String lineName) {
		super();
		this.id = id;
		this.dt = dt;
		this.deviceName = deviceName;
		this.repairPerson = repairPerson;
		this.emp = emp;
		this.reason = reason;
		this.note = note;
		this.lineId = lineId;
		this.lineName = lineName;
	}
	public CMesDeviceRepairT() {
		super();
	}


}
