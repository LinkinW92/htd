package com.skeqi.finance.pojo.vo.basicinformation.voucher;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证字-科目控制视图对象 t_bd_vchgroup_acct
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("凭证字-科目控制视图对象")
public class TBdVchgroupAcctVo {

	private static final long serialVersionUID = 1L;

	/** 分录内码 */
	@ApiModelProperty("分录内码")
	private Integer fEntryId;

	/** 凭证字内码 */
	@Excel(name = "凭证字内码")
	@ApiModelProperty("凭证字内码")
	private String fVchgroupId;

	/** 科目编码 */
	@Excel(name = "科目编码")
	@ApiModelProperty("科目编码")
	private String fAcctNumber;

	/** 借方必有 1：必有 0非必有 */
	@Excel(name = "借方必有 1：必有 0非必有")
	@ApiModelProperty("借方必有 1：必有 0非必有")
	private String fDebit;

	/** 贷方必有 1：必有 0非必有 */
	@Excel(name = "贷方必有 1：必有 0非必有")
	@ApiModelProperty("贷方必有 1：必有 0非必有")
	private String fCredit;

	/** 借或贷必有 1：必有 0非必有 */
	@Excel(name = "借或贷必有 1：必有 0非必有")
	@ApiModelProperty("借或贷必有 1：必有 0非必有")
	private String fDebitCredit;

	/** 借方必无  1：必有 0非必有 */
	@Excel(name = "借方必无  1：必有 0非必有")
	@ApiModelProperty("借方必无  1：必有 0非必有")
	private String fDebitNo;

	/** 贷方必无 1：必有 0非必有 */
	@Excel(name = "贷方必无 1：必有 0非必有")
	@ApiModelProperty("贷方必无 1：必有 0非必有")
	private String fCreditNo;

	/** 借和贷必无 1：必有 0非必有 */
	@Excel(name = "借和贷必无 1：必有 0非必有")
	@ApiModelProperty("借和贷必无 1：必有 0非必有")
	private String fDebitCreditNo;


}
