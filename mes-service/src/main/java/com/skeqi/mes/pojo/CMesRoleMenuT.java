package com.skeqi.mes.pojo;

public class CMesRoleMenuT {
	private Integer id;
	private Integer roleId;
	private Integer menuId;
	private String roleName;
	private String menuName;
	private String roleDis;
	private Integer cid;
	private String cname;



	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getRoleDis() {
		return roleDis;
	}
	public void setRoleDis(String roleDis) {
		this.roleDis = roleDis;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public CMesRoleMenuT(Integer id, Integer roleId, Integer menuId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
	}
	public CMesRoleMenuT() {
		super();
	}


}
