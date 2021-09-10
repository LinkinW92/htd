package com.skeqi.mes.pojo.project;

public class DeviceCollect {

	private Integer id;
	private String deviceName;
	private String deviceNumber;
	private Integer alarmState;
	private String lastUpdatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public Integer getAlarmState() {
		return alarmState;
	}
	public void setAlarmState(Integer alarmState) {
		this.alarmState = alarmState;
	}
	public String getLastUpdatetime() {
		return lastUpdatetime;
	}
	public void setLastUpdatetime(String lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}
	public DeviceCollect(Integer id, String deviceName, String deviceNumber, Integer alarmState,
			String lastUpdatetime) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.deviceNumber = deviceNumber;
		this.alarmState = alarmState;
		this.lastUpdatetime = lastUpdatetime;
	}
	public DeviceCollect() {
		super();
	}
	@Override
	public String toString() {
		return "DeviceCollect [id=" + id + ", deviceName=" + deviceName + ", deviceNumber=" + deviceNumber
				+ ", alarmState=" + alarmState + ", lastUpdatetime=" + lastUpdatetime + "]";
	}



}
