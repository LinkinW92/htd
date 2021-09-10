package com.skeqi.finance.pojo.bo.TBdDimensionSource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Created by DZB
 * @Date 2021/7/16 10:51
 * @Description TODO
 */
@Data
@ApiModel("禁用对象")
public class DisableBo {

	/** 内码 */
	@ApiModelProperty("内码")
	@NotNull(message = "内码不能为空")
	private Integer fId;

	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	@NotBlank(message = "禁用状态不能为空")
	private String fForbidStatus;

	/** 禁用人 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/** 禁用日期 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;
}
