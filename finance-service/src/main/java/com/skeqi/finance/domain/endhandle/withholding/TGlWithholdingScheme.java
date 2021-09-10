package com.skeqi.finance.domain.endhandle.withholding;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证预提对象 t_gl_withholding_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_withholding_scheme")
public class TGlWithholdingScheme implements Serializable {

    private static final long serialVersionUID=1L;


    /** 方案内码 */
    @TableId(value = "f_scheme_id")
    private Integer fSchemeId;

    /** 编码 */
    private String fNumber;

	private String fName;
	private String fDescription;

    /** 是否预提方案 */
    private String fIsamort;

    /** 账簿 */
    private Integer fAccountBookId;

    /** 凭证字 */
    private Integer fVoucherGroupId;

    /** 币别 */
    private Integer fCurrencyId;

    /** 汇率类型 */
    private Integer fExchangeRateType;

    /** 摘要 */
    private String fExplanation;

    /** 待预提总额 */
    private BigDecimal fPeddingDrawAmount;

    /** 已预提总额 */
    private BigDecimal fAmortizedAmount;

    /** 余额 */
    private BigDecimal fEndBalance;

    /** 执行时间 */
    private Date fLastExecuteTime;

    /** 状态 */
    private String fStatus;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Integer fForbidderId;

    /** 禁用时间 */
    private Date fForbidDate;

}
