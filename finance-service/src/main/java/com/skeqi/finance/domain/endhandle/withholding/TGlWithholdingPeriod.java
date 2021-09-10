package com.skeqi.finance.domain.endhandle.withholding;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证预提-预提期间对象 t_gl_withholding_period
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_withholding_period")
public class TGlWithholdingPeriod implements Serializable {

    private static final long serialVersionUID=1L;

	/** 方案内码 */
	private Integer fSchemeId;
    /** 会计年度 */
    private Integer fYear;

    /** 会计期间 */
    private Integer fPeriod;

    /** 会计年期间 */
    private String fYearPeriod;

    /** 预提金额 */
    private BigDecimal fAmortAmount;

    /** 已预提 */
    private String fAmorted;

    /** 公式定义 */
    private String fExpression;

}
