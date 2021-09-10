package com.skeqi.finance.pojo.bo.asset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 资产类别组分页查询对象 t_fa_asset_type_group
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资产类别组分页查询对象")
public class TFaAssetTypeGroupQueryBo extends BaseEntity {

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


	/** 资产类别分组编码 */
	@ApiModelProperty("资产类别分组编码")
	private String fNumber;

	/** 资产类别分组名称 */
	@ApiModelProperty("资产类别分组名称")
	private String fName;

	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescribe;

	/** 上级id */
	@ApiModelProperty("上级id")
	private Integer fParentId;

}
