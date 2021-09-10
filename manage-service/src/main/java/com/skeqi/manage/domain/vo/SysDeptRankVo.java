package com.skeqi.manage.domain.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 职级视图对象 sys_dept_rank
 *
 * @author toms
 * @date 2021-08-26
 */
@Data
@ApiModel("职级视图对象")
public class SysDeptRankVo {

	private static final long serialVersionUID = 1L;

	/** 自增 */
	@ApiModelProperty("自增")
	private Integer id;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String code;

	/** 职级名称 */
	@Excel(name = "职级名称")
	@ApiModelProperty("职级名称")
	private String name;

	/** 等级 */
	@Excel(name = "等级")
	@ApiModelProperty("等级")
	private Integer level;


}
