package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 结账损益核算维度对象 t_gl_plscheme_flex
 *
 * @author toms
 * @date 2021-08-17
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_plscheme_flex")
public class TGlPlschemeFlex implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录主键 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 结账损益方案主键 */
    private Integer fId;

    /** 核算维度ID */
    private Integer fFlexId;

    /** 核算维度值 */
    private String fFlexValue;

    private String fFlexName;

}
