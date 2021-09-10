package com.skeqi.mes.pojo.wms;

import java.sql.Date;

import com.skeqi.mes.pojo.CMesJlMaterialT;

/**
 * 物料数量表
 * @author FQZ
 *
 */
/**
 * @author Timberon
 *
 */
public class CWmsMaterialNumberT {

	private Integer Id; // id
	private String materialNo; // 物料编号
	private String materialName; // 物料name
	private String mType;// 物料类别
	private Integer materialNumber; // 物料数量
	private Integer projectId;// 项目ID
	private String pname;// 项目号
	private String mUnit;// 物料单位
	private Integer materialId;// 物料Id
	private String mModel;// 物料型号
	private String mNote;// 物料备注
	private Integer wareHouseId;// 仓库ID
	private String wName;// 所属仓库
	private Integer areaId;// 所在仓库区域id
	private String aName;// 区域名称
	private Integer locationId;// 库位Id
	private String lName;// 库位名称
	private Integer reservoirareaId;// 仓库库区Id
	private String rName;// 库区名称
	private Integer lmminentRelease;// 即将出库数量LMMINENT
	private String dt; // 入库时间
	private String yxq;// 剩余有效期
	private Integer freezingNumber;//冻结数
	private Integer reservedNumber;//预留数
	private String note;//备注冻结、预留
	private Integer moveNumber;//移库数
	private String moveWareName;//移入仓库
	private Integer moveWareId;//移入仓库id
	private Integer moveLocationId;//移入库位id
	private String moveLocationName;//移入库位name
	private String moveNote;//备注 移库
	private String tray;//托盘号
	private String materialCode; // 物料条码
	private String barCode;//条码

	private CMesJlMaterialT material;//物料
	private CWmsProject project;//项目
	private CWmsMaterialUnitT materialUnit;//物料单位
	private CWmsMaterialTypeT materialType;//物料类型
	private WarehouseT warehouse;//仓库
	private CWmsAreaT area;//区域
	private CWmsReservoirAreaT reservoirArea;//库区
	private CWmsLocationT location;//库位

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public CMesJlMaterialT getMaterial() {
		return material;
	}

	public void setMaterial(CMesJlMaterialT material) {
		this.material = material;
	}

	public CWmsProject getProject() {
		return project;
	}

	public void setProject(CWmsProject project) {
		this.project = project;
	}

	public CWmsMaterialUnitT getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(CWmsMaterialUnitT materialUnit) {
		this.materialUnit = materialUnit;
	}

	public CWmsMaterialTypeT getMaterialType() {
		return materialType;
	}

	public void setMaterialType(CWmsMaterialTypeT materialType) {
		this.materialType = materialType;
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

	public String getTray() {
		return tray;
	}

	public void setTray(String tray) {
		this.tray = tray;
	}

	public String getMoveNote() {
		return moveNote;
	}

	public void setMoveNote(String moveNote) {
		this.moveNote = moveNote;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public Integer getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}

	public String getMoveWareName() {
		return moveWareName;
	}

	public void setMoveWareName(String moveWareName) {
		this.moveWareName = moveWareName;
	}

	public Integer getMoveWareId() {
		return moveWareId;
	}

	public void setMoveWareId(Integer moveWareId) {
		this.moveWareId = moveWareId;
	}

	public Integer getMoveLocationId() {
		return moveLocationId;
	}

	public void setMoveLocationId(Integer moveLocationId) {
		this.moveLocationId = moveLocationId;
	}

	public String getMoveLocationName() {
		return moveLocationName;
	}

	public void setMoveLocationName(String moveLocationName) {
		this.moveLocationName = moveLocationName;
	}

	public Integer getReservedNumber() {
		return reservedNumber;
	}

	public void setReservedNumber(Integer reservedNumber) {
		this.reservedNumber = reservedNumber;
	}

	public Integer getFreezingNumber() {
		return freezingNumber;
	}

	public void setFreezingNumber(Integer freezingNumber) {
		this.freezingNumber = freezingNumber;
	}

	public Integer getLmminentRelease() {
		return lmminentRelease;
	}

	public void setLmminentRelease(Integer lmminentRelease) {
		this.lmminentRelease = lmminentRelease;
	}

	public Integer getReservoirareaId() {
		return reservoirareaId;
	}

	public void setReservoirareaId(Integer reservoirareaId) {
		this.reservoirareaId = reservoirareaId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getmUnit() {
		return mUnit;
	}

	public void setmUnit(String mUnit) {
		this.mUnit = mUnit;
	}

	public Integer getWareHouseId() {
		return wareHouseId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setWareHouseId(Integer wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String sql() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getmModel() {
		return mModel;
	}

	public void setmModel(String mModel) {
		this.mModel = mModel;
	}

	public String getmNote() {
		return mNote;
	}

	public void setmNote(String mNote) {
		this.mNote = mNote;
	}

	private Integer materialLowLimitmaterial;// 最低库存

	public Integer getMaterialLowLimitmaterial() {
		return materialLowLimitmaterial;
	}

	public void setMaterialLowLimitmaterial(Integer materialLowLimitmaterial) {
		this.materialLowLimitmaterial = materialLowLimitmaterial;
	}

	private String unitName;// 物料单位

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	private CWmsMaterialUnitT cWmsUnitT;// 单位

	public CWmsMaterialUnitT getcWmsUnitT() {
		return cWmsUnitT;
	}

	public void setcWmsUnitT(CWmsMaterialUnitT cWmsUnitT) {
		this.cWmsUnitT = cWmsUnitT;
	}

	private CWmsMaterialTypeT cWmsMaterialTypeT;// 规格型号

	private String projectName;// 项目名称

	public CWmsMaterialTypeT getcWmsMaterialTypeT() {
		return cWmsMaterialTypeT;
	}

	public void setcWmsMaterialTypeT(CWmsMaterialTypeT cWmsMaterialTypeT) {
		this.cWmsMaterialTypeT = cWmsMaterialTypeT;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private String name;// 项目名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String mtName;// 规格型号

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	private Integer daysOfFailure;// 失效天数

	public Integer getDaysOfFailure() {
		return daysOfFailure;
	}

	public void setDaysOfFailure(Integer daysOfFailure) {
		this.daysOfFailure = daysOfFailure;
	}

	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		yxq = yxq.substring(0, yxq.indexOf("."));
		if (yxq.equals("")) {
			yxq = "0";
		}
		this.yxq = yxq;
	}



}
