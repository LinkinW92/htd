package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证号排序分页查询对象 t_gl_voucher_group_no
 *
 * @author toms
 * @date 2021-08-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证号排序分页查询对象")
public class TGlVoucherGroupNoQueryBo extends BaseEntity {

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


	/** 年 */
	@ApiModelProperty("年")
	private Integer fYear;
	/** 凭证号 */
	@ApiModelProperty("凭证号")
	private Integer fVoucherGroupNo;
	/** 期间 */
	@ApiModelProperty("期间")
	private Integer fPeriod;
	/** 凭证字ID */
	@ApiModelProperty("凭证字ID")
	private Integer fVoucherGroupId;
	/** 账簿ID */
	@ApiModelProperty("账簿ID")
	private Integer fBookId;

}
