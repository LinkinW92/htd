package com.skeqi.mes.pojo;
//物品登记表
public class CMesItemRegistrationForm {
	private Integer id;
	private Integer itemType;// 物品类型，0：模组；1：其他
	private String quotationNumber;// 报价编号
	private String serialNumber;// 物品编号
	private String productId;//产品号/条码
	private String nameGoods;//物品名称
	private String typeNum;// 型号\n
	private String specifications;// 规格\n
	private String shape;// 形状\n
	private String ePackage;// 包装物
	private Double materialLength;//物料长度，单位m
	private Double materialWidth;//物料宽度，单位m
	private Double materialHight;//物料高度，单位m
	private Integer currentInventory;// 当前库存
	private String calculateUnit;// 计量单位
	private Integer safetyStock;// 安全库存
	private Integer salesReferencePrice;// 销售参考价
	private Integer currentNetWorth;// 当前净值
	private Integer purchaseReferencePrice;// 进货参考价
	private Double monthlyDepreciationRate;//月折旧率
	private Integer price;// 价格
	private String supplyOfGoods;// 货物供应
	private String note;//备注
	private String picturnPath;//图片路径
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemType() {
		return itemType;
	}
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNameGoods() {
		return nameGoods;
	}
	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(String typeNum) {
		this.typeNum = typeNum;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getePackage() {
		return ePackage;
	}
	public void setePackage(String ePackage) {
		this.ePackage = ePackage;
	}
	public Double getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(Double materialLength) {
		this.materialLength = materialLength;
	}
	public Double getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(Double materialWidth) {
		this.materialWidth = materialWidth;
	}
	public Double getMaterialHight() {
		return materialHight;
	}
	public void setMaterialHight(Double materialHight) {
		this.materialHight = materialHight;
	}
	public Integer getCurrentInventory() {
		return currentInventory;
	}
	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}
	public String getCalculateUnit() {
		return calculateUnit;
	}
	public void setCalculateUnit(String calculateUnit) {
		this.calculateUnit = calculateUnit;
	}
	public Integer getSafetyStock() {
		return safetyStock;
	}
	public void setSafetyStock(Integer safetyStock) {
		this.safetyStock = safetyStock;
	}
	public Integer getSalesReferencePrice() {
		return salesReferencePrice;
	}
	public void setSalesReferencePrice(Integer salesReferencePrice) {
		this.salesReferencePrice = salesReferencePrice;
	}
	public Integer getCurrentNetWorth() {
		return currentNetWorth;
	}
	public void setCurrentNetWorth(Integer currentNetWorth) {
		this.currentNetWorth = currentNetWorth;
	}
	public Integer getPurchaseReferencePrice() {
		return purchaseReferencePrice;
	}
	public void setPurchaseReferencePrice(Integer purchaseReferencePrice) {
		this.purchaseReferencePrice = purchaseReferencePrice;
	}
	public Double getMonthlyDepreciationRate() {
		return monthlyDepreciationRate;
	}
	public void setMonthlyDepreciationRate(Double monthlyDepreciationRate) {
		this.monthlyDepreciationRate = monthlyDepreciationRate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getSupplyOfGoods() {
		return supplyOfGoods;
	}
	public void setSupplyOfGoods(String supplyOfGoods) {
		this.supplyOfGoods = supplyOfGoods;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPicturnPath() {
		return picturnPath;
	}
	public void setPicturnPath(String picturnPath) {
		this.picturnPath = picturnPath;
	}
	@Override
	public String toString() {
		return "CMesItemRegistrationForm [id=" + id + ", itemType=" + itemType + ", quotationNumber=" + quotationNumber
				+ ", serialNumber=" + serialNumber + ", productId=" + productId + ", nameGoods=" + nameGoods
				+ ", typeNum=" + typeNum + ", specifications=" + specifications + ", shape=" + shape + ", ePackage="
				+ ePackage + ", materialLength=" + materialLength + ", materialWidth=" + materialWidth
				+ ", materialHight=" + materialHight + ", currentInventory=" + currentInventory + ", calculateUnit="
				+ calculateUnit + ", safetyStock=" + safetyStock + ", salesReferencePrice=" + salesReferencePrice
				+ ", currentNetWorth=" + currentNetWorth + ", purchaseReferencePrice=" + purchaseReferencePrice
				+ ", monthlyDepreciationRate=" + monthlyDepreciationRate + ", price=" + price + ", supplyOfGoods="
				+ supplyOfGoods + ", note=" + note + ", picturnPath=" + picturnPath + "]";
	}

}
