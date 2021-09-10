package com.skeqi.mes.pojo.wms;

import java.util.List;

/**
 * 仓库库位
 *
 * @author FQZ
 *
 */
public class CWmsLocationT {

	private Integer id;
	private String locationNo; // 库位编号
	private String locationName; // 库位名称
	private Integer locationLength; // 库位长度
	private Integer locationWidth; // 库位宽度
	private Integer locationHight; // 库位高度
	private Integer locationVolume; // 库位体积
	private Integer locationType; // 库位类型，0：立库 1：平库 2：OTHER
	private String locationMark; // 库位标识
	private Integer locationType1; // 库位种类，备用
	private String locationCapacity; // 库位容量
	private Integer locationWeight; // 库位载重量，单位KG
	private Integer locationStatus; // 库位状态，0：正常 1：满库 2：维修 3：禁用 4：报废
	private String locationX; // 库位横坐标，考虑小数点
	private String locationY; // 库位纵坐标，考虑小数点
	private String locationZ; // 库位Z坐标，考虑小数点
	private String locationVr; // 库位校验规则
	private Integer reservoirAreaId; // 库区ID，关联库区表
	private String modifyTime; // 修改时间
	private Integer trayType;// 可容纳托盘类型 1:矮、2:高
	private String tray;// 托盘
	private Integer viewMode;

	private WarehouseT warehouse;//仓库
	private CWmsAreaT area;//区域
	private CWmsReservoirAreaT reservoirArea;//库区

	private String ptlNo; // PTL编号

	public WarehouseT getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseT warehouse) {
		this.warehouse = warehouse;
	}

	public CWmsAreaT getArea() {
		return area;
	}

	public Integer getViewMode() {
		return viewMode;
	}

	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
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



	// CWmsMaterialNumberTL 一对多
	private List<CWmsMaterialNumberT> cWmsMaterialNumberTList;

	public List<CWmsMaterialNumberT> getcWmsMaterialNumberTList() {
		return cWmsMaterialNumberTList;
	}

	public void setcWmsMaterialNumberTList(List<CWmsMaterialNumberT> cWmsMaterialNumberTList) {
		this.cWmsMaterialNumberTList = cWmsMaterialNumberTList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getLocationLength() {
		return locationLength;
	}

	public void setLocationLength(Integer locationLength) {
		this.locationLength = locationLength;
	}

	public Integer getLocationWidth() {
		return locationWidth;
	}

	public void setLocationWidth(Integer locationWidth) {
		this.locationWidth = locationWidth;
	}

	public Integer getLocationHight() {
		return locationHight;
	}

	public void setLocationHight(Integer locationHight) {
		this.locationHight = locationHight;
	}

	public Integer getLocationVolume() {
		return locationVolume;
	}

	public void setLocationVolume(Integer locationVolume) {
		this.locationVolume = locationVolume;
	}

	public Integer getLocationType() {
		return locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public String getLocationMark() {
		return locationMark;
	}

	public void setLocationMark(String locationMark) {
		this.locationMark = locationMark;
	}

	public Integer getLocationType1() {
		return locationType1;
	}

	public void setLocationType1(Integer locationType1) {
		this.locationType1 = locationType1;
	}

	public String getLocationCapacity() {
		return locationCapacity;
	}

	public void setLocationCapacity(String locationCapacity) {
		this.locationCapacity = locationCapacity;
	}

	public Integer getLocationWeight() {
		return locationWeight;
	}

	public void setLocationWeight(Integer locationWeight) {
		this.locationWeight = locationWeight;
	}

	public Integer getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(Integer locationStatus) {
		this.locationStatus = locationStatus;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getLocationZ() {
		return locationZ;
	}

	public void setLocationZ(String locationZ) {
		this.locationZ = locationZ;
	}

	public String getLocationVr() {
		return locationVr;
	}

	public void setLocationVr(String locationVr) {
		this.locationVr = locationVr;
	}

	public Integer getReservoirAreaId() {
		return reservoirAreaId;
	}

	public void setReservoirAreaId(Integer reservoirAreaId) {
		this.reservoirAreaId = reservoirAreaId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getTrayType() {
		return trayType;
	}

	public void setTrayType(Integer trayType) {
		this.trayType = trayType;
	}

	public String getTray() {
		return tray;
	}

	public void setTray(String tray) {
		this.tray = tray;
	}

	@Override
	public String toString() {
		return "CWmsLocationT [id=" + id + ", locationNo=" + locationNo + ", locationName=" + locationName
				+ ", locationLength=" + locationLength + ", locationWidth=" + locationWidth + ", locationHight="
				+ locationHight + ", locationVolume=" + locationVolume + ", locationType=" + locationType
				+ ", locationMark=" + locationMark + ", locationType1=" + locationType1 + ", locationCapacity="
				+ locationCapacity + ", locationWeight=" + locationWeight + ", locationStatus=" + locationStatus
				+ ", locationX=" + locationX + ", locationY=" + locationY + ", locationZ=" + locationZ + ", locationVr="
				+ locationVr + ", reservoirAreaId=" + reservoirAreaId + ", modifyTime=" + modifyTime + ", trayType="
				+ trayType + ", tray=" + tray + ", cWmsMaterialNumberTList=" + cWmsMaterialNumberTList + "]";
	}

	public String getPtlNo() {
		return ptlNo;
	}

	public void setPtlNo(String ptlNo) {
		this.ptlNo = ptlNo;
	}

}
