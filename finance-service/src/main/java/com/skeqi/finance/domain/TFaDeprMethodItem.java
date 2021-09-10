package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 折旧方法元素对象 t_fa_depr_method_item
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_depr_method_item")
public class TFaDeprMethodItem implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 字段类型 1：文本 2：金额  */
    private Integer fFieldType;

    /** 对应的取数字段  */
    private String fFormulaType;

    /** 编码 */
    private String fNumber;

}
