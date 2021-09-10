package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证对象 t_bd_credential_param
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_credential_param")
public class TBdCredentialParam implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 允许录入凭证的未来期间数 */
    private Integer fBaseOne;

    /** 凭证过账前必须审核 */
    private String fBaseTow;

    /** 凭证过账前必须出纳复核 */
    private String fBaseThree;

    /** 允许取消复核人与出纳复核人不一致 */
    private String fBaseFour;

    /** 凭证中的汇率允许手工修改 */
    private String fBaseFive;

    /** 凭证录入显示科目全名 */
    private String fBaseSix;

    /** 凭证中显示科目的最新余额 */
    private String fBaseSeven;

    /** 现金流量科目必须输入现金流量项目 */
    private String fCashFlowOne;

    /** 录入凭证时指定现金流量附表项目 */
    private String fCashFlowTwo;

    /** 已审核、已过账凭证不允许修改现金流量 */
    private String fCashFlowThree;

    /** 凭证保存后自动指定现金流量 */
    private String fCashFlowFour;

    /** 1主表 2主表及附表 默认2 */
    private String fCashFlowFive;

    /** 每条凭证分录必须有摘要 */
    private String fDataValidationOne;

    /** 银行存款科目必须输入结算方式和结算号 */
    private String fDataValidationTwo;

    /** 凭证中的单价、数量字段允许为零 */
    private String fDataValidationThree;

    /** 凭证号排列规则 1按年度排列 2按期间排列 默认1 */
    private String fVoucherNumberOne;

    /** 不允许手工修改凭证号 */
    private String fVoucherNumberTwo;

    /** 新增凭证自动填补断号 */
    private String fVoucherNumberThree;

    /** 保存凭证时支持检查凭证号（适用于手工修改凭证号及凭证引入） */
    private String fVoucherNumberFour;

    /** 业务系统生成的总账凭证允许修改 */
    private String fOtherOne;

    /** 业务系统生成的总账凭证允许作废 */
    private String fOtherTwo;

    /** 数量金额核算中，凭证支持跨计量单位组的单位 */
    private String fOtherThree;

    /** 维度录入时部门与员工不关联 */
    private String fOtherFour;

    /** 账簿ID */
    private Integer fBookId;

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
