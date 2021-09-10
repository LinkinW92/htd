package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 区域表
 * @author FQZ
 *
 */
public class CWmsAreaT {

	private Integer id;  //id
	private String areaNo;  //区域编号
	private String areaName;  //区域名称
	private Integer areaType;  //区域类型，0：立库 1：平库 2：OTHER
	private Double areaLength;  //区域长度，单位M
	private Double areaWidth; //区域宽度，单位M
	private Double areaHight; //区域高度，单位M
	private Double areaLimit;  //区域上限，单位M
	private Double areaLoadbearing;  //区域承重,单位KG
	private String modifyTime;  //修改时间
	private Integer warehouseId;  //所属仓库,仓库ID
	private Integer viewMode;

	private WarehouseT warehouse;//仓库

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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public Double getAreaLength() {
		return areaLength;
	}
	public void setAreaLength(Double areaLength) {
		this.areaLength = areaLength;
	}
	public Double getAreaWidth() {
		return areaWidth;
	}
	public void setAreaWidth(Double areaWidth) {
		this.areaWidth = areaWidth;
	}
	public Double getAreaHight() {
		return areaHight;
	}
	public void setAreaHight(Double areaHight) {
		this.areaHight = areaHight;
	}
	public Double getAreaLimit() {
		return areaLimit;
	}
	public void setAreaLimit(Double areaLimit) {
		this.areaLimit = areaLimit;
	}
	public Double getAreaLoadbearing() {
		return areaLoadbearing;
	}
	public void setAreaLoadbearing(Double areaLoadbearing) {
		this.areaLoadbearing = areaLoadbearing;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	@Override
	public String toString() {
		return "CWmsAreaT [id=" + id + ", areaNo=" + areaNo + ", areaName=" + areaName + ", areaType=" + areaType
				+ ", areaLength=" + areaLength + ", areaWidth=" + areaWidth + ", areaHight=" + areaHight
				+ ", areaLimit=" + areaLimit + ", areaLoadbearing=" + areaLoadbearing + ", modifyTime=" + modifyTime
				+ ", warehouseId=" + warehouseId + "]";
	}

}
