package com.skeqi.mes.pojo.project;

public class PMesStationEqStatusT {

	private Integer id;
	private String dt;
	private String st;
	private String stationName;
	private String emp;
	private String eqName;
	private Integer eqStatusType;// (设备状态类型  0：正常 1：停机 2：设备故障 3：缺料 4：堵料 5：其他 )
	private String eqStatusName;
	private String resion;
	private Integer lineId;
	private String lineName;
	public PMesStationEqStatusT(Integer id, String dt, String st, String stationName, String emp, String eqName,
			Integer eqStatusType, String eqStatusName, String resion, Integer lineId, String lineName) {
		super();
		this.id = id;
		this.dt = dt;
		this.st = st;
		this.stationName = stationName;
		this.emp = emp;
		this.eqName = eqName;
		this.eqStatusType = eqStatusType;
		this.eqStatusName = eqStatusName;
		this.resion = resion;
		this.lineId = lineId;
		this.lineName = lineName;
	}
	public PMesStationEqStatusT() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	public Integer getEqStatusType() {
		return eqStatusType;
	}
	public void setEqStatusType(Integer eqStatusType) {
		this.eqStatusType = eqStatusType;
	}
	public String getEqStatusName() {
		return eqStatusName;
	}
	public void setEqStatusName(String eqStatusName) {
		this.eqStatusName = eqStatusName;
	}
	public String getResion() {
		return resion;
	}
	public void setResion(String resion) {
		this.resion = resion;
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




}
