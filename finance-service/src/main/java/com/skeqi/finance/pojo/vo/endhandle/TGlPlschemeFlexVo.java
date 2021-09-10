package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 结账损益核算维度视图对象 t_gl_plscheme_flex
 *
 * @author toms
 * @date 2021-08-17
 */
@Data
@ApiModel("结账损益核算维度视图对象")
public class TGlPlschemeFlexVo {

	private static final long serialVersionUID = 1L;

	/** 分录主键 */
	@ApiModelProperty("分录主键")
	private Integer fEntryId;

	/** 结账损益方案主键 */
	@Excel(name = "结账损益方案主键")
	@ApiModelProperty("结账损益方案主键")
	private Integer fId;

	/** 核算维度ID */
	@Excel(name = "核算维度ID")
	@ApiModelProperty("核算维度ID")
	private Integer fFlexId;

	/** 核算维度值 */
	@Excel(name = "核算维度值")
	@ApiModelProperty("核算维度值")
	private String fFlexValue;


}
