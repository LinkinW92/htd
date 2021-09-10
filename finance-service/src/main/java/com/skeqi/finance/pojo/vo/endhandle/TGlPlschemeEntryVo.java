package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 结转损益方案分录视图对象 t_gl_plscheme_entry
 *
 * @author toms
 * @date 2021-08-02
 */
@Data
@ApiModel("结转损益方案分录视图对象")
public class TGlPlschemeEntryVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer fEntryId;

	/** 结转损益方案主键 */
	@Excel(name = "结转损益方案主键")
	@ApiModelProperty("结转损益方案主键")
	private Integer fId;

	/** 待结转科目 */
	@Excel(name = "待结转科目")
	@ApiModelProperty("待结转科目")
	private Integer fAcctId;

	/** 结转科目 */
	@Excel(name = "结转科目")
	@ApiModelProperty("结转科目")
	private Integer fPlacctId;

	/** 是否选中 */
	@Excel(name = "是否选中")
	@ApiModelProperty("是否选中")
	private String fIsSelected;

	/** 序号 */
	@Excel(name = "序号")
	@ApiModelProperty("序号")
	private Integer fSeq;




	private String acctName;

	private String acctNumber;
}
