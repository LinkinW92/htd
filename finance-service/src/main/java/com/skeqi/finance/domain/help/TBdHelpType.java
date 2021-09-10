package com.skeqi.finance.domain.help;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 辅助资料类别对象 t_bd_help_type
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_help_type")
public class TBdHelpType implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 业务领域 */
    private Integer fBusinessArea;

	/** 业务领域 */
	private String fBusinessAreaName;

	/** 编码 */
	private String fNumber;

    /** 类别名称 */
    private String fTypeName;

    /** 描述 */
    private String fDescription;

    /** 上级ID */
    private Integer fParentId;

    /** 创建组织 */
    private Long fCreateOrgid;

    /** 使用组织 */
    private Long fUseOrgid;

    /** 创建人 */
    private Long fCreatorid;

    /** 创建日期 */
    private Date fCreateDate;

    /** 修改人 */
    private Long fModifierid;

    /** 修改日期 */
    private Date fModifyDate;

    /** 数据状态 */
    private String fDocumentStatus;

    /** 审核人 */
    private Long fAuditorid;

    /** 审核日期 */
    private Date fAuditDate;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Long fForbidderid;

    /** 禁用日期 */
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    private Integer fIssysPreset;

    /** 组织隔离字段 */
    private Long fMasterId;

}
