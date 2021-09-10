package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目对象 t_bd_account_table
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account_table")
public class TBdAccountTable extends BaseDomain implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 名称 */
    private String fName;

    /** 描述 */
    private String fDescription;

    /** 会计要素表内码  */
    private Integer fAcctGroupTblid;

    /** 编码 */
    private String fNumber;

    /** 最大科目等级 最大6 ，0表示不限制 */
    private Integer fMaxGrade;

    /** 是否启用管控 1是 0否 ，启用管控则数据为分配型，否则为共享型 */
    private String fIsuseControl;

    /** 创建组织 */
    private Integer fCreateOrgid;

    /** 创建人 */
    private Integer fCreatorid;

    /** 创建日期 */
    private Date fCreateDate;

    /** 使用组织,管控组织 */
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
    private String fIssysPreset;

    /** 组织隔离字段 */
    private Integer fMasterId;

}
