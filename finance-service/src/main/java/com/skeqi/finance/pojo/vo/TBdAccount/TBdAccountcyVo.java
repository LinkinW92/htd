package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 科目核算币别视图对象 t_bd_accountcy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目核算币别视图对象")
public class TBdAccountcyVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fCurrencyId;

	/** 分录内码 */
	@Excel(name = "分录内码")
	@ApiModelProperty("分录内码")
	private Integer fEntryId;

	/** 科目内码 */
	@Excel(name = "科目内码")
	@ApiModelProperty("科目内码")
	private Integer fAcctId;

	/** 显示顺序  */
	@Excel(name = "显示顺序 ")
	@ApiModelProperty("显示顺序 ")
	private Integer fSeq;


}
