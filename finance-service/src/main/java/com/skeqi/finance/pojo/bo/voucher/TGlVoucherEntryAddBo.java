package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 凭证录入分添加对象 t_gl_voucher_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("凭证录入分添加对象")
public class TGlVoucherEntryAddBo {


	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fEntryId;
	/**
	 * 凭证主表内码
	 */
	@ApiModelProperty("凭证主表内码")
	private Integer fVoucherId;

	/**
	 * 摘要：文本信息，可以来源于基础资料摘要
	 */
	@ApiModelProperty("摘要：文本信息，可以来源于基础资料摘要")
	@NotNull(message = "摘要不能为空")
	private String fExplanation;

	/**
	 * 会计科目：分录会计科目，来源于基础资料会计科目
	 * 需要控制权限和弹性域
	 */
	@ApiModelProperty("会计科目：分录会计科目，来源于基础资料会计科目 需要控制权限和弹性域")
	@NotNull(message = "会计科目不能为空")
	private Integer fAccountId;

	/**
	 * 核算维度：记录核算数据的内码
	 */
	@ApiModelProperty("核算维度")
	private String fDetailCode;

	/**
	 * 原币金额
	 */
	@ApiModelProperty("原币金额")
	private BigDecimal fAmountfor;

	/**
	 * 本位币金额
	 */
	@ApiModelProperty("本位币金额")
	private BigDecimal fAmount;

	/**
	 * 币别
	 */
	@ApiModelProperty("币别")
	@NotNull(message = "币别不能为空")
	private Integer fCurrencyId;

	/**
	 * 汇率类型
	 */
	@ApiModelProperty("汇率类型")
	@NotNull(message = "汇率类型不能为空")
	private Integer fExchangeRateType;

	/**
	 * 汇率
	 */
	@ApiModelProperty("汇率")
	@NotNull(message = "汇率不能为空")
	private BigDecimal fExchangeRate;

	/**
	 * 借贷方向
	 */
	@ApiModelProperty("借贷方向")
	private String fDc;

	/**
	 * 计量单位
	 */
	@ApiModelProperty("计量单位")
	private Integer fMeasureUnitId;

	/**
	 * 单价
	 */
	@ApiModelProperty("单价")
	private BigDecimal fUnitPrice;

	/**
	 * 数量
	 */
	@ApiModelProperty("数量")
	private BigDecimal fQuantity;

	/**
	 * 科目单位数量
	 */
	@ApiModelProperty("科目单位数量")
	private BigDecimal fAcctUnitQty;

	/**
	 * 计量单位数量
	 */
	@ApiModelProperty("计量单位数量")
	private BigDecimal fBaseUnitQty;

	/**
	 * 结算方式
	 */
	@ApiModelProperty("结算方式")
	private Integer fSettleTypeId;

	/**
	 * 结算号
	 */
	@ApiModelProperty("结算号")
	private String fSettleNo;

	/**
	 * 借方金额
	 */
	@ApiModelProperty("借方金额")
	private BigDecimal fDebit;

	/**
	 * 贷方金额
	 */
	@ApiModelProperty("贷方金额")
	private BigDecimal fCredit;

	/**
	 * 行号
	 */
	@ApiModelProperty("行号")
	private Integer fEntrySeq;

	/**
	 * 对方科目所在的分录id
	 */
	@ApiModelProperty("对方科目所在的分录id")
	private Integer fSideEntrySeq;

	/**
	 * 是否已指定现金流量
	 */
	@ApiModelProperty("是否已指定现金流量")
	private String fCashFlowItem;

	/**
	 * 原始核算维度组合ID 核算维度：记录核算数据的内码
	 */
	@ApiModelProperty("原始核算维度组合ID 核算维度：记录核算数据的内码")
	private Integer fOriginalDetailId;

	/**
	 * 是否参与多栏账汇总
	 */
	@ApiModelProperty("是否参与多栏账汇总")
	private String fIsmultiCollect;

	@ApiModelProperty("维度信息")
	private List<AccountingDimensionBo> dimensionInfo;

}
