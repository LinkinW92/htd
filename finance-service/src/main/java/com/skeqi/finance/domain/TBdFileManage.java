package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 文件管理对象 t_bd_file_manage
 *
 * @author toms
 * @date 2021-08-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_file_manage")
public class TBdFileManage implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "id")
    private Integer id;

    /** 名字 */
    private String name;

    /** 地址 */
    private String url;

    /** 备注 */
    private String remark;

    /** 编号 */
    private String code;

    /** 外部关联ID */
    private String outNumber;

    /** 类型 1图片 2文档 3视频 */
    private String fileType;

	private String busType;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建人 */
    private Integer createUser;

}
