package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 结转损益方案分录对象 t_gl_plscheme_entry
 *
 * @author toms
 * @date 2021-08-02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_plscheme_entry")
public class TGlPlschemeEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 结转损益方案主键 */
    private Integer fId;

    /** 待结转科目 */
    private Integer fAcctId;

    /** 结转科目 */
    private Integer fPlacctId;

    /** 是否选中 */
    private String fIsSelected;

    /** 序号 */
    private Integer fSeq;

}
