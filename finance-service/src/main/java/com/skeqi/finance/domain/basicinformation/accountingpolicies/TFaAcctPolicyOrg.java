package com.skeqi.finance.domain.basicinformation.accountingpolicies;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计政策适用核算组织对象 t_fa_acct_policy_org
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_acct_policy_org")
public class TFaAcctPolicyOrg implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 会计政策内码  */
    private Integer fAcctpolicyId;

    /** 序号 */
    private Integer fSeq;

    /** 是否默认 1是 0否 */
    private String fIsdefault;

    /** 会计核算体系 */
    private Integer fAcctsystemId;

    /** 适用核算组织 */
    private Integer fAcctOrgid;

    /** 适用账簿 */
    private String fAcctBook;

    /** 是否审核 */
    private String fIsaudit;

}
