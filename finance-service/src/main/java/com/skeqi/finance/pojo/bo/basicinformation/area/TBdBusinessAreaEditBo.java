package com.skeqi.finance.pojo.bo.basicinformation.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 业务领域编辑对象 t_bd_business_area
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("业务领域编辑对象")
public class TBdBusinessAreaEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    @JsonProperty(value = "id")
    private Integer id;

    /** 名称 */
    @ApiModelProperty("名称")
    @JsonProperty(value = "name")
    private String name;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    @ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
    private String fIssysPreset;
}
