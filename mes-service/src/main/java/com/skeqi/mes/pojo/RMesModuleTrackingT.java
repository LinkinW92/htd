package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * 模组运行时刻表
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午11:17:32
 */
public class RMesModuleTrackingT {
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
		return "RMesModuleTrackingT [id=" + id + ", dt=" + dt + ", st=" + st + ", bst=" + bst + ", sn=" + sn
				+ ", enginesn=" + enginesn + ", gearboxsn=" + gearboxsn + ", typename=" + typename + ", traynum="
				+ traynum + ", productnum=" + productnum + ", status=" + status + ", dis=" + dis + ", planId=" + planId
				+ ", reworkFlag=" + reworkFlag + ", productionId=" + productionId + ", lineId=" + lineId + "]";
	}
	public RMesModuleTrackingT(Integer id, Date dt, String st, String bst, String sn, String enginesn, String gearboxsn,
			String typename, String traynum, String productnum, String status, String dis, Integer planId,
			String reworkFlag, Integer productionId, Integer lineId) {
		super();
		this.id = id;
		this.dt = dt;
		this.st = st;
		this.bst = bst;
		this.sn = sn;
		this.enginesn = enginesn;
		this.gearboxsn = gearboxsn;
		this.typename = typename;
		this.traynum = traynum;
		this.productnum = productnum;
		this.status = status;
		this.dis = dis;
		this.planId = planId;
		this.reworkFlag = reworkFlag;
		this.productionId = productionId;
		this.lineId = lineId;
	}
	public RMesModuleTrackingT() {
		super();
	}


}
