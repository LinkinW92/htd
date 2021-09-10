package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 凭证分录维度控制编辑对象 t_gl_voucher_entry_dimension
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@ApiModel("凭证分录维度控制编辑对象")
public class TGlVoucherEntryDimensionEditBo {


    /** 凭证维度内码 */
    @ApiModelProperty("凭证维度内码")
    private Integer id;

    /** 维度ID */
    @ApiModelProperty("维度ID")
    private Integer dimensionId;

    /** 维度编码 */
    @ApiModelProperty("维度编码")
    private String dsCode;

    /** 名称 */
    @ApiModelProperty("名称")
    private String dsName;

    /** 凭证分录ID */
    @ApiModelProperty("凭证分录ID")
    private Integer voucherEntryId;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /** 创建用户 */
    @ApiModelProperty("创建用户")
    private Integer createUser;

    /** 更新用户 */
    @ApiModelProperty("更新用户")
    private Integer updateUser;
}
