package com.skeqi.finance.pojo.vo.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 核算维度余额表
 */
@ApiModel(value = "com-skeqi-finance-domain-report-DimensionBalanceVo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DimensionBalanceVo implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Integer id;

	/**
	 * 账簿内码
	 */
	@ApiModelProperty(value = "账簿内码")
	private Integer fAccountBookId;

	/**
	 * 科目内码
	 */
	@ApiModelProperty(value = "科目内码")
	private Integer fAccountId;

	/**
	 * 科目编码
	 */
	@ApiModelProperty(value = "科目编码")
	private String fAccountNumber;

	/**
	 * 科目名称
	 */
	@ApiModelProperty(value = "科目名称")
	private String fAccountName;

	/**
	 * 核算维度类别组合Id
	 */
	@ApiModelProperty(value = "核算维度类别组合Id")
	private String dimensionCode;

	/**
	 * 核算维度组合编码
	 */
	@ApiModelProperty(value = "核算维度组合编码")
	private String fDetailCode;

	/**
	 * 核算维度名称
	 */
	@ApiModelProperty(value = "核算维度名称")
	private String fDetailName;

	/**
	 * 币别内码
	 */
	@ApiModelProperty(value = "币别内码 ")
	private Integer fCurrencyId;

	/**
	 * 期间
	 */
	@ApiModelProperty(value = "期间")
	private Integer fPeriod;

	/**
	 * 年
	 */
	@ApiModelProperty(value = "年")
	private String fYear;

	/**
	 * 余额方向
	 */
	@ApiModelProperty(value = "余额方向")
	private Integer fDc;

	/**
	 * 期初借方
	 */
	@ApiModelProperty(value = "期初借方")
	private BigDecimal initDebit;

	/**
	 * 期初贷方
	 */
	@ApiModelProperty(value = "期初贷方")
	private BigDecimal initCredit;

	/**
	 * 期初原币金额
	 */
	@ApiModelProperty(value = "期初原币金额")
	private BigDecimal fBeginBalancefor;

	/**
	 * 期初本位币金额
	 */
	@ApiModelProperty(value = "期初本位币金额")
	private BigDecimal fBeginBalance;

	/**
	 * 借方原币金额
	 */
	@ApiModelProperty(value = "借方原币金额")
	private BigDecimal fDebitFor;

	/**
	 * 借方本位币金额
	 */
	@ApiModelProperty(value = "借方本位币金额")
	private BigDecimal fDebit;

	/**
	 * 贷方原币金额
	 */
	@ApiModelProperty(value = "贷方原币金额")
	private BigDecimal fCreditFor;

	/**
	 * 贷方本位币金额
	 */
	@ApiModelProperty(value = "贷方本位币金额")
	private BigDecimal fCredit;

	/**
	 * 本年累计借方原币金额
	 */
	@ApiModelProperty(value = "本年累计借方原币金额")
	private BigDecimal fYtdDebitfor;

	/**
	 * 本年累计借方本位币金额
	 */
	@ApiModelProperty(value = "本年累计借方本位币金额")
	private BigDecimal fYtdDebit;

	/**
	 * 本年累计贷方原币金额
	 */
	@ApiModelProperty(value = "本年累计贷方原币金额")
	private BigDecimal fYtdCreditfor;

	/**
	 * 本年累计贷方本位币金额
	 */
	@ApiModelProperty(value = "本年累计贷方本位币金额")
	private BigDecimal fYtdCredit;

	/**
	 * 期末借方
	 */
	@ApiModelProperty(value = "期末借方")
	private BigDecimal endDebit;

	/**
	 * 期末贷方
	 */
	@ApiModelProperty(value = "期末贷方")
	private BigDecimal endCredit;

	/**
	 * 期末原币金额
	 */
	@ApiModelProperty(value = "期末原币金额")
	private BigDecimal fEndBalancefor;

	/**
	 * 期末本位币金额
	 */
	@ApiModelProperty(value = "期末本位币金额")
	private BigDecimal fEndBalance;

	/**
	 * 调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期
	 */
	@ApiModelProperty(value = "调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期")
	private Integer fAdjustPeriod;

	/**
	 * 年期
	 */
	@ApiModelProperty(value = "年期")
	private Integer fYearPeriod;

	private static final long serialVersionUID = 1L;
}

