package com.skeqi.mes.pojo;

public class CMesRoleT {
	private Integer rid;
	private String roleName;
	private String roleDis;
	private Integer viewMode;

	public Integer getViewMode() {
		return viewMode;
	}
	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDis() {
		return roleDis;
	}
	public void setRoleDis(String roleDis) {
		this.roleDis = roleDis;
	}
	public CMesRoleT(Integer rid, String roleName, String roleDis) {
		super();
		this.rid = rid;
		this.roleName = roleName;
		this.roleDis = roleDis;
	}
	public CMesRoleT() {
		super();
	}


}
