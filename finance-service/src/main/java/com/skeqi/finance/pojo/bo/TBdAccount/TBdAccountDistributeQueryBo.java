package com.skeqi.finance.pojo.bo.TBdAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 科目分发分页查询对象 t_bd_account_distribute
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("科目分发分页查询对象")
public class TBdAccountDistributeQueryBo extends BaseEntity {

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


	/** 科目ID */
	@ApiModelProperty("科目ID")
	private Integer fAcctId;
	/** 分发组织ID */
	@ApiModelProperty("分发组织ID")
	private Integer fDistributeOrgid;
	/** 适用组织ID */
	@ApiModelProperty("适用组织ID")
	private Integer fUseOrgid;
	/** 分发人ID */
	@ApiModelProperty("分发人ID")
	private Integer fDistributeorId;
	/** 分发时间 */
	@ApiModelProperty("分发时间")
	private Date fDistributeDate;
	/** 是否允许新增下级 1：是（默认）0: 否 */
	@ApiModelProperty("是否允许新增下级 1：是（默认）0: 否")
	private String fIsaddChild;
	/** 禁用状态 A：未禁用 B：禁用 */
	@ApiModelProperty("禁用状态 A：未禁用 B：禁用")
	private String fForbidStatus;
	/** 第一禁用人 */
	@ApiModelProperty("第一禁用人")
	private Integer fForbidderId;
	/** 禁用组织 */
	@ApiModelProperty("禁用组织")
	private Integer fForbidOrgid;
	/** 禁用时间 */
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;
	/** 是否允许再分配 1：是0： 否（默认 */
	@ApiModelProperty("是否允许再分配 1：是0： 否（默认")
	private String fIsredistribute;

}
