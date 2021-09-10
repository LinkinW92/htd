package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 期末调汇方案分录编辑对象 t_gl_exchange_scheme_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇方案分录编辑对象")
public class TGlExchangeSchemeEntryEditBo {


    /** 分录主键 */
    @ApiModelProperty("分录主键")
    private Long fEntryId;

    /** 期末调汇方案主键 */
    @ApiModelProperty("期末调汇方案主键")
    @NotNull(message = "期末调汇方案主键不能为空")
    private Long fId;

    /** 待调汇科目 */
    @ApiModelProperty("待调汇科目")
    @NotNull(message = "待调汇科目不能为空")
    private Long fAcctId;

    /** 是否选中 */
    @ApiModelProperty("是否选中")
    @NotBlank(message = "是否选中不能为空")
    private String fIsSelected;

    /** 序号 */
    @ApiModelProperty("序号")
    @NotNull(message = "序号不能为空")
    private Long fseq;
}
