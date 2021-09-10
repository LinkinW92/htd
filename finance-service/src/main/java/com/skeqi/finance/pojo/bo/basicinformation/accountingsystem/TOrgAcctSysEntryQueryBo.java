package com.skeqi.finance.pojo.bo.basicinformation.accountingsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 会计核算体系之会计主体分页查询对象 t_org_acct_sys_entry
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会计核算体系之会计主体分页查询对象")
public class TOrgAcctSysEntryQueryBo extends BaseEntity {

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


	/** 会计核算体系ID */
	@ApiModelProperty("会计核算体系ID")
	private Integer fAcctsystemId;
	/** 核算组织ID */
	@ApiModelProperty("核算组织ID")
	private Integer fAcctOrgid;
	/** 默认会计政策 */
	@ApiModelProperty("默认会计政策")
	private Integer fDefaultAcctpolicyId;
	/** 适用会计政策 */
	@ApiModelProperty("适用会计政策")
	private Integer fAcctpolicyId;
	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;

}
