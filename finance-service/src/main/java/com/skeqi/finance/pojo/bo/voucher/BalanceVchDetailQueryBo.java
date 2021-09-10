package com.skeqi.finance.pojo.bo.voucher;

import com.skeqi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 查询明细账BO
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BalanceVchDetailQueryBo extends BaseEntity {

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


	/** 账簿内码 */
	@ApiModelProperty("账簿内码")
	@NotNull(message = "账簿内码不能为空")
	private Integer fAccountBookId;
	/** 科目内码 */
	@ApiModelProperty("科目内码")
	private Integer fAccountId;


	/** 期间 */
	@ApiModelProperty("开始期间")
	@NotNull(message = "开始期间不能为空")
	private Integer fPeriod;
	/** 年 */
	@ApiModelProperty("开始年")
	@NotNull(message = "开始年不能为空")
	private Integer fYear;

	/** 期间 */
	@ApiModelProperty("结束期间")
	@NotNull(message = "结束期间不能为空")
	private Integer efPeriod;
	/** 年 */
	@ApiModelProperty("结束年")
	@NotNull(message = "结束年不能为空")
	private Integer efYear;

	/** 核算维度组合内码 */
	@ApiModelProperty("核算维度组合内码")
	private String fDetailCode;

	/** 币别内码  */
	@ApiModelProperty("币别内码 ")
	private Integer fCurrencyId;


	/** 期初原币金额 */
	@ApiModelProperty("期初原币金额")
	private BigDecimal fBeginBalancefor;
	/** 期初本位币金额 */
	@ApiModelProperty("期初本位币金额")
	private BigDecimal fBeginBalance;
	/** 借方原币金额 */
	@ApiModelProperty("借方原币金额")
	private BigDecimal fDebitFor;
	/** 借方本位币金额 */
	@ApiModelProperty("借方本位币金额")
	private BigDecimal fDebit;
	/** 贷方原币金额 */
	@ApiModelProperty("贷方原币金额")
	private BigDecimal fCreditFor;
	/** 贷方本位币金额 */
	@ApiModelProperty("贷方本位币金额")
	private BigDecimal fCredit;
	/** 本年累计借方原币金额 */
	@ApiModelProperty("本年累计借方原币金额")
	private BigDecimal fYtdDebitfor;
	/** 本年累计借方本位币金额 */
	@ApiModelProperty("本年累计借方本位币金额")
	private BigDecimal fYtdDebit;
	/** 本年累计贷方原币金额 */
	@ApiModelProperty("本年累计贷方原币金额")
	private BigDecimal fYtdCreditfor;
	/** 本年累计贷方本位币金额 */
	@ApiModelProperty("本年累计贷方本位币金额")
	private BigDecimal fYtdCredit;
	/** 期末原币金额 */
	@ApiModelProperty("期末原币金额")
	private BigDecimal fEndBalancefor;
	/** 期末本位币金额 */
	@ApiModelProperty("期末本位币金额")
	private BigDecimal fEndBalance;
	/** 调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期 */
	@ApiModelProperty("调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期")
	private Integer fAdjustPeriod;
	/** 年期 */
	@ApiModelProperty("年期")
	private String fYearPeriod;

	private String dimensionCode;

}
