package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 凭证摊销编辑对象 t_gl_amortization_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销编辑对象")
public class TGlAmortizationSchemeEditBo {


    /** 方案内码 */
    @ApiModelProperty("方案内码")
	@NotNull(message ="方案ID不能为空" )
    private Long fSchemeId;

    /** 编码 */
    @ApiModelProperty("编码")
    @NotBlank(message = "编码不能为空")
    private String fNumber;

    private String fName;
    private String fDescription;

    /** 是否摊销方案 */
    @ApiModelProperty("是否摊销方案")
    @NotBlank(message = "是否摊销方案不能为空")
    private String fIsamort;

    /** 账簿 */
    @ApiModelProperty("账簿")
    @NotNull(message = "账簿不能为空")
    private Long fAccountBookId;

    /** 凭证字 */
    @ApiModelProperty("凭证字")
    @NotNull(message = "凭证字不能为空")
    private Long fVoucherGroupId;

    /** 币别 */
    @ApiModelProperty("币别")
    @NotNull(message = "币别不能为空")
    private Long fCurrencyId;

    /** 汇率类型 */
    @ApiModelProperty("汇率类型")
    @NotNull(message = "汇率类型不能为空")
    private Long fExchangeRateType;

    /** 摘要 */
    @ApiModelProperty("摘要")
    @NotBlank(message = "摘要不能为空")
    private String fExplanation;

    /** 待摊销总额 */
    @ApiModelProperty("待摊销总额")
    @NotNull(message = "待摊销总额不能为空")
    private BigDecimal fPeddingAmortAmount;

    /** 已摊销总额 */
    @ApiModelProperty("已摊销总额")
    @NotNull(message = "已摊销总额不能为空")
    private BigDecimal fAmortedAmount;

    /** 余额 */
    @ApiModelProperty("余额")
    @NotNull(message = "余额不能为空")
    private BigDecimal fEndBalance;

    /** 执行时间 */
    @ApiModelProperty("执行时间")
    private Date fLastExecuteTime;

    /** 状态 */
    @ApiModelProperty("状态")
    @NotBlank(message = "状态不能为空")
    private String fStatus;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    @NotBlank(message = "禁用状态不能为空")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    @NotNull(message = "禁用人不能为空")
    private Long fForbidderId;

    /** 禁用时间 */
    @ApiModelProperty("禁用时间")
    private Date fForbidDate;

	@Valid
	@ApiModelProperty("待摊销科目")
	List<TGlAmortAcctAddBo> amortAcct;

	@Valid
	@ApiModelProperty("转入科目")
	List<TGlAmortInacctAddBo> amortInAcct;
	@Valid
	@ApiModelProperty("摊销期间")
	List<TGlAmortPeriodAddBo> amortPeriod;
}
