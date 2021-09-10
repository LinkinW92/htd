package com.skeqi.mes.pojo;

import java.io.Serializable;

public class CMesMenuT implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6076923545283680179L;
	private Integer id;
	private String menuName;
	private String grade;
	private String url;
	private String menuType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public CMesMenuT(Integer id, String menuName, String grade, String url, String menuType) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.grade = grade;
		this.url = url;
		this.menuType = menuType;
	}
	public CMesMenuT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMenu [id=" + id + ", menuName=" + menuName + ", grade=" + grade + ", url=" + url + ", menuType="
				+ menuType + "]";
	}


}
