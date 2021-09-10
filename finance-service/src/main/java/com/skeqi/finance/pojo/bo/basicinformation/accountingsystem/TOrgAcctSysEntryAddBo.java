package com.skeqi.finance.pojo.bo.basicinformation.accountingsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计核算体系之会计主体添加对象 t_org_acct_sys_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计核算体系之会计主体添加对象")
public class TOrgAcctSysEntryAddBo {


    /** 会计核算体系ID */
    @ApiModelProperty("会计核算体系ID")
    private Integer fAcctsystemId;

    /** 核算组织ID */
    @ApiModelProperty("核算组织ID")
    private Integer fAcctOrgid;

    /** 默认会计政策 */
    @ApiModelProperty("默认会计政策")
    private Integer fDefaultAcctpolicyId;

    /** 适用会计政策 */
    @ApiModelProperty("适用会计政策")
    private Integer fAcctpolicyId;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;
}
