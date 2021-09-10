package com.skeqi.finance.pojo.bo.basicinformation.accountingsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计核算体系之下级组织编辑对象 t_org_acct_sys_detail
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计核算体系之下级组织编辑对象")
public class TOrgAcctSysDetailEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fId;

    /** 会计核算体系之会计主体ID */
    @ApiModelProperty("会计核算体系之会计主体ID")
    private Integer fAcctsysentryId;

    /** 下级核算组织ID */
    @ApiModelProperty("下级核算组织ID")
    private Integer fAcctOrgid;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;
}
