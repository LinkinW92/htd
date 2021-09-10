package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * 物料维修表
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2019年10月11日 下午5:07:01
 */
public class CMESMaterialRepairT {

	private Integer id;
	private Integer materialId;  //物料id
	private String materialName;  //物料名称
	private Integer stationId;  ///工位id
	private String stationName;  //工位名称
	private Date dt;  //时间
	private Date endDt;  //维修完成时间
	private String emp;  //维修人
	private String Note;
	private Integer status;  //状态
	private String repairEmp;//维修人
	private String reason;  //原因
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
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
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRepairEmp() {
		return repairEmp;
	}
	public void setRepairEmp(String repairEmp) {
		this.repairEmp = repairEmp;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "CMESMaterialRepairT [id=" + id + ", materialId=" + materialId + ", materialName=" + materialName
				+ ", stationId=" + stationId + ", stationName=" + stationName + ", dt=" + dt + ", endDt=" + endDt
				+ ", emp=" + emp + ", Note=" + Note + ", status=" + status + ", repairEmp=" + repairEmp + ", reason="
				+ reason + "]";
	}
	public CMESMaterialRepairT(Integer id, Integer materialId, String materialName, Integer stationId,
			String stationName, Date dt, Date endDt, String emp, String note, Integer status, String repairEmp,
			String reason) {
		super();
		this.id = id;
		this.materialId = materialId;
		this.materialName = materialName;
		this.stationId = stationId;
		this.stationName = stationName;
		this.dt = dt;
		this.endDt = endDt;
		this.emp = emp;
		Note = note;
		this.status = status;
		this.repairEmp = repairEmp;
		this.reason = reason;
	}
	public CMESMaterialRepairT() {
		super();
	}


}
