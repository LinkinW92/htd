package com.skeqi.finance.pojo.bo.adjustmentperiod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "com-skeqi-finance-domain-adjustmentperiod-TGlAdjustperiodVo")
@Data
public class TGlAdjustperiodpEditBo implements Serializable {
	/**
	 * 内码
	 */
	@ApiModelProperty(value = "内码")
	private Integer fAdjustPeriodId;

	/**
	 * 账簿内码
	 */
	@ApiModelProperty(value = "账簿内码")
	private Integer fBookId;

	/**
	 * 会计年度
	 */
	@ApiModelProperty(value = "会计年度")
	private Integer fYear;

	/**
	 * 调整期间
	 */
	@ApiModelProperty(value = "调整期间")
	private Integer fAdjustPeriod;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private String fAdjustStatus;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private Integer fCreatorId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date fCreateDate;

	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private Integer fModifierId;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date fModifierDate;

	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String fName;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String fDescribe;


	private static final long serialVersionUID = 1L;
}


