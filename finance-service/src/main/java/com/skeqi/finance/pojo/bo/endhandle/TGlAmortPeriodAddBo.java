package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 凭证摊销-摊销期间添加对象 t_gl_amort_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-摊销期间添加对象")
public class TGlAmortPeriodAddBo {

	/** 方案内码 */
	@ApiModelProperty("方案内码")
	private Long fSchemeId;

    /** 会计期间 */
    @ApiModelProperty("会计期间")
	@NotBlank(message = "会计期间不能为空")
    private String fYearPeriod;

    /** 摊销比例 */
    @ApiModelProperty("摊销比例")
	@NotNull(message = "摊销比例不能为空")
    private BigDecimal fAmortratio;

    /** 摊销金额 */
    @ApiModelProperty("摊销金额")
	@NotNull(message = "摊销金额不能为空")
    private BigDecimal fAmortamount;

    /** 是否摊销 */
    @ApiModelProperty("是否摊销")
    private String fAmorted;

    /** 会计年度 */
    @ApiModelProperty("会计年度")
	@NotNull(message = "会计年度不能为空")
    private Integer fYear;

    /** 会计期间 */
    @ApiModelProperty("会计期间")
	@NotNull(message = "会计期间不能为空")
    private Integer fPeriod;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fAmortRatioFixed;
}
