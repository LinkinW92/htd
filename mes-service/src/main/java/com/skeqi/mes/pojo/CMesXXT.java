package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesXXT {
	  private Integer id;//  NUMBER not null,
	  private Date dt;//  DATE,
	  private String sn;//  VARCHAR2(50),
	  private String xxt;// VARCHAR2(50)
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getXxt() {
		return xxt;
	}
	public void setXxt(String xxt) {
		this.xxt = xxt;
	}
	public CMesXXT(Integer id, Date dt, String sn, String xxt) {
		super();
		this.id = id;
		this.dt = dt;
		this.sn = sn;
		this.xxt = xxt;
	}
	public CMesXXT() {
		super();
	}


}
