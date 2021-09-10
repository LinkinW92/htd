package com.skeqi.mes.pojo;

import java.util.Date;
/*产品工艺路线表*/
public class ProductionWayT {
	private int id;//主键
	private Date dt;//时间
	private String productionName;//产品名称
	private int productionId;//产品id
	private String stName;//工位名称
	private int stId;//工位id
	private int sertalNo;//序号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public int getProductionId() {
		return productionId;
	}
	public void setProductionId(int productionId) {
		this.productionId = productionId;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public int getStId() {
		return stId;
	}
	public void setStId(int stId) {
		this.stId = stId;
	}
	public int getSertalNo() {
		return sertalNo;
	}
	public void setSertalNo(int sertalNo) {
		this.sertalNo = sertalNo;
	}

}
