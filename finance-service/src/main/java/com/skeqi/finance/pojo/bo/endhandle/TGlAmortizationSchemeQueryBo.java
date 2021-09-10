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
 * 凭证摊销分页查询对象 t_gl_amortization_scheme
 *
 * @author toms
 * @date 2021-07-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证摊销分页查询对象")
public class TGlAmortizationSchemeQueryBo extends BaseEntity {

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
	/** 是否摊销方案 */
	@ApiModelProperty("是否摊销方案")
	private String fIsamort;
	/** 账簿 */
	@ApiModelProperty("账簿")
	private Integer fAccountBookId;
	@ApiModelProperty("账簿名称")
	private String fAccountBookName;
	/** 凭证字 */
	@ApiModelProperty("凭证字")
	private Integer fVoucherGroupId;
	/** 币别 */
	@ApiModelProperty("币别")
	private Integer fCurrencyId;

	/** 币别 */
	@ApiModelProperty("币别名称")
	private String fCurrencyName;
	/** 汇率类型 */
	@ApiModelProperty("汇率类型")
	private Integer fExchangeRateType;
	/** 摘要 */
	@ApiModelProperty("摘要")
	private String fExplanation;
	/** 待摊销总额 */
	@ApiModelProperty("待摊销总额")
	private BigDecimal fPeddingAmortAmount;
	/** 已摊销总额 */
	@ApiModelProperty("已摊销总额")
	private BigDecimal fAmortedAmount;
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
	private Integer fForbidderId;
	/** 禁用时间 */
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;

}
