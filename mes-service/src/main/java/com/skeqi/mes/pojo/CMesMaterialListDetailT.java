package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesMaterialListDetailT {
	private Integer id;//                      INTEGER not null,
	private Date dt;//                      DATE,
	private String figureNo;//               VARCHAR2(100),
	private String materialNo;//             VARCHAR2(100),
	private String materialName;//           VARCHAR2(200),
	private String materialSheft;//          VARCHAR2(20),
	private String materialReplace;//        VARCHAR2(150),
	private Integer materialTrace;//          INTEGER,
	private Integer materialNumber;//         INTEGER,
	private Integer materialImpFlag;//       INTEGER,
	private Integer materialCheck;//          INTEGER,
	private Integer materialGetNumber;//     INTEGER,
	private String materialStore;//          VARCHAR2(100),
	private Integer materialGetCheckFlag;// INTEGER,
	private Integer stationId;//              INTEGER,
	private Integer materialPullFalg;//      INTEGER
	private String materilaListId;//所属bom
	private String stationName;
	private String listNo;

	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public String getMaterilaListId() {
		return materilaListId;
	}
	public void setMaterilaListId(String materilaListId) {
		this.materilaListId = materilaListId;
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
	public String getFigureNo() {
		return figureNo;
	}
	public void setFigureNo(String figureNo) {
		this.figureNo = figureNo;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSheft() {
		return materialSheft;
	}
	public void setMaterialSheft(String materialSheft) {
		this.materialSheft = materialSheft;
	}
	public String getMaterialReplace() {
		return materialReplace;
	}
	public void setMaterialReplace(String materialReplace) {
		this.materialReplace = materialReplace;
	}
	public Integer getMaterialTrace() {
		return materialTrace;
	}
	public void setMaterialTrace(Integer materialTrace) {
		this.materialTrace = materialTrace;
	}
	public Integer getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}
	public Integer getMaterialImpFlag() {
		return materialImpFlag;
	}
	public void setMaterialImpFlag(Integer materialImpFlag) {
		this.materialImpFlag = materialImpFlag;
	}
	public Integer getMaterialCheck() {
		return materialCheck;
	}
	public void setMaterialCheck(Integer materialCheck) {
		this.materialCheck = materialCheck;
	}
	public Integer getMaterialGetNumber() {
		return materialGetNumber;
	}
	public void setMaterialGetNumber(Integer materialGetNumber) {
		this.materialGetNumber = materialGetNumber;
	}
	public String getMaterialStore() {
		return materialStore;
	}
	public void setMaterialStore(String materialStore) {
		this.materialStore = materialStore;
	}
	public Integer getMaterialGetCheckFlag() {
		return materialGetCheckFlag;
	}
	public void setMaterialGetCheckFlag(Integer materialGetCheckFlag) {
		this.materialGetCheckFlag = materialGetCheckFlag;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getMaterialPullFalg() {
		return materialPullFalg;
	}
	public void setMaterialPullFalg(Integer materialPullFalg) {
		this.materialPullFalg = materialPullFalg;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public CMesMaterialListDetailT(Integer id, Date dt, String figureNo, String materialNo, String materialName,
			String materialSheft, String materialReplace, Integer materialTrace, Integer materialNumber,
			Integer materialImpFlag, Integer materialCheck, Integer materialGetNumber, String materialStore,
			Integer materialGetCheckFlag, Integer stationId, Integer materialPullFalg, String stationName) {
		super();
		this.id = id;
		this.dt = dt;
		this.figureNo = figureNo;
		this.materialNo = materialNo;
		this.materialName = materialName;
		this.materialSheft = materialSheft;
		this.materialReplace = materialReplace;
		this.materialTrace = materialTrace;
		this.materialNumber = materialNumber;
		this.materialImpFlag = materialImpFlag;
		this.materialCheck = materialCheck;
		this.materialGetNumber = materialGetNumber;
		this.materialStore = materialStore;
		this.materialGetCheckFlag = materialGetCheckFlag;
		this.stationId = stationId;
		this.materialPullFalg = materialPullFalg;
		this.stationName = stationName;
	}
	public CMesMaterialListDetailT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMaterialListDetailT [id=" + id + ", dt=" + dt + ", figureNo=" + figureNo + ", materialNo="
				+ materialNo + ", materialName=" + materialName + ", materialSheft=" + materialSheft
				+ ", materialReplace=" + materialReplace + ", materialTrace=" + materialTrace + ", materialNumber="
				+ materialNumber + ", materialImpFlag=" + materialImpFlag + ", materialCheck=" + materialCheck
				+ ", materialGetNumber=" + materialGetNumber + ", materialStore=" + materialStore
				+ ", materialGetCheckFlag=" + materialGetCheckFlag + ", stationId=" + stationId + ", materialPullFalg="
				+ materialPullFalg + ", stationName=" + stationName + "]";
	}


}
