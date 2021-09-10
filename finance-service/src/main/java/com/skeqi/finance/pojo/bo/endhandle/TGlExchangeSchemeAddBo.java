package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 期末调汇方案添加对象 t_gl_exchange_scheme
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇方案添加对象")
public class TGlExchangeSchemeAddBo {

	private Integer fId;
    /** 编码 */
    @ApiModelProperty("编码")
    @NotBlank(message = "编码不能为空")
    private String fNumber;

	/** 名称 */
	@ApiModelProperty("名称")
	@NotBlank(message = "名称不能为空")
	private String fName;

	/** 摘要 */
	@ApiModelProperty("摘要")
	@NotBlank(message = "摘要不能为空")
	private String fExplanation;

	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;
    /** 账簿 */
    @ApiModelProperty("账簿")
	@NotNull(message = "账簿不能为空")
    private Integer fAccountBookId;

    /** 生成方式 */
    @ApiModelProperty("执行方式")
	@NotNull(message = "执行方式不能为空")
    private Integer fGenerateType;

    /** 操作人 */
    @ApiModelProperty("操作人")
    private Integer fOperatorId;

    /** 自动生成频率 */
    @ApiModelProperty("自动生成频率")
	@NotNull(message = "执行频率不能为空")
    private Integer fFrequency;

    /** 自动生成天 */
    @ApiModelProperty("自动生成天")
    private Integer fGenerateDay;

    /** 自动生成时 */
    @ApiModelProperty("自动生成时")
    private Integer fGenerateHour;

    /** 自动生成分 */
    @ApiModelProperty("自动生成分")
    private Integer fGenerateMinute;

    /** 最后执行时间 */
    @ApiModelProperty("最后执行时间")
    private Date fLastExecuteTime;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    private String fDocumentStatus;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    private Integer fForbiderId;

    /** 禁用时间 */
    @ApiModelProperty("禁用时间")
    private Date fForbidDate;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Integer fCreatorId;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date fCreatorDate;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private Integer fModifierId;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date fModifyDate;

    /** 凭证字 */
    @ApiModelProperty("凭证字")
	@NotNull(message = "凭证字不能为空")
    private Integer fVchgroupId;

    /** 凭证日期方式 */
    @ApiModelProperty("凭证日期方式")
	@NotNull(message = "凭证日期方式不能为空")
    private Integer fVoucherDateType;

    /** 汇率类型 */
    @ApiModelProperty("汇率类型")
	@NotNull(message = "汇率类型不能为空")
    private Integer fExchangeType;

    /** 凭证处理方式 */
    @ApiModelProperty("未过账外币凭证处理方式")
	@NotNull(message = "未过账外币凭证处理方式不能为空")
    private Integer fVchProcessType;

    /** 结果凭证处理方式 */
    @ApiModelProperty("调汇凭证后续处理方式")
	@NotNull(message = "调汇凭证后续处理方式不能为空")
    private Integer fResultVchProcessType;

    /** 科目选择方式 */
    @ApiModelProperty("科目选择方式")
	@NotNull(message = "科目选择方式不能为空")
    private Integer fAcctChoseType;

    /** 调汇类型 */
    @ApiModelProperty("调汇类型")
	@NotNull(message = "调汇类型不能为空")
    private Integer fTransferType;

    /** 汇兑损益科目 */
    @ApiModelProperty("汇兑损益科目")
	@NotNull(message = "汇兑损益科目不能为空")
    private Integer fExacCount;

    /** 汇兑损益科目方向 */
    @ApiModelProperty("汇兑损益科目方向")
	@NotNull(message = "汇兑损益科目方向不能为空")
    private Integer fDc;

    /** 调汇科目方向 */
    @ApiModelProperty("调汇科目方向")
	@NotNull(message = "调汇科目方向不能为空")
    private Integer fExchangeAcctDc;

    /** 调汇日期方式 */
    @ApiModelProperty("调汇日期方式")
	@NotNull(message = "调汇日期方式不能为空")
    private Integer fAllocateDateType;

    /** 按收益和损益分开生成凭证 */
    @ApiModelProperty("按收益和损益分开生成凭证")
    private String fPl;

    /** 是否已指定核算维度 */
    @ApiModelProperty("是否已指定核算维度")
	@NotBlank(message = "是否已指定核算维度不能为空")
    private String fIssetFlexItem;

    /** 是否暂存失败的凭证 */
    @ApiModelProperty("是否暂存失败的凭证")
    private String fIsdraftVoucher;

	/** 是否调整期凭证 */
	@ApiModelProperty("是否调整期凭证")
	private String fIsadjustVoucher;

	/** 调整期会计年度 */
	@ApiModelProperty("调整期会计年度")
	private Integer fYear;

	/** 调整期会计期间 */
	@ApiModelProperty("调整期会计期间")
	private Integer fPeriod;

	/**
	 * 维度
	 */
	@ApiModelProperty("维度")
	List<TGlExchangeFlexEntryAddBo>  dimension;

	/**
	 * 科目分录
	 */
	@ApiModelProperty("科目分录")
	List<TGlExchangeSchemeEntryAddBo> acctEntry;


}
