package com.skeqi.finance.pojo.bo.basicinformation.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 文件管理分页查询对象 t_bd_file_manage
 *
 * @author toms
 * @date 2021-08-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("文件管理分页查询对象")
public class TBdFileManageQueryBo extends BaseEntity {

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


	/** 名字 */
	@ApiModelProperty("名字")
	private String name;
	/** 地址 */
	@ApiModelProperty("地址")
	private String url;
	/** 编号 */
	@ApiModelProperty("编号")
	private String code;
	/** 外部关联ID */
	@ApiModelProperty("外部关联ID")
	private String outNumber;

	/** 类型 1图片 2文档 3视频 */
	@ApiModelProperty("类型 1图片 2文档 3视频")
	private String fileType;
	@ApiModelProperty("业务类型")
	private String busType;
	/** 创建人 */
	@ApiModelProperty("创建人")
	private Integer createUser;

}
