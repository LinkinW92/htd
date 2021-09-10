package com.skeqi.mes.pojo.project;

public class CMesFaultTypeT {

	private Integer id;
	private String typeName;
	private String dt;
	private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public CMesFaultTypeT(Integer id, String typeName, String dt, String note) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.dt = dt;
		this.note = note;
	}
	public CMesFaultTypeT() {
		super();
	}


}
