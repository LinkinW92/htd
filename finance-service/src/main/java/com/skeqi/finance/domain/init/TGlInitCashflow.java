package com.skeqi.finance.domain.init;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
    * 现金流量项目初始余额
    */
@ApiModel(value="com-skeqi-finance-domain-init-TGlInitCashflow")
@Data
@TableName(value = "t_gl_init_cashflow")
public class TGlInitCashflow implements Serializable {
    /**
     * 内码
     */
    @TableId(value = "f_id", type = IdType.INPUT)
    @ApiModelProperty(value="内码")
    private Integer fId;

    /**
     * 账簿内码
     */
    @TableField(value = "f_account_book_id")
    @ApiModelProperty(value="账簿内码")
    private Integer fAccountBookId;

    /**
     * 现金流量项目内码
     */
    @TableField(value = "f_item_id")
    @ApiModelProperty(value="现金流量项目内码")
    private Integer fItemId;

    /**
     * 币别
     */
    @TableField(value = "f_currency_id")
    @ApiModelProperty(value="币别")
    private Integer fCurrencyId;

    /**
     * 是否明细
     */
    @TableField(value = "f_is_detail")
    @ApiModelProperty(value="是否明细")
    private String fIsDetail;

    /**
     * 原币金额
     */
    @TableField(value = "f_amount_for")
    @ApiModelProperty(value="原币金额")
    private BigDecimal fAmountFor;

    /**
     * 本位币金额
     */
    @TableField(value = "f_amount")
    @ApiModelProperty(value="本位币金额")
    private BigDecimal fAmount;

    /**
     * 现金流量项目类别内码
     */
    @TableField(value = "f_type_id")
    @ApiModelProperty(value="现金流量项目类别内码")
    private Integer fTypeId;

    private static final long serialVersionUID = 1L;

    public static final String COL_F_ID = "f_id";

    public static final String COL_F_ACCOUNT_BOOK_ID = "f_account_book_id";

    public static final String COL_F_ITEM_ID = "f_item_id";

    public static final String COL_F_CURRENCY_ID = "f_currency_id";

    public static final String COL_F_IS_DETAIL = "f_is_detail";

    public static final String COL_F_AMOUNT_FOR = "f_amount_for";

    public static final String COL_F_AMOUNT = "f_amount";

    public static final String COL_F_TYPE_ID = "f_type_id";
}