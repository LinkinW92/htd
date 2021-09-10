package com.skeqi.finance.pojo.vo.voucher;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BalanceDetailVo {

	private Date fDate;

	private String fVoucherWords;

	private Integer fVoucherGroupNo;

	private String fExplanation;

	private BigDecimal fDebit;
	private BigDecimal fDebitFor;

	private BigDecimal fCredit;
	private BigDecimal fCreditFor;

	private String fDc;

	private BigDecimal balance;


}
