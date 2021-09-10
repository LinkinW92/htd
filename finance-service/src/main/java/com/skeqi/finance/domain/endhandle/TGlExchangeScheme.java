package com.skeqi.finance.domain.endhandle;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 期末调汇方案对象 t_gl_exchange_scheme
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_exchange_scheme")
public class TGlExchangeScheme implements Serializable {

    private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 编码 */
    private String fNumber;

    private String fName;

    /** 账簿 */
    private Integer fAccountBookId;

    /** 生成方式 */
    private Integer fGenerateType;

    /** 操作人 */
    private Integer fOperatorId;

    /** 自动生成频率 */
    private Integer fFrequency;

    /** 自动生成天 */
    private Integer fGenerateDay;

    /** 自动生成时 */
    private Integer fGenerateHour;

    /** 自动生成分 */
    private Integer fGenerateMinute;

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

    /** 创建时间 */
    private Date fCreatorDate;

    /** 修改人 */
    private Integer fModifierId;

    /** 修改时间 */
    private Date fModifyDate;

    /** 凭证字 */
    private Integer fVchgroupId;

    /** 凭证日期方式 */
    private Integer fVoucherDateType;

    /** 汇率类型 */
    private Integer fExchangeType;

    /** 凭证处理方式 */
    private Integer fVchProcessType;

    /** 结果凭证处理方式 */
    private Integer fResultVchProcessType;

    /** 科目选择方式 */
    private Integer fAcctChoseType;

    /** 凭证类型 */
    private Integer fTransferType;

    /** 汇兑损益科目 */
    private Integer fExacCount;

    /** 汇兑损益科目方向 */
    private Integer fDc;

    /** 调汇科目方向 */
    private Integer fExchangeAcctDc;

    /** 调汇日期方式 */
    private Integer fAllocateDateType;

    /** 按收益和损益分开生成凭证 */
    private String fPl;

    /** 是否已指定核算维度 */
    private String fIssetFlexItem;

    /** 是否暂存失败的凭证 */
    private String fIsdraftVoucher;

	/** 是否调整期凭证 */
	private String fIsadjustVoucher;

	/** 调整期会计年度 */
	private Integer fYear;

	/** 调整期会计期间 */
	private Integer fPeriod;

	private String fDescription;

	private String fExplanation;

}
