package com.skeqi.manage.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 职级添加对象 sys_dept_rank
 *
 * @author toms
 * @date 2021-08-26
 */
@Data
@ApiModel("职级添加对象")
public class SysDeptRankAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
    private String code;

    /** 职级名称 */
    @ApiModelProperty("职级名称")
    private String name;

    /** 等级 */
    @ApiModelProperty("等级")
    private Integer level;

    /** 时间 */
    @ApiModelProperty("时间")
    private Date createTime;
}
