package com.skeqi.finance.domain.endhandle.withholding;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证预提-预提科目对象 t_gl_withholding_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_withholding_acct")
public class TGlWithholdingAcct implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "f_id")
    private Integer fId;

    /** 方案内码 */
    private Integer fSchemeId;

    /** 预提科目 */
    private Integer fProvisionAccount;

    /** 核算维度 */
    private Integer fDetailId;

    /** 比例 */
    private BigDecimal fProvisionRatio;

    /** 固定 */
    private String fProvisionFixed;

}
