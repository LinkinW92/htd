package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计日历对象 t_bd_account_calendar
 *
 * @author toms
 * @date 2021-07-14
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account_calendar")
public class TBdAccountCalendar implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码  */
    private String fNumber;

    /** 名称 */
    private String fName;

    /** 会计日历开始日期 */
    private Date fStartDate;

    /** 会计日历截止日期 */
    private Date fEndDate;

    /** 期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日  */
    private String fPeriodType;

    /** 起始会计年度：前后控制各50年 */
    private String fStartYear;

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

    /** 主ID 继承与模板,用于组织隔离 */
    private Integer fMasterId;

    /** 期间数  */
    private Integer fPeriodCount;

}
