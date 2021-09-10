package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesRecipeTypeT {
	private Integer id;//          INTEGER not null,
	private Date dt;//           DATE,
	private String typeName;//    VARCHAR2(100),
	private Integer typeNo;//      INTEGER,
	private String dis;//          VARCHAR2(100),
	private Integer distinguish;//  INTEGER
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public Integer getDistinguish() {
		return distinguish;
	}
	public void setDistinguish(Integer distinguish) {
		this.distinguish = distinguish;
	}
	public CMesRecipeTypeT(Integer id, Date dt, String typeName, Integer typeNo, String dis, Integer distinguish) {
		super();
		this.id = id;
		this.dt = dt;
		this.typeName = typeName;
		this.typeNo = typeNo;
		this.dis = dis;
		this.distinguish = distinguish;
	}
	public CMesRecipeTypeT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesRecipeTypeT [id=" + id + ", dt=" + dt + ", typeName=" + typeName + ", typeNo=" + typeNo + ", dis="
				+ dis + ", distinguish=" + distinguish + "]";
	}


}
