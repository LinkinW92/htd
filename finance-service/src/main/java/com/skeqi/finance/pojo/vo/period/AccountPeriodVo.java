package com.skeqi.finance.pojo.vo.period;


import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountPeriodVo {

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 分录内码 */
	@Excel(name = "分录内码")
	@ApiModelProperty("分录内码")
	private Integer fEntryId;

	/** 期间所在年 */
	@Excel(name = "期间所在年")
	@ApiModelProperty("期间所在年")
	private Integer fYear;

	/** 期间流水 */
	@Excel(name = "期间流水")
	@ApiModelProperty("期间流水")
	private Integer fPeriod;

	private String fYearAndPeriod;
}
