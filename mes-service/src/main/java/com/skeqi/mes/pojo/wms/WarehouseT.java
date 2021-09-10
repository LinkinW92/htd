package com.skeqi.mes.pojo.wms;

import java.util.Date;

import com.skeqi.mes.pojo.CMesUserT;
/**
 * 仓库
 * @author Ryan
 * @date 2018年12月28日
 * @mappingTable C_WMS_WAREHOUSE_T
 */
public class WarehouseT {

	private Integer id;
	private String name;
	private Integer manager;
	private String operations;
	private String managerTele;
	private String address;
	private String note;
	private Integer defaultM;
	private String modifyTime;
	private Integer viewMode;

	private CMesUserT managerUser;
	private CMesUserT operationsUser;

	public CMesUserT getManagerUser() {
		return managerUser;
	}
	public void setManagerUser(CMesUserT managerUser) {
		this.managerUser = managerUser;
	}
	public CMesUserT getOperationsUser() {
		return operationsUser;
	}
	public void setOperationsUser(CMesUserT operationsUser) {
		this.operationsUser = operationsUser;
	}
	public Integer getViewMode() {
		return viewMode;
	}
	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public String getManagerTele() {
		return managerTele;
	}
	public void setManagerTele(String managerTele) {
		this.managerTele = managerTele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getDefaultM() {
		return defaultM;
	}
	public void setDefaultM(Integer defaultM) {
		this.defaultM = defaultM;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public WarehouseT(Integer id, String name, Integer manager, String operations, String managerTele, String address,
			String note, Integer defaultM, String modifyTime) {
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.operations = operations;
		this.managerTele = managerTele;
		this.address = address;
		this.note = note;
		this.defaultM = defaultM;
		this.modifyTime = modifyTime;
	}
	public WarehouseT() {
	}
	@Override
	public String toString() {
		return "Warhouse [id=" + id + ", name=" + name + ", manager=" + manager + ", operations=" + operations
				+ ", managerTele=" + managerTele + ", address=" + address + ", note=" + note + ", defaultM=" + defaultM
				+ ", modifyTime=" + modifyTime + "]";
	}



}
