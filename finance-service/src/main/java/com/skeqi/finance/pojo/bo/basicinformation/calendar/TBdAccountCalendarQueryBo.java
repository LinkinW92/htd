package com.skeqi.finance.pojo.bo.basicinformation.calendar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 会计日历分页查询对象 t_bd_account_calendar
 *
 * @author toms
 * @date 2021-07-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会计日历分页查询对象")
public class TBdAccountCalendarQueryBo extends BaseEntity {

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


	/** 编码  */
	@ApiModelProperty("编码 ")
	private String fNumber;
	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
	/** 会计日历开始日期 */
	@ApiModelProperty("会计日历开始日期")
	private Date fStartDate;
	/** 会计日历截止日期 */
	@ApiModelProperty("会计日历截止日期")
	private Date fEndDate;
	/** 期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日  */
	@ApiModelProperty("期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日 ")
	private String fPeriodType;
	/** 起始会计年度：前后控制各50年 */
	@ApiModelProperty("起始会计年度：前后控制各50年")
	private String fStartYear;
	/** 创建组织 */
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;
	/** 创建人 */

	@ApiModelProperty("创建人")
	private Integer fCreatorid;
	/** 创建日期 */
	@ApiModelProperty("创建日期")
	private Date fCreateDate;
	/** 使用组织 */
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;
	/** 修改人 */
	@ApiModelProperty("修改人")
	private Integer fModifierid;
	/** 修改日期 */
	@ApiModelProperty("修改日期")
	private Date fModifyDate;
	/** 数据状态 */
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;
	/** 审核人 */
	@ApiModelProperty("审核人")
	private Integer fAuditorid;
	/** 审核日期 */
	@ApiModelProperty("审核日期")
	private Date fAuditDate;
	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;
	/** 禁用人 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;
	/** 禁用日期 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;
	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;
	/** 主ID 继承与模板,用于组织隔离 */
	@ApiModelProperty("主ID 继承与模板,用于组织隔离")
	private Integer fMasterId;
	/** 期间数  */
	@ApiModelProperty("期间数 ")
	private Integer fPeriodCount;

}
