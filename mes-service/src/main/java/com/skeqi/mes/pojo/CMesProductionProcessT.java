package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesProductionProcessT {
	private Integer id;//               INTEGER not null,
	private Integer lineId;//          INTEGER,
	private Integer materialListId;// INTEGER,
	private Integer mfParameterId;//  INTEGER,
	private Integer productonId;//     INTEGER,
	private Date dt;//               DATE
	private String lineName;
	private String materialListName;
	private String mfParameterName;
	private String productionName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getMaterialListId() {
		return materialListId;
	}
	public void setMaterialListId(Integer materialListId) {
		this.materialListId = materialListId;
	}
	public Integer getMfParameterId() {
		return mfParameterId;
	}
	public void setMfParameterId(Integer mfParameterId) {
		this.mfParameterId = mfParameterId;
	}
	public Integer getProductonId() {
		return productonId;
	}
	public void setProductonId(Integer productonId) {
		this.productonId = productonId;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getMaterialListName() {
		return materialListName;
	}
	public void setMaterialListName(String materialListName) {
		this.materialListName = materialListName;
	}
	public String getMfParameterName() {
		return mfParameterName;
	}
	public void setMfParameterName(String mfParameterName) {
		this.mfParameterName = mfParameterName;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public CMesProductionProcessT(Integer id, Integer lineId, Integer materialListId, Integer mfParameterId,
			Integer productonId, Date dt, String lineName, String materialListName, String mfParameterName,
			String productionName) {
		super();
		this.id = id;
		this.lineId = lineId;
		this.materialListId = materialListId;
		this.mfParameterId = mfParameterId;
		this.productonId = productonId;
		this.dt = dt;
		this.lineName = lineName;
		this.materialListName = materialListName;
		this.mfParameterName = mfParameterName;
		this.productionName = productionName;
	}
	public CMesProductionProcessT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesProductionProcessT [id=" + id + ", lineId=" + lineId + ", materialListId=" + materialListId
				+ ", mfParameterId=" + mfParameterId + ", productonId=" + productonId + ", dt=" + dt + ", lineName="
				+ lineName + ", materialListName=" + materialListName + ", mfParameterName=" + mfParameterName
				+ ", productionName=" + productionName + "]";
	}



}
