package com.skeqi.finance.domain.endhandle.withholding;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证预提转入科目维度控制对象 t_gl_withholding_inacct_dimension
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_withholding_inacct_dimension")
public class TGlWithholdingInacctDimension implements Serializable {

    private static final long serialVersionUID=1L;

	/** 方案内码 */
	private Integer fSchemeId;

    /** 维度ID */
    private Integer dimensionId;

    /** 维度编码 */
    private String dsCode;

    /** 名称 */
    private String dsName;

    /** 预提关联ID */
    private Integer amortEntryId;

    /** 科目ID */
    private Integer acctId;

}
