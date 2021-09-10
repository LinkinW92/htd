package com.skeqi.finance.pojo.bo.cashflow;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashFlowBO {

	/**
	 * 对方分录ID
	 */
	private Integer fVoucherEntryId;

	/**
	 * 主表ID
	 */
	private Integer mainTableId;
	/**
	 * 附表ID
	 */
	private Integer attachTableId;

	private BigDecimal amount;
	private String fExplanation;
}
