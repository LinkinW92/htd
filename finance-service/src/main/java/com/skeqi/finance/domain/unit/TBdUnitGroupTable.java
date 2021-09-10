package com.skeqi.finance.domain.unit;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 计量单位分组对象 t_bd_unit_group_table
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_unit_group_table")
public class TBdUnitGroupTable implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    /** 名称 */
    private String fName;

    /** 上级ID */
    private Integer fParentId;

    /** 描述 */
    private String fDescription;

}
