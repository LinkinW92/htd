package com.skeqi.finance.pojo.bo.asset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 资产类别分页查询对象 t_fa_asset_type
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资产类别分页查询对象")
public class TFaAssetTypeQueryBo extends BaseEntity {

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


	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;
	/** 资产类别分组 */
	@ApiModelProperty("资产类别分组")
	private Integer fGroup;
	/** 卡片编码规则 */
	@ApiModelProperty("卡片编码规则")
	private String fCodeRule;
	/** 资产编码规则 */
	@ApiModelProperty("资产编码规则")
	private String fAssetCodeRule;
	/** 默认计量单位 */
	@ApiModelProperty("默认计量单位")
	private Integer fUnitId;
	/** 原值包含进税项 1：包含 0不包含 */
	@ApiModelProperty("原值包含进税项 1：包含 0不包含")
	private String fIncludeTax;
	/** 卡片编码流水号长度 */
	@ApiModelProperty("卡片编码流水号长度")
	private Integer fCardSwiftNumber;
	/** 资产编码流水号长度 */
	@ApiModelProperty("资产编码流水号长度")
	private Integer fAssetSwiftNumber;
	/** 卡片编码规则ID */
	@ApiModelProperty("卡片编码规则ID")
	private String fCodeRuleId;
	/** 资产编码规则ID */
	@ApiModelProperty("资产编码规则ID")
	private String fAssetCodeRuleid;
	/** 创建组织 */
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;
	/** 创建人 */
	@ApiModelProperty("创建人")
	private Integer fCreatorid;
	/** 创建日期 */
	@ApiModelProperty("创建日期")
	private Date fCreateDate;
	/** 使用组织 */
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;
	/** 修改人 */
	@ApiModelProperty("修改人")
	private Integer fModifierid;
	/** 修改日期 */
	@ApiModelProperty("修改日期")
	private Date fModifyDate;
	/** 数据状态 */
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;
	/** 审核人 */
	@ApiModelProperty("审核人")
	private Integer fAuditorid;
	/** 审核日期 */
	@ApiModelProperty("审核日期")
	private Date fAuditDate;
	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;
	/** 禁用人 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;
	/** 禁用日期 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;
	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;
	/** 组织隔离字段 */
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;

}
