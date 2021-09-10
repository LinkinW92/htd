package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证号排序对象 t_gl_voucher_group_no
 *
 * @author toms
 * @date 2021-08-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_voucher_group_no")
public class TGlVoucherGroupNo implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 年 */
    private Integer fYear;

    /** 凭证号 */
    private Integer fVoucherGroupNo;

    /** 期间 */
    private Integer fPeriod;

    /** 凭证字ID */
    private Integer fVoucherGroupId;

    /** 账簿ID */
    private Integer fBookId;

}
