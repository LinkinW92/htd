package com.skeqi.finance.domain.cashflow;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 现金流量T型账查询
 * @author toms
 * @date 2021-07-29
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TAccount implements Serializable {

	/** 日期 */
	private Date fDate;
	/** 账簿id */
	private Integer fAccountBookId;
	/** 是否是现金流量 */
	private Integer fIscashFlow;
	/** 币别 */
	private Integer fCurrencyId;
}
