package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 结转损益方案对象 t_gl_plscheme
 *
 * @author toms
 * @date 2021-08-02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_plscheme")
public class TGlPlscheme implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    /** 摘要 */
    private String fExplanation;

    /** 描述 */
    private String fDescription;

    /** 账簿ID */
    private Integer fAccountBookId;

    /** 生成方式 1手动 0自动 */
    private Integer fGenerateType;

    /** 操作人 */
    private Integer fOperatorId;

    /** 频率：0,年1,月 2,周 */
    private Integer fFrequency;

    /** 生成天 */
    private Integer fGenerateDay;

    /** 生成小时 */
    private Integer fGenerateHour;

    /** 生成分钟 */
    private Integer fGenerateMinute;

    /** 名称 */
    private String fName;

    /** 最后执行时间 */
    private Date fLastExecuteTime;

    /** 数据状态 */
    private String fDocumentStatus;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Integer fForbiderId;

    /** 禁用时间 */
    private Date fForbidDate;

    /** 创建人 */
    private Integer fCreatorId;

    /** 创建日期 */
    private Date fCreatorDate;

    /** 修改人 */
    private Integer fModifierId;

    /** 修改日期 */
    private Date fModifyDate;

    /** 凭证字 */
    private Integer fVchgroupId;

    /** 凭证日期类型 */
    private Integer fVoucherDateType;

    /** 结转方式 */
    private Integer fTransferType;

    /** 凭证生成方式 */
    private Integer fVoucherType;

    /** 损益凭证处理方式 */
    private Integer fPlvchProcessType;

    /** 结果凭证处理方式 */
    private Integer fResultVchProcessType;

    /** 是否按科目的反方向生成凭证 */
    private String fIsAcctDc;

    /** 是否关联单位 */
    private String fIsConnectUnit;

    /** 是否合并损益科目 */
    private String fIsMergeplAcct;

    /** 核算维度 */
    private String fFlexItem;

    /** 科目选择方式 */
    private Integer fAcctChoseType;

    /** 是否暂存失败的凭证 */
    private String fIsDraftVoucher;

	private Integer fAccountId;

	private String fDc;

	/** 是否调整期凭证 */
	private String fIsadjustVoucher;

	/** 调整期会计年度 */
	private Integer fYear;

	/** 调整期会计期间 */
	private Integer fPeriod;

}
