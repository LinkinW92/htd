package com.skeqi.mes.pojo;

import java.util.Date;


//报价记录表
public class CMesQuotationRecord {

	private Integer id;
	private String quotationNumber;// 报价号
	private Date quotationDate;// 报价日期
	private String serialNumber;// 物品编号
	private String nameGoods;// 物品名称
	private Integer cNumber;// 数量
	private String company;// 单位
	private Integer money;// 金额
	private Date deliveryDate;// 交货日期
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
	public Date getQuotationDate() {
		return quotationDate;
	}
	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
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
	public Integer getcNumber() {
		return cNumber;
	}
	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "CMesQuotationRecord [id=" + id + ", quotationNumber=" + quotationNumber + ", quotationDate="
				+ quotationDate + ", serialNumber=" + serialNumber + ", nameGoods=" + nameGoods + ", cNumber=" + cNumber
				+ ", company=" + company + ", money=" + money + ", deliveryDate=" + deliveryDate + ", note=" + note
				+ "]";
	}

}
