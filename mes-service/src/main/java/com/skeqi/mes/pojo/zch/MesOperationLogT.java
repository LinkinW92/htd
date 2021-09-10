package com.skeqi.mes.pojo.zch;

import java.util.Date;

public class MesOperationLogT {
	private Integer id;
	private String username;
	private String module;
	private String module2;
	private String methods;
	private String content;
	private String actionurl;
	private String ip;
	private Date dt;
	private byte commite;
	private String params;
	/**
	 * API注释
	 */
	private String apiAnnotation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModule2() {
		return module2;
	}

	public void setModule2(String module2) {
		this.module2 = module2;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getActionurl() {
		return actionurl;
	}

	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public byte getCommite() {
		return commite;
	}

	public void setCommite(byte commite) {
		this.commite = commite;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getApiAnnotation() {
		return apiAnnotation;
	}

	public void setApiAnnotation(String apiAnnotation) {
		this.apiAnnotation = apiAnnotation;
	}

	@Override
	public String toString() {
		return " " + this.username + "(" + this.ip + ") [操作] " + this.module + " - " + this.module2 + " - " + this.methods;
	}
}
