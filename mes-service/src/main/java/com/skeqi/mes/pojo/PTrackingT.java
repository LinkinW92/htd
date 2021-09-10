package com.skeqi.mes.pojo;

import java.util.Date;

/*总成状态永久性表*/
public class PTrackingT {
	private int id;//主键
	private Date dt;//日期
	private String st;//工位
	private String bst;//前工号
	private String sn;//总成
	private String engineSN;//总成所属当前工位下标
	private String gearboxSN;//当前所处工位下标
	private String typename;//产品类型
	private String traynum;//托盘号
	private String productnum;//产品编号
	private String status;//状态
	private String dis;//描述
	private int planId;//计划id
	private Date offlineDt;//下线时间
	private String reworkFlag;//返工标记  0：正常 1：返工
	private Integer productionId;//产品id
	private Integer lineId;//产线id
	private String productionName;
	private String reason;



	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
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
	public String getEngineSN() {
		return engineSN;
	}
	public void setEngineSN(String engineSN) {
		this.engineSN = engineSN;
	}
	public String getGearboxSN() {
		return gearboxSN;
	}
	public void setGearboxSN(String gearboxSN) {
		this.gearboxSN = gearboxSN;
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
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public Date getOfflineDt() {
		return offlineDt;
	}
	public void setOfflineDt(Date offlineDt) {
		this.offlineDt = offlineDt;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}

}
