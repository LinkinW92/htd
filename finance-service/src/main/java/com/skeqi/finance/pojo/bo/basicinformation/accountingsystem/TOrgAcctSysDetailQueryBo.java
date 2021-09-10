package com.skeqi.finance.pojo.bo.basicinformation.accountingsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 会计核算体系之下级组织分页查询对象 t_org_acct_sys_detail
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会计核算体系之下级组织分页查询对象")
public class TOrgAcctSysDetailQueryBo extends BaseEntity {

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


	/** 会计核算体系之会计主体ID */
	@ApiModelProperty("会计核算体系之会计主体ID")
	private Integer fAcctsysentryId;
	/** 下级核算组织ID */
	@ApiModelProperty("下级核算组织ID")
	private Integer fAcctOrgid;
	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;

}
