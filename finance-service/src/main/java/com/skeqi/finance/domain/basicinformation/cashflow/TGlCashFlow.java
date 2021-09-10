package com.skeqi.finance.domain.basicinformation.cashflow;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 现金流量项目-3对象 t_gl_cash_flow
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_cash_flow")
public class TGlCashFlow implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    /** 名称 */
    private String fName;

    /** 描述 */
    private String fDescription;

    /** 上级流量项目 */
    private Integer fParentId;

    /** 级次 */
    private Integer fLevel;

    /** 方向 */
    private Integer fDirection;

    /** 项目类别内码 1:现金流入;-1:现金流出  */
    private Integer fItemTypeid;

    /** 是否明细 1明细 0非明细 */
    private String fIsdetail;

    /** 现金流量项目表 */
    private Integer fCashFlowItemTable;

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
