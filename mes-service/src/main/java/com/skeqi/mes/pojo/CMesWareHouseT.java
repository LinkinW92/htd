package com.skeqi.mes.pojo;

public class CMesWareHouseT {
	private Integer id;//                NUMBER not null,
	private String warehouseName;//    NVARCHAR2(20) not null,
	private String warehouseAddress;// NVARCHAR2(50) not null,
	private String dis;//               NVARCHAR2(50)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseAddress() {
		return warehouseAddress;
	}
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesWareHouseT(Integer id, String warehouseName, String warehouseAddress, String dis) {
		super();
		this.id = id;
		this.warehouseName = warehouseName;
		this.warehouseAddress = warehouseAddress;
		this.dis = dis;
	}
	public CMesWareHouseT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesWareHouseT [id=" + id + ", warehouseName=" + warehouseName + ", warehouseAddress="
				+ warehouseAddress + ", dis=" + dis + "]";
	}


}
