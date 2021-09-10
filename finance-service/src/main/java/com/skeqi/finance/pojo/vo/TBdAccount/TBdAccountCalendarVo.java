package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.finance.domain.TBdAccountPeriod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会计日历视图对象 t_bd_account_calendar
 *
 * @author toms
 * @date 2021-07-14
 */
@Data
@ApiModel("会计日历视图对象")
public class TBdAccountCalendarVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 编码  */
	@Excel(name = "编码 ")
	@ApiModelProperty("编码 ")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 会计日历开始日期 */
	@Excel(name = "会计日历开始日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("会计日历开始日期")
	private Date fStartDate;

	/** 会计日历截止日期 */
	@Excel(name = "会计日历截止日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("会计日历截止日期")
	private Date fEndDate;

	/** 期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日  */
	@Excel(name = "期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日 ")
	@ApiModelProperty("期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日 ")
	private String fPeriodType;

	/** 起始会计年度：前后控制各50年 */
	@Excel(name = "起始会计年度：前后控制各50年")
	@ApiModelProperty("起始会计年度：前后控制各50年")
	private String fStartYear;

	/** 创建组织 */
	@Excel(name = "创建组织")
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/** 使用组织 */
	@Excel(name = "使用组织")
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/** 修改人 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/** 修改日期 */
	@Excel(name = "修改日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/** 审核人 */
	@Excel(name = "审核人")
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/** 审核日期 */
	@Excel(name = "审核日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 禁用人 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/** 禁用日期 */
	@Excel(name = "禁用日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/** 主ID 继承与模板,用于组织隔离 */
	@Excel(name = "主ID 继承与模板,用于组织隔离")
	@ApiModelProperty("主ID 继承与模板,用于组织隔离")
	private Integer fMasterId;

	/** 期间数  */
	@Excel(name = "期间数 ")
	@ApiModelProperty("期间数 ")
	private Integer fPeriodCount;


	private List<TBdAccountPeriod> periodList;

}
