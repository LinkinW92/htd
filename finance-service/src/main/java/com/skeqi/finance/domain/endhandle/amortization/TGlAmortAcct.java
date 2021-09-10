package com.skeqi.finance.domain.endhandle.amortization;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证摊销-待摊销科目对象 t_gl_amort_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_amort_acct")
public class TGlAmortAcct implements Serializable {

    private static final long serialVersionUID=1L;


	/** 自增ID */
	@TableId(value = "f_id")
	private Integer fId;

    /** 方案ID */
    private Integer fSchemeId;


    /** 待摊销科目 */
    private Integer fAmortizeAccount;

    /** 待摊销金额 */
    private BigDecimal fAmortizingAmount;

    /** 核算维度 */
    private Integer fDetailId;

    /** 公式定义 */
    private String fExpression;

}
