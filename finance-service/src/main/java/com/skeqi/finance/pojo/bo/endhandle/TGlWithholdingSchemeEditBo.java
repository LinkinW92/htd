package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 凭证预提编辑对象 t_gl_withholding_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提编辑对象")
public class TGlWithholdingSchemeEditBo {


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

    /** 待预提总额 */
    @ApiModelProperty("待预提总额")
    @NotNull(message = "待预提总额不能为空")
    private BigDecimal fPeddingDrawAmount;

    /** 已预提总额 */
    @ApiModelProperty("已预提总额")
    @NotNull(message = "已预提总额不能为空")
    private BigDecimal fAmortizedAmount;

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
}
