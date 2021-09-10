package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesInWareHouse {
	private Integer id;//                NUMBER not null,
	private String orderNumber;//      NVARCHAR2(20) not null,
	private Integer materialId;//       NUMBER not null,
	private String materialName;//       NUMBER not null,
	private Date inDate;//           DATE not null,
	private Integer positionId;//          NVARCHAR2(50) not null,
	private Integer materialNumber;//   NUMBER not null,
	private String checkName;//        NVARCHAR2(10) not null,
	private String operatorName;//     NVARCHAR2(10) not null,
	private String inWarehouseType;// NVARCHAR2(2) not null,
	private String supplier;// NVARCHAR2(2) not null,
	private String dis;//               NVARCHAR2(50)
	private String positionName;


	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getInWarehouseType() {
		return inWarehouseType;
	}
	public void setInWarehouseType(String inWarehouseType) {
		this.inWarehouseType = inWarehouseType;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesInWareHouse(Integer id, String orderNumber, Integer materialId, Date inDate, String position,
			Integer materialNumber, String checkName, String operatorName, String inWarehouseType, String dis) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.materialId = materialId;
		this.inDate = inDate;
		this.materialNumber = materialNumber;
		this.checkName = checkName;
		this.operatorName = operatorName;
		this.inWarehouseType = inWarehouseType;
		this.dis = dis;
	}
	public CMesInWareHouse() {
		super();
	}
	@Override
	public String toString() {
		return "CMesInWareHouse [id=" + id + ", orderNumber=" + orderNumber + ", materialId=" + materialId + ", inDate="
				+ inDate + ", position=" + positionId + ", materialNumber=" + materialNumber + ", checkName=" + checkName
				+ ", operatorName=" + operatorName + ", inWarehouseType=" + inWarehouseType + ", dis=" + dis + "]";
	}

}
