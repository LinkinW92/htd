package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 自动转账分录分页查询对象 t_gl_auto_transfer_entry
 *
 * @author toms
 * @date 2021-07-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("自动转账分录分页查询对象")
public class TGlAutoTransferEntryQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 自动转账ID */
	@ApiModelProperty("自动转账ID")
	private Integer fTransferId;
	/** 序号 */
	@ApiModelProperty("序号")
	private Integer fEntrySeq;
	/** 摘要 */
	@ApiModelProperty("摘要")
	private String fExplanation;
	/** 会计科目 */
	@ApiModelProperty("会计科目")
	private Integer fAccountId;
	/** 核算维度是否启用 */
	@ApiModelProperty("核算维度是否启用")
	private String fItemIsvalid;
	/** 币别 */
	@ApiModelProperty("币别")
	private Integer fCurrencyId;
	/** 汇率类型 */
	@ApiModelProperty("汇率类型")
	private Integer fExchangeRateType;
	/** 方向 */
	@ApiModelProperty("方向")
	private Integer fDc;
	/** 转账方式 */
	@ApiModelProperty("转账方式")
	private String fType;
	/** 转账比例 */
	@ApiModelProperty("转账比例")
	private BigDecimal fPercentage;
	/** 公式方法 */
	@ApiModelProperty("公式方法")
	private String fFormulaType;
	/** 原币公式 */
	@ApiModelProperty("原币公式")
	private String fAmountforFormula;
	/** 本币公式 */
	@ApiModelProperty("本币公式")
	private String fAmountFormula;
	/** 数量公式 */
	@ApiModelProperty("数量公式")
	private String fQtymula;
	/** 不参与多栏账汇总 */
	@ApiModelProperty("不参与多栏账汇总")
	private String fIsmultiCollect;
	/** 包含未过账凭证 */
	@ApiModelProperty("包含未过账凭证")
	private String fPosted;

}
