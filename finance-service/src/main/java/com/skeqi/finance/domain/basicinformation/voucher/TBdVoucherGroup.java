package com.skeqi.finance.domain.basicinformation.voucher;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证字对象 t_bd_voucher_group
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_voucher_group")
public class TBdVoucherGroup implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_vchgroup_id")
    private Integer fVchgroupId;

    /** 限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0
  */
    private String fLimitMulti;

    /** 科目表内码，用于单据体中科目的过滤  */
    private Integer fAccttableId;

    /** 是否默认  0 非默认 1 默认 */
    private String fIsdefault;


    /** 创建组织 */
    private Integer fCreateOrgid;

    /** 创建人 */
    private Integer fCreatorid;

    /** 创建日期 */
    @TableField(fill = FieldFill.INSERT)
    private Date fCreateDate;

    /** 使用组织 */
    private Integer fUseOrgid;

    /** 修改人 */
    private Integer fModifierid;

    /** 修改日期 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
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

	/** 凭证字 */
	private String fVoucherWords;

}
