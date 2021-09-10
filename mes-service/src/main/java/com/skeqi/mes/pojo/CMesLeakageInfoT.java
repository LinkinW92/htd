package com.skeqi.mes.pojo;

public class CMesLeakageInfoT {
	private Integer id;//           INTEGER not null,
	private Integer st;//           INTEGER,
	private String leakageName;// VARCHAR2(100),
	private String pvLimit;//     VARCHAR2(100),
	private String lvLimit;//     VARCHAR2(100),
	private String dis;//          VARCHAR2(200),
	private String uploadCode;//  VARCHAR2(100)
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
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	public String getLeakageName() {
		return leakageName;
	}
	public void setLeakageName(String leakageName) {
		this.leakageName = leakageName;
	}
	public String getPvLimit() {
		return pvLimit;
	}
	public void setPvLimit(String pvLimit) {
		this.pvLimit = pvLimit;
	}
	public String getLvLimit() {
		return lvLimit;
	}
	public void setLvLimit(String lvLimit) {
		this.lvLimit = lvLimit;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getUploadCode() {
		return uploadCode;
	}
	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
	public CMesLeakageInfoT(Integer id, Integer st, String leakageName, String pvLimit, String lvLimit, String dis,
			String uploadCode) {
		super();
		this.id = id;
		this.st = st;
		this.leakageName = leakageName;
		this.pvLimit = pvLimit;
		this.lvLimit = lvLimit;
		this.dis = dis;
		this.uploadCode = uploadCode;
	}
	public CMesLeakageInfoT() {
		super();
	}


}
