package com.skeqi.finance.pojo.bo.TBdAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 科目核算币别编辑对象 t_bd_accountcy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目核算币别编辑对象")
public class TBdAccountcyEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fCurrencyId;

    /** 分录内码 */
    @ApiModelProperty("分录内码")
    private Integer fEntryId;

    /** 科目内码 */
    @ApiModelProperty("科目内码")
    private Integer fAcctId;

    /** 显示顺序  */
    @ApiModelProperty("显示顺序 ")
    private Integer fSeq;
}
