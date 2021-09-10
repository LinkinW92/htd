package com.skeqi.mes.pojo;

public class CMesMaterialMsgT {
	private Integer id;//                 NUMBER not null,
	private String materialName;//       VARCHAR2(20),
	private Integer st;//                  NUMBER,
	private Integer materialType;//       NUMBER,
	private String materialVr;//         VARCHAR2(20),
	private String materialComeType;//  VARCHAR2(20),
	private String materialSupplier;//   VARCHAR2(20),
	private String dis;//                 VARCHAR2(100),
	private String uploadCode;//         VARCHAR2(100)
	private String stationName;

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	public Integer getMaterialType() {
		return materialType;
	}
	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}
	public String getMaterialVr() {
		return materialVr;
	}
	public void setMaterialVr(String materialVr) {
		this.materialVr = materialVr;
	}
	public String getMaterialComeType() {
		return materialComeType;
	}
	public void setMaterialComeType(String materialComeType) {
		this.materialComeType = materialComeType;
	}
	public String getMaterialSupplier() {
		return materialSupplier;
	}
	public void setMaterialSupplier(String materialSupplier) {
		this.materialSupplier = materialSupplier;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getUploadCode() {
		return uploadCode;
	}
	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
	public CMesMaterialMsgT(Integer id, String materialName, Integer st, Integer materialType, String materialVr,
			String materialComeType, String materialSupplier, String dis, String uploadCode) {
		super();
		this.id = id;
		this.materialName = materialName;
		this.st = st;
		this.materialType = materialType;
		this.materialVr = materialVr;
		this.materialComeType = materialComeType;
		this.materialSupplier = materialSupplier;
		this.dis = dis;
		this.uploadCode = uploadCode;
	}
	public CMesMaterialMsgT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMaterialMsg_T [id=" + id + ", materialName=" + materialName + ", st=" + st + ", materialType="
				+ materialType + ", materialVr=" + materialVr + ", materialComeType=" + materialComeType
				+ ", materialSupplier=" + materialSupplier + ", dis=" + dis + ", uploadCode=" + uploadCode + "]";
	}


}
