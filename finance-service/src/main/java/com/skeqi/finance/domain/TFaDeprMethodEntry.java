package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 折旧方法明细对象 t_fa_depr_method_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_depr_method_entry")
public class TFaDeprMethodEntry implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 折旧方法内码 */
    private Integer fId;

    /** 公式类型 1、期折旧额 2、期折旧率 */
    private String fFormulaType;

    /** 公式内容 */
    private String fFormulaContent;

    /** 是否静态 0静态 1动态 */
    private String fIsactive;

    /** 是否最后两年 1：是最后两年 0否 */
    private String fIslastTwoyear;

}
