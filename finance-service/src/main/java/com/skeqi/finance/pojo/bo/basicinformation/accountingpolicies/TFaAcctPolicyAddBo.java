package com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 会计政策添加对象 t_fa_acct_policy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("会计政策添加对象")
public class TFaAcctPolicyAddBo {


    /** 名称 */
    @ApiModelProperty("名称")
    private String fName;

    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;

    /** 币别 */
    @ApiModelProperty("币别")
    private Integer fCurrencyId;

    /** 会计日历 */
    @ApiModelProperty("会计日历")
    private Integer fAcctcalendarId;

    /** 会计要素 */
    @ApiModelProperty("会计要素")
    private Integer fAcctgroupId;

    /** 汇率类型 */
    @ApiModelProperty("汇率类型")
    private Integer fRatetypeId;

    /** 存货核算按费用项目明细核算默认0 */
    @ApiModelProperty("存货核算按费用项目明细核算默认0")
    private String fIscalbyexpItem;

    /** 成本以含税金额进行核算 */
    @ApiModelProperty("成本以含税金额进行核算")
    private String fIstaxAmount;

    /** 启用实际成本核算 默认0 */
    @ApiModelProperty("启用实际成本核算 默认0")
    private String fIscalbyactualCost;

    /** 是否默认 0：非默认，默认 1： */
    @ApiModelProperty("是否默认 0：非默认，默认 1：")
    private String fIsdefault;

    /** 创建组织 */
    @ApiModelProperty("创建组织")
    private Integer fCreateOrgid;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Integer fCreatorid;

    /** 创建日期 */
    @ApiModelProperty("创建日期")
    private Date fCreateDate;

    /** 使用组织 */
    @ApiModelProperty("使用组织")
    private Integer fUseOrgid;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private Integer fModifierid;

    /** 修改日期 */
    @ApiModelProperty("修改日期")
    private Date fModifyDate;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    private String fDocumentStatus;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private Integer fAuditorid;

    /** 审核日期 */
    @ApiModelProperty("审核日期")
    private Date fAuditDate;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    private Integer fForbidderid;

    /** 禁用日期 */
    @ApiModelProperty("禁用日期")
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    @ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
    private Integer fIssysPreset;

    /** 组织隔离字段 */
    @ApiModelProperty("组织隔离字段")
    private Integer fMasterId;


	/** 会计政策之资产政策集合 */
	@ApiModelProperty("会计政策之资产政策集合")
	private List<TFaAcctPolicyAssetAddBo> policyAssetList;
}
