package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 库存明细表
 *
 * @author FQZ
 *
 */
public class CWmsStorageListDetailT {

	private Integer id;
	private String dt; // 入库时间
	private Integer materialId; // 物料id
	private String materialName; // 物料name
	private Integer materialNumber; // 数量
	private String materialCode; // 物料条码
	private Integer areaId; // 区域ID
	private String areaName;// 区域name
	private Integer reservoirAreaId; // 库区id
	private String reservoirAreaName; // 库区name
	private Integer loctionId; // 库位id
	private String loctionName; // 库位name
	private String note; // 备注
	private String listNo; // 单号
	private Integer projectId;
	private String projectName;// 项目名称
	private Integer supplierId;// 供应商id
	private String supplierName;// 供应商name
	private String tray;// 托盘号
	private Integer state;// 审核状态
	private String whouseName;// 仓库名称
	private String dmName;// 部门名称
	private Integer approvalResult;// 0:未审批，1:通过，2:不通过
	private String reason;// 原因
	private String appdt;// 审批时间
	private String appnote;// 审批的备注
	private String comeWarehouse;// 出|入库
	private String materialNo;// 物料编号
	private String unitName;// 单位
	private Integer warehouseId;//仓库id
	private String userName;//用户name
	private Integer materialCost;//单价
	private Integer sum;//总价
	private String unitname;//物料单位
	// 型号MATERIAL_MODEL
	private String materialModel;
	// 最低库存MATERIAL_LOW_LIMITMATERIAL
	private Integer materialLowLimitmaterial;
	// 时间qxtrkdate
	private String qxtrkdate;
	private Integer materialLength;
	private Integer materialWidth;
	private Integer materialHight;
	private Integer materialVolume;
	private Integer materialWeight;
	private String materialSc;
	private String materialVr;
	private String mtName;// 物料类型
	private String locationName;// 库位name
	private String warehouseName;//生产入库仓库名称
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
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getReservoirAreaId() {
		return reservoirAreaId;
	}
	public void setReservoirAreaId(Integer reservoirAreaId) {
		this.reservoirAreaId = reservoirAreaId;
	}
	public String getReservoirAreaName() {
		return reservoirAreaName;
	}
	public void setReservoirAreaName(String reservoirAreaName) {
		this.reservoirAreaName = reservoirAreaName;
	}
	public Integer getLoctionId() {
		return loctionId;
	}
	public void setLoctionId(Integer loctionId) {
		this.loctionId = loctionId;
	}
	public String getLoctionName() {
		return loctionName;
	}
	public void setLoctionName(String loctionName) {
		this.loctionName = loctionName;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getTray() {
		return tray;
	}
	public void setTray(String tray) {
		this.tray = tray;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getWhouseName() {
		return whouseName;
	}
	public void setWhouseName(String whouseName) {
		this.whouseName = whouseName;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public Integer getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAppdt() {
		return appdt;
	}
	public void setAppdt(String appdt) {
		this.appdt = appdt;
	}
	public String getAppnote() {
		return appnote;
	}
	public void setAppnote(String appnote) {
		this.appnote = appnote;
	}
	public String getComeWarehouse() {
		return comeWarehouse;
	}
	public void setComeWarehouse(String comeWarehouse) {
		this.comeWarehouse = comeWarehouse;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(Integer materialCost) {
		this.materialCost = materialCost;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public Integer getMaterialLowLimitmaterial() {
		return materialLowLimitmaterial;
	}
	public void setMaterialLowLimitmaterial(Integer materialLowLimitmaterial) {
		this.materialLowLimitmaterial = materialLowLimitmaterial;
	}
	public String getQxtrkdate() {
		return qxtrkdate;
	}
	public void setQxtrkdate(String qxtrkdate) {
		this.qxtrkdate = qxtrkdate;
	}
	public Integer getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(Integer materialLength) {
		this.materialLength = materialLength;
	}
	public Integer getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(Integer materialWidth) {
		this.materialWidth = materialWidth;
	}
	public Integer getMaterialHight() {
		return materialHight;
	}
	public void setMaterialHight(Integer materialHight) {
		this.materialHight = materialHight;
	}
	public Integer getMaterialVolume() {
		return materialVolume;
	}
	public void setMaterialVolume(Integer materialVolume) {
		this.materialVolume = materialVolume;
	}
	public Integer getMaterialWeight() {
		return materialWeight;
	}
	public void setMaterialWeight(Integer materialWeight) {
		this.materialWeight = materialWeight;
	}
	public String getMaterialSc() {
		return materialSc;
	}
	public void setMaterialSc(String materialSc) {
		this.materialSc = materialSc;
	}
	public String getMaterialVr() {
		return materialVr;
	}
	public void setMaterialVr(String materialVr) {
		this.materialVr = materialVr;
	}
	public String getMtName() {
		return mtName;
	}
	public void setMtName(String mtName) {
		this.mtName = mtName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


}
