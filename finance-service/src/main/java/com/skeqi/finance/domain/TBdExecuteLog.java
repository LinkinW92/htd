package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 执行业务日志记录对象 t_bd_execute_log
 *
 * @author toms
 * @date 2021-08-07
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_execute_log")
public class TBdExecuteLog implements Serializable {

    private static final long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "id")
    private Integer id;

    /** 外部关联执行ID */
    private Integer outExecuteId;

    private Integer executeId;

    /** 执行结果 1成功 0失败 */
    private String executeStatus;

    /** 详细信息 */
    private String executeDetail;

    private String executeType;

    /** $column.columnComment */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
