package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 凭证摊销转入科目维度控制添加对象 t_gl_amort_inacct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销转入科目维度控制添加对象")
public class TGlAmortInacctDimensionAddBo {


    /** 维度ID */
    @ApiModelProperty("维度ID")
	@NotNull(message = "维度ID不能为空")
    private Integer dimensionId;

    /** 维度编码 */
    @ApiModelProperty("维度编码")
	@NotNull(message = "维度编码不能为空")
    private String dsCode;

    /** 名称 */
    @ApiModelProperty("名称")
	@NotNull(message = "名称不能为空")
    private String dsName;

    /** 摊销关联ID */
    @ApiModelProperty("摊销关联ID")
    private Integer amortEntryId;

    /** 科目ID */
    @ApiModelProperty("科目ID")
    private Integer acctId;
}
