package com.skeqi.finance.domain.endhandle.withholding;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证预提-转入科目对象 t_gl_withholding_inacct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_withholding_inacct")
public class TGlWithholdingInacct implements Serializable {

    private static final long serialVersionUID=1L;

	/** 自增ID */
	@TableId(value = "f_id")
	private Integer fId;

	/** 方案内码 */
	private Integer fSchemeId;

    private Integer fEnterAccountId;

    /** 比例 */
    private BigDecimal fEnterRatio;

    /** 核算维度 */
    private Integer fEnterDetail;

    /** 固定 */
    private String fEnterRatioFixed;

}
