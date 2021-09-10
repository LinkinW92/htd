package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CMesEmpTypeT {
	private Integer id;
	@ApiModelProperty(value="类型名",required=true)
	private String empType;
	@ApiModelProperty(value="介绍",required=true)
	private String dis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesEmpTypeT(Integer id, String empType, String dis) {
		super();
		this.id = id;
		this.empType = empType;
		this.dis = dis;
	}
	public CMesEmpTypeT() {
		super();
	}


}
