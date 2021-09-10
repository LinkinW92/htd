package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 基础-币别对象 t_bd_currency
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_currency")
public class TBdCurrency implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码  */
    @TableId(value = "f_currency_id")
    private Integer fCurrencyId;

    /** 名称 */
    private String fName;

    /** 币别编码  */
    private String fNumber;

    /** 货币代码  */
    private String fCode;

    /** 货币符号 */
    private String fSysmbol;


    /** 单价精度  */
    private Integer fPricedigits;

    /** 金额精度  */
    private Integer fAmountdigits;

    /** 优先级 */
    private Integer fPriority;

    /** 是否中间币  0不是 1是 */
    private String fIstrans;

    /** 基类主标识  */
    private Integer fMasterId;

    /** 显示货币符号  */
    private String fIsshowCsymbol;

    /** 货币符号内码 */
    private String fCurrencySymbolid;

    /** 显示顺序 */
    private String fFormatOrder;

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

	/**
	 * 舍入类型 1四舍五入  2四舍六入五双
	 */
	private String fSettleType;

}
