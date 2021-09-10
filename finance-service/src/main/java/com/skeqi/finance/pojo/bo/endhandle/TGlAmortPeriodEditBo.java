package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证摊销-摊销期间编辑对象 t_gl_amort_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-摊销期间编辑对象")
public class TGlAmortPeriodEditBo {


    /** 方案内码 */
    @ApiModelProperty("方案内码")
    private Long fSchemeId;

    /** 会计期间 */
    @ApiModelProperty("会计期间")
    private String fYearPeriod;

    /** 摊销比例 */
    @ApiModelProperty("摊销比例")
    private BigDecimal fAmortratio;

    /** 摊销金额 */
    @ApiModelProperty("摊销金额")
    private BigDecimal fAmortamount;

    /** 是否摊销 */
    @ApiModelProperty("是否摊销")
    private String fAmorted;

    /** 会计年度 */
    @ApiModelProperty("会计年度")
    private Long fYear;

    /** 会计期间 */
    @ApiModelProperty("会计期间")
    private Long fPeriod;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fAmortRatioFixed;
}
