package com.skeqi.finance.pojo.bo;

import com.skeqi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("核算维度明细查询对象")
public class ABFlexItemPropertyDetailQueryBo extends BaseEntity {

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


	@ApiModelProperty("只显示明细核算维度(0:不显示,其他:显示)")
	private String fChooseShowA1;
	@ApiModelProperty("显示禁用核算维度(0:不显示,其他:显示)")
	private String fChooseShowA2;

	@ApiModelProperty("核算维度无发生额不显示(0:不显示,其他:显示)")
	private String fChooseShowA5;
	@ApiModelProperty("余额为零且无发生额不显示(0:不显示,其他:显示)")
	private String fChooseShowA6;

	@ApiModelProperty("显示禁用科目(0:不显示,其他:显示)")
	private String fChooseShowB1;
	@ApiModelProperty("包括未过账凭证(0:不显示,其他:显示)")
	private String fChooseShowB2;
	@ApiModelProperty("显示科目全名(0:不显示,其他:显示)")
	private String fChooseShowB3;
	@ApiModelProperty("多个科目合并显示(0:不显示,其他:显示)")
	private String fChooseShowB4;
	@ApiModelProperty("不包含调整期凭证(0:不显示,其他:显示)")
	private String fChooseShowB7;


	@ApiModelProperty("科目范围")
	private List<Integer> fAccountIdList;


	/**
	 * 账簿内码
	 */
	@ApiModelProperty("账簿内码")
	@NotNull(message = "账簿内码不能为空")
	private Integer fAccountBookId;
	/**
	 * 科目内码
	 */
//	@ApiModelProperty("科目内码")
//	private Integer fAccountId;


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
