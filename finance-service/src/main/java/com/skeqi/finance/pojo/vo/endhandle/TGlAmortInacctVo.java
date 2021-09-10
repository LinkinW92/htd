package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证摊销-转入科目视图对象 t_gl_amort_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销-转入科目视图对象")
public class TGlAmortInacctVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer fId;

	/** 转入科目ID */
	@Excel(name = "转入科目ID")
	@ApiModelProperty("转入科目ID")
	private Integer fEnterAccountId;
	@Excel(name = "转入科目名称")
	@ApiModelProperty("转入科目名称")
	private String fAccountName;
	/** 比例 */
	@Excel(name = "比例")
	@ApiModelProperty("比例")
	private BigDecimal fEnterRatio;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private String fDetail;

	/** 固定 */
	@Excel(name = "固定")
	@ApiModelProperty("固定")
	private String fEnterRatioFixed;


	private List<TGlAmortInacctDimensionVo> inacctDimension;

}
