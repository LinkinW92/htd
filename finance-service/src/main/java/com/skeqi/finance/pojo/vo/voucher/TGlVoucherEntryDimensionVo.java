package com.skeqi.finance.pojo.vo.voucher;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证分录维度控制视图对象 t_gl_voucher_entry_dimension
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@ApiModel("凭证分录维度控制视图对象")
public class TGlVoucherEntryDimensionVo {

	private static final long serialVersionUID = 1L;


	/** 维度编码 */
	@Excel(name = "维度编码")
	@ApiModelProperty("维度编码")
	private String dsCode;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String dsName;

	/** 凭证分录ID */
	@Excel(name = "凭证分录ID")
	@ApiModelProperty("凭证分录ID")
	private Integer voucherEntryId;

	private String dimensionCode;

	private String dimensionAll;

}
