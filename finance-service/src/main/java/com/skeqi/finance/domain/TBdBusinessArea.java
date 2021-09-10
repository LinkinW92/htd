package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 业务领域对象 t_bd_business_area
 *
 * @author toms
 * @date 2021-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_business_area")
public class TBdBusinessArea implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "id")
    private Integer id;

    /** 名称 */
    private String name;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    private String fIssysPreset;

}
