package com.skeqi.finance.pojo.vo.voucher;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherPageVo {

	private Date fDate;
	private Integer fYear;
	private Integer fPeriod;
	private String fVoucherWords;
	private Integer fVoucherGroupNo;
	private String fExplanation;
	private BigDecimal fCreditTotal;
	private BigDecimal fDebitTotal;
	private Integer fCreatorid;
	private Integer fCheckerId;
	private Integer  fPosterId;
	private Integer  fCashierId;
	private Integer fAttachments;
	private String  fInternalind;
	private String fDocumentStatus;
	private String  fInvalid;
	private String fPosted;
	private Integer fVoucherId;
	private String fCreatorName;
	private String fCheckerName;
	private String fPosterName;
	private String fIscashFlow;
}
