package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证预提-转入科目编辑对象 t_gl_withholding_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-转入科目编辑对象")
public class TGlWithholdingInacctEditBo {


    /** 转入科目ID */
    @ApiModelProperty("转入科目ID")
    private Long fEnterAccountId;

    /** 比例 */
    @ApiModelProperty("比例")
    private BigDecimal fEnterRatio;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Long fEnterDetail;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fEnterRatioFixed;
}
