package com.skeqi.mes.pojo;
//业务记录表
public class CMesBusinessRecord {
	private Integer id;
	private String orderNo;// 订单号
	private String customerOrderNumber;// 客户订单号
	private String nameGoods;// 物品名称
	private String deliveryDate;// 交货日期
	private Integer orderStatus;// 订单状态：0.已到货、1.未到货
	private Integer unitPrice;// 单价
	private Integer cNumber;// 数量
	private Integer money;// 金额

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getcNumber() {
		return cNumber;
	}

	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "CMesProductionT [id=" + id + ", exOrderNo=" + orderNo + ", customerOrderNumber=" + customerOrderNumber
				+ ", nameGoods=" + nameGoods + ", deliveryDate=" + deliveryDate + ", orderStatus=" + orderStatus
				+ ", unitPrice=" + unitPrice + ", cNumber=" + cNumber + ", money=" + money + "]";
	}

}
