package com.skeqi.mes.pojo;
/*角色明细表*/
public class RoleDetailT {
	private int id;//角色id
	private String modules;//所具模块权限
	private String dis;//角色描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}

}
