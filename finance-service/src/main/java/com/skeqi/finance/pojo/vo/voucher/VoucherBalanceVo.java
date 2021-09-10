package com.skeqi.finance.pojo.vo.voucher;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VoucherBalanceVo {

	private Integer fAccountBookId;

	private Integer fYear;

	private Integer fPeriod;

	private Integer fAccountId;

	private Integer fCurrencyId;

	private String fDetailCode;

	private BigDecimal fAmountfor;

	private BigDecimal fAmount;

	private BigDecimal fDebit;

	private BigDecimal fCredit;
}
