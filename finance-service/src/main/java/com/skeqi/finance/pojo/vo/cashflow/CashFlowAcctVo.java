package com.skeqi.finance.pojo.vo.cashflow;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashFlowAcctVo {

	/**
	 * 会计科目：分录会计科目，来源于基础资料会计科目
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目")
	@ApiModelProperty("会计科目")
	private Integer fAccountId;

	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fEntryId;

	/**
	 * 会计科目：分录会计科目，来源于基础资料会计科目
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目编码")
	@ApiModelProperty("会计科目编码")
	private Integer fAccountNumber;

	/**
	 * 会计科目名称
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目名称")
	@ApiModelProperty("会计科目名称")
	private String fAccountName;


	@Excel(name = "币别")
	@ApiModelProperty("币别")
	private Integer fCurrencyId;

	/**
	 * 币别名
	 */
	@Excel(name = "币别名")
	@ApiModelProperty("币别名")
	private String fCurrencyName;

	private String fDc;

	/**
	 * 原币金额
	 */
	@Excel(name = "原币金额")
	@ApiModelProperty("原币金额")
	private BigDecimal fAmountfor;

	/**
	 * 本位币金额
	 */
	@Excel(name = "本位币金额")
	@ApiModelProperty("本位币金额")
	private BigDecimal fAmount;
}
