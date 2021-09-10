package com.skeqi.finance.domain.endhandle.amortization;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证摊销-转入科目对象 t_gl_amort_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_amort_inacct")
public class TGlAmortInacct implements Serializable {

    private static final Long serialVersionUID=1L;


    /** 自增ID */
    @TableId(value = "f_id")
    private Integer fId;

    /** 方案内码 */
    private Integer fSchemeId;

    /** 转入科目ID */
    private Integer fEnterAccountId;

    /** 比例 */
    private BigDecimal fEnterRatio;

    /** 核算维度 */
    private Integer fDetailId;

    /** 固定 */
    private String fEnterRatioFixed;

}
