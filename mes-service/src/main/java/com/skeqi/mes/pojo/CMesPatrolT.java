package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesPatrolT {

	private Integer id;
	private Integer productionId;
	private String productionName;
	private String sn;
	private String emp;
	private String reason;
	private String dt;
	private Integer stationId;
	private String stationName;

	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public CMesPatrolT() {
		super();
	}
	public CMesPatrolT(Integer id, Integer productionId, String productionName, String sn, String emp, String reason,
			String dt, Integer stationId, String stationName) {
		super();
		this.id = id;
		this.productionId = productionId;
		this.productionName = productionName;
		this.sn = sn;
		this.emp = emp;
		this.reason = reason;
		this.dt = dt;
		this.stationId = stationId;
		this.stationName = stationName;
	}
	@Override
	public String toString() {
		return "CMesPatrolT [id=" + id + ", productionId=" + productionId + ", productionName=" + productionName
				+ ", sn=" + sn + ", emp=" + emp + ", reason=" + reason + ", dt=" + dt + ", stationId=" + stationId
				+ ", stationName=" + stationName + "]";
	}


}
