package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 凭证预提-预提科目添加对象 t_gl_withholding_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-预提科目添加对象")
public class TGlWithholdingAcctAddBo {


    /** 方案内码 */
    @ApiModelProperty("方案内码")
    private Integer fSchemeId;

    /** 预提科目 */
    @ApiModelProperty("预提科目")
	@NotNull(message = "预提科目不能为空")
    private Integer fProvisionAccount;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Integer fDetailId;

    /** 比例 */
    @ApiModelProperty("比例")
	@NotNull(message = "比例不能为空")
    private BigDecimal fProvisionRatio;

    /** 固定 */
    @ApiModelProperty("固定")
    private String fProvisionFixed;

	/**
	 * 维度
	 */
	List<TGlWithholdingAcctDimensionAddBo> acctDimension;
}
