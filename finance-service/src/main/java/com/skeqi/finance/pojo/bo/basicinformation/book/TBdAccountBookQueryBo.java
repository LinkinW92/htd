package com.skeqi.finance.pojo.bo.basicinformation.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 账簿分页查询对象 t_bd_account_book
 *
 * @author toms
 * @date 2021-07-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("账簿分页查询对象")
public class TBdAccountBookQueryBo extends BaseEntity {

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

    private String sysNumber;
    private String orgName;
    private String sysName;
    private String orgCode;
    private String acctTabName;
	/** 科目表内码 */
	@ApiModelProperty("科目表内码")
	private Long fAccttableId;
	/** 会计日历内码 */
	@ApiModelProperty("会计日历内码")
	private Long fPeriodId;
	/** 币别表内码 */
	@ApiModelProperty("币别表内码")
	private Long fCurrencyId;
	/** 汇率类型内码 */
	@ApiModelProperty("汇率类型内码")
	private Long fRateTypeId;
	/** 核算组织 */
	@ApiModelProperty("核算组织")
	private Long fAccountOrgid;
	/** 核算体系 */
	@ApiModelProperty("核算体系")
	private Long fAcctsystemId;
	/** 引用枚举类型【账簿类型】0 非主账簿1主账簿默认0 */
    @JsonProperty(value = "fBookType")
	@ApiModelProperty("引用枚举类型【账簿类型】0 非主账簿1主账簿默认0")
	private String fBookType;
	/** 默认凭证字 */
	@ApiModelProperty("默认凭证字")
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
	private String fYearandPeriod;
	/** 会计政策内码 */
	@ApiModelProperty("会计政策内码")
	private Long fAcctPolicyid;
	/** 财务应付确认方式 */
	@ApiModelProperty("财务应付确认方式")
	private String fApfinType;
	/** 财务应收确认方式 */
	@ApiModelProperty("财务应收确认方式")
	private String fArfinType;
	/** 创建组织 */
	@ApiModelProperty("创建组织")
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

    private String fBookName;

}
