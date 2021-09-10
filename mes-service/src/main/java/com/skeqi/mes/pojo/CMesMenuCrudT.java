package com.skeqi.mes.pojo;

public class CMesMenuCrudT {
	private Integer id;//           INTEGER not null,
	private Integer menuId;//      INTEGER not null,
	private Integer menuCrudId;// INTEGER not null
	private Integer roleId;
	private String crudName;
	private String crudAlias;

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuCrudId() {
		return menuCrudId;
	}
	public void setMenuCrudId(Integer menuCrudId) {
		this.menuCrudId = menuCrudId;
	}
	public String getCrudName() {
		return crudName;
	}
	public void setCrudName(String crudName) {
		this.crudName = crudName;
	}
	public String getCrudAlias() {
		return crudAlias;
	}
	public void setCrudAlias(String crudAlias) {
		this.crudAlias = crudAlias;
	}
	public CMesMenuCrudT(Integer id, Integer menuId, Integer menuCrudId, String crudName, String crudAlias) {
		super();
		this.id = id;
		this.menuId = menuId;
		this.menuCrudId = menuCrudId;
		this.crudName = crudName;
		this.crudAlias = crudAlias;
	}
	public CMesMenuCrudT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMenuCrudT [id=" + id + ", menuId=" + menuId + ", menuCrudId=" + menuCrudId + ", crudName="
				+ crudName + ", crudAlias=" + crudAlias + "]";
	}


}
