package com.skeqi.mes.pojo;

import java.util.Arrays;

import io.swagger.annotations.ApiModelProperty;

public class CMesRoleMenuTl {

	private Integer id;
	@ApiModelProperty(value="角色ID",required=false)
	private Integer roleId;;
	@ApiModelProperty(value="选中的菜单ID",required=true)
	private Integer[] menuId;
	@ApiModelProperty(value="角色名",required=true)
	private String roleName;
	@ApiModelProperty(value="角色介绍",required=true)
	private String roleDis;
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
	public Integer[] getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer[] menuId) {
		this.menuId = menuId;
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
	@Override
	public String toString() {
		return "CMesRoleMenuT1 [id=" + id + ", roleId=" + roleId + ", menuId=" + Arrays.toString(menuId) + ", roleName="
				+ roleName + ", roleDis=" + roleDis + "]";
	}
	public CMesRoleMenuTl(Integer id, Integer roleId, Integer[] menuId, String roleName, String roleDis) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
		this.roleName = roleName;
		this.roleDis = roleDis;
	}
	public CMesRoleMenuTl() {
		super();
	}



}
