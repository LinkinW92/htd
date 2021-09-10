package com.skeqi.finance.pojo.vo.transfer;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 自动转账分录视图对象 t_gl_auto_transfer_entry
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账分录视图对象")
public class TGlAutoTransferEntryVo {

	private static final long serialVersionUID = 1L;

	/** 分录表内码 */
	@ApiModelProperty("分录表内码")
	private Integer fTransferEntryId;

	/** 自动转账ID */
	@Excel(name = "自动转账ID")
	@ApiModelProperty("自动转账ID")
	private Integer fTransferId;

	/** 序号 */
	@Excel(name = "序号")
	@ApiModelProperty("序号")
	private Integer fEntrySeq;

	/** 摘要 */
	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String fExplanation;

	/** 会计科目 */
	@Excel(name = "会计科目")
	@ApiModelProperty("会计科目")
	private Integer fAccountId;

	/** 核算维度是否启用 */
	@Excel(name = "核算维度是否启用")
	@ApiModelProperty("核算维度是否启用")
	private String fItemIsvalid;

	/** 币别 */
	@Excel(name = "币别")
	@ApiModelProperty("币别")
	private Integer fCurrencyId;

	/** 汇率类型 */
	@Excel(name = "汇率类型")
	@ApiModelProperty("汇率类型")
	private Integer fExchangeRateType;

	/** 方向 */
	@Excel(name = "方向")
	@ApiModelProperty("方向")
	private Integer fDc;

	/** 转账方式 */
	@Excel(name = "转账方式")
	@ApiModelProperty("转账方式")
	private String fType;

	/** 转账比例 */
	@Excel(name = "转账比例")
	@ApiModelProperty("转账比例")
	private BigDecimal fPercentage;

	/** 公式方法 */
	@Excel(name = "公式方法")
	@ApiModelProperty("公式方法")
	private String fFormulaType;

	/** 原币公式 */
	@Excel(name = "原币公式")
	@ApiModelProperty("原币公式")
	private String fAmountforFormula;

	/** 本币公式 */
	@Excel(name = "本币公式")
	@ApiModelProperty("本币公式")
	private String fAmountFormula;

	/** 数量公式 */
	@Excel(name = "数量公式")
	@ApiModelProperty("数量公式")
	private String fQtymula;

	/** 不参与多栏账汇总 */
	@Excel(name = "不参与多栏账汇总")
	@ApiModelProperty("不参与多栏账汇总")
	private String fIsmultiCollect;

	/** 包含未过账凭证 */
	@Excel(name = "包含未过账凭证")
	@ApiModelProperty("包含未过账凭证")
	private String fPosted;

	/**
	 * 维度数据
	 */
	List<TGlAutoTransferEntryItemVo> itemVoList;


	private String acctName;
	private String acctNumber;
	private String rateName;
	private String rateNumber;
	private String cuyNumber;
	private String cuyName;


}
