package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 自动转账核算维度添加对象 t_gl_auto_transfer_entry_item
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账核算维度添加对象")
public class TGlAutoTransferEntryItemAddBo {


    /** 自动转账分录内码 */
    @ApiModelProperty("自动转账分录内码")
    @NotNull(message = "自动转账分录内码不能为空")
    private Integer fTransferEntryId;

    /** 核算维度 */
    @ApiModelProperty("核算维度")
    private Integer fFlexitemPropertyId;

    /** 起始核算维度编码 */
    @ApiModelProperty("起始核算维度编码")
    private String fBeginItemNumber;

    /** 截止核算维度编码 */
    @ApiModelProperty("截止核算维度编码")
    private String fEndItemNumber;

    /** 连续范围过滤 */
    @ApiModelProperty("连续范围过滤")
    private String fIssequentSelect;

    /** 项目 */
    @ApiModelProperty("项目")
    private String fItemNumber;
}
