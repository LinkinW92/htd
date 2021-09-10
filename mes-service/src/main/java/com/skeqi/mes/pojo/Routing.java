package com.skeqi.mes.pojo;

public class Routing {

	public int ID;
	public String productionName;   //��Ʒ����
	public String route;  //��Ʒ·��
	public String lineName;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getRoute() {
		return route;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public void setRoute(String route) {
		this.route = route;
	}

}
