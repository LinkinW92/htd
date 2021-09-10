package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesStationT;

/**
 * @dare 2020年2月17日
 * @author Yinp
 * 库存详情
 */
public class CWmsStorageDetailT{
	private Integer id;
	private String dt; // 入库时间
	private Integer materialId; // 物料id
	private Integer materialNumber; // 数量
	private String materialCode; // 物料条码
	private Integer areaId; // 区域ID
	private Integer reservoirAreaId; // 库区id
	private Integer locationId; // 库位id
	private String note; // 备注
	private String listNo; // 单号
	private Integer projectId;
	private Integer supplierId;// 供应商id
	private String tray;// 托盘号
	private Integer warehouseId;//仓库id
	private Integer ynShift;
	private Integer issueOrReceipt;//出库还是入库
	private Integer stationId;//目标工位地标
	private String barCode;//条码
	private int materialNumberId;

	private CMesJlMaterialT material;//物料
	private WarehouseT warehouse;//仓库
	private CWmsAreaT area;//区域
	private CWmsReservoirAreaT reservoirArea;//库区
	private CWmsLocationT location;//库位
	private CWmsProject project;//项目
	private CMesStationT station;//工位



	public int getMaterialNumberId() {
		return materialNumberId;
	}
	public void setMaterialNumberId(int materialNumberId) {
		this.materialNumberId = materialNumberId;
	}
	public CMesStationT getStation() {
		return station;
	}
	public void setStation(CMesStationT station) {
		this.station = station;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getIssueOrReceipt() {
		return issueOrReceipt;
	}
	public void setIssueOrReceipt(Integer issueOrReceipt) {
		this.issueOrReceipt = issueOrReceipt;
	}
	public CWmsProject getProject() {
		return project;
	}
	public void setProject(CWmsProject project) {
		this.project = project;
	}
	public CMesJlMaterialT getMaterial() {
		return material;
	}
	public void setMaterial(CMesJlMaterialT material) {
		this.material = material;
	}
	public WarehouseT getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(WarehouseT warehouse) {
		this.warehouse = warehouse;
	}
	public CWmsAreaT getArea() {
		return area;
	}
	public void setArea(CWmsAreaT area) {
		this.area = area;
	}
	public CWmsReservoirAreaT getReservoirArea() {
		return reservoirArea;
	}
	public void setReservoirArea(CWmsReservoirAreaT reservoirArea) {
		this.reservoirArea = reservoirArea;
	}
	public CWmsLocationT getLocation() {
		return location;
	}
	public void setLocation(CWmsLocationT location) {
		this.location = location;
	}
	public CWmsStorageDetailT() {
		// TODO Auto-generated constructor stub
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
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getReservoirAreaId() {
		return reservoirAreaId;
	}
	public void setReservoirAreaId(Integer reservoirAreaId) {
		this.reservoirAreaId = reservoirAreaId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getTray() {
		return tray;
	}
	public void setTray(String tray) {
		this.tray = tray;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getYnShift() {
		return ynShift;
	}
	public void setYnShift(Integer ynShift) {
		this.ynShift = ynShift;
	}

}
