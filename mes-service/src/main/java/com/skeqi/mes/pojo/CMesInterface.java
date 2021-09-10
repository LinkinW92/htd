package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesInterface {
	private Integer id;//     INTEGER not null,
	private Date dt;//     DATE,
	private String name;//   VARCHAR2(50),
	private String remark;// VARCHAR2(50),
	private String val;//    VARCHAR2(50),
	private String dis;//    VARCHAR2(50)
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesInterface(Integer id, Date dt, String name, String remark, String val, String dis) {
		super();
		this.id = id;
		this.dt = dt;
		this.name = name;
		this.remark = remark;
		this.val = val;
		this.dis = dis;
	}
	public CMesInterface() {
		super();
	}
	@Override
	public String toString() {
		return "CMesInterface [id=" + id + ", dt=" + dt + ", name=" + name + ", remark=" + remark + ", val=" + val
				+ ", dis=" + dis + "]";
	}


}
