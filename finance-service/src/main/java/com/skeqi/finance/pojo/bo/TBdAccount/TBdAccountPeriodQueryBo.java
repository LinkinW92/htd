package com.skeqi.finance.pojo.bo.TBdAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 会计期间分页查询对象 t_bd_account_period
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会计期间分页查询对象")
public class TBdAccountPeriodQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


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
