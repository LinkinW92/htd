package com.skeqi.finance.pojo.bo.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 核算维度余额表
 */
@ApiModel(value = "com-skeqi-finance-domain-report-DimensionBalanceVo")
@Data
public class DimensionBalanceQueryBo implements Serializable {
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

	/**
	 * 账簿内码
	 */
	@NotNull(message = "账簿不能为空")
	@ApiModelProperty(value = "账簿内码")
	private Integer fAccountBookId;

	/**
	 * 科目内码
	 */
	@ApiModelProperty(value = "科目内码")
	private Integer fAccountId;


	/**
	 * 核算维度类别组合Id
	 */
	@ApiModelProperty(value = "核算维度类别组合Id")
	private String dimensionCode;

	/**
	 * 核算维度组合编码
	 */
	@ApiModelProperty(value = "核算维度组合编码")
	private String fDetailCode;



	/**
	 * 币别内码
	 */
	@NotNull(message = "币别不能为空")
	@ApiModelProperty(value = "币别内码 ")
	private Integer fCurrencyId;

	/**
	 * 期间
	 */
	@ApiModelProperty(value = "期间")
	private Integer fPeriod;

	/**
	 * 年
	 */
	@ApiModelProperty(value = "年")
	private String fYear;

	/**
	 * 余额方向
	 */
	@ApiModelProperty(value = "余额方向")
	private Integer fDc;

	/**
	 * 年期
	 */
	@ApiModelProperty(value = "年期")
	private Integer fYearPeriod;

	private static final long serialVersionUID = 1L;
}

