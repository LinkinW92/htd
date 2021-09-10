package com.skeqi.finance.pojo.vo.cashflow;

import lombok.Data;

import java.util.List;

@Data
public class VoucherCashFlowVo {

	private Integer fAccountBookId;

	private String fAccountBookName;
	/**
	 * 现金科目
	 */
	List<CashFlowAcctVo> flowAcct;

	/**
	 * 对方现金科目
	 */
	List<TGlVoucherEntryCashVo> cashFor;
}
