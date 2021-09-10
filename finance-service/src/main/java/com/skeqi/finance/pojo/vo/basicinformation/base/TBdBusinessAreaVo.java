package com.skeqi.finance.pojo.vo.basicinformation.base;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 业务领域视图对象 t_bd_business_area
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("业务领域视图对象")
public class TBdBusinessAreaVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer id;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String name;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private String fIssysPreset;


}
