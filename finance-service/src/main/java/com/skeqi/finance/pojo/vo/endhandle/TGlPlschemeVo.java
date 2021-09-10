package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.finance.domain.endhandle.TGlPlschemeEntry;
import com.skeqi.finance.domain.endhandle.TGlPlschemeFlex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 结转损益方案视图对象 t_gl_plscheme
 *
 * @author toms
 * @date 2021-08-02
 */
@Data
@ApiModel("结转损益方案视图对象")
public class TGlPlschemeVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Long fId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 摘要 */
	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String fExplanation;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 账簿ID */
	@Excel(name = "账簿ID")
	@ApiModelProperty("账簿ID")
	private Long fAccountBookId;

	/** 生成方式 1手动 0自动 */
	@Excel(name = "生成方式 1手动 0自动")
	@ApiModelProperty("生成方式 1手动 0自动")
	private Long fGenerateType;

	/** 操作人 */
	@Excel(name = "操作人")
	@ApiModelProperty("操作人")
	private Long fOperatorId;

	/** 频率：0,年1,月 2,周 */
	@Excel(name = "频率：0,年1,月 2,周")
	@ApiModelProperty("频率：0,年1,月 2,周")
	private Long fFrequency;

	/** 生成天 */
	@Excel(name = "生成天")
	@ApiModelProperty("生成天")
	private Long fGenerateDay;

	/** 生成小时 */
	@Excel(name = "生成小时")
	@ApiModelProperty("生成小时")
	private Long fGenerateHour;

	/** 生成分钟 */
	@Excel(name = "生成分钟")
	@ApiModelProperty("生成分钟")
	private Long fGenerateMinute;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

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
	private Long fForbiderId;

	/** 禁用时间 */
	@Excel(name = "禁用时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用时间")
	private Date fForbidDate;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Long fCreatorId;

	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreatorDate;

	/** 修改人 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Long fModifierId;

	/** 修改日期 */
	@Excel(name = "修改日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private Long fVchgroupId;

	/** 凭证日期类型 */
	@Excel(name = "凭证日期类型")
	@ApiModelProperty("凭证日期类型")
	private Long fVoucherDateType;

	/** 结转方式 */
	@Excel(name = "结转方式")
	@ApiModelProperty("结转方式")
	private Long fTransferType;

	/** 凭证生成方式 */
	@Excel(name = "凭证生成方式")
	@ApiModelProperty("凭证生成方式")
	private Long fVoucherType;

	/** 损益凭证处理方式 */
	@Excel(name = "损益凭证处理方式")
	@ApiModelProperty("损益凭证处理方式")
	private Long fPlvchProcessType;

	/** 结果凭证处理方式 */
	@Excel(name = "结果凭证处理方式")
	@ApiModelProperty("结果凭证处理方式")
	private Long fResultVchProcessType;

	/** 是否按科目的反方向生成凭证 */
	@Excel(name = "是否按科目的反方向生成凭证")
	@ApiModelProperty("是否按科目的反方向生成凭证")
	private String fIsAcctDc;

	/** 是否关联单位 */
	@Excel(name = "是否关联单位")
	@ApiModelProperty("是否关联单位")
	private String fIsConnectUnit;

	/** 是否合并损益科目 */
	@Excel(name = "是否合并损益科目")
	@ApiModelProperty("是否合并损益科目")
	private String fIsMergeplAcct;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private Long fFlexItem;

	/** 科目选择方式 */
	@Excel(name = "科目选择方式")
	@ApiModelProperty("科目选择方式")
	private Long fAcctChoseType;

	/** 是否暂存失败的凭证 */
	@Excel(name = "是否暂存失败的凭证")
	@ApiModelProperty("是否暂存失败的凭证")
	private String fIsDraftVoucher;

	@ApiModelProperty("损益类科目ID")
	private Integer fAccountId;

	@ApiModelProperty("科目方向")
	private String fDc;

	/** 是否调整期凭证 */
	@ApiModelProperty("是否调整期凭证")
	private String fIsadjustVoucher;

	/** 调整期会计年度 */
	@ApiModelProperty("调整期会计年度")
	private Integer fYear;

	/** 调整期会计期间 */
	@ApiModelProperty("调整期会计期间")
	private Integer fPeriod;


	private List<TGlPlschemeEntryVo> acctEntry;

	private List<TGlPlschemeFlex> flexList;

	private String acctName;

	private String acctNumber;

	private String fVoucherWords;

	private String bookNumber;
	private String bookName;

	private String orgName;

}
