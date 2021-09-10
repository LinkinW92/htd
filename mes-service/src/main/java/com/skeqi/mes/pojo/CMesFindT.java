package com.skeqi.mes.pojo;

public class CMesFindT {

	private Integer productionid;
	private String productionName;
	private String productionCode;
	public Integer getProductionid() {
		return productionid;
	}
	public void setProductionid(Integer productionid) {
		this.productionid = productionid;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}

	public CMesFindT() {
		super();
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	public CMesFindT(Integer productionid, String productionName, String productionCode) {
		super();
		this.productionid = productionid;
		this.productionName = productionName;
		this.productionCode = productionCode;
	}
	@Override
	public String toString() {
		return "CMesFindT [productionid=" + productionid + ", productionName=" + productionName + ", productionCode="
				+ productionCode + "]";
	}


}
