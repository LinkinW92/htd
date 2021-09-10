package com.skeqi.manage.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 职级分页查询对象 sys_dept_rank
 *
 * @author toms
 * @date 2021-08-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("职级分页查询对象")
public class SysDeptRankQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 编码 */
	@ApiModelProperty("编码")
	private String code;
	/** 职级名称 */
	@ApiModelProperty("职级名称")
	private String name;
	/** 等级 */
	@ApiModelProperty("等级")
	private Integer level;

}
