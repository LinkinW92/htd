package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 结账损益核算维度分页查询对象 t_gl_plscheme_flex
 *
 * @author toms
 * @date 2021-08-17
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("结账损益核算维度分页查询对象")
public class TGlPlschemeFlexQueryBo extends BaseEntity {

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


	/** 结账损益方案主键 */
	@ApiModelProperty("结账损益方案主键")
	private Integer fId;
	/** 核算维度ID */
	@ApiModelProperty("核算维度ID")
	private Integer fFlexId;
	/** 核算维度值 */
	@ApiModelProperty("核算维度值")
	private String fFlexValue;

	private String fFlexName;

}
