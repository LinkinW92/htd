package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 期末调汇方案视图对象 t_gl_exchange_scheme
 *
 * @author toms
 * @date 2021-07-30
 */
@Data
@ApiModel("期末调汇方案视图对象")
public class TGlExchangeSchemeVo {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty("主键")
	private Integer fId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 账簿 */
	@Excel(name = "账簿")
	@ApiModelProperty("账簿")
	private Integer fAccountBookId;

	/** 生成方式 */
	@Excel(name = "生成方式")
	@ApiModelProperty("生成方式")
	private Integer fGenerateType;

	/** 操作人 */
	@Excel(name = "操作人")
	@ApiModelProperty("操作人")
	private Integer fOperatorId;

	/** 自动生成频率 */
	@Excel(name = "自动生成频率")
	@ApiModelProperty("自动生成频率")
	private Integer fFrequency;

	/** 自动生成天 */
	@Excel(name = "自动生成天")
	@ApiModelProperty("自动生成天")
	private Integer fGenerateDay;

	/** 自动生成时 */
	@Excel(name = "自动生成时")
	@ApiModelProperty("自动生成时")
	private Integer fGenerateHour;

	/** 自动生成分 */
	@Excel(name = "自动生成分")
	@ApiModelProperty("自动生成分")
	private Integer fGenerateMinute;

	/** 最后执行时间 */
	@Excel(name = "最后执行时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("最后执行时间")
	private Date fLastExecuteTime;

	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 禁用人 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbiderId;

	/** 禁用时间 */
	@Excel(name = "禁用时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorId;

	/** 创建时间 */
	@Excel(name = "创建时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建时间")
	private Date fCreatorDate;

	/** 修改人 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierId;

	/** 修改时间 */
	@Excel(name = "修改时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改时间")
	private Date fModifyDate;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private Integer fVchgroupId;

	/** 凭证日期方式 */
	@Excel(name = "凭证日期方式")
	@ApiModelProperty("凭证日期方式")
	private Integer fVoucherDateType;

	/** 汇率类型 */
	@Excel(name = "汇率类型")
	@ApiModelProperty("汇率类型")
	private Integer fExchangeType;

	/** 凭证处理方式 */
	@Excel(name = "凭证处理方式")
	@ApiModelProperty("凭证处理方式")
	private Integer fVchProcessType;

	/** 结果凭证处理方式 */
	@Excel(name = "结果凭证处理方式")
	@ApiModelProperty("结果凭证处理方式")
	private Integer fResultVchProcessType;

	/** 科目选择方式 */
	@Excel(name = "科目选择方式")
	@ApiModelProperty("科目选择方式")
	private Integer fAcctChoseType;

	/** 凭证类型 */
	@Excel(name = "凭证类型")
	@ApiModelProperty("凭证类型")
	private Integer fTransferType;

	/** 汇兑损益科目 */
	@Excel(name = "汇兑损益科目")
	@ApiModelProperty("汇兑损益科目")
	private Integer fExacCount;

	/** 汇兑损益科目方向 */
	@Excel(name = "汇兑损益科目方向")
	@ApiModelProperty("汇兑损益科目方向")
	private Integer fDc;

	/** 调汇科目方向 */
	@Excel(name = "调汇科目方向")
	@ApiModelProperty("调汇科目方向")
	private Integer fExchangeAcctDc;

	/** 调汇日期方式 */
	@Excel(name = "调汇日期方式")
	@ApiModelProperty("调汇日期方式")
	private Integer fAllocateDateType;

	/** 按收益和损益分开生成凭证 */
	@Excel(name = "按收益和损益分开生成凭证")
	@ApiModelProperty("按收益和损益分开生成凭证")
	private String fPl;

	/** 是否已指定核算维度 */
	@Excel(name = "是否已指定核算维度")
	@ApiModelProperty("是否已指定核算维度")
	private String fIssetFlexItem;

	/** 是否暂存失败的凭证 */
	@Excel(name = "是否暂存失败的凭证")
	@ApiModelProperty("是否暂存失败的凭证")
	private String fIsdraftVoucher;

	/** 摘要 */
	@ApiModelProperty("摘要")
	private String fExplanation;

	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;

	/** 是否调整期凭证 */
	@ApiModelProperty("是否调整期凭证")
	private String fIsadjustVoucher;

	/** 调整期会计年度 */
	@ApiModelProperty("调整期会计年度")
	private Integer fYear;

	/** 调整期会计期间 */
	@ApiModelProperty("调整期会计期间")
	private Integer fPeriod;

	private String fBookName;
	private String fBookNumber;

	private String fVoucherWords;

	private String orgName;
	private String orgId;

	private String acctName;

	private String acctNumber;

	private String rateNumber;
	private String rateName;
	private Integer rateId;
	private Integer fBaseCurrencyId;


	/**
	 * 维度
	 */
	@ApiModelProperty("维度")
	List<TGlExchangeFlexEntryVo> dimension;

	/**
	 * 科目分录
	 */
	@ApiModelProperty("科目分录")
	List<TGlExchangeSchemeEntryVo> acctEntry;


}
