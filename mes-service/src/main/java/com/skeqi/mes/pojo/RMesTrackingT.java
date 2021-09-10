package com.skeqi.mes.pojo;

import java.util.Date;

public class RMesTrackingT {
	private Integer id;
	private Date dt;
	private String st;
	private String bst;
	private String sn;
	private String enginesn;
	private String gearboxsn;
	private String typename;
	private String traynum;
	private String productnum;
	private String status;
	private String dis;
	private Integer planId;
	private String reworkFlag;
	private Integer productionId;
	private Integer lineId;


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
	public String getGearboxsn() {
		return gearboxsn;
	}
	public void setGearboxsn(String gearboxsn) {
		this.gearboxsn = gearboxsn;
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
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	@Override
	public String toString() {
		return "RMesTrackingT [id=" + id + ", dt=" + dt + ", st=" + st + ", bst=" + bst + ", sn=" + sn + ", enginesn="
				+ enginesn + ", gearboxsn=" + gearboxsn + ", typename=" + typename + ", traynum=" + traynum
				+ ", productnum=" + productnum + ", status=" + status + ", dis=" + dis + ", planId=" + planId
				+ ", reworkFlag=" + reworkFlag + ", productionId=" + productionId + ", lineId=" + lineId + "]";
	}







}
