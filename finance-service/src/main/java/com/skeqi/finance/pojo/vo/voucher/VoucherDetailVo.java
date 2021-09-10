package com.skeqi.finance.pojo.vo.voucher;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherDetailVo {

	private Date fDate;

	private String fVoucherGroupName;

	private String fVoucherGroupNo;

	private String fExplanation;

	private BigDecimal fDebit;

	private BigDecimal fCredit;

	private String fDc;

	private BigDecimal fBalance;


}
