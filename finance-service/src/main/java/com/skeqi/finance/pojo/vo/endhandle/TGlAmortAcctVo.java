package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证摊销-待摊销科目视图对象 t_gl_amort_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-待摊销科目视图对象")
public class TGlAmortAcctVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer fId;

	/** 方案ID */
	@Excel(name = "方案ID")
	@ApiModelProperty("方案ID")
	private Integer fSchemeId;

	/** 待摊销科目 */
	@Excel(name = "待摊销科目")
	@ApiModelProperty("待摊销科目")
	private Integer fAmortizeAccount;

	/** 待摊销科目 */
	@Excel(name = "待摊销科目")
	@ApiModelProperty("待摊销科目")
	private String fAccountName;

	/** 待摊销金额 */
	@Excel(name = "待摊销金额")
	@ApiModelProperty("待摊销金额")
	private BigDecimal fAmortizingAmount;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private String fDetail;

	/** 公式定义 */
	@Excel(name = "公式定义")
	@ApiModelProperty("公式定义")
	private String fExpression;

	/**
	 * 核算维度
	 */
	private List<TGlAmortAcctDimensionVo> acctDimension;


}
