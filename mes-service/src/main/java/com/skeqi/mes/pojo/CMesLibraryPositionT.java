package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesLibraryPositionT {
	private Integer id;//            NUMBER not null,
	private String positionName;// NVARCHAR2(20) not null,
	private Date lastDate;//     DATE not null,
	private Integer materialId;//   NUMBER not null,
	private Integer warehouseId;//  NUMBER not null,
	private String dis;//           NVARCHAR2(50)
	private String materialName;
	private String warehouseName;
	private String supplier;
	private Integer materielNumber;


	public Integer getMaterielNumber() {
		return materielNumber;
	}
	public void setMaterielNumber(Integer materielNumber) {
		this.materielNumber = materielNumber;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public CMesLibraryPositionT(Integer id, String positionName, Date lastDate, Integer materialId, Integer warehouseId,
			String dis, String materialName, String warehouseName) {
		super();
		this.id = id;
		this.positionName = positionName;
		this.lastDate = lastDate;
		this.materialId = materialId;
		this.warehouseId = warehouseId;
		this.dis = dis;
		this.materialName = materialName;
		this.warehouseName = warehouseName;
	}
	public CMesLibraryPositionT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesLibraryPositionT [id=" + id + ", positionName=" + positionName + ", lastDate=" + lastDate
				+ ", materialId=" + materialId + ", warehouseId=" + warehouseId + ", dis=" + dis + ", materialName="
				+ materialName + ", warehouseName=" + warehouseName + "]";
	}


}
