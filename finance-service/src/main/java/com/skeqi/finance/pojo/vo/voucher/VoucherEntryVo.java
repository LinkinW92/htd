package com.skeqi.finance.pojo.vo.voucher;

import com.skeqi.common.annotation.Excel;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryDimension;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class VoucherEntryVo {


	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fEntryId;

	private String fDetailCode;

	private String dimensionCode;

	/**
	 * 凭证主表内码
	 */
	@Excel(name = "凭证主表内码")
	@ApiModelProperty("凭证主表内码")
	private Integer fVoucherId;

	/**
	 * 摘要：文本信息，可以来源于基础资料摘要
	 */
	@Excel(name = "摘要")
	@ApiModelProperty("摘要：文本信息，可以来源于基础资料摘要")
	private String fExplanation;

	/**
	 * 会计科目：分录会计科目，来源于基础资料会计科目
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目")
	@ApiModelProperty("会计科目")
	private Integer fAccountId;

	/**
	 * 会计科目：分录会计科目，来源于基础资料会计科目
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目编码")
	@ApiModelProperty("会计科目编码")
	private String fAccountNumber;

	/**
	 * 会计科目名称
	 * 需要控制权限和弹性域
	 */
	@Excel(name = "会计科目名称")
	@ApiModelProperty("会计科目名称")
	private String fAccountName;

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

	/**
	 * 币别
	 */
	@Excel(name = "币别")
	@ApiModelProperty("币别")
	private Integer fCurrencyId;

	/**
	 * 币别名
	 */
	@Excel(name = "币别名")
	@ApiModelProperty("币别名")
	private String fCurrencyName;

	/**
	 * 汇率类型
	 */
	@Excel(name = "汇率类型")
	@ApiModelProperty("汇率类型")
	private Integer fExchangeRateType;

	/**
	 * 汇率类型名字
	 */
	@Excel(name = "汇率类型名字")
	@ApiModelProperty("汇率类型名字")
	private String fExchangeRateName;

	/**
	 * 汇率
	 */
	@Excel(name = "汇率")
	@ApiModelProperty("汇率")
	private BigDecimal fExchangeRate;

	/**
	 * 借贷方向
	 */
	@Excel(name = "借贷方向")
	@ApiModelProperty("借贷方向")
	private String fDc;

	/**
	 * 计量单位
	 */
	@Excel(name = "计量单位")
	@ApiModelProperty("计量单位")
	private Integer fMeasureUnitId;

	/**
	 * 计量单位名称
	 */
	@Excel(name = "计量单位名称")
	@ApiModelProperty("计量单位名称")
	private String fMeasureUnitName;

	/**
	 * 单价
	 */
	@Excel(name = "单价")
	@ApiModelProperty("单价")
	private BigDecimal fUnitPrice;

	/**
	 * 数量
	 */
	@Excel(name = "数量")
	@ApiModelProperty("数量")
	private BigDecimal fQuantity;

	/**
	 * 科目单位数量
	 */
	@Excel(name = "科目单位数量")
	@ApiModelProperty("科目单位数量")
	private BigDecimal fAcctUnitQty;

	/**
	 * 计量单位数量
	 */
	@Excel(name = "计量单位数量")
	@ApiModelProperty("计量单位数量")
	private BigDecimal fBaseUnitQty;

	/**
	 * 结算方式
	 */
	@Excel(name = "结算方式")
	@ApiModelProperty("结算方式")
	private Integer fSettleTypeId;

	/**
	 * 结算号
	 */
	@Excel(name = "结算号")
	@ApiModelProperty("结算号")
	private String fSettleNo;

	/**
	 * 借方金额
	 */
	@Excel(name = "借方金额")
	@ApiModelProperty("借方金额")
	private BigDecimal fDebit;

	/**
	 * 贷方金额
	 */
	@Excel(name = "贷方金额")
	@ApiModelProperty("贷方金额")
	private BigDecimal fCredit;

	/**
	 * 行号
	 */
	@Excel(name = "行号")
	@ApiModelProperty("行号")
	private Integer fEntrySeq;

	/**
	 * 对方科目所在的分录id
	 */
	@Excel(name = "对方科目所在的分录id")
	@ApiModelProperty("对方科目所在的分录id")
	private Integer fSideEntrySeq;

	/**
	 * 是否已指定现金流量
	 */
	@Excel(name = "是否已指定现金流量")
	@ApiModelProperty("是否已指定现金流量")
	private String fCashFlowItem;

	/**
	 * 原始核算维度组合ID 核算维度：记录核算数据的内码
	 */
	@Excel(name = "原始核算维度组合ID 核算维度：记录核算数据的内码")
	@ApiModelProperty("原始核算维度组合ID 核算维度：记录核算数据的内码")
	private Integer fOriginalDetailId;

	/**
	 * 是否参与多栏账汇总
	 */
	@Excel(name = "是否参与多栏账汇总")
	@ApiModelProperty("是否参与多栏账汇总")
	private String fIsmultiCollect;

	@Excel(name = "维度信息")
	@ApiModelProperty("维度信息")
	private String dimension;

	private TGlVoucherEntryDimensionVo dimensionVo;
}
