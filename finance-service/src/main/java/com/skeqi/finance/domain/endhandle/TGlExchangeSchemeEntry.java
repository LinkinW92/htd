package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 期末调汇方案分录对象 t_gl_exchange_scheme_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_exchange_scheme_entry")
public class TGlExchangeSchemeEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录主键 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 期末调汇方案主键 */
    private Integer fId;

    /** 待调汇科目 */
    private Integer fAcctId;

    /** 是否选中 */
    private String fIsSelected;

    /** 序号 */
    private Integer fseq;

}
