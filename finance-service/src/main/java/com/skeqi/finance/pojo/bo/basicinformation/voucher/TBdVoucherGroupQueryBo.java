package com.skeqi.finance.pojo.bo.basicinformation.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 凭证字分页查询对象 t_bd_voucher_group
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证字分页查询对象")
public class TBdVoucherGroupQueryBo extends BaseEntity {

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
	 * 限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0
	 */
	@ApiModelProperty("限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0")
	private String fLimitMulti;
	/**
	 * 科目表内码，用于单据体中科目的过滤
	 */
	@ApiModelProperty("科目表内码，用于单据体中科目的过滤 ")
	private Integer fAccttableId;
	/**
	 * 是否默认  0 非默认 1 默认
	 */
	@ApiModelProperty("是否默认  0 非默认 1 默认")
	private String fIsdefault;
	/**
	 * 创建组织
	 */
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;
	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
	private Integer fCreatorid;
	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	private Date fCreateDate;
	/**
	 * 使用组织
	 */
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;
	/**
	 * 修改人
	 */
	@ApiModelProperty("修改人")
	private Integer fModifierid;
	/**
	 * 修改日期
	 */
	@ApiModelProperty("修改日期")
	private Date fModifyDate;
	/**
	 * 数据状态
	 */
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;
	/**
	 * 审核人
	 */
	@ApiModelProperty("审核人")
	private Integer fAuditorid;
	/**
	 * 审核日期
	 */
	@ApiModelProperty("审核日期")
	private Date fAuditDate;
	/**
	 * 禁用状态
	 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;
	/**
	 * 禁用人
	 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;
	/**
	 * 禁用日期
	 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;
	/**
	 * 是否系统预设1 系统预设0 非系统预设默认0
	 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/** 凭证字 */
	@ApiModelProperty("凭证字")
	private String fVoucherWords;
	/**
	 * 组织隔离字段
	 */
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;

}
