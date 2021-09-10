package com.skeqi.mes.pojo.project;

public class CMesAndonInfo {

	private String lineName;
	private String stationName;
	private Integer  lossType;



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
	public CMesAndonInfo(String lineName, String stationName) {
		super();
		this.lineName = lineName;
		this.stationName = stationName;
	}
	public CMesAndonInfo() {
		super();
	}
	public Integer getLossType() {
		return lossType;
	}
	public void setLossType(Integer lossType) {
		this.lossType = lossType;
	}



}
