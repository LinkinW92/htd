package com.skeqi.finance.pojo.bo.help;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 辅助资料类别添加对象 t_bd_help_type
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("辅助资料类别添加对象")
public class TBdHelpTypeAddBo {


    /** 业务领域 */
    @ApiModelProperty("业务领域")
	@NotNull(message = "业务领域不能为空")
    private Integer fBusinessArea;

	/** 编码 */
	@ApiModelProperty("编码")
	@NotBlank(message = "编码不能为空")
	private String fNumber;

    /** 类别名称 */
    @ApiModelProperty("类别名称")
	@NotBlank(message = "类别名称不能为空")
    private String fTypeName;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;

    /** 上级ID */
    @ApiModelProperty("上级ID")
	@NotNull(message = "上级ID不能为空")
    private Integer fParentId;

    /** 创建组织 */
    @ApiModelProperty("创建组织")
    private Long fCreateOrgid;

    /** 使用组织 */
    @ApiModelProperty("使用组织")
    private Long fUseOrgid;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Long fCreatorid;

    /** 创建日期 */
    @ApiModelProperty("创建日期")
    private Date fCreateDate;

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
