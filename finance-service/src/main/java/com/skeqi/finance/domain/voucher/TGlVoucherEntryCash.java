package com.skeqi.finance.domain.voucher;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证分录现金流量对象 t_gl_voucher_entry_cash
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_voucher_entry_cash")
public class TGlVoucherEntryCash implements Serializable {

    private static final long serialVersionUID=1L;


    /** 凭证现金流量ID */
    @TableId(value = "id")
    private Integer id;

    /** 账簿ID */
    private Integer bookId;

    /** 现金凭证分录ID */
    private Integer voucherEntryId;

	/** 对方分录ID */
    private Integer forVoucherEntryId;


	/** 现金科目ID */
	private Integer cashAccountId;
	/** 对方科目ID */
	private Integer forAcctId;

	/**
	 * 币种ID
	 */
	private Integer currencyId;

    /** 主表ID */
    private Integer mainTableId;

    /** 附表ID */
    private Integer attachTableId;

    /** 本位币金额 */
    private BigDecimal amount;


	/** 原币金额 */
	private BigDecimal amountFor;

	private String fExplanation;

}
