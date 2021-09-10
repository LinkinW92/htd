package com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计政策资产政策视图对象 t_fa_acct_policy_asset
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计政策资产政策视图对象")
public class TFaAcctPolicyAssetVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fEntryId;

	/** 序号 */
	@Excel(name = "序号")
	@ApiModelProperty("序号")
	private Integer fSeq;

	/** 会计政策内码 */
	@Excel(name = "会计政策内码")
	@ApiModelProperty("会计政策内码")
	private Integer fAcctpolicyId;

	/** 资产类别 */
	@Excel(name = "资产类别")
	@ApiModelProperty("资产类别")
	private Integer fAssetTypeid;

	/** 法定折旧年限 */
	@Excel(name = "法定折旧年限")
	@ApiModelProperty("法定折旧年限")
	private BigDecimal fLegalDepryears;

	/** 企业折旧年限 */
	@Excel(name = "企业折旧年限")
	@ApiModelProperty("企业折旧年限")
	private BigDecimal fEntDepryears;

	/** 残值类型 1：百分比 2：金额 */
	@Excel(name = "残值类型 1：百分比 2：金额")
	@ApiModelProperty("残值类型 1：百分比 2：金额")
	private String fResidualType;

	/** 法定残值率 */
	@Excel(name = "法定残值率")
	@ApiModelProperty("法定残值率")
	private BigDecimal fLegalResidualRate;

	/** 企业残值率 */
	@Excel(name = "企业残值率")
	@ApiModelProperty("企业残值率")
	private BigDecimal fEntResidualRate;

	/** 残值额 */
	@Excel(name = "残值额")
	@ApiModelProperty("残值额")
	private BigDecimal fResidualAmount;

	/** 折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法 */
	@Excel(name = "折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法")
	@ApiModelProperty("折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法")
	private String fDeprMethod;

	/** 折旧政策 */
	@Excel(name = "折旧政策")
	@ApiModelProperty("折旧政策")
	private Integer fDeprPolicyId;

	/** 工作量单位 */
	@Excel(name = "工作量单位")
	@ApiModelProperty("工作量单位")
	private Integer fWorkLoadunitId;

	/** 企业折旧工作量 */
	@Excel(name = "企业折旧工作量")
	@ApiModelProperty("企业折旧工作量")
	private BigDecimal fEntdeprWorkload;

	/** 法定折旧工作量 */
	@Excel(name = "法定折旧工作量")
	@ApiModelProperty("法定折旧工作量")
	private BigDecimal fLegalDeprWorkload;


}
