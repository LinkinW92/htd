package com.skeqi.finance.pojo.vo.voucher;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VoucherVo {

	private Integer fVoucherId;

	private String fBookName;

	private Integer fAccttableId;

	private String fBookNumber;

	private String orgName;

	private String fVoucherWords;

	private Integer fAccountBookId;

	private Integer fAcctOrgid;
	private Date fDate;

	private Date fBusDate;

	private Integer fPeriod;
	private Integer fYear;

	private Integer fVoucherGroupId;

	private Integer fVoucherGroupNo;

    private Integer fCreatorid;

    private String fChecked ;

    private String fPosted;

    private Integer fCashierId;

    private Integer fAttachments;

	@ApiModelProperty("本位币")
	private Integer fBaseCurrencyId;

	private String fCreatorName;
	private String fCheckerName;
	private String fPosterName;

    List<VoucherEntryVo> entryVoList;
}
