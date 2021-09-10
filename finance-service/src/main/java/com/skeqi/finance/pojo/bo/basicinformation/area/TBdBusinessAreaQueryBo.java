package com.skeqi.finance.pojo.bo.basicinformation.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 业务领域分页查询对象 t_bd_business_area
 *
 * @author toms
 * @date 2021-07-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("业务领域分页查询对象")
public class TBdBusinessAreaQueryBo extends BaseEntity {

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


	/** 名称 */
    @JsonProperty(value = "name")
	@ApiModelProperty("名称")
	private String name;
	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private String fIssysPreset;

}
