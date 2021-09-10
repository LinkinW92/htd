package com.skeqi.finance.pojo.vo.basicinformation.base;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 文件管理视图对象 t_bd_file_manage
 *
 * @author toms
 * @date 2021-08-18
 */
@Data
@ApiModel("文件管理视图对象")
public class TBdFileManageVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer id;

	/** 名字 */
	@Excel(name = "名字")
	@ApiModelProperty("名字")
	private String name;

	/** 地址 */
	@Excel(name = "地址")
	@ApiModelProperty("地址")
	private String url;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;

	/** 编号 */
	@Excel(name = "编号")
	@ApiModelProperty("编号")
	private String code;

	/** 外部关联ID */
	@Excel(name = "外部关联ID")
	@ApiModelProperty("外部关联ID")
	private String outNumber;

	/** 类型 1图片 2文档 3视频 */
	@Excel(name = "类型 1图片 2文档 3视频")
	@ApiModelProperty("类型 1图片 2文档 3视频")
	private String fileType;

	@Excel(name = "业务员类型")
	@ApiModelProperty("业务员类型")
	private String busType;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer createUser;


}
