package com.skeqi.finance.domain.basicinformation.voucher;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证字-科目控制对象 t_bd_vchgroup_acct
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_vchgroup_acct")
public class TBdVchgroupAcct implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录内码 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 凭证字内码 */
    private Integer fVchgroupId;

    /** 科目编码 */
    private String fAcctNumber;

    /** 借方必有 1：必有 0非必有 */
    private String fDebit;

    /** 贷方必有 1：必有 0非必有 */
    private String fCredit;

    /** 借或贷必有 1：必有 0非必有 */
    private String fDebitCredit;

    /** 借方必无  1：必有 0非必有 */
    private String fDebitNo;

    /** 贷方必无 1：必有 0非必有 */
    private String fCreditNo;

    /** 借和贷必无 1：必有 0非必有 */
    private String fDebitCreditNo;

}
