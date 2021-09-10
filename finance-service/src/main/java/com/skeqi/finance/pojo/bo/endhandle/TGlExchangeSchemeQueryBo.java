package com.skeqi.finance.pojo.bo.endhandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 期末调汇方案分页查询对象 t_gl_exchange_scheme
 *
 * @author toms
 * @date 2021-07-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("期末调汇方案分页查询对象")
public class TGlExchangeSchemeQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;
	/** 账簿 */
	@ApiModelProperty("账簿")
	private Integer fAccountBookId;
	/** 生成方式 */
	@ApiModelProperty("生成方式")
	private Integer fGenerateType;
	/** 操作人 */
	@ApiModelProperty("操作人")
	private Integer fOperatorId;
	/** 自动生成频率 */
	@ApiModelProperty("自动生成频率")
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
	private Integer fVchgroupId;
	/** 凭证日期方式 */
	@ApiModelProperty("凭证日期方式")
	private Integer fVoucherDateType;
	/** 汇率类型 */
	@ApiModelProperty("汇率类型")
	private Integer fExchangeType;
	/** 凭证处理方式 */
	@ApiModelProperty("凭证处理方式")
	private Integer fVchProcessType;
	/** 结果凭证处理方式 */
	@ApiModelProperty("结果凭证处理方式")
	private Integer fResultVchProcessType;
	/** 科目选择方式 */
	@ApiModelProperty("科目选择方式")
	private Integer fAcctChoseType;
	/** 凭证类型 */
	@ApiModelProperty("凭证类型")
	private Integer fTransferType;
	/** 汇兑损益科目 */
	@ApiModelProperty("汇兑损益科目")
	private Integer fExacCount;
	/** 汇兑损益科目方向 */
	@ApiModelProperty("汇兑损益科目方向")
	private Integer fDc;
	/** 调汇科目方向 */
	@ApiModelProperty("调汇科目方向")
	private Integer fExchangeAcctDc;
	/** 调汇日期方式 */
	@ApiModelProperty("调汇日期方式")
	private Integer fAllocateDateType;
	/** 按收益和损益分开生成凭证 */
	@ApiModelProperty("按收益和损益分开生成凭证")
	private String fPl;
	/** 是否已指定核算维度 */
	@ApiModelProperty("是否已指定核算维度")
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

}
