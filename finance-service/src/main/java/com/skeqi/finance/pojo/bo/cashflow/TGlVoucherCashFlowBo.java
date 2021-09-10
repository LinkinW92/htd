package com.skeqi.finance.pojo.bo.cashflow;

import com.skeqi.finance.pojo.bo.cashflow.CashFlowBO;
import lombok.Data;

import java.util.List;

@Data
public class TGlVoucherCashFlowBo {

	/**
	 * 分录ID
	 */
	private Integer fVoucherEntryId;

	/**
	 * 账簿ID
	 */
	private Integer bookId;

	List<CashFlowBO> cashFlow;
}
