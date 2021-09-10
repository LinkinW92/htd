package com.skeqi.finance.pojo.vo.basicinformation.help;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 辅助资料视图对象 t_bd_help_data
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@ApiModel("辅助资料视图对象")
public class TBdHelpDataVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String fRemark;

	/** 排序 */
	@Excel(name = "排序")
	@ApiModelProperty("排序")
	private Integer fSort;

	/** 类别ID */
	@Excel(name = "类别ID")
	@ApiModelProperty("类别ID")
	private Long fTypeId;

	/** 类别名字 */
	@Excel(name = "类别名字")
	@ApiModelProperty("类别名字")
	private String fTypeName;


	private String fDocumentStatus;

	private String fIssysPreset;


}
