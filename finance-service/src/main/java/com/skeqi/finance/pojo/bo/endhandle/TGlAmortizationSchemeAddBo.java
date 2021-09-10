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
 * 凭证摊销添加对象 t_gl_amortization_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销添加对象")
public class TGlAmortizationSchemeAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
    @NotBlank(message = "编码不能为空")
    private String fNumber;

	@NotBlank(message = "名字不能为空")
	private String fName;

	private String fDescription;

    /** 是否摊销方案 */
    @ApiModelProperty("是否摊销方案")
    //@NotBlank(message = "是否摊销方案不能为空")
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

    /** 待摊销总额 */
    @ApiModelProperty("待摊销总额")
    private BigDecimal fPeddingAmortAmount;

    /** 已摊销总额 */
    @ApiModelProperty("已摊销总额")
    private BigDecimal fAmortedAmount;

    /** 余额 */
    @ApiModelProperty("余额")
    private BigDecimal fEndBalance;

    /** 执行时间 */
    @ApiModelProperty("执行时间")
    private Date fLastExecuteTime;

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
