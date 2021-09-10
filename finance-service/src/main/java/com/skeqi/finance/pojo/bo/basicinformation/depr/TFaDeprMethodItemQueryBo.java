package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 折旧方法元素分页查询对象 t_fa_depr_method_item
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("折旧方法元素分页查询对象")
public class TFaDeprMethodItemQueryBo extends BaseEntity {

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


	/** 字段类型 1：文本 2：金额  */
	@ApiModelProperty("字段类型 1：文本 2：金额 ")
	private Integer fFieldType;
	/** 对应的取数字段  */
	@ApiModelProperty("对应的取数字段 ")
	private String fFormulaType;
	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;

}
