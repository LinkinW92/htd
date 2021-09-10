package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 折旧方法明细添加对象 t_fa_depr_method_entry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧方法明细添加对象")
public class TFaDeprMethodEntryAddBo {


    /** 折旧方法内码 */
    @ApiModelProperty("折旧方法内码")
    private Integer fId;

    /** 公式类型 1、期折旧额 2、期折旧率 */
    @ApiModelProperty("公式类型 1、期折旧额 2、期折旧率")
    private String fFormulaType;

    /** 公式内容 */
    @ApiModelProperty("公式内容")
    private String fFormulaContent;

    /** 是否静态 0静态 1动态 */
    @ApiModelProperty("是否静态 0静态 1动态")
    private String fIsactive;

    /** 是否最后两年 1：是最后两年 0否 */
    @ApiModelProperty("是否最后两年 1：是最后两年 0否")
    private String fIslastTwoyear;
}
