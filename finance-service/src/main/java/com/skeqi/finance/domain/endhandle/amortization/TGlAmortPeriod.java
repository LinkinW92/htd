package com.skeqi.finance.domain.endhandle.amortization;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证摊销-摊销期间对象 t_gl_amort_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_amort_period")
public class TGlAmortPeriod implements Serializable {

    private static final long serialVersionUID=1L;


    /** 方案内码 */
    private Integer fSchemeId;

    /** 会计期间 */
    private String fYearPeriod;

    /** 摊销比例 */
    private BigDecimal fAmortratio;

    /** 摊销金额 */
    private BigDecimal fAmortamount;

    /** 是否摊销 */
    private String fAmorted;

    /** 会计年度 */
    private Integer fYear;

    /** 会计期间 */
    private Integer fPeriod;

    /** 固定 */
    private String fAmortRatioFixed;

}
