package com.skeqi.mes.pojo;

public class CMesMaterialMessage {
	private Integer id;//                NUMBER not null,
	private Integer materielId;//                NUMBER not null,
	private String materielName;//     NVARCHAR2(50) not null,
	private Integer materielNumber;//   NUMBER not null,
	private String materielType;//     NVARCHAR2(50) not null,
	private Integer positionId;// NVARCHAR2(50) not null,
	private String positionName;// NVARCHAR2(50) not null,
	private Float unitPrice;//        NVARCHAR2(20) not null,
	private String company;//           NVARCHAR2(20) not null,
	private String materielColour;//   NVARCHAR2(20) not null,
	private String supplier;//          NVARCHAR2(20) not null,
	private String dis;//               NVARCHAR2(50)


	public Integer getMaterielId() {
		return materielId;
	}
	public void setMaterielId(Integer materielId) {
		this.materielId = materielId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public Integer getMaterielNumber() {
		return materielNumber;
	}
	public void setMaterielNumber(Integer materielNumber) {
		this.materielNumber = materielNumber;
	}
	public String getMaterielType() {
		return materielType;
	}
	public void setMaterielType(String materielType) {
		this.materielType = materielType;
	}

	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMaterielColour() {
		return materielColour;
	}
	public void setMaterielColour(String materielColour) {
		this.materielColour = materielColour;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesMaterialMessage(Integer id, String materielName, Integer materielNumber, String materielType,
			String materielPosition, Float unitPrice, String company, String materielColour, String supplier,
			String dis) {
		super();
		this.id = id;
		this.materielName = materielName;
		this.materielNumber = materielNumber;
		this.materielType = materielType;
		this.unitPrice = unitPrice;
		this.company = company;
		this.materielColour = materielColour;
		this.supplier = supplier;
		this.dis = dis;
	}
	public CMesMaterialMessage() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMaterialMessage [id=" + id + ", materielName=" + materielName + ", materielNumber=" + materielNumber
				+ ", materielType=" + materielType + ", materielPosition=" + positionId + ", unitPrice="
				+ unitPrice + ", company=" + company + ", materielColour=" + materielColour + ", supplier=" + supplier
				+ ", dis=" + dis + "]";
	}


}
