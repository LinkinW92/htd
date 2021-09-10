package com.skeqi.mes.pojo;

import java.io.Serializable;

public class CMesDeviceT implements Serializable{
	private Integer id;
	private String dt;// 时间
	private String toolNo;// 编号
	private String toolName;// 名称
	private String toolDis;// 描述
	private Integer estimateLife;// 预计寿命
	private Integer usefulLife;// 已用寿命
	private Integer suprplusLife;// 剩余寿命
	private Integer maintainCycle;// 维护周期
	private String lastMaintain;// 上次维护时间
	private String nextMaintain;// 下次维护时间
	private Integer surplusMaintain;// 剩余维护天数
	private String firstUse;// 初次使用时间
	private Integer everytimes;// 每一次消耗寿命
	private Integer state;//状态


	public CMesDeviceT() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CMesDeviceT [id=" + id + ", dt=" + dt + ", toolNo=" + toolNo + ", toolName=" + toolName + ", toolDis="
				+ toolDis + ", estimateLife=" + estimateLife + ", usefulLife=" + usefulLife + ", suprplusLife="
				+ suprplusLife + ", maintainCycle=" + maintainCycle + ", lastMaintain=" + lastMaintain
				+ ", nextMaintain=" + nextMaintain + ", surplusMaintain=" + surplusMaintain + ", firstUse=" + firstUse
				+ ", everytimes=" + everytimes + ", state=" + state + "]";
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
	public String getToolNo() {
		return toolNo;
	}
	public void setToolNo(String toolNo) {
		this.toolNo = toolNo;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getToolDis() {
		return toolDis;
	}
	public void setToolDis(String toolDis) {
		this.toolDis = toolDis;
	}
	public Integer getEstimateLife() {
		return estimateLife;
	}
	public void setEstimateLife(Integer estimateLife) {
		this.estimateLife = estimateLife;
	}
	public Integer getUsefulLife() {
		return usefulLife;
	}
	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}
	public Integer getSuprplusLife() {
		return suprplusLife;
	}
	public void setSuprplusLife(Integer suprplusLife) {
		this.suprplusLife = suprplusLife;
	}
	public Integer getMaintainCycle() {
		return maintainCycle;
	}
	public void setMaintainCycle(Integer maintainCycle) {
		this.maintainCycle = maintainCycle;
	}
	public String getLastMaintain() {
		return lastMaintain;
	}
	public void setLastMaintain(String lastMaintain) {
		this.lastMaintain = lastMaintain;
	}
	public String getNextMaintain() {
		return nextMaintain;
	}
	public void setNextMaintain(String nextMaintain) {
		this.nextMaintain = nextMaintain;
	}
	public Integer getSurplusMaintain() {
		return surplusMaintain;
	}
	public void setSurplusMaintain(Integer surplusMaintain) {
		this.surplusMaintain = surplusMaintain;
	}
	public String getFirstUse() {
		return firstUse;
	}
	public void setFirstUse(String firstUse) {
		this.firstUse = firstUse;
	}
	public Integer getEverytimes() {
		return everytimes;
	}
	public void setEverytimes(Integer everytimes) {
		this.everytimes = everytimes;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}


}
