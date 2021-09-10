package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesOutWareHouse {
	private Integer id;//              NUMBER not null,
	private String materialName;
	private Date outDate;//        DATE,
	private Integer materialNumber;// NUMBER,
	private String outType;
	private String dis;//             VARCHAR2(50)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Integer getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesOutWareHouse(Integer id, String materialName, Date outDate, Integer materialNumber, String outType,
			String dis) {
		super();
		this.id = id;
		this.materialName = materialName;
		this.outDate = outDate;
		this.materialNumber = materialNumber;
		this.outType = outType;
		this.dis = dis;
	}
	public CMesOutWareHouse() {
		super();
	}


}
