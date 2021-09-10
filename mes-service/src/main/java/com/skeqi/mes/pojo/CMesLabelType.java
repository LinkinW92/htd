package com.skeqi.mes.pojo;


import io.swagger.annotations.ApiModelProperty;

public class CMesLabelType {
	private Integer id;// INTEGER not null,
	@ApiModelProperty(value = "日期", required = false)
	private String dt;// DATE,
	@ApiModelProperty(value = "名称", required = true)
	private String name;// VARCHAR2(100),
	@ApiModelProperty(value = "规则", required = true)
	private String labelVr;// VARCHAR2(100),
	@ApiModelProperty(value = "描述", required = true)
	private String labelDis;// VARCHAR2(100)

	public CMesLabelType() {
		super();
	}

	@Override
	public String toString() {
		return "CMesLabelType [id=" + id + ", dt=" + dt + ", name=" + name + ", labelVr=" + labelVr + ", labelDis="
				+ labelDis + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabelVr() {
		return labelVr;
	}

	public void setLabelVr(String labelVr) {
		this.labelVr = labelVr;
	}

	public String getLabelDis() {
		return labelDis;
	}

	public void setLabelDis(String labelDis) {
		this.labelDis = labelDis;
	}

	public CMesLabelType(Integer id, String dt, String name, String labelVr, String labelDis) {
		super();
		this.id = id;
		this.dt = dt;
		this.name = name;
		this.labelVr = labelVr;
		this.labelDis = labelDis;
	}

}
