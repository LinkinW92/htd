package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目核算维度组分录对象 t_bd_account_flexentry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account_flexentry")
public class TBdAccountFlexentry  implements Serializable {

    private static final long serialVersionUID=1L;


    /** 分录内码  */
    @TableId(value = "f_entry_id")
    private Integer fEntryId;

    /** 科目主表内码 */
    private Integer fAcctId;

    /** 维度内码 */
    private Integer fFlexitempropertyId;

    /** 是否启用 '0'未启用 '1'启用 */
    private String fAcctitemisvalid;

    /** 必录类型需要有分类：1 必录 0 可选 */
    private String fInputType;

    /** 维度数据表中的字段名称 */
    private String fDataFieldname;

    /** 显示顺序 */
    private String fSeq;

    /** 组织隔离字段 */
    private Integer fMasterId;

}
