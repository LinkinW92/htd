package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 凭证摊销-待摊销科目添加对象 t_gl_amort_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-待摊销科目添加对象")
public class TGlAmortAcctAddBo {

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Long fId;

    /** 方案ID */
    @ApiModelProperty("方案ID")
    private Integer fSchemeId;

    /** 待摊销科目 */
    @ApiModelProperty("待摊销科目")
	@NotNull(message = "待摊销科目不能为空")
    private Integer fAmortizeAccount;

    /** 待摊销金额 */
    @ApiModelProperty("待摊销金额")
	@NotNull(message = "待摊销金额不能为空")
    private BigDecimal fAmortizingAmount;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Integer fDetailId;

    /** 公式定义 */
    @ApiModelProperty("公式定义")
    private String fExpression;

	/**
	 * 维度
	 */
	List<TGlAmortAcctDimensionAddBo> acctDimension;
}
