package com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 会计政策适用核算组织分页查询对象 t_fa_acct_policy_org
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会计政策适用核算组织分页查询对象")
public class TFaAcctPolicyOrgQueryBo extends BaseEntity {

	/**
	 * 分页大小
	 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/**
	 * 排序列
	 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/**
	 * 排序的方向desc或者asc
	 */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/**
	 * 会计政策内码
	 */
	@ApiModelProperty("会计政策内码 ")
	private Integer fAcctpolicyId;
	/**
	 * 序号
	 */
	@ApiModelProperty("序号")
	private Integer fSeq;
	/**
	 * 是否默认 1是 0否
	 */
	@ApiModelProperty("是否默认 1是 0否")
	private String fIsdefault;
	/**
	 * 会计核算体系
	 */
	@ApiModelProperty("会计核算体系")
	private Integer fAcctsystemId;
	/**
	 * 适用核算组织
	 */
	@ApiModelProperty("适用核算组织")
	private Integer fAcctOrgid;
	/**
	 * 适用账簿
	 */
	@ApiModelProperty("适用账簿")
	private String fAcctBook;
	/**
	 * 是否审核
	 */
	@ApiModelProperty("是否审核")
	private String fIsaudit;

}
