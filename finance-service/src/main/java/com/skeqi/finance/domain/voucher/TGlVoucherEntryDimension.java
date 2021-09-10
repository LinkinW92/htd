package com.skeqi.finance.domain.voucher;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 凭证分录维度控制对象 t_gl_voucher_entry_dimension
 *
 * @author toms
 * @date 2021-07-21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_voucher_entry_dimension")
public class TGlVoucherEntryDimension implements Serializable {

    private static final long serialVersionUID=1L;


    /** 凭证维度内码 */
    @TableId(value = "id")
    private Integer id;

	private String dimensionCode;
    /** 维度ID */
    private String dimensionAll;

    /** 维度编码 */
    private String dsCode;

    /** 名称 */
    private String dsName;

    /** 凭证分录ID */
    private Integer voucherEntryId;

	/** 账簿ID */
	private Integer bookId;

	/** 对方科目ID */
	private Integer acctId;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建用户 */
    private Integer createUser;

    /** 更新用户 */
    private Integer updateUser;

}
