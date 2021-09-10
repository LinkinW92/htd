package com.skeqi.mes.pojo.wms;

/**
 * 所有物料条码
 * @author Administrator
 *
 */
public class CWmsAllMaterialBarcode {
	private Integer id;
	private Integer materialBarcodeRuleId;//物料条码规则Id
	private String barCode;//条码
	private String generationTime;//生成时间
	private String printingTime;//打印时间
	private Integer state;//状态：0:使用，1：丢弃
	public CWmsAllMaterialBarcode(Integer id, Integer materialBarcodeRuleId, String barCode, String generationTime,
			String printingTime, Integer state) {
		this.id = id;
		this.materialBarcodeRuleId = materialBarcodeRuleId;
		this.barCode = barCode;
		this.generationTime = generationTime;
		this.printingTime = printingTime;
		this.state = state;
	}
	public CWmsAllMaterialBarcode() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CWmsAllMaterialBarcode [id=" + id + ", materialBarcodeRuleId=" + materialBarcodeRuleId + ", barCode="
				+ barCode + ", generationTime=" + generationTime + ", printingTime=" + printingTime + ", state=" + state
				+ "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMaterialBarcodeRuleId() {
		return materialBarcodeRuleId;
	}
	public void setMaterialBarcodeRuleId(Integer materialBarcodeRuleId) {
		this.materialBarcodeRuleId = materialBarcodeRuleId;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getGenerationTime() {
		return generationTime;
	}
	public void setGenerationTime(String generationTime) {
		this.generationTime = generationTime;
	}
	public String getPrintingTime() {
		return printingTime;
	}
	public void setPrintingTime(String printingTime) {
		this.printingTime = printingTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
