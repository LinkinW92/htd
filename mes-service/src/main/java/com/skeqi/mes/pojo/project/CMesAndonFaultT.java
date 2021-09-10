package com.skeqi.mes.pojo.project;

import java.util.Date;

/**
 * 安灯故障父类
 * @author : FQZ
 * @Package: com.skeqi.mes.pojo.project
 * @date   : 2020年4月20日 下午1:25:17
 */
public class CMesAndonFaultT {

	private String lineName;
	private String stationName;
	private String lossType;
	private Integer pages;



	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getLossType() {
		return lossType;
	}
	public void setLossType(String lossType) {
		this.lossType = lossType;
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
	public CMesAndonFaultT(String lineName, String stationName) {
		super();
		this.lineName = lineName;
		this.stationName = stationName;
	}
	public CMesAndonFaultT() {
		super();
	}


}
