package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证预提-预提期间视图对象 t_gl_withholding_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-预提期间视图对象")
public class TGlWithholdingPeriodVo {

	private static final long serialVersionUID = 1L;


	/** 会计年度 */
	@Excel(name = "会计年度")
	@ApiModelProperty("会计年度")
	private Long fYear;

	/** 会计期间 */
	@Excel(name = "会计期间")
	@ApiModelProperty("会计期间")
	private Long fPeriod;

	/** 会计年期间 */
	@Excel(name = "会计年期间")
	@ApiModelProperty("会计年期间")
	private String fYearPeriod;

	/** 预提金额 */
	@Excel(name = "预提金额")
	@ApiModelProperty("预提金额")
	private BigDecimal fAmortAmount;

	/** 已预提 */
	@Excel(name = "已预提")
	@ApiModelProperty("已预提")
	private String fAmorted;

	/** 公式定义 */
	@Excel(name = "公式定义")
	@ApiModelProperty("公式定义")
	private String fExpression;


}
