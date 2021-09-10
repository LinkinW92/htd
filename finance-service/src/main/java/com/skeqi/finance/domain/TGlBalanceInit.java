package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目初始录入数据对象 t_gl_balance_init
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_balance_init")
public class TGlBalanceInit implements Serializable {

    private static final long serialVersionUID=1L;


    /** 账簿内码 */
    private Integer fAccountBookId;

    /** 科目内码 */
    private Integer fAccountId;

	/**
	 *核算维度类型组合内码
	 */
	private String dimensionCode;
	/**
	 * 核算维度值组合内码
	 */
	private String fDetailCode;

    /** 币别内码  */
    private Integer fCurrencyId;

    /** 期间 */
    private Integer fPeriod;

    /** 年 */
    private Integer fYear;

    /** 余额方向 */
    private String fDc;

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
    private Integer fYearPeriod;

}
