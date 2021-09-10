package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 凭证预提科目维度控制编辑对象 t_gl_withholding_acct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提科目维度控制编辑对象")
public class TGlWithholdingAcctDimensionEditBo {


    /** 维度ID */
    @ApiModelProperty("维度ID")
    private Long dimensionId;

    /** 维度编码 */
    @ApiModelProperty("维度编码")
    private String dsCode;

    /** 名称 */
    @ApiModelProperty("名称")
    private String dsName;

    /** 预提关联ID */
    @ApiModelProperty("预提关联ID")
    private Long amortEntryId;

    /** 科目ID */
    @ApiModelProperty("科目ID")
    private Long acctId;
}
