package com.skeqi.finance.domain.basicinformation.accountingsystem;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计核算体系之会计主体对象 t_org_acct_sys_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_org_acct_sys_entry")
public class TOrgAcctSysEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 会计核算体系ID */
    private Integer fAcctsystemId;

    /** 核算组织ID */
    private Integer fAcctOrgid;

    /** 默认会计政策 */
    private Integer fDefaultAcctpolicyId;

    /** 适用会计政策 */
    private Integer fAcctpolicyId;

    /** 描述 */
    private String fDescription;

}
