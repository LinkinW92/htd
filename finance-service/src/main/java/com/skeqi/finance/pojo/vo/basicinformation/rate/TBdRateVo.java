package com.skeqi.finance.pojo.vo.basicinformation.rate;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 基础汇率视图对象 t_bd_rate
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("基础汇率视图对象")
public class TBdRateVo {

	private static final long serialVersionUID = 1L;

	/** 汇率类型内码 */
	@ApiModelProperty("汇率内码")
	private Integer fRateId;

	/** 汇率类型内码 */
	@Excel(name = "汇率类型内码")
	@ApiModelProperty("汇率类型内码")
	private Integer fRateTypeId;

	/** 原币内码  */
	@Excel(name = "原币内码 ")
	@ApiModelProperty("原币内码 ")
	private Integer fCyforid;

	@ApiModelProperty("汇率名称")
	private String fRateTypeName;


	@ApiModelProperty("原币名")
	private String fCyforName;

	private String fCyforNumber;


	@ApiModelProperty("目标币名")
	private String fCytoName;
	/** 目标币内码 */
	@Excel(name = "目标币内码")
	@ApiModelProperty("目标币内码")
	private Integer fCytoid;



	/** 直接汇率 */
	@Excel(name = "直接汇率")
	@ApiModelProperty("直接汇率")
	private BigDecimal fExchangeRate;

	/** 间接汇率 */
	@Excel(name = "间接汇率")
	@ApiModelProperty("间接汇率")
	private BigDecimal fReverseexRate;

	/** 生效日期 */
	@Excel(name = "生效日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("生效日期")
	private Date fBegDate;

	/** 失效日期 */
	@Excel(name = "失效日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("失效日期")
	private Date fEndDate;

	/** 特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见 */
	@Excel(name = "特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见")
	@ApiModelProperty("特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见")
	private String fNumber;


	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;


	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;



	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;


}
