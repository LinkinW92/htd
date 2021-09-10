package com.skeqi.finance.pojo.bo.unit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 计量单位分组分页查询对象 t_bd_unit_group_table
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("计量单位分组分页查询对象")
public class TBdUnitGroupTableQueryBo extends BaseEntity {

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
	private String fNumber;
	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
	/** 上级ID */
	@ApiModelProperty("上级ID")
	private Integer fParentId;
	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;

}
