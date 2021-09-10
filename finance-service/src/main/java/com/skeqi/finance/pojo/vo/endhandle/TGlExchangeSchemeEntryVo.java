package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 期末调汇方案分录视图对象 t_gl_exchange_scheme_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇方案分录视图对象")
public class TGlExchangeSchemeEntryVo {

	private static final long serialVersionUID = 1L;

	/** 分录主键 */
	@ApiModelProperty("分录主键")
	private Long fEntryId;

	/** 期末调汇方案主键 */
	@Excel(name = "期末调汇方案主键")
	@ApiModelProperty("期末调汇方案主键")
	private Long fId;

	/** 待调汇科目 */
	@Excel(name = "待调汇科目")
	@ApiModelProperty("待调汇科目")
	private Long fAcctId;

	/** 是否选中 */
	@Excel(name = "是否选中")
	@ApiModelProperty("是否选中")
	private String fIsSelected;

	/** 序号 */
	@Excel(name = "序号")
	@ApiModelProperty("序号")
	private Long fseq;

	private String acctNumber;
	private String acctName;



}
