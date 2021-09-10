package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CMesProductionT {
	private Integer id;
	@ApiModelProperty(value = "产品名称", required = true)
	private String productionName;
	@ApiModelProperty(value = "产品型号", required = true)
	private String productionType;
	@ApiModelProperty(value = "产品商标", required = true)
	private String productionTrademark;
	@ApiModelProperty(value = "序列号", required = true)
	private String productionSeries;
	@ApiModelProperty(value = "产品效验规则", required = true)
	private String productionVr;
	@ApiModelProperty(value = "产品描述", required = true)
	private String productionDiscription;
	@ApiModelProperty(value = "工位名称", required = false)
	private String stationName;
	@ApiModelProperty()
	private String productionEt;
	@ApiModelProperty()
	private String productionGt;
	@ApiModelProperty(value = "产品状态 0:在线  1:离线", required = true)
	private String productionSte;
	@ApiModelProperty(value = "产品标签ID(pack码)", required = true)
	private Integer productionPrintId;

	private String productionPrintName;
	@ApiModelProperty(value = "图片路径 ", required = true)
	private String path;

	@ApiModelProperty(value = "产品标签ID(系统码)", required = false)
	private Integer productionSystemId;
	@ApiModelProperty(value = "产品标签ID(套数码)", required = false)
	private Integer productionGroupId;
	private Integer groupNumber;

	private String productionSystemName;
	private String productionGroupName;
	private String biaoqianName;
	private Integer codeDigit;
	private String PRODUCTION_CODE;

	@ApiModelProperty(value = "产品条码", required = true)
	private String productionSn;

	@ApiModelProperty(value = "物料编码", required = true)
	private String materialCode;

	public String getProductionSn() {
		return productionSn;
	}

	public void setProductionSn(String productionSn) {
		this.productionSn = productionSn;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public Integer getCodeDigit() {
		return codeDigit;
	}

	public void setCodeDigit(Integer codeDigit) {
		this.codeDigit = codeDigit;
	}

	public String getBiaoqianName() {
		return biaoqianName;
	}

	public void setBiaoqianName(String biaoqianName) {
		this.biaoqianName = biaoqianName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Integer getProductionSystemId() {
		return productionSystemId;
	}

	public void setProductionSystemId(Integer productionSystemId) {
		this.productionSystemId = productionSystemId;
	}

	public Integer getProductionGroupId() {
		return productionGroupId;
	}

	public void setProductionGroupId(Integer productionGroupId) {
		this.productionGroupId = productionGroupId;
	}

	public String getProductionSystemName() {
		return productionSystemName;
	}

	public void setProductionSystemName(String productionSystemName) {
		this.productionSystemName = productionSystemName;
	}

	public String getProductionGroupName() {
		return productionGroupName;
	}

	public void setProductionGroupName(String productionGroupName) {
		this.productionGroupName = productionGroupName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductionPrintId() {
		return productionPrintId;
	}

	public void setProductionPrintId(Integer productionPrintId) {
		this.productionPrintId = productionPrintId;
	}

	public String getProductionPrintName() {
		return productionPrintName;
	}

	public void setProductionPrintName(String productionPrintName) {
		this.productionPrintName = productionPrintName;
	}

	public CMesProductionT() {
		super();
	}

	public String getProductionName() {
		return productionName;
	}

	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}

	public String getProductionType() {
		return productionType;
	}

	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}

	public String getProductionTrademark() {
		return productionTrademark;
	}

	public void setProductionTrademark(String productionTrademark) {
		this.productionTrademark = productionTrademark;
	}

	public String getProductionSeries() {
		return productionSeries;
	}

	public void setProductionSeries(String productionSeries) {
		this.productionSeries = productionSeries;
	}

	public String getProductionVr() {
		return productionVr;
	}

	public void setProductionVr(String productionVr) {
		this.productionVr = productionVr;
	}

	public String getProductionDiscription() {
		return productionDiscription;
	}

	public void setProductionDiscription(String productionDiscription) {
		this.productionDiscription = productionDiscription;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getProductionEt() {
		return productionEt;
	}

	public void setProductionEt(String productionEt) {
		this.productionEt = productionEt;
	}

	public String getProductionGt() {
		return productionGt;
	}

	public void setProductionGt(String productionGt) {
		this.productionGt = productionGt;
	}

	public String getProductionSte() {
		return productionSte;
	}

	public void setProductionSte(String productionSte) {
		this.productionSte = productionSte;
	}

	public CMesProductionT(Integer id, String productionName, String productionType, String productionTrademark,
			String productionSeries, String productionVr, String productionDiscription, String stationName,
			String productionEt, String productionGt, String productionSte, Integer productionPrintId,
			String productionPrintName, String path, Integer productionSystemId, Integer productionGroupId,
			Integer groupNumber, String productionSystemName, String productionGroupName) {
		super();
		this.id = id;
		this.productionName = productionName;
		this.productionType = productionType;
		this.productionTrademark = productionTrademark;
		this.productionSeries = productionSeries;
		this.productionVr = productionVr;
		this.productionDiscription = productionDiscription;
		this.stationName = stationName;
		this.productionEt = productionEt;
		this.productionGt = productionGt;
		this.productionSte = productionSte;
		this.productionPrintId = productionPrintId;
		this.productionPrintName = productionPrintName;
		this.path = path;
		this.productionSystemId = productionSystemId;
		this.productionGroupId = productionGroupId;
		this.groupNumber = groupNumber;
		this.productionSystemName = productionSystemName;
		this.productionGroupName = productionGroupName;
	}

	@Override
	public String toString() {
		return "CMesProductionT{" +
				"id=" + id +
				", productionName='" + productionName + '\'' +
				", productionType='" + productionType + '\'' +
				", productionTrademark='" + productionTrademark + '\'' +
				", productionSeries='" + productionSeries + '\'' +
				", productionVr='" + productionVr + '\'' +
				", productionDiscription='" + productionDiscription + '\'' +
				", stationName='" + stationName + '\'' +
				", productionEt='" + productionEt + '\'' +
				", productionGt='" + productionGt + '\'' +
				", productionSte='" + productionSte + '\'' +
				", productionPrintId=" + productionPrintId +
				", productionPrintName='" + productionPrintName + '\'' +
				", path='" + path + '\'' +
				", productionSystemId=" + productionSystemId +
				", productionGroupId=" + productionGroupId +
				", groupNumber=" + groupNumber +
				", productionSystemName='" + productionSystemName + '\'' +
				", productionGroupName='" + productionGroupName + '\'' +
				", biaoqianName='" + biaoqianName + '\'' +
				", codeDigit=" + codeDigit +
				", PRODUCTION_CODE='" + PRODUCTION_CODE + '\'' +
				", productionSn=" + productionSn +
				", materialCode=" + materialCode +
				'}';
	}

	public String getPRODUCTION_CODE() {
		return PRODUCTION_CODE;
	}

	public void setPRODUCTION_CODE(String pRODUCTION_CODE) {
		PRODUCTION_CODE = pRODUCTION_CODE;
	}

}
