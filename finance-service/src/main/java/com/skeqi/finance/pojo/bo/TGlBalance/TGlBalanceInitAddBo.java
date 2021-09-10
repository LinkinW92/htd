package com.skeqi.finance.pojo.bo.TGlBalance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 科目初始录入数据添加对象 t_gl_balance_init
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目初始录入数据添加对象")
public class TGlBalanceInitAddBo {


    /** 账簿内码 */
    @ApiModelProperty("账簿内码")
	@NotNull(message = "账簿内码不能为空")
    private Integer fAccountBookId;

    /** 科目内码 */
    @ApiModelProperty("科目内码")
	@NotNull(message = "科目内码不能为空")
    private Integer fAccountId;


	@ApiModelProperty("核算维度类型组合内码")
	private String dimensionCode;
	/**
	 * 核算维度值组合内码
	 */

	@ApiModelProperty("核算维度值组合内码")
	private String fDetailCode;

    /** 币别内码  */
    @ApiModelProperty("币别内码 ")
    private Integer fCurrencyId;

    /** 期间 */
    @ApiModelProperty("期间")
    private Integer fPeriod;

    /** 年 */
    @ApiModelProperty("年")
    private Integer fYear;

    /** 余额方向 */
    @ApiModelProperty("余额方向")
    private String fDc;

    /** 期初原币金额 */
    @ApiModelProperty("期初原币金额")
    private BigDecimal fBeginBalancefor;

    /** 期初本位币金额 */
    @ApiModelProperty("期初本位币金额")
//	@NotNull(message = "期初本位币金额不能为空")
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
    private Integer fYearPeriod;
}
