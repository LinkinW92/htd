package com.skeqi.mes.pojo;

public class CMesPlanTypeT {
	private Integer id;
	private String typeName;
	private String dis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesPlanTypeT(Integer id, String typeName, String dis) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.dis = dis;
	}
	public CMesPlanTypeT() {
		super();
	}


}
