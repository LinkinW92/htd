package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 科目核算维度初始数据视图对象 t_gl_init_dimension
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目核算维度初始数据视图对象")
public class TGlInitDimensionVo {

	private static final long serialVersionUID = 1L;

	/** 科目初始核算维度内码 */
	@ApiModelProperty("科目初始核算维度内码")
	private Integer fId;

	/** 科目ID */
	@Excel(name = "科目ID")
	@ApiModelProperty("科目ID")
	private Integer fAcctId;

	/** 账簿ID */
	@Excel(name = "账簿ID")
	@ApiModelProperty("账簿ID")
	private Integer fBookId;

	/** 维度内码 */
	@Excel(name = "维度内码")
	@ApiModelProperty("维度内码")
	private Integer fDimensionId;

	/** 维度名称 */
	@Excel(name = "维度名称")
	@ApiModelProperty("维度名称")
	private String fDimensionName;

	/** 期初原币金额 */
	@Excel(name = "期初原币金额")
	@ApiModelProperty("期初原币金额")
	private BigDecimal fBeginBalancefor;

	/** 期初本位币金额 */
	@Excel(name = "期初本位币金额")
	@ApiModelProperty("期初本位币金额")
	private BigDecimal fBeginBalance;

	/** 借方原币金额 */
	@Excel(name = "借方原币金额")
	@ApiModelProperty("借方原币金额")
	private BigDecimal fDebitFor;

	/** 借方本位币金额 */
	@Excel(name = "借方本位币金额")
	@ApiModelProperty("借方本位币金额")
	private BigDecimal fDebit;

	/** 贷方原币金额 */
	@Excel(name = "贷方原币金额")
	@ApiModelProperty("贷方原币金额")
	private BigDecimal fCreditFor;

	/** 贷方本位币金额 */
	@Excel(name = "贷方本位币金额")
	@ApiModelProperty("贷方本位币金额")
	private BigDecimal fCredit;

	/** 本年累计借方原币金额 */
	@Excel(name = "本年累计借方原币金额")
	@ApiModelProperty("本年累计借方原币金额")
	private BigDecimal fYtdDebitfor;

	/** 本年累计借方本位币金额 */
	@Excel(name = "本年累计借方本位币金额")
	@ApiModelProperty("本年累计借方本位币金额")
	private BigDecimal fYtdDebit;

	/** 本年累计贷方原币金额 */
	@Excel(name = "本年累计贷方原币金额")
	@ApiModelProperty("本年累计贷方原币金额")
	private BigDecimal fYtdCreditfor;

	/** 本年累计贷方本位币金额 */
	@Excel(name = "本年累计贷方本位币金额")
	@ApiModelProperty("本年累计贷方本位币金额")
	private BigDecimal fYtdCredit;

	/** 期末原币金额 */
	@Excel(name = "期末原币金额")
	@ApiModelProperty("期末原币金额")
	private BigDecimal fEndBalancefor;

	/** 期末本位币金额 */
	@Excel(name = "期末本位币金额")
	@ApiModelProperty("期末本位币金额")
	private BigDecimal fEndBalance;

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

	/** 组织隔离字段 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;


}
