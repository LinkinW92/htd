package com.skeqi.finance.pojo.bo.basicinformation.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 账簿添加对象 t_bd_account_book
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("账簿添加对象")
public class TBdAccountBookAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
	@NotBlank(message = "编码不能为空")
    private String fNumber;

    /** 科目表内码 */
    @ApiModelProperty("科目表")
	@NotNull(message = "科目表不能为空")
    private Long fAccttableId;

	/** 名称 */
	@ApiModelProperty("名称")
	@NotBlank(message = "账簿名称不能为空")
	private String fBookName;

    /** 会计日历内码 */
    @ApiModelProperty("会计日历内码")
	@NotNull(message = "会计日历不能为空")
    private Long fPeriodId;

    /** 币别表内码 */
    @ApiModelProperty("币别表内码")
	@NotNull(message = "币别不能为空")
    private Long fCurrencyId;

    /** 汇率类型内码 */
    @ApiModelProperty("汇率类型内码")
	@NotNull(message = "汇率类型不能为空")
    private Long fRateTypeId;

    /** 核算组织 */
    @ApiModelProperty("核算组织")
	@NotNull(message = "核算组织不能为空")
    private Long fAccountOrgid;

    /** 核算体系 */
    @ApiModelProperty("核算体系")
	@NotNull(message = "核算体系不能为空")
    private Long fAcctsystemId;

    /** 引用枚举类型【账簿类型】0 非主账簿1主账簿默认0 */
    @ApiModelProperty("引用枚举类型【账簿类型】0 非主账簿1主账簿默认0")
	@NotBlank(message = "账簿类型不能为空")
    private String fBookType;

    /** 默认凭证字 */
    @ApiModelProperty("默认凭证字")
	@NotNull(message = "凭证字不能为空")
    private Long fDefaultVoucherType;

    /** 启用调整期 */
    @ApiModelProperty("启用调整期")
    private String fUseAdjustPeriod;

    /** 初始化状态 */
    @ApiModelProperty("初始化状态")
    private String fInitialStatus;

    /** 当前年度 */
    @ApiModelProperty("当前年度")
    private Integer fCurrentYear;

    /** 当前期间 */
    @ApiModelProperty("当前期间")
    private Long fCurrentPeriod;

    /** 当前年度期间 */
    @ApiModelProperty("当前年度期间")
    private String fCrtYearPeriod;

    /** 启用年度 */
    @ApiModelProperty("启用年度")
    private Integer fStartYear;

    /** 启用期间 */
    @ApiModelProperty("启用期间")
    private Integer fStartPeriod;

    /** 启用年度期间 */
    @ApiModelProperty("启用年度期间")
	@NotBlank(message = "启用期间不能为空")
    private String fYearandPeriod;

    /** 会计政策内码 */
    @ApiModelProperty("会计政策内码")
	@NotNull(message = "会计政策不能为空")
    private Long fAcctPolicyid;

    /** 财务应付确认方式 */
    @ApiModelProperty("财务应付确认方式")
	@NotBlank(message = "财务应付确认方式不能为空")
    private String fApfinType;

    /** 财务应收确认方式 */
    @ApiModelProperty("财务应收确认方式")
	@NotBlank(message = "财务应收确认方式不能为空")
    private String fArfinType;

    /** 创建组织 */
    @ApiModelProperty("创建组织")
	@NotNull(message = "创建组织不能为空")
    private Long fCreateOrgid;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Long fCreatorid;

    /** 创建日期 */
    @ApiModelProperty("创建日期")
    private Date fCreateDate;

    /** 使用组织 */
    @ApiModelProperty("使用组织")
    private Long fUseOrgid;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private Long fModifierid;

    /** 修改日期 */
    @ApiModelProperty("修改日期")
    private Date fModifyDate;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    private String fDocumentStatus;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private Long fAuditorid;

    /** 审核日期 */
    @ApiModelProperty("审核日期")
    private Date fAuditDate;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    private Long fForbidderid;

    /** 禁用日期 */
    @ApiModelProperty("禁用日期")
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    @ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
    private Integer fIssysPreset;

    /** 组织隔离字段 */
    @ApiModelProperty("组织隔离字段")
    private Long fMasterId;
}
