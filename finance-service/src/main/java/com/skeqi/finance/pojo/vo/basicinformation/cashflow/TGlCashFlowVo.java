package com.skeqi.finance.pojo.vo.basicinformation.cashflow;

import com.skeqi.common.annotation.Excel;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 现金流量项目-3视图对象 t_gl_cash_flow
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("现金流量项目-3视图对象")
public class TGlCashFlowVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 名称 */
	@Excel(name = "上级现金流量项目名称")
	@ApiModelProperty("上级现金流量项目名称")
	private String fParentName;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 上级流量项目 */
	@Excel(name = "上级流量项目")
	@ApiModelProperty("上级流量项目")
	private Integer fParentId;

	/** 级次 */
	@Excel(name = "级次")
	@ApiModelProperty("级次")
	private Integer fLevel;

	/** 方向 */
	@Excel(name = "方向")
	@ApiModelProperty("方向")
	private Integer fDirection;

	/** 项目类别内码 1:现金流入;-1:现金流出  */
	@Excel(name = "项目类别内码 1:现金流入;-1:现金流出 ")
	@ApiModelProperty("项目类别内码 1:现金流入;-1:现金流出 ")
	private Integer fItemTypeid;

	/** 是否明细 1明细 0非明细 */
	@Excel(name = "是否明细 1明细 0非明细")
	@ApiModelProperty("是否明细 1明细 0非明细")
	private String fIsdetail;

	/** 现金流量项目表 */
	@Excel(name = "现金流量项目表")
	@ApiModelProperty("现金流量项目表")
	private Integer fCashFlowItemTable;

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


	/** 类别属性 主表项目 = 1，附表项目 = 2 */
	@ApiModelProperty("类别属性 主表项目 = 1，附表项目 = 2")
	private Integer fItemGroupid;


}
