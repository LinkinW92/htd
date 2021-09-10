package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 科目类别视图对象 t_bd_account_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目类别视图对象")
public class TBdAccountTypeVo {

	private static final long serialVersionUID = 1L;

	//region 关联会计要素表
	/**
	 * 科目表名称
	 */
	@Excel(name = "科目表名称")
	@ApiModelProperty("科目表名称")
	private String fAcctTableName;
	/**
	 * 会计要素名称
	 */
	@Excel(name = "会计要素名称")
	@ApiModelProperty("名称")
	private String fAcctgroupName;
	//endregion


	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fAcctTypeId;

	/**
	 * 编码
	 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/**
	 * 名称
	 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/**
	 * 会计要素内码
	 */
	@Excel(name = "会计要素内码")
	@ApiModelProperty("会计要素内码")
	private Integer fAcctGroupId;

	/**
	 * 科目表内码
	 */
	@Excel(name = "科目表内码 ")
	@ApiModelProperty("科目表内码 ")
	private Integer fAcctTableId;

	/**
	 * 借贷方向  1:借方 ；-1：贷方
	 */
	@Excel(name = "借贷方向  1:借方 ；-1：贷方")
	@ApiModelProperty("借贷方向  1:借方 ；-1：贷方")
	private String fDc;

	/**
	 * 级别
	 */
	@Excel(name = "级别")
	@ApiModelProperty("级别")
	private Integer fLevel;

	/**
	 * 上级类别内码
	 */
	@Excel(name = "上级类别内码")
	@ApiModelProperty("上级类别内码")
	private Integer fParentId;

	/**
	 * 以前年度损益调整
	 */
	@Excel(name = "以前年度损益调整 ")
	@ApiModelProperty("以前年度损益调整 ")
	private String fPriorplAdjust;

	/**
	 * 创建组织
	 */
	@Excel(name = "创建组织")
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/**
	 * 创建人
	 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/**
	 * 创建日期
	 */
	@Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/**
	 * 使用组织
	 */
	@Excel(name = "使用组织")
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/**
	 * 修改人
	 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/**
	 * 修改日期
	 */
	@Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/**
	 * 数据状态
	 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/**
	 * 审核人
	 */
	@Excel(name = "审核人")
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/**
	 * 审核日期
	 */
	@Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/**
	 * 禁用状态
	 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/**
	 * 禁用人
	 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/**
	 * 禁用日期
	 */
	@Excel(name = "禁用日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/**
	 * 是否系统预设1 系统预设0 非系统预设默认0
	 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/**
	 * 组织隔离字段
	 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;


}
