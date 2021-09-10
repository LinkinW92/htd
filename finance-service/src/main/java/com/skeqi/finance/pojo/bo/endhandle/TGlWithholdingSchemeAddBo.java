package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 凭证预提添加对象 t_gl_withholding_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提添加对象")
public class TGlWithholdingSchemeAddBo {


	/** 方案内码 */
	@ApiModelProperty("方案内码")
	private Long fSchemeId;
    /** 编码 */
    @ApiModelProperty("编码")
    @NotBlank(message = "编码不能为空")
    private String fNumber;

	@NotBlank(message = "名字不能为空")
    private String fName;

    private String fDescription;

    /** 是否预提方案 */
    @ApiModelProperty("是否预提方案")
    @NotBlank(message = "是否预提方案不能为空")
    private String fIsamort;

    /** 账簿 */
    @ApiModelProperty("账簿")
    @NotNull(message = "账簿不能为空")
    private Integer fAccountBookId;

    /** 凭证字 */
    @ApiModelProperty("凭证字")
    @NotNull(message = "凭证字不能为空")
    private Integer fVoucherGroupId;

    /** 币别 */
    @ApiModelProperty("币别")
    @NotNull(message = "币别不能为空")
    private Integer fCurrencyId;

    /** 汇率类型 */
    @ApiModelProperty("汇率类型")
    @NotNull(message = "汇率类型不能为空")
    private Integer fExchangeRateType;

    /** 摘要 */
    @ApiModelProperty("摘要")
    @NotBlank(message = "摘要不能为空")
    private String fExplanation;

    /** 待预提总额 */
    @ApiModelProperty("待预提总额")
    private BigDecimal fPeddingDrawAmount;

    /** 已预提总额 */
    @ApiModelProperty("已预提总额")
    private BigDecimal fAmortizedAmount;

    /** 余额 */
    @ApiModelProperty("余额")
    private BigDecimal fEndBalance;

	@Valid
	@ApiModelProperty("预提科目")
	List<TGlWithholdingAcctAddBo> amortAcct;

	@Valid
	@ApiModelProperty("转入科目")
	List<TGlWithholdingInacctAddBo> amortInAcct;
	@Valid
	@ApiModelProperty("预提期间")
	List<TGlWithholdingPeriodAddBo> amortPeriod;



}
