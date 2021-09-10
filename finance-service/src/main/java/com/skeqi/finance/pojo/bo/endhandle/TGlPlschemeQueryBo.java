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
 * 结转损益方案分页查询对象 t_gl_plscheme
 *
 * @author toms
 * @date 2021-08-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("结转损益方案分页查询对象")
public class TGlPlschemeQueryBo extends BaseEntity {

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
	/** 摘要 */
	@ApiModelProperty("摘要")
	private String fExplanation;
	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;
	/** 账簿ID */
	@ApiModelProperty("账簿ID")
	private Integer fAccountBookId;
	/** 生成方式 1手动 0自动 */
	@ApiModelProperty("生成方式 1手动 0自动")
	private Integer fGenerateType;
	/** 操作人 */
	@ApiModelProperty("操作人")
	private Integer fOperatorId;
	/** 频率：0,年1,月 2,周 */
	@ApiModelProperty("频率：0,年1,月 2,周")
	private Integer fFrequency;
	/** 生成天 */
	@ApiModelProperty("生成天")
	private Integer fGenerateDay;
	/** 生成小时 */
	@ApiModelProperty("生成小时")
	private Integer fGenerateHour;
	/** 生成分钟 */
	@ApiModelProperty("生成分钟")
	private Integer fGenerateMinute;
	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
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
	/** 创建日期 */
	@ApiModelProperty("创建日期")
	private Date fCreatorDate;
	/** 修改人 */
	@ApiModelProperty("修改人")
	private Integer fModifierId;
	/** 修改日期 */
	@ApiModelProperty("修改日期")
	private Date fModifyDate;
	/** 凭证字 */
	@ApiModelProperty("凭证字")
	private Integer fVchgroupId;
	/** 凭证日期类型 */
	@ApiModelProperty("凭证日期类型")
	private Integer fVoucherDateType;
	/** 结转方式 */
	@ApiModelProperty("结转方式")
	private Integer fTransferType;
	/** 凭证生成方式 */
	@ApiModelProperty("凭证生成方式")
	private Integer fVoucherType;
	/** 损益凭证处理方式 */
	@ApiModelProperty("损益凭证处理方式")
	private Integer fPlvchProcessType;
	/** 结果凭证处理方式 */
	@ApiModelProperty("结果凭证处理方式")
	private Integer fResultVchProcessType;
	/** 是否按科目的反方向生成凭证 */
	@ApiModelProperty("是否按科目的反方向生成凭证")
	private String fIsAcctDc;
	/** 是否关联单位 */
	@ApiModelProperty("是否关联单位")
	private String fIsConnectUnit;
	/** 是否合并损益科目 */
	@ApiModelProperty("是否合并损益科目")
	private String fIsMergeplAcct;
	/** 核算维度 */
	@ApiModelProperty("核算维度")
	private Integer fFlexItem;
	/** 科目选择方式 */
	@ApiModelProperty("科目选择方式")
	private Integer fAcctChoseType;
	/** 是否暂存失败的凭证 */
	@ApiModelProperty("是否暂存失败的凭证")
	private String fIsDraftVoucher;

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
