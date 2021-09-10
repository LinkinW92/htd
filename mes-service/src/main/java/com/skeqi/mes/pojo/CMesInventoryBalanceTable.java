package com.skeqi.mes.pojo;
//库存余额
public class CMesInventoryBalanceTable {
	private Integer id;
	private Integer tradeName;// 商品类别：0.模组、1.小物件。。。
	private String nameGoods;// 商品名称
	private String typenum;// 规格型号
	private String commodityEncoding;// 商品编码
	private Integer cNumber;// 库存数量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTradeName() {
		return tradeName;
	}

	public void setTradeName(Integer tradeName) {
		this.tradeName = tradeName;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getTypenum() {
		return typenum;
	}

	public void setTypenum(String typenum) {
		this.typenum = typenum;
	}

	public String getCommodityEncoding() {
		return commodityEncoding;
	}

	public void setCommodityEncoding(String commodityEncoding) {
		this.commodityEncoding = commodityEncoding;
	}

	public Integer getcNumber() {
		return cNumber;
	}

	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}

	@Override
	public String toString() {
		return "CMesProductionT [id=" + id + ", tradeName=" + tradeName + ", nameGoods=" + nameGoods + ", typenum="
				+ typenum + ", commodityEncoding=" + commodityEncoding + ", cNumber=" + cNumber + "]";
	}

}
