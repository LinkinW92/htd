package com.skeqi.finance.pojo.bo.TBdAccountType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 科目类别添加对象 t_bd_account_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目类别添加对象")
public class TBdAccountTypeAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
	@NotBlank(message = "编码不能为空")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
	@NotBlank(message = "名称不能为空")
    private String fName;

    /** 会计要素内码 */
    @ApiModelProperty("会计要素内码")
	@NotNull(message = "会计要素不能为空")
    private Integer fAcctGroupId;

    /** 科目表内码  */
    @ApiModelProperty("科目表内码 ")
	@NotNull(message = "科目不能为空")
    private Integer fAcctTableId;

    /** 借贷方向  1:借方 ；-1：贷方
  */
    @ApiModelProperty("借贷方向  1:借方 ；-1：贷方")
	@NotBlank(message = "名称不能为空")
    private String fDc;

    /** 级别 */
    @ApiModelProperty("级别")
    private Integer fLevel;

    /** 上级类别内码 */
    @ApiModelProperty("上级类别内码")
    private Integer fParentId;

    /** 以前年度损益调整  */
    @ApiModelProperty("以前年度损益调整 ")
    private String fPriorplAdjust;

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
