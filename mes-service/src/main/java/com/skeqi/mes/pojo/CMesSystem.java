package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CMesSystem {

	private int id;
	@ApiModelProperty(value="参数名")
	private String name;
	@ApiModelProperty(value="参数值")
	private String parameter;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public CMesSystem(int id, String name, String parameter) {
		super();
		this.id = id;
		this.name = name;
		this.parameter = parameter;
	}
	public CMesSystem() {
		super();
	}
	@Override
	public String toString() {
		return "CMesSystem [id=" + id + ", name=" + name + ", parameter=" + parameter + "]";
	}



}
