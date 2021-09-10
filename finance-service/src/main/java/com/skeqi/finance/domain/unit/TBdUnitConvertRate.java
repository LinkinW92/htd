package com.skeqi.finance.domain.unit;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 单位换算对象 t_bd_unit_convert_rate
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_unit_convert_rate")
public class TBdUnitConvertRate implements Serializable {

    private static final long serialVersionUID=1L;


    /** 换算率内码 */
    @TableId(value = "f_unit_convert_rateid")
    private Integer fUnitConvertRateid;

    /** 单据编号 */
    private String fBillNo;

    /** 关联计量单位内码  */
    private Integer fUnitId;

    /** 单据类型 */
    private String fFormid;

    /** 物料代码 */
    private Integer fMaterialId;

    /** 当前单位内码 */
    private Integer fCurrentUnitId;

    /** 目标单位内码 */
    private Integer fDestUnitId;

    /** 换算类型 */
    private String fConvertType;

    /** 换算分子 */
    private BigDecimal fConvertNumerator;

    /** 换算分母 */
    private BigDecimal fConvertDenominator;

    /** 创建组织 */
    private Integer fCreateOrgid;

    /** 创建人 */
    private Integer fCreatorid;

    /** 创建日期 */
    private Date fCreateDate;

    /** 使用组织 */
    private Integer fUseOrgid;

    /** 修改人 */
    private Integer fModifierid;

    /** 修改日期 */
    private Date fModifyDate;

    /** 数据状态 */
    private String fDocumentStatus;

    /** 审核人 */
    private Integer fAuditorid;

    /** 审核日期 */
    private Date fAuditDate;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Integer fForbidderid;

    /** 禁用日期 */
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    private Integer fIssysPreset;

    /** 组织隔离字段 */
    private Integer fMasterId;

}
