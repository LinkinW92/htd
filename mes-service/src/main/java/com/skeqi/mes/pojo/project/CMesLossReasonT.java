package com.skeqi.mes.pojo.project;

public class CMesLossReasonT {

	private Integer id;
	private String name;
	private Integer lossId;
	private String lossName;
	private String reasonNo;

	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLossId() {
		return lossId;
	}

	public void setLossId(Integer lossId) {
		this.lossId = lossId;
	}

	public String getLossName() {
		return lossName;
	}

	public void setLossName(String lossName) {
		this.lossName = lossName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReasonNo() {
		return reasonNo;
	}

	public void setReasonNo(String reasonNo) {
		this.reasonNo = reasonNo;
	}

	public CMesLossReasonT(Integer id, String name, Integer lossId, String lossName, String reasonNo, String note) {
		super();
		this.id = id;
		this.name = name;
		this.lossId = lossId;
		this.lossName = lossName;
		this.reasonNo = reasonNo;
		this.note = note;
	}

	public CMesLossReasonT() {
		super();
	}



}
