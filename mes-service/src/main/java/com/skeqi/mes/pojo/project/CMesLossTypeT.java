package com.skeqi.mes.pojo.project;

public class CMesLossTypeT {

	private Integer id;
	private String lossName ;

	private String Dt;
	private String note;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLossName() {
		return lossName;
	}
	public void setLossName(String lossName) {
		this.lossName = lossName;
	}
	public CMesLossTypeT(Integer id, String lossName) {
		super();
		this.id = id;
		this.lossName = lossName;
	}
	public String getDt() {
		return Dt;
	}
	public void setDt(String dt) {
		Dt = dt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public CMesLossTypeT(Integer id, String lossName, String dt, String note) {
		super();
		this.id = id;
		this.lossName = lossName;
		Dt = dt;
		this.note = note;
	}
	public CMesLossTypeT() {
		super();
	}

}
