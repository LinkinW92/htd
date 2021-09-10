package com.skeqi.mes.pojo;

/**
 * 工艺路线表
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月27日 上午10:41:37
 */
public class CMesRoutingT {

	private Integer id;
	private String name;   //工艺路线名称
	private Integer productionId;   //产品id
	private String productionName;   //产品名称
	private Integer lineId;   //产线id
	private String lineName;   //产线名称
	private String route;   //路线
	private Integer defaultRoute;//默认路线
	private String json;
	private Integer sertalNo ;
	private Integer status;



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getSertalNo() {
		return sertalNo;
	}

	public void setSertalNo(Integer sertalNo) {
		this.sertalNo = sertalNo;
	}

	public Integer getDefaultRoute() {
		return defaultRoute;
	}

	public void setDefaultRoute(Integer defaultRoute) {
		this.defaultRoute = defaultRoute;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductionId() {
		return productionId;
	}



	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}



	public String getProductionName() {
		return productionName;
	}



	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}



	public Integer getLineId() {
		return lineId;
	}



	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}



	public String getLineName() {
		return lineName;
	}



	public void setLineName(String lineName) {
		this.lineName = lineName;
	}



	public String getRoute() {
		return route;
	}



	public void setRoute(String route) {
		this.route = route;
	}



	@Override
	public String toString() {
		return "CMesRoutingT [id=" + id + ", name=" + name + ", productionId=" + productionId + ", productionName="
				+ productionName + ", lineId=" + lineId + ", lineName=" + lineName + ", route=" + route + "]";
	}



	public CMesRoutingT(Integer id, String name, Integer productionId, String productionName, Integer lineId,
			String lineName, String route) {
		super();
		this.id = id;
		this.name = name;
		this.productionId = productionId;
		this.productionName = productionName;
		this.lineId = lineId;
		this.lineName = lineName;
		this.route = route;
	}



	public CMesRoutingT() {
		super();
	}


}
