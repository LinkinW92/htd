package com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 会计政策资产政策编辑对象 t_fa_acct_policy_asset
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计政策资产政策编辑对象")
public class TFaAcctPolicyAssetEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fEntryId;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer fSeq;

    /** 会计政策内码 */
    @ApiModelProperty("会计政策内码")
    private Integer fAcctpolicyId;

    /** 资产类别 */
    @ApiModelProperty("资产类别")
    private Integer fAssetTypeid;

    /** 法定折旧年限 */
    @ApiModelProperty("法定折旧年限")
    private BigDecimal fLegalDepryears;

    /** 企业折旧年限 */
    @ApiModelProperty("企业折旧年限")
    private BigDecimal fEntDepryears;

    /** 残值类型 1：百分比 2：金额 */
    @ApiModelProperty("残值类型 1：百分比 2：金额")
    private String fResidualType;

    /** 法定残值率 */
    @ApiModelProperty("法定残值率")
    private BigDecimal fLegalResidualRate;

    /** 企业残值率 */
    @ApiModelProperty("企业残值率")
    private BigDecimal fEntResidualRate;

    /** 残值额 */
    @ApiModelProperty("残值额")
    private BigDecimal fResidualAmount;

    /** 折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法 */
    @ApiModelProperty("折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法")
    private String fDeprMethod;

    /** 折旧政策 */
    @ApiModelProperty("折旧政策")
    private Integer fDeprPolicyId;

    /** 工作量单位 */
    @ApiModelProperty("工作量单位")
    private Integer fWorkLoadunitId;

    /** 企业折旧工作量 */
    @ApiModelProperty("企业折旧工作量")
    private BigDecimal fEntdeprWorkload;

    /** 法定折旧工作量 */
    @ApiModelProperty("法定折旧工作量")
    private BigDecimal fLegalDeprWorkload;
}
