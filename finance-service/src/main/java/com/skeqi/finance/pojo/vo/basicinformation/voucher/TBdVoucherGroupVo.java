package com.skeqi.finance.pojo.vo.basicinformation.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 凭证字视图对象 t_bd_voucher_group
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("凭证字视图对象")
public class TBdVoucherGroupVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fVchgroupId;

	/** 限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0
  */
	@Excel(name = "限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0")
	@ApiModelProperty("限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0")
	private String fLimitMulti;

	/** 科目表内码，用于单据体中科目的过滤  */
	@Excel(name = "科目表内码，用于单据体中科目的过滤 ")
	@ApiModelProperty("科目表内码，用于单据体中科目的过滤 ")
	private Integer fAccttableId;

	/** 科目表名称 */
	@Excel(name = " 科目表名称 ")
	@ApiModelProperty("科目表名称")
	private String fAccttableName;


	/** 科目表编码 */
	@Excel(name = " 科目表编码 ")
	@ApiModelProperty("科目表编码")
	private String fAccttableNumber;


	/** 是否默认  0 非默认 1 默认 */
	@Excel(name = "是否默认  0 非默认 1 默认")
	@ApiModelProperty("是否默认  0 非默认 1 默认")
	private String fIsdefault;

	/** 创建组织 */
	@Excel(name = "创建组织")
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/** 使用组织 */
	@Excel(name = "使用组织")
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/** 修改人 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/** 修改日期 */
	@Excel(name = "修改日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/** 审核人 */
	@Excel(name = "审核人")
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/** 审核日期 */
	@Excel(name = "审核日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 禁用人 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/** 禁用日期 */
	@Excel(name = "禁用日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private String fVoucherWords;

	/** 组织隔离字段 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;


}
