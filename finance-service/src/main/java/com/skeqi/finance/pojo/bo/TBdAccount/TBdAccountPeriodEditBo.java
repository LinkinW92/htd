package com.skeqi.finance.pojo.bo.TBdAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 会计期间编辑对象 t_bd_account_period
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计期间编辑对象")
public class TBdAccountPeriodEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fId;

    /** 分录内码 */
    @ApiModelProperty("分录内码")
    private Integer fEntryId;

    /** 期间所在年 */
    @ApiModelProperty("期间所在年")
    private Integer fYear;

    /** 期间流水 */
    @ApiModelProperty("期间流水")
    private Integer fPeriod;

    /** 期间开始日期 */
    @ApiModelProperty("期间开始日期")
    private Date fPeriodStartdate;

    /** 期间截止日期 */
    @ApiModelProperty("期间截止日期")
    private Date fPeriodEnddate;

    /** 本期间所在季度：动态算出 */
    @ApiModelProperty("本期间所在季度：动态算出")
    private Integer fQuarter;

    /** 月份 */
    @ApiModelProperty("月份")
    private Integer fMonth;

    /** 周 */
    @ApiModelProperty("周")
    private Integer fWeek;

    /** 行号 */
    @ApiModelProperty("行号")
    private Integer fEntryseq;
}
