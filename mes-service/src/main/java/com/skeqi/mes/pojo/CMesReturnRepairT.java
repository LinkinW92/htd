package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesReturnRepairT {

	private Integer id;
	private Date dt;
	private Integer productionId;
	private String productionName;
	private Integer lineId;
	private String lineName;
	private String sn;
	private String reason;
	private Date endTime;
	private Integer status;
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
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CMesReturnRepairT [id=" + id + ", dt=" + dt + ", productionId=" + productionId + ", productionName="
				+ productionName + ", lineId=" + lineId + ", lineName=" + lineName + ", sn=" + sn + ", reason=" + reason
				+ ", endTime=" + endTime + ", status=" + status + "]";
	}
	public CMesReturnRepairT(Integer id, Date dt, Integer productionId, String productionName, Integer lineId,
			String lineName, String sn, String reason, Date endTime, Integer status) {
		super();
		this.id = id;
		this.dt = dt;
		this.productionId = productionId;
		this.productionName = productionName;
		this.lineId = lineId;
		this.lineName = lineName;
		this.sn = sn;
		this.reason = reason;
		this.endTime = endTime;
		this.status = status;
	}
	public CMesReturnRepairT() {
		super();
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
