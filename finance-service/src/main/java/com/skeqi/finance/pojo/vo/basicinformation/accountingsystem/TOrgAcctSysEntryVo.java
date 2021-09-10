package com.skeqi.finance.pojo.vo.basicinformation.accountingsystem;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计核算体系之会计主体视图对象 t_org_acct_sys_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计核算体系之会计主体视图对象")
public class TOrgAcctSysEntryVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 会计核算体系ID */
	@Excel(name = "会计核算体系ID")
	@ApiModelProperty("会计核算体系ID")
	private Integer fAcctsystemId;

	/** 核算组织ID */
	@Excel(name = "核算组织ID")
	@ApiModelProperty("核算组织ID")
	private Integer fAcctOrgid;

	/** 核算组织编码 */
	@Excel(name = "核算组织编码")
	@ApiModelProperty("核算组织编码")
	private String fAcctOrgCode;

	/** 核算组织名称 */
	@Excel(name = "核算组织名称")
	@ApiModelProperty("核算组织名称")
	private String fAcctOrgName;

	/** 默认会计政策 */
	@Excel(name = "默认会计政策")
	@ApiModelProperty("默认会计政策")
	private Integer fDefaultAcctpolicyId;

	/** 适用会计政策 */
	@Excel(name = "适用会计政策")
	@ApiModelProperty("适用会计政策")
	private Integer fAcctpolicyId;

	/** 适用会计政策名称 */
	@Excel(name = "适用会计政策名称")
	@ApiModelProperty("适用会计政策名称")
	private String policyName;

	/** 适用会计政策编码 */
	@Excel(name = "适用会计政策编码")
	@ApiModelProperty("适用会计政策编码")
	private String policyNumber;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;


}
