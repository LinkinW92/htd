package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证预提-预提期间编辑对象 t_gl_withholding_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-预提期间编辑对象")
public class TGlWithholdingPeriodEditBo {


    /** 会计年度 */
    @ApiModelProperty("会计年度")
    private Long fYear;

    /** 会计期间 */
    @ApiModelProperty("会计期间")
    private Long fPeriod;

    /** 会计年期间 */
    @ApiModelProperty("会计年期间")
    private String fYearPeriod;

    /** 预提金额 */
    @ApiModelProperty("预提金额")
    private BigDecimal fAmortAmount;

    /** 已预提 */
    @ApiModelProperty("已预提")
    private String fAmorted;

    /** 公式定义 */
    @ApiModelProperty("公式定义")
    private String fExpression;
}
