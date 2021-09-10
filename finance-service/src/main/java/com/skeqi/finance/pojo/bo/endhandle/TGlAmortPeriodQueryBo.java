package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证摊销-摊销期间分页查询对象 t_gl_amort_period
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证摊销-摊销期间分页查询对象")
public class TGlAmortPeriodQueryBo extends BaseEntity {

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


	/** 会计期间 */
	@ApiModelProperty("会计期间")
	private String fYearPeriod;
	/** 摊销比例 */
	@ApiModelProperty("摊销比例")
	private BigDecimal fAmortratio;
	/** 摊销金额 */
	@ApiModelProperty("摊销金额")
	private BigDecimal fAmortamount;
	/** 是否摊销 */
	@ApiModelProperty("是否摊销")
	private String fAmorted;
	/** 会计年度 */
	@ApiModelProperty("会计年度")
	private Long fYear;
	/** 会计期间 */
	@ApiModelProperty("会计期间")
	private Long fPeriod;
	/** 固定 */
	@ApiModelProperty("固定")
	private String fAmortRatioFixed;

}
