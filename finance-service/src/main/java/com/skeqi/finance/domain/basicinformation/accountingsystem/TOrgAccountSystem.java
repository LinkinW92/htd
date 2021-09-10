package com.skeqi.finance.domain.basicinformation.accountingsystem;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计核算体系对象 t_org_account_system
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_org_account_system")
public class TOrgAccountSystem implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    /** 名称 */
    private String fName;

    /** 默认核算体系 0否 1是 */
    private String fIsdefaultAcct;

    /** 核算组织ID */
    private Integer fAcctOrgid;

    /** 默认会计政策 */
    private Integer fDefaultAcctpolicyId;

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

    /** 法人核算体系 */
    private String fIscorpoRate;

    /** 描述 */
    private String fDescription;

}
