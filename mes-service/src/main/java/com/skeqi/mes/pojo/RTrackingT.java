package com.skeqi.mes.pojo;

import java.util.Date;
/*总成状态运行时表*/
public class RTrackingT {
	private int id;//
	private Date DT;//上线时间
	private String ST;//工位
	private String BST;//前工位
	private String SN;//总成号
	private String engineSN;//总成所属当前工位下标
	private String gearboxSN;//当前所处工位下标
	private String typeName;//产品类型
	private String trayNum;//托盘号
	private String productNum;//产品编号
	private String status;//状态
	private String dis;//描述
	private int plan_id;//计划id
	private String rework_flag;//返工标记  0：正常 1：返工
	private String lineId;
	private String productionName;
	private Integer productionId;
	private String reason;



	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	@Override
	public String toString() {
		return "RTrackingT [id=" + id + ", DT=" + DT + ", ST=" + ST + ", BST=" + BST + ", SN=" + SN + ", engineSN="
				+ engineSN + ", gearboxSN=" + gearboxSN + ", typeName=" + typeName + ", trayNum=" + trayNum
				+ ", productNum=" + productNum + ", status=" + status + ", dis=" + dis + ", plan_id=" + plan_id
				+ ", rework_flag=" + rework_flag + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDT() {
		return DT;
	}
	public void setDT(Date dT) {
		DT = dT;
	}
	public String getST() {
		return ST;
	}
	public void setST(String sT) {
		ST = sT;
	}
	public String getBST() {
		return BST;
	}
	public void setBST(String bST) {
		BST = bST;
	}
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTrayNum() {
		return trayNum;
	}
	public void setTrayNum(String trayNum) {
		this.trayNum = trayNum;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
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
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getRework_flag() {
		return rework_flag;
	}
	public void setRework_flag(String rework_flag) {
		this.rework_flag = rework_flag;
	}


}
