package com.skeqi.finance.pojo.bo.TBdAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 科目核算币别分页查询对象 t_bd_accountcy
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("科目核算币别分页查询对象")
public class TBdAccountcyQueryBo extends BaseEntity {

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


	/** 分录内码 */
	@ApiModelProperty("分录内码")
	private Integer fEntryId;
	/** 科目内码 */
	@ApiModelProperty("科目内码")
	private Integer fAcctId;
	/** 显示顺序  */
	@ApiModelProperty("显示顺序 ")
	private Integer fSeq;

}
