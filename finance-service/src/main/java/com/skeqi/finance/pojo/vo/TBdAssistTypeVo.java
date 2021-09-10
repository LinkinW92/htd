package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 辅助资料类别视图对象 t_bd_assist_type
 *
 * @author skeqi
 * @date 2021-07-13
 */
@Data
@ApiModel("辅助资料类别视图对象")
public class TBdAssistTypeVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 业务领域 */
	@Excel(name = "业务领域")
	@ApiModelProperty("业务领域")
	private String fBusinessArea;

	/** 类别名称 */
	@Excel(name = "类别名称")
	@ApiModelProperty("类别名称")
	private String fTypeName;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private String fIssysPreset;


}
