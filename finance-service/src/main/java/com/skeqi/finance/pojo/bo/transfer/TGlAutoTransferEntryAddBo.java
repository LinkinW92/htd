package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 自动转账分录添加对象 t_gl_auto_transfer_entry
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账分录添加对象")
public class TGlAutoTransferEntryAddBo {

	private Integer fTransferEntryId;

    /** 自动转账ID */
    @ApiModelProperty("自动转账ID")
    private Integer fTransferId;

    /** 序号 */
    @ApiModelProperty("序号")
	@NotNull(message = "序号不能为空")
    private Integer fEntrySeq;

    /** 摘要 */
    @ApiModelProperty("摘要")
	@NotBlank(message = "摘要不能为空")
    private String fExplanation;

    /** 会计科目 */
    @ApiModelProperty("科目信息")
    @NotNull(message = "科目信息不能为空")
    private Integer fAccountId;

    /** 核算维度是否启用 */
    @ApiModelProperty("核算维度是否启用")
	@NotBlank(message = "核算维度是否启用不能为空")
    private String fItemIsvalid;

    /** 币别 */
    @ApiModelProperty("币别")
    @NotNull(message = "币别不能为空")
    private Integer fCurrencyId;

    /** 汇率类型 */
    @ApiModelProperty("汇率类型")
    @NotNull(message = "汇率类型不能为空")
    private Integer fExchangeRateType;

    /** 方向 */
    @ApiModelProperty("方向")
    @NotNull(message = "方向不能为空")
    private Integer fDc;

    /** 转账方式 */
    @ApiModelProperty("转账方式")
	@NotBlank(message = "转账方式不能为空")
    private String fType;

    /** 转账比例 */
    @ApiModelProperty("转账比例")
	@NotNull(message = "转账比例不能为空")
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
	@NotBlank(message = "包含未过账凭证不能为空")
    private String fPosted;

	/**
	 * 核算维度
	 */
	private List<TGlAutoTransferEntryItemAddBo> itemBo;
}
