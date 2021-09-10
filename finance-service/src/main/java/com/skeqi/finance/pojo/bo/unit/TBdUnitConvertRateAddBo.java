package com.skeqi.finance.pojo.bo.unit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


import java.math.BigDecimal;

/**
 * 单位换算添加对象 t_bd_unit_convert_rate
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("单位换算添加对象")
public class TBdUnitConvertRateAddBo {


    /** 单据编号 */
    @ApiModelProperty("单据编号")
    private String fBillNo;

    /** 关联计量单位内码  */
    @ApiModelProperty("关联计量单位内码 ")
    private Integer fUnitId;

    /** 单据类型 */
    @ApiModelProperty("单据类型")
    private String fFormid;

    /** 物料代码 */
    @ApiModelProperty("物料代码")
    private Integer fMaterialId;

    /** 当前单位内码 */
    @ApiModelProperty("当前单位内码")
    private Integer fCurrentUnitId;

    /** 目标单位内码 */
    @ApiModelProperty("目标单位内码")
    private Integer fDestUnitId;

    /** 换算类型 */
    @ApiModelProperty("换算类型")
    private String fConvertType;

    /** 换算分子 */
    @ApiModelProperty("换算分子")
    private BigDecimal fConvertNumerator;

    /** 换算分母 */
    @ApiModelProperty("换算分母")
    private BigDecimal fConvertDenominator;

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
