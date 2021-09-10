package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesAssemblyType {
	private Integer id;//                 NUMBER not null,
	private String assemblyTypeNo;//    NUMBER,
	private String assemblyTypeName;//  VARCHAR2(50),
	private String assemblyTypeDis;//   VARCHAR2(200),
	private Date dt;//                  TIMESTAMP(6),
	private Integer lineId;//             NUMBER
	private String lineName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssemblyTypeNo() {
		return assemblyTypeNo;
	}
	public void setAssemblyTypeNo(String assemblyTypeNo) {
		this.assemblyTypeNo = assemblyTypeNo;
	}
	public String getAssemblyTypeName() {
		return assemblyTypeName;
	}
	public void setAssemblyTypeName(String assemblyTypeName) {
		this.assemblyTypeName = assemblyTypeName;
	}
	public String getAssemblyTypeDis() {
		return assemblyTypeDis;
	}
	public void setAssemblyTypeDis(String assemblyTypeDis) {
		this.assemblyTypeDis = assemblyTypeDis;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
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
	public CMesAssemblyType(Integer id, String assemblyTypeNo, String assemblyTypeName, String assemblyTypeDis,
			Date dt, Integer lineId, String lineName) {
		super();
		this.id = id;
		this.assemblyTypeNo = assemblyTypeNo;
		this.assemblyTypeName = assemblyTypeName;
		this.assemblyTypeDis = assemblyTypeDis;
		this.dt = dt;
		this.lineId = lineId;
		this.lineName = lineName;
	}
	public CMesAssemblyType() {
		super();
	}
	@Override
	public String toString() {
		return "CMesAssemblyType [id=" + id + ", assemblyTypeNo=" + assemblyTypeNo + ", assemblyTypeName="
				+ assemblyTypeName + ", assemblyTypeDis=" + assemblyTypeDis + ", dt=" + dt + ", lineId=" + lineId
				+ ", lineName=" + lineName + "]";
	}


}
