package com.skeqi.mes.pojo.project;

public class CMesToolManager {

	private Integer id;
	private String dt;
	private String toolNo;
	private String toolName;
	private String toolDis;
	private Integer estimateLife;
	private Integer usefulLife;
	private Integer surplusLife;
	private Integer maintainCycle;
	private String lastMaintain;
	private String nextMaintain;
	private Integer surplusMaintain;
	private String firstUse;
	private Integer everytimes;
	private Integer viewState;
	private Integer lineId;
	private Integer stationId;
	private String lineName;
	private String stationName;

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
	public Integer getViewState() {
		return viewState;
	}
	public void setViewState(Integer viewState) {
		this.viewState = viewState;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public CMesToolManager(Integer id, String dt, String toolNo, String toolName, String toolDis, Integer estimateLife,
			Integer usefulLife, Integer surplusLife, Integer maintainCycle, String lastMaintain, String nextMaintain,
			Integer surplusMaintain, String firstUse, Integer everytimes, Integer viewState, Integer lineId,
			Integer stationId, String lineName, String stationName) {
		super();
		this.id = id;
		this.dt = dt;
		this.toolNo = toolNo;
		this.toolName = toolName;
		this.toolDis = toolDis;
		this.estimateLife = estimateLife;
		this.usefulLife = usefulLife;
		this.surplusLife = surplusLife;
		this.maintainCycle = maintainCycle;
		this.lastMaintain = lastMaintain;
		this.nextMaintain = nextMaintain;
		this.surplusMaintain = surplusMaintain;
		this.firstUse = firstUse;
		this.everytimes = everytimes;
		this.viewState = viewState;
		this.lineId = lineId;
		this.stationId = stationId;
		this.lineName = lineName;
		this.stationName = stationName;
	}
	public CMesToolManager() {
		super();
	}
	@Override
	public String toString() {
		return "CMesToolManager [id=" + id + ", dt=" + dt + ", toolNo=" + toolNo + ", toolName=" + toolName
				+ ", toolDis=" + toolDis + ", estimateLife=" + estimateLife + ", usefulLife=" + usefulLife
				+ ", surplusLife=" + surplusLife + ", maintainCycle=" + maintainCycle + ", lastMaintain=" + lastMaintain
				+ ", nextMaintain=" + nextMaintain + ", surplusMaintain=" + surplusMaintain + ", firstUse=" + firstUse
				+ ", everytimes=" + everytimes + ", viewState=" + viewState + ", lineId=" + lineId + ", stationId="
				+ stationId + ", lineName=" + lineName + ", stationName=" + stationName + "]";
	}



}
