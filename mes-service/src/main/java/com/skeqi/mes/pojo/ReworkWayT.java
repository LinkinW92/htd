package com.skeqi.mes.pojo;

import java.util.Date;

/*产品返工工艺路线表*/
public class ReworkWayT {
	private int id;//
	private Date dt;//时间
	private String sn;//总成号
	private String stName;//工位名称
	private int stId;//工位id
	private int sertalNo;//序列号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public int getStId() {
		return stId;
	}
	public void setStId(int stId) {
		this.stId = stId;
	}
	public int getSertalNo() {
		return sertalNo;
	}
	public void setSertalNo(int sertalNo) {
		this.sertalNo = sertalNo;
	}

}
