package com.skeqi.finance.domain.endhandle.transfer;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 自动转账核算维度对象 t_gl_auto_transfer_entry_item
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_auto_transfer_entry_item")
public class TGlAutoTransferEntryItem implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 自动转账分录内码 */
    private Integer fTransferEntryId;

    /** 核算维度 */
    private Integer fFlexitemPropertyId;

    /** 起始核算维度编码 */
    private String fBeginItemNumber;

    /** 截止核算维度编码 */
    private String fEndItemNumber;

    /** 连续范围过滤 */
    private String fIssequentSelect;

    /** 项目 */
    private String fItemNumber;

}
