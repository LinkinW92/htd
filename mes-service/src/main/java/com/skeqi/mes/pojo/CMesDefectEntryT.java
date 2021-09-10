package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesDefectEntryT {

	private Integer id;
	private String dt;
	private Integer productionId;
	private String ProductionName;
	private String sn;
	private Integer lineId;
	private String emp;
	private String reason;
	private String starttime;
	private String endtime;


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
		return ProductionName;
	}
	public void setProductionName(String productionName) {
		ProductionName = productionName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public CMesDefectEntryT() {
		super();
	}
	public CMesDefectEntryT(Integer id, String dt, Integer productionId, String productionName, String sn,
			Integer lineId, String emp, String reason, String starttime, String endtime) {
		super();
		this.id = id;
		this.dt = dt;
		this.productionId = productionId;
		ProductionName = productionName;
		this.sn = sn;
		this.lineId = lineId;
		this.emp = emp;
		this.reason = reason;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "CMesDefectEntryT [id=" + id + ", dt=" + dt + ", productionId=" + productionId + ", ProductionName="
				+ ProductionName + ", sn=" + sn + ", lineId=" + lineId + ", emp=" + emp + ", reason=" + reason
				+ ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}


}
