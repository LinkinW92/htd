package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesDefectResionT {

	private Integer id;
	private String resionNumber;
	private String dis;
	private String dt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResionNumber() {
		return resionNumber;
	}
	public void setResionNumber(String resionNumber) {
		this.resionNumber = resionNumber;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}

	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public CMesDefectResionT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesDefectResionT [id=" + id + ", resionNumber=" + resionNumber + ", dis=" + dis + ", dt=" + dt + "]";
	}

}
