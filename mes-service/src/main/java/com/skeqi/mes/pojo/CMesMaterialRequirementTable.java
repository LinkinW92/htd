package com.skeqi.mes.pojo;

//物料需求表
public class CMesMaterialRequirementTable {
	private Integer id;
	private String processingOrderNo;// 加工订单号
	private Integer quantityProduction;// 生产数量
	private Integer materialCategory;// 物料类别：0.模组、1.小物件。。。
	private String serialNumber;// 物品编号
	private String nameGoods;// 物品名称
	private String materialProperties;// 物料属性
	private Integer aggregateDemand;// 总需求量
	private String company;// 单位
	private Integer currentInventory;// 当前库存
	private String note;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcessingOrderNo() {
		return processingOrderNo;
	}

	public void setProcessingOrderNo(String processingOrderNo) {
		this.processingOrderNo = processingOrderNo;
	}

	public Integer getQuantityProduction() {
		return quantityProduction;
	}

	public void setQuantityProduction(Integer quantityProduction) {
		this.quantityProduction = quantityProduction;
	}

	public Integer getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(Integer materialCategory) {
		this.materialCategory = materialCategory;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getMaterialProperties() {
		return materialProperties;
	}

	public void setMaterialProperties(String materialProperties) {
		this.materialProperties = materialProperties;
	}

	public Integer getAggregateDemand() {
		return aggregateDemand;
	}

	public void setAggregateDemand(Integer aggregateDemand) {
		this.aggregateDemand = aggregateDemand;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "CMesMaterialRequirementTable [id=" + id + ", processingOrderNo=" + processingOrderNo
				+ ", quantityProduction=" + quantityProduction + ", materialCategory=" + materialCategory
				+ ", serialNumber=" + serialNumber + ", nameGoods=" + nameGoods + ", materialProperties="
				+ materialProperties + ", aggregateDemand=" + aggregateDemand + ", company=" + company
				+ ", currentInventory=" + currentInventory + ", note=" + note + "]";
	}

}
