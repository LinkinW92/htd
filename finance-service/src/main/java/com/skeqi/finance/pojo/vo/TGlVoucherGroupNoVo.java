package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证号排序视图对象 t_gl_voucher_group_no
 *
 * @author toms
 * @date 2021-08-09
 */
@Data
@ApiModel("凭证号排序视图对象")
public class TGlVoucherGroupNoVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 年 */
	@Excel(name = "年")
	@ApiModelProperty("年")
	private Integer fYear;

	/** 凭证号 */
	@Excel(name = "凭证号")
	@ApiModelProperty("凭证号")
	private Integer fVoucherGroupNo;

	/** 期间 */
	@Excel(name = "期间")
	@ApiModelProperty("期间")
	private Integer fPeriod;

	/** 凭证字ID */
	@Excel(name = "凭证字ID")
	@ApiModelProperty("凭证字ID")
	private Integer fVoucherGroupId;

	/** 账簿ID */
	@Excel(name = "账簿ID")
	@ApiModelProperty("账簿ID")
	private Integer fBookId;


}
