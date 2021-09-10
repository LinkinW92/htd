package com.skeqi.finance.pojo.vo.transfer;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 自动转账核算维度视图对象 t_gl_auto_transfer_entry_item
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账核算维度视图对象")
public class TGlAutoTransferEntryItemVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fEntryId;

	/** 自动转账分录内码 */
	@Excel(name = "自动转账分录内码")
	@ApiModelProperty("自动转账分录内码")
	private Integer fTransferEntryId;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private Integer fFlexitemPropertyId;

	/** 起始核算维度编码 */
	@Excel(name = "起始核算维度编码")
	@ApiModelProperty("起始核算维度编码")
	private String fBeginItemNumber;

	/** 截止核算维度编码 */
	@Excel(name = "截止核算维度编码")
	@ApiModelProperty("截止核算维度编码")
	private String fEndItemNumber;

	/** 连续范围过滤 */
	@Excel(name = "连续范围过滤")
	@ApiModelProperty("连续范围过滤")
	private String fIssequentSelect;

	/** 项目 */
	@Excel(name = "项目")
	@ApiModelProperty("项目")
	private String fItemNumber;


}
