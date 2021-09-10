package com.skeqi.finance.pojo.vo.basicinformation.accountingsystem;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计核算体系之下级组织视图对象 t_org_acct_sys_detail
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计核算体系之下级组织视图对象")
public class TOrgAcctSysDetailVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 会计核算体系之会计主体ID */
	@Excel(name = "会计核算体系之会计主体ID")
	@ApiModelProperty("会计核算体系之会计主体ID")
	private Integer fAcctsysentryId;

	/** 下级核算组织ID */
	@Excel(name = "下级核算组织ID")
	@ApiModelProperty("下级核算组织ID")
	private Integer fAcctOrgid;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;


}
