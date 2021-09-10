package com.skeqi.finance.pojo.vo.dimension;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel("维度来源数据")
public class DimensionSourceData {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("维度内码")
	private Integer fId;

	/** 列表名称 */
	@Excel(name = "列表名称")
	@ApiModelProperty("列表名称")
	private String listName;

	/** 列表名称 */
	@Excel(name = "数据")
	@ApiModelProperty("数据")
	private List<Map> data;
}
