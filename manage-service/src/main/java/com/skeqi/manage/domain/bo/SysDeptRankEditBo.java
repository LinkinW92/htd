package com.skeqi.manage.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 职级编辑对象 sys_dept_rank
 *
 * @author toms
 * @date 2021-08-26
 */
@Data
@ApiModel("职级编辑对象")
public class SysDeptRankEditBo {


    /** 自增 */
    @ApiModelProperty("自增")
    private Integer id;

    /** 编码 */
    @ApiModelProperty("编码")
    private String code;

    /** 职级名称 */
    @ApiModelProperty("职级名称")
    private String name;

    /** 等级 */
    @ApiModelProperty("等级")
    private Integer level;
}
