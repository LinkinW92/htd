package com.skeqi.finance.pojo.vo.book;

import lombok.Data;

@Data
public class BookPolicyVo {

	private Integer policyId;
	private String policyNumber;
	private String  policyName;
	private Integer calendarId;
	private String calendarName;
	private String calendarNumber;
	private String currencyCode;
	private String currencyName;
	private Integer fCurrencyId;
	private Integer fRateTypeId;
	private String rateName;
	private String rateNumber;
}
