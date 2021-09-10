package com.skeqi.finance.pojo.bo;

import com.skeqi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("总分类账查询对象")
public class ABTotalBalanceQueryBo extends BaseEntity {

	/**
	 * 分页大小
	 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/**
	 * 排序列
	 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/**
	 * 排序的方向desc或者asc
	 */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	@ApiModelProperty("是否显示核算维度(0:不显示,其他:显示)")
	private String fDetailCodeShow;


	/**
	 * 账簿内码
	 */
	@ApiModelProperty("账簿内码")
	@NotNull(message = "账簿内码不能为空")
	private Integer fAccountBookId;
	/**
	 * 科目内码
	 */
	@ApiModelProperty("科目内码")
	private Integer fAccountId;


	/**
	 * 期间
	 */
	@ApiModelProperty("开始期间")
	@NotNull(message = "开始期间不能为空")
	private Integer fPeriod;
	/**
	 * 年
	 */
	@ApiModelProperty("开始年")
	@NotNull(message = "开始年不能为空")
	private Integer fYear;

	/**
	 * 期间
	 */
	@ApiModelProperty("结束期间")
	@NotNull(message = "结束期间不能为空")
	private Integer efPeriod;
	/**
	 * 年
	 */
	@ApiModelProperty("结束年")
	@NotNull(message = "结束年不能为空")
	private Integer efYear;


	/**
	 * 币别内码
	 */
	@ApiModelProperty("币别内码 ")
	@NotNull(message = "币别内码不能为空")
	private Integer fCurrencyId;


}
