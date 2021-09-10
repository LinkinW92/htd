package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesDeviceUpkeep {

	private Integer id;
	private Date dt;  //时间
	private String deviceName;  //设备名称
	private Integer lineId; //产线id
	private String lineName;  //产线name
	private String emp;  //负责员工
	private String upkeepPerson;  //保养人
	private String note;  //备注
	private Date endTime;  //下次时间
	private Integer estimateLif;  //预计寿命
	private Integer useLife; //使用寿命
	private Integer surplusLife;  //剩余寿命
	private Integer maintainCycle;  //维护周期
	private Integer surplusMaintain;   //剩余维护天数
	private Integer everylims;   //每次使用消耗寿命
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
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getUpkeepPerson() {
		return upkeepPerson;
	}
	public void setUpkeepPerson(String upkeepPerson) {
		this.upkeepPerson = upkeepPerson;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getEstimateLif() {
		return estimateLif;
	}
	public void setEstimateLif(Integer estimateLif) {
		this.estimateLif = estimateLif;
	}
	public Integer getUseLife() {
		return useLife;
	}
	public void setUseLife(Integer useLife) {
		this.useLife = useLife;
	}
	public Integer getSurplusLife() {
		return surplusLife;
	}
	public void setSurplusLife(Integer surplusLife) {
		this.surplusLife = surplusLife;
	}
	public Integer getMaintainCycle() {
		return maintainCycle;
	}
	public void setMaintainCycle(Integer maintainCycle) {
		this.maintainCycle = maintainCycle;
	}
	public Integer getSurplusMaintain() {
		return surplusMaintain;
	}
	public void setSurplusMaintain(Integer surplusMaintain) {
		this.surplusMaintain = surplusMaintain;
	}
	public Integer getEverylims() {
		return everylims;
	}
	public void setEverylims(Integer everylims) {
		this.everylims = everylims;
	}
	@Override
	public String toString() {
		return "CMesDeviceUpkeep [id=" + id + ", dt=" + dt + ", deviceName=" + deviceName + ", lineId=" + lineId
				+ ", lineName=" + lineName + ", emp=" + emp + ", upkeepPerson=" + upkeepPerson + ", note=" + note
				+ ", endTime=" + endTime + ", estimateLif=" + estimateLif + ", useLife=" + useLife + ", surplusLife="
				+ surplusLife + ", maintainCycle=" + maintainCycle + ", surplusMaintain=" + surplusMaintain
				+ ", everylims=" + everylims + "]";
	}
	public CMesDeviceUpkeep(Integer id, Date dt, String deviceName, Integer lineId, String lineName, String emp,
			String upkeepPerson, String note, Date endTime, Integer estimateLif, Integer useLife, Integer surplusLife,
			Integer maintainCycle, Integer surplusMaintain, Integer everylims) {
		super();
		this.id = id;
		this.dt = dt;
		this.deviceName = deviceName;
		this.lineId = lineId;
		this.lineName = lineName;
		this.emp = emp;
		this.upkeepPerson = upkeepPerson;
		this.note = note;
		this.endTime = endTime;
		this.estimateLif = estimateLif;
		this.useLife = useLife;
		this.surplusLife = surplusLife;
		this.maintainCycle = maintainCycle;
		this.surplusMaintain = surplusMaintain;
		this.everylims = everylims;
	}
	public CMesDeviceUpkeep() {
		super();
	}


}
