package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证预提-预提科目编辑对象 t_gl_withholding_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-预提科目编辑对象")
public class TGlWithholdingAcctEditBo {


    /** 自增ID */
    @ApiModelProperty("自增ID")
    private Long fId;

    /** 方案内码 */
    @ApiModelProperty("方案内码")
    private Long fSchemeId;

    /** 预提科目 */
    @ApiModelProperty("预提科目")
    private Long fProvisionAccount;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Long fDetailId;

    /** 比例 */
    @ApiModelProperty("比例")
    private BigDecimal fProvisionRatio;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fProvisionFixed;
}
