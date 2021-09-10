package com.skeqi.finance.domain.rate;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 基础汇率对象 t_bd_rate
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_rate")
public class TBdRate implements Serializable {

    private static final long serialVersionUID=1L;


    /** 汇率类型内码 */
    @TableId(value = "f_rate_id",type = IdType.AUTO)
    private Integer fRateId;

    /** 汇率类型内码 */
    private Integer fRateTypeId;

    /** 原币内码  */
    private Integer fCyforid;

    /** 目标币内码 */
    private Integer fCytoid;

    /** 直接汇率 */
    private BigDecimal fExchangeRate;

    /** 间接汇率 */
    private BigDecimal fReverseexRate;

    /** 生效日期 */
    private Date fBegDate;

    /** 失效日期 */
    private Date fEndDate;

    /** 特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见 */
    private String fNumber;

    /** 组织隔离字段 */
    private Integer fMasterId;

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

}
