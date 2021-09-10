package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 仓库库区
 *
 * @author FQZ
 *
 */
public class CWmsReservoirAreaT {

	private Integer id; // id
	private String raNo; // 库区编号
	private String raName; // 库区名称
	private Integer raType; // 库区类型，0：立库 1：平库 2：OTHER
	private Integer raLocationLimit; // 库位上限
	private Integer raAlarmLimit; // 库区报警下限
	private String raPrintAdd; // 库区打印机地址
	private Integer areaId; // 所属区域，区域ID
	private String modifyTime; // 修改时间
	private Integer viewMode;

	private CWmsAreaT area;
	private WarehouseT warehouse;

	public Integer getViewMode() {
		return viewMode;
	}

	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRaNo() {
		return raNo;
	}

	public void setRaNo(String raNo) {
		this.raNo = raNo;
	}

	public String getRaName() {
		return raName;
	}

	public void setRaName(String raName) {
		this.raName = raName;
	}

	public Integer getRaType() {
		return raType;
	}

	public void setRaType(Integer raType) {
		this.raType = raType;
	}

	public Integer getRaLocationLimit() {
		return raLocationLimit;
	}

	public void setRaLocationLimit(Integer raLocationLimit) {
		this.raLocationLimit = raLocationLimit;
	}

	public Integer getRaAlarmLimit() {
		return raAlarmLimit;
	}

	public void setRaAlarmLimit(Integer raAlarmLimit) {
		this.raAlarmLimit = raAlarmLimit;
	}

	public String getRaPrintAdd() {
		return raPrintAdd;
	}

	public void setRaPrintAdd(String raPrintAdd) {
		this.raPrintAdd = raPrintAdd;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "CWmsReservoirAreaT [id=" + id + ", raNo=" + raNo + ", raName=" + raName + ", raType=" + raType
				+ ", raLocationLimit=" + raLocationLimit + ", raAlarmLimit="
				+ raAlarmLimit + ", raPrintAdd=" + raPrintAdd + ", areaId=" + areaId + ", modifyTime=" + modifyTime
				+ "]";
	}

}
