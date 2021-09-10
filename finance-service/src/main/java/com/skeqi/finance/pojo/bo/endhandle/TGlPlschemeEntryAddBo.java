package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 结转损益方案分录添加对象 t_gl_plscheme_entry
 *
 * @author toms
 * @date 2021-08-02
 */
@Data
@ApiModel("结转损益方案分录添加对象")
public class TGlPlschemeEntryAddBo {

	private Integer fEntryId;

    /** 结转损益方案主键 */
    @ApiModelProperty("结转损益方案主键")
    @NotNull(message = "结转损益方案主键不能为空")
    private Integer fId;

    /** 待结转科目 */
    @ApiModelProperty("待结转科目")
    @NotNull(message = "待结转科目不能为空")
    private Integer fAcctId;

    /** 结转科目 */
    @ApiModelProperty("结转科目")
    @NotNull(message = "结转科目不能为空")
    private Integer fPlacctId;

    /** 是否选中 */
    @ApiModelProperty("是否选中")
    @NotBlank(message = "是否选中不能为空")
    private String fIsSelected;

    /** 序号 */
    @ApiModelProperty("序号")
    @NotNull(message = "序号不能为空")
    private Integer fSeq;
}
