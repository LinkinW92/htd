package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 自动转账核算维度分页查询对象 t_gl_auto_transfer_entry_item
 *
 * @author toms
 * @date 2021-07-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("自动转账核算维度分页查询对象")
public class TGlAutoTransferEntryItemQueryBo extends BaseEntity {

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


	/** 自动转账分录内码 */
	@ApiModelProperty("自动转账分录内码")
	private Integer fTransferEntryId;
	/** 核算维度 */
	@ApiModelProperty("核算维度")
	private Integer fFlexitemPropertyId;
	/** 起始核算维度编码 */
	@ApiModelProperty("起始核算维度编码")
	private String fBeginItemNumber;
	/** 截止核算维度编码 */
	@ApiModelProperty("截止核算维度编码")
	private String fEndItemNumber;
	/** 连续范围过滤 */
	@ApiModelProperty("连续范围过滤")
	private String fIssequentSelect;
	/** 项目 */
	@ApiModelProperty("项目")
	private String fItemNumber;

}
