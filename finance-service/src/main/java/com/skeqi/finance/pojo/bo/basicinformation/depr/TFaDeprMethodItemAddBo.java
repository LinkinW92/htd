package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 折旧方法元素添加对象 t_fa_depr_method_item
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧方法元素添加对象")
public class TFaDeprMethodItemAddBo {


    /** 字段类型 1：文本 2：金额  */
    @ApiModelProperty("字段类型 1：文本 2：金额 ")
    private Integer fFieldType;

    /** 对应的取数字段  */
    @ApiModelProperty("对应的取数字段 ")
    private String fFormulaType;

    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;
}
