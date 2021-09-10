package com.skeqi.mes.pojo;

import java.util.Date;

import javax.xml.crypto.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PMesTrackingT {
	private Integer id;				//主键
	private Date dt;				//日期
	private String st;				//当前工位
	private String bst;				//前工位
	private String sn;				//总成
	private String enginesn;		//下线类别：0：正常下线 1：维修下线 2：报废
	private String gearBodSn;		//当前工位下标（前工位）
	private String typename;		//产品类别
	private String traynum;			//托盘号
	private String productnum;		//产品编号（与PLC交互用）
	private String status;			//产品状态
	private String dis;				//描述
	private String planId;			//计划ID
	private String offLineDt;		//下线时间
	private String reworkFlag;		//返工标记
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
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getBst() {
		return bst;
	}
	public void setBst(String bst) {
		this.bst = bst;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getEnginesn() {
		return enginesn;
	}
	public void setEnginesn(String enginesn) {
		this.enginesn = enginesn;
	}
	public String getGearBodSn() {
		return gearBodSn;
	}
	public void setGearBodSn(String gearBodSn) {
		this.gearBodSn = gearBodSn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getOffLineDt() {
		return offLineDt;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTraynum() {
		return traynum;
	}
	public void setTraynum(String traynum) {
		this.traynum = traynum;
	}
	public String getProductnum() {
		return productnum;
	}
	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}
	public void setOffLineDt(String offLineDt) {
		this.offLineDt = offLineDt;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public PMesTrackingT(Integer id, Date dt, String st, String bst, String sn, String enginesn, String gearBodSn,
			String typename, String traynum, String productnum, String status, String dis, String planId,
			String offLineDt, String reworkFlag) {
		super();
		this.id = id;
		this.dt = dt;
		this.st = st;
		this.bst = bst;
		this.sn = sn;
		this.enginesn = enginesn;
		this.gearBodSn = gearBodSn;
		this.typename = typename;
		this.traynum = traynum;
		this.productnum = productnum;
		this.status = status;
		this.dis = dis;
		this.planId = planId;
		this.offLineDt = offLineDt;
		this.reworkFlag = reworkFlag;
	}
	public PMesTrackingT() {
		super();
	}
	@Override
	public String toString() {
		return "PMesTrackingT [id=" + id + ", dt=" + dt + ", st=" + st + ", bst=" + bst + ", sn=" + sn + ", enginesn="
				+ enginesn + ", gearBodSn=" + gearBodSn + ", typename=" + typename + ", traynum=" + traynum
				+ ", productnum=" + productnum + ", status=" + status + ", dis=" + dis + ", planId=" + planId
				+ ", offLineDt=" + offLineDt + ", reworkFlag=" + reworkFlag + "]";
	}







}
