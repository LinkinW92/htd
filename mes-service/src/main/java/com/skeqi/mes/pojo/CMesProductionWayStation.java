package com.skeqi.mes.pojo;

public class CMesProductionWayStation implements Comparable<CMesProductionWayStation>{

	private String rectNum;

	//工位名字
	private String stationName;
	//工位id
	private String stationID;
	//工位暂时排序id (带上  start 和 end 的)
	private Integer ID;
	//开始或者结束使用的type  开始为start  结束为end  普通工位为task
	private String type;

	//工位序号(不带  start  和  end 的)
	private Integer serialNo;





	public String getRectNum() {
		return rectNum;
	}

	public void setRectNum(String rectNum) {
		this.rectNum = rectNum;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Override
    public int compareTo(CMesProductionWayStation way) {
        int i = this.getID() - way.getID();//先按照年龄排序

        return i;
    }



}
