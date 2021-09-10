package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证摊销-摊销期间视图对象 t_gl_amort_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-摊销期间视图对象")
public class TGlAmortPeriodVo {

	private static final long serialVersionUID = 1L;

	/** 方案内码 */
	@ApiModelProperty("方案内码")
	private Integer fSchemeId;

	/** 会计期间 */
	@Excel(name = "会计期间")
	@ApiModelProperty("会计期间")
	private String fYearPeriod;

	/** 摊销比例 */
	@Excel(name = "摊销比例")
	@ApiModelProperty("摊销比例")
	private BigDecimal fAmortratio;

	/** 摊销金额 */
	@Excel(name = "摊销金额")
	@ApiModelProperty("摊销金额")
	private BigDecimal fAmortamount;

	/** 是否摊销 */
	@Excel(name = "是否摊销")
	@ApiModelProperty("是否摊销")
	private String fAmorted;

	/** 会计年度 */
	@Excel(name = "会计年度")
	@ApiModelProperty("会计年度")
	private Integer fYear;

	/** 会计期间 */
	@Excel(name = "会计期间")
	@ApiModelProperty("会计期间")
	private Integer fPeriod;

	/** 固定 */
	@Excel(name = "固定")
	@ApiModelProperty("固定")
	private String fAmortRatioFixed;


}
