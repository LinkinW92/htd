package com.skeqi.manage.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 职级对象 sys_dept_rank
 *
 * @author toms
 * @date 2021-08-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_dept_rank")
public class SysDeptRank implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增 */
    @TableId(value = "id")
    private Integer id;

    /** 编码 */
    private String code;

    /** 职级名称 */
    private String name;

    /** 等级 */
    private Integer level;

    /** 时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
