package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 自动转账分录编辑对象 t_gl_auto_transfer_entry
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账分录编辑对象")
public class TGlAutoTransferEntryEditBo {


    /** 分录表内码 */
    @ApiModelProperty("分录表内码")
    private Integer fTransferEntryId;

    /** 自动转账ID */
    @ApiModelProperty("自动转账ID")
    @NotNull(message = "自动转账ID不能为空")
    private Integer fTransferId;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer fEntrySeq;

    /** 摘要 */
    @ApiModelProperty("摘要")
    private String fExplanation;

    /** 会计科目 */
    @ApiModelProperty("会计科目")
    @NotNull(message = "会计科目不能为空")
    private Integer fAccountId;

    /** 核算维度是否启用 */
    @ApiModelProperty("核算维度是否启用")
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
