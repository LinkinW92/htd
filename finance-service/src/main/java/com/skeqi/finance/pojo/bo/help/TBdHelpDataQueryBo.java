package com.skeqi.finance.pojo.bo.help;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 辅助资料分页查询对象 t_bd_help_data
 *
 * @author toms
 * @date 2021-07-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("辅助资料分页查询对象")
public class TBdHelpDataQueryBo extends BaseEntity {

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


	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;
	/** 备注 */
	@ApiModelProperty("备注")
	private String fRemark;
	/** 排序 */
	@ApiModelProperty("排序")
	private Integer fSort;
	/** 类别ID */
	@ApiModelProperty("类别ID")
	private Long fTypeId;
	/** 类别名字 */
	@ApiModelProperty("类别名字")
	private String fTypeName;

	/** 预设 */
	@ApiModelProperty("预设")
	private String fIssysPreset;

	private String fDocumentStatus;

}
