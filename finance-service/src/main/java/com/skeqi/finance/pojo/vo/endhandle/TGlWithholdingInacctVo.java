package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.List;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacctDimension;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证预提-转入科目视图对象 t_gl_withholding_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-转入科目视图对象")
public class TGlWithholdingInacctVo {

	private static final long serialVersionUID = 1L;

	/** 转入科目ID */
	@ApiModelProperty("转入科目ID")
	private Integer fEnterAccountId;

	private Integer fId;

	/** 待摊销科目 */
	@Excel(name = "待摊销科目")
	@ApiModelProperty("待摊销科目")
	private String fAccountName;

	/** 比例 */
	@Excel(name = "比例")
	@ApiModelProperty("比例")
	private BigDecimal fEnterRatio;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private Integer fEnterDetail;

	/** 固定 */
	@Excel(name = "固定")
	@ApiModelProperty("固定")
	private String fEnterRatioFixed;


	private List<TGlWithholdingInacctDimension> dimensions;
}
