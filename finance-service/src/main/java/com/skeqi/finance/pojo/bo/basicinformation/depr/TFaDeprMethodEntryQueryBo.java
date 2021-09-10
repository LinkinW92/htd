package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 折旧方法明细分页查询对象 t_fa_depr_method_entry
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("折旧方法明细分页查询对象")
public class TFaDeprMethodEntryQueryBo extends BaseEntity {

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


	/** 折旧方法内码 */
	@ApiModelProperty("折旧方法内码")
	private Integer fId;
	/** 公式类型 1、期折旧额 2、期折旧率 */
	@ApiModelProperty("公式类型 1、期折旧额 2、期折旧率")
	private String fFormulaType;
	/** 公式内容 */
	@ApiModelProperty("公式内容")
	private String fFormulaContent;
	/** 是否静态 0静态 1动态 */
	@ApiModelProperty("是否静态 0静态 1动态")
	private String fIsactive;
	/** 是否最后两年 1：是最后两年 0否 */
	@ApiModelProperty("是否最后两年 1：是最后两年 0否")
	private String fIslastTwoyear;

}
