package com.skeqi.finance.pojo.bo.TBdDimensionSource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 维度来源编辑对象 t_bd_dimension_source
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("维度来源编辑对象")
public class TBdDimensionSourceEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
	@NotNull(message = "内码不能为空")
    private Integer fId;

    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
    private String fName;

    /** 类型 1基础资料 2辅助资料 */
    @ApiModelProperty("类型 1基础资料 2辅助资料")
    private String fType;

    /** 策略类型 */
    @ApiModelProperty("策略类型")
    private String fStrategyType;

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
    private String fIssysPreset;

    /** 组织隔离字段 */
    @ApiModelProperty("组织隔离字段")
    private Integer fMasterId;
}
