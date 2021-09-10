package com.skeqi.htd.controller.vo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 产品vo
 *
 * @author linkin
 */
@Data
public class ProductVO {
	@ApiModelProperty(value = "外部订单号", hidden = true)
	private String exOrderNo;
	@ApiModelProperty("产品编码")
	private String code;
	@ApiModelProperty("产品名称")
	private String name;
	@ApiModelProperty("产品规格")
	private String productSku;
	@ApiModelProperty("产品数量")
	private Integer amount;
	@ApiModelProperty("数量单位")
	private String unit;
	@ApiModelProperty("产品品牌")
	private String brand;
	@ApiModelProperty("产品分仓")
	private String subWarehouse;
	@ApiModelProperty("产品单价")
	private String unitPrice;
	@ApiModelProperty("产品销售价格")
	private String salePrice;
	@ApiModelProperty("含税单价")
	private String unitPriceWithTax;
	@ApiModelProperty("含税售价")
	private String salePriceWithTax;
	@ApiModelProperty("税率")
	private String taxRate;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("产品ID")
	private Integer productId;


	@Data
	public static class ProductExt {
		private Integer amount;
		@ApiModelProperty("数量单位")
		private String unit;
		@ApiModelProperty("产品分仓")
		private String subWarehouse;
		@ApiModelProperty("产品单价")
		private String unitPrice;
		@ApiModelProperty("产品销售价格")
		private String salePrice;
		@ApiModelProperty("含税单价")
		private String unitPriceWithTax;
		@ApiModelProperty("含税售价")
		private String salePriceWithTax;
		@ApiModelProperty("税率")
		private String taxRate;
		@ApiModelProperty("备注")
		private String remark;
	}

	@Data
	public static class ProductBase {
		@ApiModelProperty("产品ID")
		private Integer productId;
		@ApiModelProperty("产品编码")
		private String code;
		@ApiModelProperty("产品名称")
		private String name;
		@ApiModelProperty("产品规格")
		private String productSku;
		@ApiModelProperty("产品品牌")
		private String brand;
		@ApiModelProperty("产品图片")
		private String picture;
		@ApiModelProperty("单位")
		private String unit;
	}

	public String stringifyExt() {
		ProductExt ext = new ProductExt();
		BeanUtils.copyProperties(this, ext);
		return JSON.toJSONString(ext);
	}
}
