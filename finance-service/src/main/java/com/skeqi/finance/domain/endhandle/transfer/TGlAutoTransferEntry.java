package com.skeqi.finance.domain.endhandle.transfer;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 自动转账分录对象 t_gl_auto_transfer_entry
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_auto_transfer_entry")
public class TGlAutoTransferEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录表内码 */
    @TableId(value = "f_transfer_entry_id")
    private Integer fTransferEntryId;

    /** 自动转账ID */
    private Integer fTransferId;

    /** 序号 */
    private Integer fEntrySeq;

    /** 摘要 */
    private String fExplanation;

    /** 会计科目 */
    private Integer fAccountId;

    /** 核算维度是否启用 */
    private String fItemIsvalid;

    /** 币别 */
    private Integer fCurrencyId;

    /** 汇率类型 */
    private Integer fExchangeRateType;

    /** 方向 */
    private Integer fDc;

    /** 转账方式 */
    private String fType;

    /** 转账比例 */
    private BigDecimal fPercentage;

    /** 公式方法 */
    private String fFormulaType;

    /** 原币公式 */
    private String fAmountforFormula;

    /** 本币公式 */
    private String fAmountFormula;

    /** 数量公式 */
    private String fQtymula;

    /** 不参与多栏账汇总 */
    private String fIsmultiCollect;

    /** 包含未过账凭证 */
    private String fPosted;

}
