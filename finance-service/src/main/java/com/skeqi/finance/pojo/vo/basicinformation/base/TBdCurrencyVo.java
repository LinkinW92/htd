package com.skeqi.finance.pojo.vo.basicinformation.base;

import com.skeqi.common.annotation.Excel;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 基础-币别视图对象 t_bd_currency
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("基础-币别视图对象")
public class TBdCurrencyVo {

	private static final long serialVersionUID = 1L;

	/** 内码  */
	@ApiModelProperty("内码 ")
	private Integer fCurrencyId;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 币别编码  */
	@Excel(name = "币别编码 ")
	@ApiModelProperty("币别编码 ")
	private String fNumber;

	/** 货币代码  */
	@Excel(name = "货币代码 ")
	@ApiModelProperty("货币代码 ")
	private String fCode;

	/** 货币符号 */
	@Excel(name = "货币符号")
	@ApiModelProperty("货币符号")
	private String fSysmbol;

	/** 单价精度  */
	@Excel(name = "单价精度 ")
	@ApiModelProperty("单价精度 ")
	private Integer fPricedigits;

	/** 金额精度  */
	@Excel(name = "金额精度 ")
	@ApiModelProperty("金额精度 ")
	private Integer fAmountdigits;

	/** 优先级 */
	@Excel(name = "优先级")
	@ApiModelProperty("优先级")
	private Integer fPriority;

	/** 是否中间币  0不是 1是 */
	@Excel(name = "是否中间币  0不是 1是")
	@ApiModelProperty("是否中间币  0不是 1是")
	private String fIstrans;

	/** 基类主标识  */
	@Excel(name = "基类主标识 ")
	@ApiModelProperty("基类主标识 ")
	private Integer fMasterId;

	/** 显示货币符号  */
	@Excel(name = "显示货币符号 ")
	@ApiModelProperty("显示货币符号 ")
	private String fIsshowCsymbol;

	/** 货币符号内码 */
	@Excel(name = "货币符号内码")
	@ApiModelProperty("货币符号内码")
	private String fCurrencySymbolid;

	/** 显示顺序 */
	@Excel(name = "显示顺序")
	@ApiModelProperty("显示顺序")
	private String fFormatOrder;

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

	@ApiModelProperty("舍入类型 1四舍五入  2四舍六入五双")
	private String fSettleType;
}
