package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 期末调汇核算维度分录对象 t_gl_exchange_flex_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_exchange_flex_entry")
public class TGlExchangeFlexEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录主键 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 期末调汇方案主键 */
    private Integer fId;

    /** 核算维度ID */
    private Integer fFlexId;

    /** 核算维度值 */
    private String fFlexValue;

    private String fFlexName;

}
