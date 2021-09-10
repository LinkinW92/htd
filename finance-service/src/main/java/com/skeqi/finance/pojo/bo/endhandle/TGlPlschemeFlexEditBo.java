package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 结账损益核算维度编辑对象 t_gl_plscheme_flex
 *
 * @author toms
 * @date 2021-08-17
 */
@Data
@ApiModel("结账损益核算维度编辑对象")
public class TGlPlschemeFlexEditBo {


    /** 分录主键 */
    @ApiModelProperty("分录主键")
    private Integer fEntryId;

    /** 结账损益方案主键 */
    @ApiModelProperty("结账损益方案主键")
    private Integer fId;

    /** 核算维度ID */
    @ApiModelProperty("核算维度ID")
    private Integer fFlexId;

    /** 核算维度值 */
    @ApiModelProperty("核算维度值")
    private String fFlexValue;

	@ApiModelProperty("核算维度名字")
    private String fFlexName;
}
