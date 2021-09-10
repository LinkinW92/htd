package com.skeqi.finance.pojo.bo.voucher;

import lombok.Data;

import java.util.List;

@Data
public class AccountingDimensionBo {

	/**
	 * 维度信息内码 比如部分所属维度ID
	 */
	private Integer acctDimsId;

	/**
	 *  部分-研发部编码
	 */
	private String detailCode;
	/**
	 *  部分-研发部名称
	 */
	private String detailName;

//	/**
//	 * 显示维度信息
//	 */
//	private List<DimensionDetail> asDetail;
}
