package com.skeqi.mes.pojo;

public class CMesOtherDataT {

	private Integer id;//          INTEGER not null,
	private String otherName;//  VARCHAR2(200),
	private Integer st;//          INTEGER,
	private String uploadCode;// VARCHAR2(100),
	private String dis;//         VARCHAR2(200)
	private String stationName;

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	public String getUploadCode() {
		return uploadCode;
	}
	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesOtherDataT(Integer id, String otherName, Integer st, String uploadCode, String dis) {
		super();
		this.id = id;
		this.otherName = otherName;
		this.st = st;
		this.uploadCode = uploadCode;
		this.dis = dis;
	}
	public CMesOtherDataT() {
		super();
	}


}
