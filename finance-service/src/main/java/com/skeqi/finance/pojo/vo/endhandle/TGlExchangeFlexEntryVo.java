package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 期末调汇核算维度分录视图对象 t_gl_exchange_flex_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇核算维度分录视图对象")
public class TGlExchangeFlexEntryVo {

	private static final long serialVersionUID = 1L;

	/** 分录主键 */
	@ApiModelProperty("分录主键")
	private Integer fEntryId;

	/** 期末调汇方案主键 */
	@Excel(name = "期末调汇方案主键")
	@ApiModelProperty("期末调汇方案主键")
	private Integer fId;

	/** 核算维度ID */
	@Excel(name = "核算维度ID")
	@ApiModelProperty("核算维度ID")
	private Integer fFlexId;

	/** 核算维度值 */
	@Excel(name = "核算维度值")
	@ApiModelProperty("核算维度值")
	private String fFlexValue;

	@Excel(name = "核算维度值名字")
	@ApiModelProperty("核算维度值名字")
	private String fFlexName;


}
