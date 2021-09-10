package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证预提科目维度控制视图对象 t_gl_withholding_acct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提科目维度控制视图对象")
public class TGlWithholdingAcctDimensionVo {

	private static final long serialVersionUID = 1L;



	/** 维度ID */
	@Excel(name = "维度ID")
	@ApiModelProperty("维度ID")
	private Long dimensionId;

	/** 维度编码 */
	@Excel(name = "维度编码")
	@ApiModelProperty("维度编码")
	private String dsCode;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String dsName;

	/** 预提关联ID */
	@Excel(name = "预提关联ID")
	@ApiModelProperty("预提关联ID")
	private Long amortEntryId;

	/** 科目ID */
	@Excel(name = "科目ID")
	@ApiModelProperty("科目ID")
	private Long acctId;


}
