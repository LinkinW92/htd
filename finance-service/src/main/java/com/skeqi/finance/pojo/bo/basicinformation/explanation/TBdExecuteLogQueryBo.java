package com.skeqi.finance.pojo.bo.basicinformation.explanation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 执行业务日志记录分页查询对象 t_bd_execute_log
 *
 * @author toms
 * @date 2021-08-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("执行业务日志记录分页查询对象")
public class TBdExecuteLogQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 外部关联执行ID */
	@ApiModelProperty("外部关联执行ID")
	private Integer outExecuteId;
	/** 执行结果 1成功 0失败 */
	@ApiModelProperty("执行结果 1成功 0失败")
	private String executeStatus;
	/** 详细信息 */
	@ApiModelProperty("详细信息")
	private String executeDetail;

	private String executeType;

}
