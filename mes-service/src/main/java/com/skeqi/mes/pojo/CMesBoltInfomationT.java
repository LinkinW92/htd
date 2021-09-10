package com.skeqi.mes.pojo;

public class CMesBoltInfomationT {
	private Integer id;//          INTEGER not null,
	private String boltName;//    VARCHAR2(200),
	private Integer st;//           INTEGER,
	private String aLimit;//      VARCHAR2(50),
	private String tLimit;//      VARCHAR2(50),
	private String uploadCode;//  VARCHAR2(100),
	private Integer programNo;//   INTEGER,
	private Integer boltNo;//      INTEGER,
	private String dis;//          VARCHAR2(500)'
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
	public String getBoltName() {
		return boltName;
	}
	public void setBoltName(String boltName) {
		this.boltName = boltName;
	}
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	public String getaLimit() {
		return aLimit;
	}
	public void setaLimit(String aLimit) {
		this.aLimit = aLimit;
	}
	public String gettLimit() {
		return tLimit;
	}
	public void settLimit(String tLimit) {
		this.tLimit = tLimit;
	}
	public String getUploadCode() {
		return uploadCode;
	}
	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
	public Integer getProgramNo() {
		return programNo;
	}
	public void setProgramNo(Integer programNo) {
		this.programNo = programNo;
	}
	public Integer getBoltNo() {
		return boltNo;
	}
	public void setBoltNo(Integer boltNo) {
		this.boltNo = boltNo;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesBoltInfomationT(Integer id, String boltName, Integer st, String aLimit, String tLimit, String uploadCode,
			Integer programNo, Integer boltNo, String dis) {
		super();
		this.id = id;
		this.boltName = boltName;
		this.st = st;
		this.aLimit = aLimit;
		this.tLimit = tLimit;
		this.uploadCode = uploadCode;
		this.programNo = programNo;
		this.boltNo = boltNo;
		this.dis = dis;
	}
	public CMesBoltInfomationT() {
		super();
	}


}
