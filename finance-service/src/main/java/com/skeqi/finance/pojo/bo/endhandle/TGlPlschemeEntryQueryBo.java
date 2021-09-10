package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 结转损益方案分录分页查询对象 t_gl_plscheme_entry
 *
 * @author toms
 * @date 2021-08-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("结转损益方案分录分页查询对象")
public class TGlPlschemeEntryQueryBo extends BaseEntity {

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


	/** 结转损益方案主键 */
	@ApiModelProperty("结转损益方案主键")
	private Long fId;
	/** 待结转科目 */
	@ApiModelProperty("待结转科目")
	private Long fAcctId;
	/** 结转科目 */
	@ApiModelProperty("结转科目")
	private Long fPlacctId;
	/** 是否选中 */
	@ApiModelProperty("是否选中")
	private String fIsSelected;
	/** 序号 */
	@ApiModelProperty("序号")
	private Long fSeq;

}
