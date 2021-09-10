package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel("总分类账视图对象")
public class ABTotalBalanceVo {

	//region 关联查询
	/**
	 * 科目名称
	 */
	@Excel(name = "科目名称")
	@ApiModelProperty("科目名称")
	private String fName;

	/**
	 * 余额方向 1 ：借方 ； -1 ：贷方
	 */
	@Excel(name = "余额方向 1 ：借方 ； -1 ：贷方 ")
	@ApiModelProperty("余额方向 1 ：借方 ； -1 ：贷方 ")
	private Integer fDc;
	//endregion

	/**
	 * 账簿内码
	 */
	@Excel(name = "账簿内码")
	@ApiModelProperty("账簿内码")
	private Integer fAccountBookId;

	/**
	 * 科目内码
	 */
	@Excel(name = "科目内码")
	@ApiModelProperty("科目内码")
	private Integer fAccountId;

	/**
	 * 核算维度组合内码
	 */
	@Excel(name = "核算维度组合内码")
	@ApiModelProperty("核算维度组合内码")
	private String fDetailCode;

	/**
	 * 币别内码
	 */
	@Excel(name = "币别内码 ")
	@ApiModelProperty("币别内码 ")
	private Integer fCurrencyId;

	/**
	 * 期间
	 */
	@Excel(name = "期间")
	@ApiModelProperty("期间")
	private Integer fPeriod;

	/**
	 * 年
	 */
	@Excel(name = "年")
	@ApiModelProperty("年")
	private Integer fYear;

	/**
	 * 借方本位币金额
	 */
	@Excel(name = "借方本位币金额")
	@ApiModelProperty("借方本位币金额")
	private BigDecimal fDebit;

	/**
	 * 贷方本位币金额
	 */
	@Excel(name = "贷方本位币金额")
	@ApiModelProperty("贷方本位币金额")
	private BigDecimal fCredit;

	/**
	 * 摘要
	 */
	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String Summary;

	/**
	 * 余额
	 */
	@Excel(name = "余额")
	@ApiModelProperty("余额")
	private BigDecimal balance;
}
