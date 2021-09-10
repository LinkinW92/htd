package com.skeqi.mes.pojo;

import java.util.Date;
//采购报价表
public class CMesPurchaseQuotation {
	private Integer id;
	private String quotationNumber;// 报价编号
	private String serialNumber;// 厂方物品编号
	private String supplierNo;// 供应商编号
	private String shortName;// 供应商简称
	private Date quotationDate;// 报价日期
	private Integer invoiceStatus;// 是否开发票：0.需要、1.不需要
	private Integer price;// 价格
	private String company;// 单位
	private Integer startingQuantity;// 起订量
	private String rowPicturnPath;// 报价单扫描图路径
	private String note;// 备注
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getStartingQuantity() {
		return startingQuantity;
	}

	public void setStartingQuantity(Integer startingQuantity) {
		this.startingQuantity = startingQuantity;
	}

	public String getRowPicturnPath() {
		return rowPicturnPath;
	}

	public void setRowPicturnPath(String rowPicturnPath) {
		this.rowPicturnPath = rowPicturnPath;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "CMesPurchaseQuotation [id=" + id + ", quotationNumber=" + quotationNumber + ", serialNumber="
				+ serialNumber + ", supplierNo=" + supplierNo + ", shortName=" + shortName + ", quotationDate="
				+ quotationDate + ", invoiceStatus=" + invoiceStatus + ", price=" + price + ", company=" + company
				+ ", startingQuantity=" + startingQuantity + ", rowPicturnPath=" + rowPicturnPath + ", note=" + note
				+ "]";
	}

}
