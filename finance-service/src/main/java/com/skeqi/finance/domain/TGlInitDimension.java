package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目核算维度初始数据对象 t_gl_init_dimension
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_init_dimension")
public class TGlInitDimension implements Serializable {

    private static final long serialVersionUID=1L;


    /** 科目初始核算维度内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 科目ID */
    private Integer fAcctId;

    /** 账簿ID */
    private Integer fBookId;

    /** 维度内码 */
    private Integer fDimensionId;

    /** 维度名称 */
    private String fDimensionName;

    /** 期初原币金额 */
    private BigDecimal fBeginBalancefor;

    /** 期初本位币金额 */
    private BigDecimal fBeginBalance;

    /** 借方原币金额 */
    private BigDecimal fDebitFor;

    /** 借方本位币金额 */
    private BigDecimal fDebit;

    /** 贷方原币金额 */
    private BigDecimal fCreditFor;

    /** 贷方本位币金额 */
    private BigDecimal fCredit;

    /** 本年累计借方原币金额 */
    private BigDecimal fYtdDebitfor;

    /** 本年累计借方本位币金额 */
    private BigDecimal fYtdDebit;

    /** 本年累计贷方原币金额 */
    private BigDecimal fYtdCreditfor;

    /** 本年累计贷方本位币金额 */
    private BigDecimal fYtdCredit;

    /** 期末原币金额 */
    private BigDecimal fEndBalancefor;

    /** 期末本位币金额 */
    private BigDecimal fEndBalance;

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
