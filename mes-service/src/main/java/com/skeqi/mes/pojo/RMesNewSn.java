package com.skeqi.mes.pojo;

import java.util.Date;

public class RMesNewSn {

	private Integer id;
	private Date dt;
	private String oldSn;
	private String newSn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getOldSn() {
		return oldSn;
	}
	public void setOldSn(String oldSn) {
		this.oldSn = oldSn;
	}
	public String getNewSn() {
		return newSn;
	}
	public void setNewSn(String newSn) {
		this.newSn = newSn;
	}

}
