package com.skeqi.finance.pojo.vo.basicinformation.base;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 执行业务日志记录视图对象 t_bd_execute_log
 *
 * @author toms
 * @date 2021-08-07
 */
@Data
@ApiModel("执行业务日志记录视图对象")
public class TBdExecuteLogVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer id;

	/** 外部关联执行ID */
	@Excel(name = "外部关联执行ID")
	@ApiModelProperty("外部关联执行ID")
	private Integer outExecuteId;

	private Integer executeId;

	/** 执行结果 1成功 0失败 */
	@Excel(name = "执行结果 1成功 0失败")
	@ApiModelProperty("执行结果 1成功 0失败")
	private String executeStatus;

	/** 详细信息 */
	@Excel(name = "详细信息")
	@ApiModelProperty("详细信息")
	private String executeDetail;


}
