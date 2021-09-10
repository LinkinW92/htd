package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesTrayT {
	private Integer id;//        INTEGER not null,
	private String trayName;// VARCHAR2(100) not null,
	private String trayVr;//   VARCHAR2(100),
	private Date dt;//        DATE,
	private Integer lineId;//   INTEGER,
	private String dis;//       VARCHAR2(200)
	private String lineName;

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
	public String getTrayName() {
		return trayName;
	}
	public void setTrayName(String trayName) {
		this.trayName = trayName;
	}
	public String getTrayVr() {
		return trayVr;
	}
	public void setTrayVr(String trayVr) {
		this.trayVr = trayVr;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesTrayT(Integer id, String trayName, String trayVr, Date dt, Integer lineId, String dis) {
		super();
		this.id = id;
		this.trayName = trayName;
		this.trayVr = trayVr;
		this.dt = dt;
		this.lineId = lineId;
		this.dis = dis;
	}
	public CMesTrayT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesTrayT [id=" + id + ", trayName=" + trayName + ", trayVr=" + trayVr + ", dt=" + dt + ", lineId="
				+ lineId + ", dis=" + dis + "]";
	}


}
