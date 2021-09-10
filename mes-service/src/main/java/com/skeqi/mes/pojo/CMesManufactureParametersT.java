package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesManufactureParametersT {
	private Integer id;//             INTEGER not null,
	private Date dt;//             DATE,
	private String listNo;//        VARCHAR2(100),
	private String listName;//      VARCHAR2(200),
	private Date effectiveTime;// DATE,
	private Date invalidTime;//    DATE,
	private Double listVersion;//   INTEGER
	private Integer lineId;
	private String lineName;

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
	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public Double getListVersion() {
		return listVersion;
	}
	public void setListVersion(Double listVersion) {
		this.listVersion = listVersion;
	}
	@Override
	public String toString() {
		return "CMesManufactureParametersT [id=" + id + ", dt=" + dt + ", listNo=" + listNo + ", listName=" + listName
				+ ", effectiveTime=" + effectiveTime + ", invalidTime=" + invalidTime + ", listVersion=" + listVersion
				+ ", lineId=" + lineId + ", lineName=" + lineName + "]";
	}
	public CMesManufactureParametersT(Integer id, Date dt, String listNo, String listName, Date effectiveTime,
			Date invalidTime, Double listVersion, Integer lineId, String lineName) {
		super();
		this.id = id;
		this.dt = dt;
		this.listNo = listNo;
		this.listName = listName;
		this.effectiveTime = effectiveTime;
		this.invalidTime = invalidTime;
		this.listVersion = listVersion;
		this.lineId = lineId;
		this.lineName = lineName;
	}
	public CMesManufactureParametersT() {
		super();
	}


}
