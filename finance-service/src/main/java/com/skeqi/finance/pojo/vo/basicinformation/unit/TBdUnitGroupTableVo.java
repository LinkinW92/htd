package com.skeqi.finance.pojo.vo.basicinformation.unit;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 计量单位分组视图对象 t_bd_unit_group_table
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("计量单位分组视图对象")
public class TBdUnitGroupTableVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 上级ID */
	@Excel(name = "上级ID")
	@ApiModelProperty("上级ID")
	private Integer fParentId;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;


}
