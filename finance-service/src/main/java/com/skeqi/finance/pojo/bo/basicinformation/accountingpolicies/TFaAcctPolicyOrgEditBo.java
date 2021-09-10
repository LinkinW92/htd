package com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计政策适用核算组织编辑对象 t_fa_acct_policy_org
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计政策适用核算组织编辑对象")
public class TFaAcctPolicyOrgEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fEntryId;

    /** 会计政策内码  */
    @ApiModelProperty("会计政策内码 ")
    private Integer fAcctpolicyId;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer fSeq;

    /** 是否默认 1是 0否 */
    @ApiModelProperty("是否默认 1是 0否")
    private String fIsdefault;

    /** 会计核算体系 */
    @ApiModelProperty("会计核算体系")
    private Integer fAcctsystemId;

    /** 适用核算组织 */
    @ApiModelProperty("适用核算组织")
    private Integer fAcctOrgid;

    /** 适用账簿 */
    @ApiModelProperty("适用账簿")
    private String fAcctBook;

    /** 是否审核 */
    @ApiModelProperty("是否审核")
    private String fIsaudit;
}
