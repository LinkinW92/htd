package com.skeqi.mes.pojo.project;

/**
 * 钢平台数据
 */
public class AndonSteelPlatform {

	private Integer id;
	private String dt;//扫码时间
	private String sn;//总成号
	private Integer productId;//产品id
	private String productName;//产品名称
	private String productNo;//产品编码
	private String productModel;//产品型号
	private String productAbbreviation;//产品简称

	@Override
	public String toString() {
		return "AndonSteelPlatform [id=" + id + ", dt=" + dt + ", sn=" + sn + ", productId=" + productId
				+ ", productName=" + productName + ", productNo=" + productNo + ", productModel=" + productModel
				+ ", productAbbreviation=" + productAbbreviation + "]";
	}
	public AndonSteelPlatform(String sn, Integer productId, String productName, String productNo,
			String productModel, String productAbbreviation) {
		this.sn = sn;
		this.productId = productId;
		this.productName = productName;
		this.productNo = productNo;
		this.productModel = productModel;
		this.productAbbreviation = productAbbreviation;
	}
	public AndonSteelPlatform() {
	}
	public AndonSteelPlatform(Integer id, String dt, String sn, Integer productId, String productName, String productNo,
			String productModel, String productAbbreviation) {
		this.id = id;
		this.dt = dt;
		this.sn = sn;
		this.productId = productId;
		this.productName = productName;
		this.productNo = productNo;
		this.productModel = productModel;
		this.productAbbreviation = productAbbreviation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getProductAbbreviation() {
		return productAbbreviation;
	}
	public void setProductAbbreviation(String productAbbreviation) {
		this.productAbbreviation = productAbbreviation;
	}

}
