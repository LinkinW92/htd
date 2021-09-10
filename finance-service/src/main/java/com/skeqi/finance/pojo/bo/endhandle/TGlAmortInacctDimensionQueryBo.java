package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证摊销转入科目维度控制分页查询对象 t_gl_amort_inacct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证摊销转入科目维度控制分页查询对象")
public class TGlAmortInacctDimensionQueryBo extends BaseEntity {

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


	/** 维度ID */
	@ApiModelProperty("维度ID")
	private Long dimensionId;
	/** 维度编码 */
	@ApiModelProperty("维度编码")
	private String dsCode;
	/** 名称 */
	@ApiModelProperty("名称")
	private String dsName;
	/** 摊销关联ID */
	@ApiModelProperty("摊销关联ID")
	private Long amortEntryId;
	/** 科目ID */
	@ApiModelProperty("科目ID")
	private Long acctId;

}
