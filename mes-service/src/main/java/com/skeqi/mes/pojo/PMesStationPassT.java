package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesStationPassT {
	private Integer id;   //id
	private Date dt;		//时间
	private String sn;		//总成
	private String st;		//工位
	private String emp;		//员工号
	private String tid;		//托盘号
	private String status;	//总成状态
	private Integer passFlag;//1.进站，2.出站
	private  Integer lineId;//所属产线


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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

}
