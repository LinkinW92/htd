package com.skeqi.mes.pojo;

public class RUploadDataT {
	private Integer id;//            INTEGER not null,
	private String st;//            NVARCHAR2(50),
	private String sn;//            NVARCHAR2(100),
	private String name;//          NVARCHAR2(50),
	private String datavalue;//     NVARCHAR2(100),
	private String spdatavalue;//   NVARCHAR2(100),
	private String type;//          NVARCHAR2(50),
	private String numbers;//       NVARCHAR2(50),
	private String materialName;// NVARCHAR2(150)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	public String getSpdatavalue() {
		return spdatavalue;
	}
	public void setSpdatavalue(String spdatavalue) {
		this.spdatavalue = spdatavalue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public RUploadDataT(Integer id, String st, String sn, String name, String datavalue, String spdatavalue,
			String type, String numbers, String materialName) {
		super();
		this.id = id;
		this.st = st;
		this.sn = sn;
		this.name = name;
		this.datavalue = datavalue;
		this.spdatavalue = spdatavalue;
		this.type = type;
		this.numbers = numbers;
		this.materialName = materialName;
	}
	public RUploadDataT() {
		super();
	}


}
