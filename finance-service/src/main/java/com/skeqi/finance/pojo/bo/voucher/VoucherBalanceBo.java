package com.skeqi.finance.pojo.bo.voucher;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VoucherBalanceBo {

	private Integer fAccountBookId;

	private Integer fYear;

	private Integer fPeriod;

	private Integer fAccountId;

	private Integer fCurrencyId;

	private String fDetailCode;

	private String dimensionCode;

	private Integer fVoucherId;

	private String fPosted;
}
