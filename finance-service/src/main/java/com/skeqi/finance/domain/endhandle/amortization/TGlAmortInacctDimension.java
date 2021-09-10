package com.skeqi.finance.domain.endhandle.amortization;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证摊销转入科目维度控制对象 t_gl_amort_inacct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_amort_inacct_dimension")
public class TGlAmortInacctDimension implements Serializable {

    private static final long serialVersionUID=1L;


    /** 维度ID */
    private Integer dimensionId;

    /** 维度编码 */
    private String dsCode;

    /** 名称 */
    private String dsName;

    /** 摊销关联ID */
    private Integer amortEntryId;

    /** 科目ID */
    private Integer acctId;

}
