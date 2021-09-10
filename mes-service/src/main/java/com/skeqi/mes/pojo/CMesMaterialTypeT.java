package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesMaterialTypeT {
	private Integer id;//            NUMBER not null,
	private String materialType;// NVARCHAR2(50),
	private Date dt;
	private String dis;//           NVARCHAR2(100)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesMaterialTypeT(Integer id, String materialType, String dis) {
		super();
		this.id = id;
		this.materialType = materialType;
		this.dis = dis;
	}
	public CMesMaterialTypeT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesMaterialTypeT [id=" + id + ", materialType=" + materialType + ", dis=" + dis + "]";
	}


}
