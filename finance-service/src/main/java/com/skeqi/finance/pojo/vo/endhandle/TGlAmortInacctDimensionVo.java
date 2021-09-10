package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证摊销转入科目维度控制视图对象 t_gl_amort_inacct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销转入科目维度控制视图对象")
public class TGlAmortInacctDimensionVo {

	private static final long serialVersionUID = 1L;


	/** 维度ID */
	@Excel(name = "维度ID")
	@ApiModelProperty("维度ID")
	private Integer dimensionId;

	/** 维度编码 */
	@Excel(name = "维度编码")
	@ApiModelProperty("维度编码")
	private String dsCode;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String dsName;

	/** 摊销关联ID */
	@Excel(name = "摊销关联ID")
	@ApiModelProperty("摊销关联ID")
	private Integer amortEntryId;

	/** 科目ID */
	@Excel(name = "科目ID")
	@ApiModelProperty("科目ID")
	private Integer acctId;


}
