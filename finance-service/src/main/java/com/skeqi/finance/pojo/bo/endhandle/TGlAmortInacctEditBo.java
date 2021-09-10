package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证摊销-转入科目编辑对象 t_gl_amort_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-转入科目编辑对象")
public class TGlAmortInacctEditBo {


    /** 自增ID */
    @ApiModelProperty("自增ID")
    private Long fId;

    /** 方案内码 */
    @ApiModelProperty("方案内码")
    private Long fSchemeId;

    /** 转入科目ID */
    @ApiModelProperty("转入科目ID")
    @NotNull(message = "转入科目ID不能为空")
    private Long fEnterAccountId;

    /** 比例 */
    @ApiModelProperty("比例")
    private BigDecimal fEnterRatio;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Long fDetailId;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fEnterRatioFixed;
}
