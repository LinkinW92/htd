package com.skeqi.finance.pojo.bo.rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;


import java.math.BigDecimal;

/**
 * 基础汇率添加对象 t_bd_rate
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("基础汇率添加对象")
public class TBdRateAddBo {


    /** 汇率类型内码 */
    @ApiModelProperty("汇率类型内码")
	@NotNull(message = "汇率类型不能为空")
    private Integer fRateTypeId;

    /** 原币内码  */
    @ApiModelProperty("原币内码 ")
	@NotNull(message = "原币不能为空")
    private Integer fCyforid;

    /** 目标币内码 */
    @ApiModelProperty("目标币内码")
	@NotNull(message = "目标币不能为空")
    private Integer fCytoid;

    /** 直接汇率 */
    @ApiModelProperty("直接汇率")
	@DecimalMin(value = "0", message = "直接汇率不能低于0")
    private BigDecimal fExchangeRate;

    /** 间接汇率 */
    @ApiModelProperty("间接汇率")
	@DecimalMin(value = "0", message = "间接汇率不能低于0")
    private BigDecimal fReverseexRate;

	/** 生效日期 */
	@ApiModelProperty("生效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date fBegDate;

	/** 失效日期 */
	@ApiModelProperty("失效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date fEndDate;

    /** 特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见 */
    @ApiModelProperty("特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见")
    private String fNumber;

    /** 组织隔离字段 */
    @ApiModelProperty("组织隔离字段")
    private Integer fMasterId;

    /** 创建组织 */
    @ApiModelProperty("创建组织")
	@NotNull(message = "创建组织不能为空")
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
