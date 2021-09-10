package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证摊销-待摊销科目编辑对象 t_gl_amort_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-待摊销科目编辑对象")
public class TGlAmortAcctEditBo {


    /** 方案ID */
    @ApiModelProperty("方案ID")
    private Long fSchemeId;

    /** 自增ID */
    @ApiModelProperty("自增ID")
    private Long fId;

    /** 待摊销科目 */
    @ApiModelProperty("待摊销科目")
    private Long fAmortizeAccount;

    /** 待摊销金额 */
    @ApiModelProperty("待摊销金额")
    private BigDecimal fAmortizingAmount;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Long fDetailId;

    /** 公式定义 */
    @ApiModelProperty("公式定义")
    private String fExpression;
}
