package com.skeqi.finance.pojo.vo.init;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 科目初始录入数据视图对象 t_gl_balance_init
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目初始录入数据视图对象")
public class GeneralLedgarInitVo {

	private static final long serialVersionUID = 1L;


	/** 账簿内码 */
	@Excel(name = "账簿内码")
	@ApiModelProperty("账簿内码")
	private Integer fAccountBookId;

	/** 科目内码 */
	@Excel(name = "科目内码")
	@ApiModelProperty("科目内码")
	private Integer fAccountId;

	/** 核算维度组合内码 */
	@Excel(name = "核算维度组合内码")
	@ApiModelProperty("核算维度组合内码")
	private Integer fDetailId;

	/** 币别内码  */
	@Excel(name = "币别内码 ")
	@ApiModelProperty("币别内码 ")
	private Integer fCurrencyId;

	/** 期间 */
	@Excel(name = "期间")
	@ApiModelProperty("期间")
	private Integer fPeriod;

	/** 年 */
	@Excel(name = "年")
	@ApiModelProperty("年")
	private String fYear;

	/** 余额方向 */
	@Excel(name = "余额方向")
	@ApiModelProperty("余额方向")
	private String fDc;

	/** 期初原币金额 */
	@Excel(name = "期初原币金额")
	@ApiModelProperty("期初原币金额")
	private BigDecimal fBeginBalancefor;

	/** 期初本位币金额 */
	@Excel(name = "期初本位币金额")
	@ApiModelProperty("期初本位币金额")
	private BigDecimal fBeginBalance;

	/** 借方原币金额 */
	@Excel(name = "借方原币金额")
	@ApiModelProperty("借方原币金额")
	private BigDecimal fDebitFor;

	/** 借方本位币金额 */
	@Excel(name = "借方本位币金额")
	@ApiModelProperty("借方本位币金额")
	private BigDecimal fDebit;

	/** 贷方原币金额 */
	@Excel(name = "贷方原币金额")
	@ApiModelProperty("贷方原币金额")
	private BigDecimal fCreditFor;

	/** 贷方本位币金额 */
	@Excel(name = "贷方本位币金额")
	@ApiModelProperty("贷方本位币金额")
	private BigDecimal fCredit;

	/** 本年累计借方原币金额 */
	@Excel(name = "本年累计借方原币金额")
	@ApiModelProperty("本年累计借方原币金额")
	private BigDecimal fYtdDebitfor;

	/** 本年累计借方本位币金额 */
	@Excel(name = "本年累计借方本位币金额")
	@ApiModelProperty("本年累计借方本位币金额")
	private BigDecimal fYtdDebit;

	/** 本年累计贷方原币金额 */
	@Excel(name = "本年累计贷方原币金额")
	@ApiModelProperty("本年累计贷方原币金额")
	private BigDecimal fYtdCreditfor;

	/** 本年累计贷方本位币金额 */
	@Excel(name = "本年累计贷方本位币金额")
	@ApiModelProperty("本年累计贷方本位币金额")
	private BigDecimal fYtdCredit;

	/** 期末原币金额 */
	@Excel(name = "期末原币金额")
	@ApiModelProperty("期末原币金额")
	private BigDecimal fEndBalancefor;

	/** 期末本位币金额 */
	@Excel(name = "期末本位币金额")
	@ApiModelProperty("期末本位币金额")
	private BigDecimal fEndBalance;

	/** 调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期 */
	@Excel(name = "调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期")
	@ApiModelProperty("调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期")
	private Integer fAdjustPeriod;

	/** 年期 */
	@Excel(name = "年期")
	@ApiModelProperty("年期")
	private Integer fYearPeriod;


}
