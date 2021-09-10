package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 科目核算币别对象 t_bd_accountcy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_accountcy")
public class TBdAccountcy implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_currency_id")
    private Integer fCurrencyId;

    /** 分录内码 */
    private Integer fEntryId;

    /** 科目内码 */
    private Integer fAcctId;

    /** 显示顺序  */
    private Integer fSeq;

}
