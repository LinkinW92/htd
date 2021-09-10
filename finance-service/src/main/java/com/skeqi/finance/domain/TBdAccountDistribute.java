package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目分发对象 t_bd_account_distribute
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account_distribute")
public class TBdAccountDistribute implements Serializable {

    private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 科目ID */
    private Integer fAcctId;

    /** 分发组织ID */
    private Integer fDistributeOrgid;

    /** 适用组织ID */
    private Integer fUseOrgid;

    /** 分发人ID */
    private Integer fDistributeorId;

    /** 分发时间 */
    private Date fDistributeDate;

    /** 是否允许新增下级 1：是（默认）0: 否 */
    private String fIsaddChild;

    /** 禁用状态 A：未禁用 B：禁用 */
    private String fForbidStatus;

    /** 第一禁用人 */
    private Integer fForbidderId;

    /** 禁用组织 */
    private Integer fForbidOrgid;

    /** 禁用时间 */
    private Date fForbidDate;

    /** 是否允许再分配 1：是0： 否（默认 */
    private String fIsredistribute;

}
