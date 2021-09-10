package com.skeqi.mes.pojo;

import java.util.Date;
/**
 * 设备点检
 * @author Lenovo
 *
 */
public class CMesDeviceSpotT {
	private Integer id;//设备点检ID
	private Integer lineId;//设备点检产线Id
	private String dt;//设备点检时间
	private Integer status;//设备点检状态
	private String emp;//负责员工
	private String deviceName;//设备名称
	private String lineName;//产线名称
	public CMesDeviceSpotT() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	@Override
	public String toString() {
		return "CMesDeviceSpotT [id=" + id + ", lineId=" + lineId + ", dt=" + dt + ", status=" + status + ", emp=" + emp
				+ ", deviceName=" + deviceName + ", lineName=" + lineName + "]";
	}
	public CMesDeviceSpotT(Integer id, Integer lineId, String dt, Integer status, String emp, String deviceName,
			String lineName) {
		super();
		this.id = id;
		this.lineId = lineId;
		this.dt = dt;
		this.status = status;
		this.emp = emp;
		this.deviceName = deviceName;
		this.lineName = lineName;
	}



}
