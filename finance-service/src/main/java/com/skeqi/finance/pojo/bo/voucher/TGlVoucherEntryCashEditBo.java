package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * 凭证分录现金流量编辑对象 t_gl_voucher_entry_cash
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@ApiModel("凭证分录现金流量编辑对象")
public class TGlVoucherEntryCashEditBo {


    /** 凭证现金流量ID */
    @ApiModelProperty("凭证现金流量ID")
    private Integer id;

    /** 账簿ID */
    @ApiModelProperty("账簿ID")
    private Integer bookId;

    /** 对方科目ID */
    @ApiModelProperty("对方科目ID")
    private Integer acctId;

    /** 凭证分录ID */
    @ApiModelProperty("凭证分录ID")
    private Integer voucherEntryId;

    /** 主表ID */
    @ApiModelProperty("主表ID")
    private Integer mainTableId;

    /** 附表ID */
    @ApiModelProperty("附表ID")
    private Integer attachTableId;

    /** 本位币金额 */
    @ApiModelProperty("本位币金额")
    private BigDecimal amount;
}
