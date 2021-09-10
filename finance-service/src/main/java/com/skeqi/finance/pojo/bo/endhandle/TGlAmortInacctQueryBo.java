package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证摊销-转入科目分页查询对象 t_gl_amort_inacct
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证摊销-转入科目分页查询对象")
public class TGlAmortInacctQueryBo extends BaseEntity {

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


	/** 转入科目ID */
	@ApiModelProperty("转入科目ID")
	private Long fEnterAccountId;
	/** 比例 */
	@ApiModelProperty("比例")
	private BigDecimal fEnterRatio;
	/** 核算维度 */
	@ApiModelProperty("核算维度")
	private Long fDetailId;
	/** 固定 */
	@ApiModelProperty("固定")
	private String fEnterRatioFixed;

}
