package com.skeqi.finance.pojo.bo.TBdAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 会计期间添加对象 t_bd_account_period
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计期间添加对象")
public class TBdAccountPeriodAddBo {


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
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date fStartdate;

    /** 期间截止日期 */
    @ApiModelProperty("期间截止日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date fEnddate;

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
	/**
	 * 追加年限数量
	 */
	@ApiModelProperty("追加年限数量")
	private Integer count;

	@ApiModelProperty("期间类型")
	@NotNull(message = "期间类型不能为空")
	private Integer periodType;
}
