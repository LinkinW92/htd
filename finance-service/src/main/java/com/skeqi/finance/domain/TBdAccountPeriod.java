package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 会计期间对象 t_bd_account_period
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account_period")
public class TBdAccountPeriod implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 分录内码 */
    private Integer fEntryId;

    /** 期间所在年 */
    private Integer fYear;

    /** 期间流水 */
    private Integer fPeriod;

    /** 期间开始日期 */
    private Date fPeriodStartdate;

    /** 期间截止日期 */
    private Date fPeriodEnddate;

    /** 本期间所在季度：动态算出 */
    private Integer fQuarter;

    /** 月份 */
    private Integer fMonth;

    /** 周 */
    private Integer fWeek;

    /** 行号 */
    private Integer fEntryseq;

}
