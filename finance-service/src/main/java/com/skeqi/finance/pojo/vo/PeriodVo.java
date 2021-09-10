package com.skeqi.finance.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PeriodVo {

	@ApiModelProperty("期间所在年")
	private Integer fYear;

	@JsonFormat( timezone="GMT+8", pattern="yyyy/MM/dd")
	@ApiModelProperty("期间开始日期")
	private Date fPeriodStartdate;

	/** 期间截止日期 */
	@JsonFormat( timezone="GMT+8", pattern="yyyy/MM/dd")
	@ApiModelProperty("期间截止日期")
	private Date fPeriodEnddate;

	@ApiModelProperty("季度")
	private Integer fQuarter;

	@ApiModelProperty("流水")
	private Integer fPeriod;

	private Integer fMonth;
}
