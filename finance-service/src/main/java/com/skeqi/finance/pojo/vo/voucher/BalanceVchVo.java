package com.skeqi.finance.pojo.vo.voucher;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceVchVo {


	/** 账簿内码 */
	private Integer fAccountBookId;

	/** 科目内码 */
	private Integer fAccountId;

	/** 核算维度组合内码 */
	private String fDetailCode;

	/** 币别内码  */
	private Integer fCurrencyId;

	/** 期间 */
	private Integer fPeriod;

	/** 年 */
	private Integer fYear;

	/** 期初原币金额 */
	private BigDecimal fBeginBalancefor;

	/** 期初本位币金额 */
	private BigDecimal fBeginBalance;

	/** 借方原币金额 */
	private BigDecimal fDebitFor;

	/** 借方本位币金额 */
	private BigDecimal fDebit;

	/** 贷方原币金额 */
	private BigDecimal fCreditFor;

	/** 贷方本位币金额 */
	private BigDecimal fCredit;

	/** 本年累计借方原币金额 */
	private BigDecimal fYtdDebitfor;

	/** 本年累计借方本位币金额 */
	private BigDecimal fYtdDebit;

	/** 本年累计贷方原币金额 */
	private BigDecimal fYtdCreditfor;

	/** 本年累计贷方本位币金额 */
	private BigDecimal fYtdCredit;

	/** 期末原币金额 */
	private BigDecimal fEndBalancefor;

	/** 期末本位币金额 */
	private BigDecimal fEndBalance;

	/** 调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期 */
	private Integer fAdjustPeriod;

	/** 年期 */
	private String fYearPeriod;

	private String fDc;
}
