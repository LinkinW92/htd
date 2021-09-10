package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证预提-预提期间分页查询对象 t_gl_withholding_period
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证预提-预提期间分页查询对象")
public class TGlWithholdingPeriodQueryBo extends BaseEntity {

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


	/** 会计年度 */
	@ApiModelProperty("会计年度")
	private Long fYear;
	/** 会计期间 */
	@ApiModelProperty("会计期间")
	private Long fPeriod;
	/** 会计年期间 */
	@ApiModelProperty("会计年期间")
	private String fYearPeriod;
	/** 预提金额 */
	@ApiModelProperty("预提金额")
	private BigDecimal fAmortAmount;
	/** 已预提 */
	@ApiModelProperty("已预提")
	private String fAmorted;
	/** 公式定义 */
	@ApiModelProperty("公式定义")
	private String fExpression;

}
