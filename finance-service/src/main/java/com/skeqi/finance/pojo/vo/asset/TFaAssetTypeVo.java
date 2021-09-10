package com.skeqi.finance.pojo.vo.asset;

import com.skeqi.common.annotation.Excel;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 资产类别视图对象 t_fa_asset_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("资产类别视图对象")
public class TFaAssetTypeVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 资产类别分组 */
	@Excel(name = "资产类别分组")
	@ApiModelProperty("资产类别分组")
	private Integer fGroup;

	/** 资产类别分组名称 */
	@Excel(name = "资产类别分组名称")
	@ApiModelProperty("资产类别分组名称")
	private String fAssetTypeGroupName;

	/** 资产类别分组编码*/
	@Excel(name = "资产类别分组编码")
	@ApiModelProperty("资产类别分组编码")
	private String fAssetTypeGroupNumber;

	/** 卡片编码规则 */
	@Excel(name = "卡片编码规则")
	@ApiModelProperty("卡片编码规则")
	private String fCodeRule;

	/** 资产编码规则 */
	@Excel(name = "资产编码规则")
	@ApiModelProperty("资产编码规则")
	private String fAssetCodeRule;

	/** 默认计量单位 */
	@Excel(name = "默认计量单位")
	@ApiModelProperty("默认计量单位")
	private Integer fUnitId;

	/** 默认计量单位名称 */
	@Excel(name = "默认计量单位名称")
	@ApiModelProperty("默认计量单位名称")
	private String fUnitName;


	/** 原值包含进税项 1：包含 0不包含 */
	@Excel(name = "原值包含进税项 1：包含 0不包含")
	@ApiModelProperty("原值包含进税项 1：包含 0不包含")
	private String fIncludeTax;

	/** 卡片编码流水号长度 */
	@Excel(name = "卡片编码流水号长度")
	@ApiModelProperty("卡片编码流水号长度")
	private Integer fCardSwiftNumber;

	/** 资产编码流水号长度 */
	@Excel(name = "资产编码流水号长度")
	@ApiModelProperty("资产编码流水号长度")
	private Integer fAssetSwiftNumber;

	/** 卡片编码规则ID */
	@Excel(name = "卡片编码规则ID")
	@ApiModelProperty("卡片编码规则ID")
	private String fCodeRuleId;

	/** 资产编码规则ID */
	@Excel(name = "资产编码规则ID")
	@ApiModelProperty("资产编码规则ID")
	private String fAssetCodeRuleid;

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
