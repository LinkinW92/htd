package com.skeqi.finance.pojo.bo.basicinformation.cashflow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 现金流量项目-3编辑对象 t_gl_cash_flow
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("现金流量项目-3编辑对象")
public class TGlCashFlowEditBo {


    /** 内码 */
	@NotNull(message = "内码不能为空")
    @ApiModelProperty("内码")
    private Integer fId;

	/** 编码 */
	@NotBlank(message = "编码不能为空")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@NotBlank(message = "名称不能为空")
	@ApiModelProperty("名称")
	private String fName;

	/** 描述 */
	@NotBlank(message = "描述不能为空")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 上级流量项目 */
	@NotNull(message = "上级流量项目不能为空")
	@ApiModelProperty("上级流量项目")
	private Integer fParentId;

	/** 级次 */
	@ApiModelProperty("级次")
	private Integer fLevel;

	/** 方向 */
	@ApiModelProperty("方向")
	private Integer fDirection;

	/** 项目类别内码 1:现金流入;-1:现金流出  */
	@NotNull(message = "方向不能为空")
	@ApiModelProperty("项目类别内码 1:现金流入;-1:现金流出 ")
	private Integer fItemTypeid;

    /** 是否明细 1明细 0非明细 */
    @ApiModelProperty("是否明细 1明细 0非明细")
    private String fIsdetail;

    /** 现金流量项目表 */
    @ApiModelProperty("现金流量项目表")
    private Integer fCashFlowItemTable;

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
