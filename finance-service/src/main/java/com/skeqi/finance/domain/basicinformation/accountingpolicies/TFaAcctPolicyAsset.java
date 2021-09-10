package com.skeqi.finance.domain.basicinformation.accountingpolicies;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计政策资产政策对象 t_fa_acct_policy_asset
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_acct_policy_asset")
public class TFaAcctPolicyAsset implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 序号 */
    private Integer fSeq;

    /** 会计政策内码 */
    private Integer fAcctpolicyId;

    /** 资产类别 */
    private Integer fAssetTypeid;

    /** 法定折旧年限 */
    private BigDecimal fLegalDepryears;

    /** 企业折旧年限 */
    private BigDecimal fEntDepryears;

    /** 残值类型 1：百分比 2：金额 */
    private String fResidualType;

    /** 法定残值率 */
    private BigDecimal fLegalResidualRate;

    /** 企业残值率 */
    private BigDecimal fEntResidualRate;

    /** 残值额 */
    private BigDecimal fResidualAmount;

    /** 折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法 */
    private String fDeprMethod;

    /** 折旧政策 */
    private Integer fDeprPolicyId;

    /** 工作量单位 */
    private Integer fWorkLoadunitId;

    /** 企业折旧工作量 */
    private BigDecimal fEntdeprWorkload;

    /** 法定折旧工作量 */
    private BigDecimal fLegalDeprWorkload;

}
