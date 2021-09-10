package com.skeqi.finance.pojo.bo.rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 基础汇率分页查询对象 t_bd_rate
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("基础汇率分页查询对象")
public class TBdRateQueryBo extends BaseEntity {

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
	@ApiModelProperty("汇率类型")
	private String fRateTypeName;


	@ApiModelProperty("原币名")
	private String fCyforName;


	@ApiModelProperty("目标币名")
	private String fCytoName;
	/** 汇率类型内码 */
	@ApiModelProperty("汇率类型内码")
	private Integer fRateTypeId;
	/** 原币内码  */
	@ApiModelProperty("原币内码 ")
	private Integer fCyforid;
	/** 目标币内码 */
	@ApiModelProperty("目标币内码")
	private Integer fCytoid;
	/** 直接汇率 */
	@ApiModelProperty("直接汇率")
	private BigDecimal fExchangeRate;
	/** 间接汇率 */
	@ApiModelProperty("间接汇率")
	private BigDecimal fReverseexRate;
	/** 生效日期 */
	@ApiModelProperty("生效日期")
	private Date fBegDate;
	/** 失效日期 */
	@ApiModelProperty("失效日期")
	private Date fEndDate;
	/** 特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见 */
	@ApiModelProperty("特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见")
	private String fNumber;
	/** 组织隔离字段 */
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;
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

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date fDate;

	private Integer fCurrencyId;

}
