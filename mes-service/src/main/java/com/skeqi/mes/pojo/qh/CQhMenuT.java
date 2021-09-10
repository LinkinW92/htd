package com.skeqi.mes.pojo.qh;

/**
 * @author yinp
 * @explain 菜单
 * @date 2020-9-3
 */
public class CQhMenuT {

	private Integer id;
	// 菜单等级
	private Integer menuGrade;
	// 上级菜单id
	private Integer superiorMenuId;
	// 菜单名称
	private String menuName;
	// 是否启用
	private String ifEnable;
	// 上级菜单
	private CQhMenuT superiorMenu;

	public CQhMenuT getSuperiorMenu() {
		return superiorMenu;
	}

	public String getIfEnable() {
		return ifEnable;
	}

	public void setIfEnable(String ifEnable) {
		this.ifEnable = ifEnable;
	}

	public void setSuperiorMenu(CQhMenuT superiorMenu) {
		this.superiorMenu = superiorMenu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuGrade() {
		return menuGrade;
	}

	public void setMenuGrade(Integer menuGrade) {
		this.menuGrade = menuGrade;
	}

	public Integer getSuperiorMenuId() {
		return superiorMenuId;
	}

	public void setSuperiorMenuId(Integer superiorMenuId) {
		this.superiorMenuId = superiorMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
