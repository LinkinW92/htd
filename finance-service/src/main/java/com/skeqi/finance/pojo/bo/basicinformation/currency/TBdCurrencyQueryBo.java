package com.skeqi.finance.pojo.bo.basicinformation.currency;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 基础-币别分页查询对象 t_bd_currency
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("基础-币别分页查询对象")
public class TBdCurrencyQueryBo extends BaseEntity {

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


	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
	/** 币别编码  */
	@ApiModelProperty("币别编码 ")
	private String fNumber;
	/** 货币代码  */
	@ApiModelProperty("货币代码 ")
	private String fCode;
	/** 货币符号 */
	@ApiModelProperty("货币符号")
	private String fSysmbol;
	/** 单价精度  */
	@ApiModelProperty("单价精度 ")
	private Integer fPricedigits;
	/** 金额精度  */
	@ApiModelProperty("金额精度 ")
	private Integer fAmountdigits;
	/** 优先级 */
	@ApiModelProperty("优先级")
	private Integer fPriority;
	/** 是否中间币  0不是 1是 */
	@ApiModelProperty("是否中间币  0不是 1是")
	private String fIstrans;
	/** 基类主标识  */
	@ApiModelProperty("基类主标识 ")
	private Integer fMasterId;
	/** 显示货币符号  */
	@ApiModelProperty("显示货币符号 ")
	private String fIsshowCsymbol;
	/** 货币符号内码 */
	@ApiModelProperty("货币符号内码")
	private String fCurrencySymbolid;
	/** 显示顺序 */
	@ApiModelProperty("显示顺序")
	private String fFormatOrder;
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

}
