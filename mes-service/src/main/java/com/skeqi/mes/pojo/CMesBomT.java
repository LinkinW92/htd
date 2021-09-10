package com.skeqi.mes.pojo;

import java.io.Serializable;

public class CMesBomT implements Serializable{
	private Integer id;//            INTEGER not null,
	private Integer productionId;// INTEGER,
	private String bomName;//      VARCHAR2(100),
	private String dis;//           VARCHAR2(200)
	private String productionName;
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getBomName() {
		return bomName;
	}
	public void setBomName(String bomName) {
		this.bomName = bomName;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public CMesBomT(Integer id, Integer productionId, String bomName, String dis) {
		super();
		this.id = id;
		this.productionId = productionId;
		this.bomName = bomName;
		this.dis = dis;
	}
	public CMesBomT() {
		super();
	}


}
