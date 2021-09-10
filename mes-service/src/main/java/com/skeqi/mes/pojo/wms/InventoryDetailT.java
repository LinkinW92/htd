package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesJlMaterialT;

/**
 * 库存盘点详情
 *
 * @date 2020年2月24日
 * @author YinP
 *
 */
public class InventoryDetailT {

	private Integer Id;// 主键
	private Integer inventoryId;// 盘点id
	private Integer materialId;// 物料ID
	private Integer inventoryNo;// 物料盘点数量
	private Integer stockNo;// 物料库存数量
	private Integer differenceNo;// 物料差异数量
	private Integer locationId;// 库位id
	private Integer projectId;// 项目id
	private String trayCode;// 托盘码
	private String listNo;// 单据号

	private CMesJlMaterialT material;// 物料
	private CWmsLocationT location;// 库位
	private CWmsProject project;// 项目

	public String getTrayCode() {
		return trayCode;
	}

	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public void setTrayCode(String trayCode) {
		this.trayCode = trayCode;
	}

	public CMesJlMaterialT getMaterial() {
		return material;
	}

	public void setMaterial(CMesJlMaterialT material) {
		this.material = material;
	}

	public CWmsLocationT getLocation() {
		return location;
	}

	public void setLocation(CWmsLocationT location) {
		this.location = location;
	}

	public CWmsProject getProject() {
		return project;
	}

	public void setProject(CWmsProject project) {
		this.project = project;
	}

	public InventoryDetailT() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDetailT(Integer id, Integer inventoryId, Integer materialId, Integer inventoryNo, Integer stockNo,
			Integer differenceNo, Integer locationId, Integer projectId) {
		Id = id;
		this.inventoryId = inventoryId;
		this.materialId = materialId;
		this.inventoryNo = inventoryNo;
		this.stockNo = stockNo;
		this.differenceNo = differenceNo;
		this.locationId = locationId;
		this.projectId = projectId;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(Integer inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public Integer getStockNo() {
		return stockNo;
	}

	public void setStockNo(Integer stockNo) {
		this.stockNo = stockNo;
	}

	public Integer getDifferenceNo() {
		return differenceNo;
	}

	public void setDifferenceNo(Integer differenceNo) {
		this.differenceNo = differenceNo;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "InventoryDetailT [Id=" + Id + ", InventoryId=" + inventoryId + ", materialId=" + materialId
				+ ", inventoryNo=" + inventoryNo + ", stockNo=" + stockNo + ", differenceNo=" + differenceNo
				+ ", locationId=" + locationId + ", projectId=" + projectId + "]";
	}

}
