package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证摊销视图对象 t_gl_amortization_scheme
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证摊销视图对象")
public class TGlAmortizationSchemeVo {

	private static final long serialVersionUID = 1L;

	/** 方案内码 */
	@ApiModelProperty("方案内码")
	private Integer fSchemeId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	private String fName;
	private String fDescription;

	/** 是否摊销方案 */
	@Excel(name = "是否摊销方案")
	@ApiModelProperty("是否摊销方案")
	private String fIsamort;

	/** 账簿 */
	@Excel(name = "账簿")
	@ApiModelProperty("账簿")
	private Integer fAccountBookId;

	@ApiModelProperty("账簿名称")
	private String fAccountBookName;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private Integer fVoucherGroupId;

	@Excel(name = "凭证字名称")
	@ApiModelProperty("凭证字名称")
	private String fVoucherGroupName;

	/** 币别 */
	@Excel(name = "币别")
	@ApiModelProperty("币别")
	private Integer fCurrencyId;

	@ApiModelProperty("币别名称")
	private String fCurrencyName;

	/** 汇率类型 */
	@Excel(name = "汇率类型")
	@ApiModelProperty("汇率类型")
	private Integer fExchangeRateType;

	@ApiModelProperty("汇率类型名称")
	private String fExchangeRateTypeName;

	/** 摘要 */
	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String fExplanation;

	/** 待摊销总额 */
	@Excel(name = "待摊销总额")
	@ApiModelProperty("待摊销总额")
	private BigDecimal fPeddingAmortAmount;

	/** 已摊销总额 */
	@Excel(name = "已摊销总额")
	@ApiModelProperty("已摊销总额")
	private BigDecimal fAmortedAmount;

	/** 余额 */
	@Excel(name = "余额")
	@ApiModelProperty("余额")
	private BigDecimal fEndBalance;

	/** 执行时间 */
	@Excel(name = "执行时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("执行时间")
	private Date fLastExecuteTime;

	/** 状态 */
	@Excel(name = "状态")
	@ApiModelProperty("状态")
	private String fStatus;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 禁用人 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderId;

	/** 禁用时间 */
	@Excel(name = "禁用时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;

	@ApiModelProperty("待摊销科目")
	List<TGlAmortAcctVo> acctVos;

	@ApiModelProperty("转入科目")
	List<TGlAmortInacctVo> inacctVos;

	@ApiModelProperty("摊销期间")
	List<TGlAmortPeriodVo> periodVos;


}
