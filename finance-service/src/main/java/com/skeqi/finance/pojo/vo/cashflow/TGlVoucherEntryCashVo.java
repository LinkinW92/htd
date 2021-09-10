package com.skeqi.finance.pojo.vo.cashflow;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证分录现金流量视图对象 t_gl_voucher_entry_cash
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@ApiModel("凭证分录现金流量视图对象")
public class TGlVoucherEntryCashVo {

	private static final long serialVersionUID = 1L;

	private String fAccuntName;

	private String fAccuntNumber;
	/** 日期 */
	@Excel(name = "日期")
	@ApiModelProperty("日期")
	private Date fDate;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private String fVoucherWords;

	/** 凭证号 */
	@Excel(name = "凭证号")
	@ApiModelProperty("凭证号")
	private Integer fVoucherGroupNo;

	/** 凭证摘要 */
	@Excel(name = "凭证摘要")
	@ApiModelProperty("凭证摘要")
	private String fExplanation;

	/** 原币金额 */
	@Excel(name = "原币金额")
	@ApiModelProperty("原币金额")
	private BigDecimal fAmountfor;

	/** 本位币金额 */
	@Excel(name = "本位币金额")
	@ApiModelProperty("本位币金额")
	private BigDecimal fAmount;

	@ApiModelProperty("本位币金额")
	private BigDecimal amount;

	/** 借方金额 */
	@Excel(name = "借方金额")
	@ApiModelProperty("借方金额")
	private BigDecimal fDebit;

	/** 贷方金额 */
	@Excel(name = "贷方金额")
	@ApiModelProperty("贷方金额")
	private BigDecimal fCredit;

	/** 凭证现金流量ID */
	@Excel(name = "凭证现金流量ID")
	@ApiModelProperty("凭证现金流量ID")
	private Integer id;

	/** 账簿ID */
	@Excel(name = "账簿ID")
	@ApiModelProperty("账簿ID")
	private Integer bookId;

	/** 账簿ID */
	@Excel(name = "现金分录ID")
	@ApiModelProperty("现金分录ID")
	private Integer voucherEntryId;

	/** 对方分录ID */
	@Excel(name = "对方分录ID")
	@ApiModelProperty("对方分录ID")
	private Integer forVoucherEntryId;

	/** 现金科目 */
	@Excel(name = "现金科目")
	@ApiModelProperty("现金科目")
	private Integer cashAccountId;

	/** 对方科目ID */
	@Excel(name = "对方科目ID")
	@ApiModelProperty("对方科目ID")
	private Integer forAcctId;


	/** 主表ID */
	@Excel(name = "主表ID")
	@ApiModelProperty("主表ID")
	private Integer mainTableId;

	/** 附表ID */
	@Excel(name = "附表ID")
	@ApiModelProperty("附表ID")
	private Integer attachTableId;

	/** 币别id */
	@Excel(name = "币别id")
	@ApiModelProperty("币别id")
	private Integer currencyId;

	/** 流量原币金额 */
	@Excel(name = "流量原币金额")
	@ApiModelProperty("流量原币金额")
	private Integer flowAmountFor;

	/** 流量本位币金额 */
	@Excel(name = "流量本位币金额")
	@ApiModelProperty("流量本位币金额")
	private Integer flowAmount;

	/** 科目编码 */
	@Excel(name = "科目编码")
	@ApiModelProperty("科目编码")
	private String subjectNumber;

	/** 科目名称 */
	@Excel(name = "科目名称")
	@ApiModelProperty("科目名称")
	private String subjectName;

	/** 流量币别名称 */
	@Excel(name = "流量币别名称")
	@ApiModelProperty("流量币别名称")
	private String flowAccountName;

	/** 主表项目名称 */
	@Excel(name = "主表项目名称")
	@ApiModelProperty("主表项目名称")
	private String mainTableName;

	/** 附表项目名称 */
	@Excel(name = "附表项目名称")
	@ApiModelProperty("附表项目名称")
	private String attachTableName;


	/** 借贷方向 */
	@Excel(name = "借贷方向")
	@ApiModelProperty("借贷方向")
	private String fDc;

	private String fItemTypeid;

	private String mainTableNumber;
}
