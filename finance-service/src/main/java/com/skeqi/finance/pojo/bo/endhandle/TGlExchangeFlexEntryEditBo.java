package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 期末调汇核算维度分录编辑对象 t_gl_exchange_flex_entry
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇核算维度分录编辑对象")
public class TGlExchangeFlexEntryEditBo {


    /** 分录主键 */
    @ApiModelProperty("分录主键")
    private Integer fEntryId;

    /** 期末调汇方案主键 */
    @ApiModelProperty("期末调汇方案主键")
    @NotNull(message = "期末调汇方案主键不能为空")
    private Integer fId;

    /** 核算维度ID */
    @ApiModelProperty("核算维度ID")
    @NotNull(message = "核算维度ID不能为空")
    private Integer fFlexId;

    /** 核算维度值 */
    @ApiModelProperty("核算维度值")
    @NotBlank(message = "核算维度值不能为空")
    private String fFlexValue;

	@ApiModelProperty("核算维度值名称")
	@NotBlank(message = "核算维度值名称不能为空")
	private String fFlexName;
}
