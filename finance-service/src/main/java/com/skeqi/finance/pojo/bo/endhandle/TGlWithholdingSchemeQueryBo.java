package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证预提分页查询对象 t_gl_withholding_scheme
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证预提分页查询对象")
public class TGlWithholdingSchemeQueryBo extends BaseEntity {

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


	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;
	/** 是否预提方案 */
	@ApiModelProperty("是否预提方案")
	private String fIsamort;
	/** 账簿 */
	@ApiModelProperty("账簿")
	private Long fAccountBookId;
	/** 凭证字 */
	@ApiModelProperty("凭证字")
	private Long fVoucherGroupId;
	/** 币别 */
	@ApiModelProperty("币别")
	private Long fCurrencyId;
	/** 汇率类型 */
	@ApiModelProperty("汇率类型")
	private Long fExchangeRateType;
	/** 摘要 */
	@ApiModelProperty("摘要")
	private String fExplanation;
	/** 待预提总额 */
	@ApiModelProperty("待预提总额")
	private BigDecimal fPeddingDrawAmount;
	/** 已预提总额 */
	@ApiModelProperty("已预提总额")
	private BigDecimal fAmortizedAmount;
	/** 余额 */
	@ApiModelProperty("余额")
	private BigDecimal fEndBalance;
	/** 执行时间 */
	@ApiModelProperty("执行时间")
	private Date fLastExecuteTime;
	/** 状态 */
	@ApiModelProperty("状态")
	private String fStatus;
	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;
	/** 禁用人 */
	@ApiModelProperty("禁用人")
	private Long fForbidderId;
	/** 禁用时间 */
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;

	private String fAccountBookName;

}
