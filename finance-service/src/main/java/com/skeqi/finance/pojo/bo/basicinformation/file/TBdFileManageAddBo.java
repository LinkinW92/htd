package com.skeqi.finance.pojo.bo.basicinformation.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 文件管理添加对象 t_bd_file_manage
 *
 * @author toms
 * @date 2021-08-18
 */
@Data
@ApiModel("文件管理添加对象")
public class TBdFileManageAddBo {


    /** 名字 */
    @ApiModelProperty("名字")
    private String name;

    /** 地址 */
    @ApiModelProperty("地址")
    private String url;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 编号 */
    @ApiModelProperty("编号")
    private String code;

    /** 外部关联ID */
    @ApiModelProperty("外部关联ID")
    private String outNumber;

    /** 类型 1图片 2文档 3视频 */
    @ApiModelProperty("类型 1图片 2文档 3视频")
    private String fileType;

	/** 业务类型 F财务，M MES */
	@ApiModelProperty("业务类型 F财务，M MES")
	private String busType;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Integer createUser;
}
