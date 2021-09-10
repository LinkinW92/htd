package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesStationT;

public class CWmsOutTaskqueueT {

	private Integer id;
	private String dt;//日期
	private String listNo;//单号
	private String tray;//托盘号
	private Integer stationId;//目标工位地标
	private String flag;//动作标记
	private String overDt;//完成时间
	private Integer locationId;//库位id

	private CWmsLocationT location;//库位
	private CMesStationT station;//工位
	public CMesStationT getStation() {
		return station;
	}
	public void setStation(CMesStationT station) {
		this.station = station;
	}
	public CWmsLocationT getLocation() {
		return location;
	}
	public void setLocation(CWmsLocationT location) {
		this.location = location;
	}
	public CWmsOutTaskqueueT() {
		// TODO Auto-generated constructor stub
	}
	public CWmsOutTaskqueueT(Integer id, String dt, String listNo, String tray, Integer stationId, String flag,
			String overDt, Integer locationId) {
		this.id = id;
		this.dt = dt;
		this.listNo = listNo;
		this.tray = tray;
		this.stationId = stationId;
		this.flag = flag;
		this.overDt = overDt;
		this.locationId = locationId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public String getTray() {
		return tray;
	}
	public void setTray(String tray) {
		this.tray = tray;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOverDt() {
		return overDt;
	}
	public void setOverDt(String overDt) {
		this.overDt = overDt;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	@Override
	public String toString() {
		return "CWmsOutTaskqueueT [id=" + id + ", dt=" + dt + ", listNo=" + listNo + ", tray=" + tray
				+ ", stationId=" + stationId + ", flag=" + flag + ", overDt=" + overDt + ", locationId="
				+ locationId + "]";
	}

}
