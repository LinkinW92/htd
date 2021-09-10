package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 凭证号排序编辑对象 t_gl_voucher_group_no
 *
 * @author toms
 * @date 2021-08-09
 */
@Data
@ApiModel("凭证号排序编辑对象")
public class TGlVoucherGroupNoEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fId;

    /** 年 */
    @ApiModelProperty("年")
    private Integer fYear;

    /** 凭证号 */
    @ApiModelProperty("凭证号")
    @NotNull(message = "凭证号不能为空")
    private Integer fVoucherGroupNo;

    /** 期间 */
    @ApiModelProperty("期间")
    private Integer fPeriod;

    /** 凭证字ID */
    @ApiModelProperty("凭证字ID")
    @NotNull(message = "凭证字ID不能为空")
    private Integer fVoucherGroupId;

    /** 账簿ID */
    @ApiModelProperty("账簿ID")
    private Integer fBookId;
}
