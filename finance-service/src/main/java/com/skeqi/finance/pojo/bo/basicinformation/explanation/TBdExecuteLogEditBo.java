package com.skeqi.finance.pojo.bo.basicinformation.explanation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 执行业务日志记录编辑对象 t_bd_execute_log
 *
 * @author toms
 * @date 2021-08-07
 */
@Data
@ApiModel("执行业务日志记录编辑对象")
public class TBdExecuteLogEditBo {


    /** 自增ID */
    @ApiModelProperty("自增ID")
    private Integer id;

    /** 外部关联执行ID */
    @ApiModelProperty("外部关联执行ID")
    private Integer outExecuteId;

    /** 执行结果 1成功 0失败 */
    @ApiModelProperty("执行结果 1成功 0失败")
    private String executeStatus;

    /** 详细信息 */
    @ApiModelProperty("详细信息")
    private String executeDetail;
}
