package com.skeqi.finance.pojo.bo.basicinformation.explanation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 执行业务日志记录添加对象 t_bd_execute_log
 *
 * @author toms
 * @date 2021-08-07
 */
@Data
@ApiModel("执行业务日志记录添加对象")
public class TBdExecuteLogAddBo {


    /** 外部关联执行ID */
    @ApiModelProperty("外部关联执行ID")
	@NotNull(message = "外部ID不能为空")
    private Integer outExecuteId;

	@ApiModelProperty("执行ID")
	@NotNull(message = "执行ID不能为空")
	private Integer executeId;

    /** 执行结果 1成功 0失败 */
    @ApiModelProperty("执行结果 1成功 0失败")
    private String executeStatus;

    /** 详细信息 */
    @ApiModelProperty("详细信息")
	@NotNull(message = "详细信息不能为空")
    private String executeDetail;

	@NotNull(message = "执行类型不能为空")
    private String executeType;

    /** $column.columnComment */
    @ApiModelProperty("时间")
    private Date createTime;
}
