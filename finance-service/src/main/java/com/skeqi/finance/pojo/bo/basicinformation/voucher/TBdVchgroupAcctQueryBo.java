package com.skeqi.finance.pojo.bo.basicinformation.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证字-科目控制分页查询对象 t_bd_vchgroup_acct
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证字-科目控制分页查询对象")
public class TBdVchgroupAcctQueryBo extends BaseEntity {

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


	/** 凭证字内码 */
	@ApiModelProperty("凭证字内码")
	private Integer fVchgroupId;
	/** 科目编码 */
	@ApiModelProperty("科目编码")
	private String fAcctNumber;
	/** 借方必有 1：必有 0非必有 */
	@ApiModelProperty("借方必有 1：必有 0非必有")
	private String fDebit;
	/** 贷方必有 1：必有 0非必有 */
	@ApiModelProperty("贷方必有 1：必有 0非必有")
	private String fCredit;
	/** 借或贷必有 1：必有 0非必有 */
	@ApiModelProperty("借或贷必有 1：必有 0非必有")
	private String fDebitCredit;
	/** 借方必无  1：必有 0非必有 */
	@ApiModelProperty("借方必无  1：必有 0非必有")
	private String fDebitNo;
	/** 贷方必无 1：必有 0非必有 */
	@ApiModelProperty("贷方必无 1：必有 0非必有")
	private String fCreditNo;
	/** 借和贷必无 1：必有 0非必有 */
	@ApiModelProperty("借和贷必无 1：必有 0非必有")
	private String fDebitCreditNo;

}
