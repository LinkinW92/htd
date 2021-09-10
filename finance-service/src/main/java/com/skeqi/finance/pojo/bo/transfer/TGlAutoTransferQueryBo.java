package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 自动转账主分页查询对象 t_gl_auto_transfer
 *
 * @author toms
 * @date 2021-07-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("自动转账主分页查询对象")
public class TGlAutoTransferQueryBo extends BaseEntity {

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

	/** 编码 */
	@ApiModelProperty("名称")
	private String fName;
	/** 账簿内码 */
	@ApiModelProperty("账簿内码")
	private Integer fBookId;
	private String fBookName;
	/** 转账类型 */
	@ApiModelProperty("转账类型")
	private String fTransferType;
	/** 凭证字 */
	@ApiModelProperty("凭证字")
	private Integer fVoucherGroupId;
	/** 转账期间 */
	@ApiModelProperty("转账期间")
	private String fPeriodRange;
	/** 最近一次转账时间 */
	@ApiModelProperty("最近一次转账时间")
	private Date fLastDate;
	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;
	/** 禁用人 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderId;
	/** 禁用日期 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	@ApiModelProperty("操作方式")
	private String fOptWay;

	@ApiModelProperty("操作频率")
	private String fOptFrequency;

}
