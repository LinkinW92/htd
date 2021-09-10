package com.skeqi.finance.domain.asset;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 资产类别对象 t_fa_asset_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_asset_type")
public class TFaAssetType implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    /** 资产类别分组 */
    private Integer fGroup;

    /** 卡片编码规则 */
    private String fCodeRule;

    /** 资产编码规则 */
    private String fAssetCodeRule;

    /** 默认计量单位 */
    private Integer fUnitId;

    /** 原值包含进税项 1：包含 0不包含 */
    private String fIncludeTax;

    /** 卡片编码流水号长度 */
    private Integer fCardSwiftNumber;

    /** 资产编码流水号长度 */
    private Integer fAssetSwiftNumber;

    /** 卡片编码规则ID */
    private String fCodeRuleId;

    /** 资产编码规则ID */
    private String fAssetCodeRuleid;

    /** 创建组织 */
    private Integer fCreateOrgid;

    /** 创建人 */
    private Integer fCreatorid;

    /** 创建日期 */
    private Date fCreateDate;

    /** 使用组织 */
    private Integer fUseOrgid;

    /** 修改人 */
    private Integer fModifierid;

    /** 修改日期 */
    private Date fModifyDate;

    /** 数据状态 */
    private String fDocumentStatus;

    /** 审核人 */
    private Integer fAuditorid;

    /** 审核日期 */
    private Date fAuditDate;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Integer fForbidderid;

    /** 禁用日期 */
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    private Integer fIssysPreset;

    /** 组织隔离字段 */
    private Integer fMasterId;

}
