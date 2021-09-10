package com.skeqi.mes.pojo;

public class CMesBomDetailT {
	private Integer id;//            INTEGER not null,
	private Integer bomId;//        INTEGER,
	private Integer dataType;//     INTEGER,
	private Integer dataId;//       INTEGER,
	private Integer reciveNumber;// INTEGER,
	private String traceFlag;//    VARCHAR2(2)
	private String bomName;
	private Integer lineId;
	private Integer stationId;
	private String lineName;
	private String stationName;
	private String materialName;


	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getReciveNumber() {
		return reciveNumber;
	}
	public void setReciveNumber(Integer reciveNumber) {
		this.reciveNumber = reciveNumber;
	}
	public String getBomName() {
		return bomName;
	}
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBomId() {
		return bomId;
	}
	public void setBomId(Integer bomId) {
		this.bomId = bomId;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getTraceFlag() {
		return traceFlag;
	}
	public void setTraceFlag(String traceFlag) {
		this.traceFlag = traceFlag;
	}
	public CMesBomDetailT(Integer id, Integer bomId, Integer dataType, Integer dataId, Integer recive_number,
			String traceFlag) {
		super();
		this.id = id;
		this.bomId = bomId;
		this.dataType = dataType;
		this.dataId = dataId;
		this.traceFlag = traceFlag;
	}
	public CMesBomDetailT() {
		super();
	}


}
