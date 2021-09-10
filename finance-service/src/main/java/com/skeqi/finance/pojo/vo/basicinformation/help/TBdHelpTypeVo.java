package com.skeqi.finance.pojo.vo.basicinformation.help;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.annotation.Excel;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 辅助资料类别视图对象 t_bd_help_type
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("辅助资料类别视图对象")
public class TBdHelpTypeVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 业务领域 */
	@Excel(name = "业务领域")
	@ApiModelProperty("业务领域")
	private String fBusinessArea;

	@ApiModelProperty("业务领域名字")
	private String fBusinessAreaName;
	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 类别名称 */
	@Excel(name = "类别名称")
	@ApiModelProperty("类别名称")
	private String fTypeName;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 上级ID */
	@Excel(name = "上级ID")
	@ApiModelProperty("上级ID")
	private String fParentId;

	/** 上级名称 */
	@Excel(name = "上级名称")
	@ApiModelProperty("上级名称")
	private String fParentName;


	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;


	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;



}
