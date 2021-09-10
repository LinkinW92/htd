package com.skeqi.mes.mapper.qh;

import java.util.List;

public class CmesCrud {

	private Integer id;//模板ID
	private Integer roleId;//角色ID
	private int cid;//crudID
	private String url;//别名
    private String label;
    private List<CmesCrudL> children;

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<CmesCrudL> getChildren() {
		return children;
	}
	public void setChildren(List<CmesCrudL> children) {
		this.children = children;
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Integer getId() {
		return id;
	}



}
