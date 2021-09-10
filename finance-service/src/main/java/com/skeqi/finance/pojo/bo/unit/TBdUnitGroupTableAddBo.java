package com.skeqi.finance.pojo.bo.unit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 计量单位分组添加对象 t_bd_unit_group_table
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("计量单位分组添加对象")
public class TBdUnitGroupTableAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
    private String fName;

    /** 上级ID */
    @ApiModelProperty("上级ID")
    private Integer fParentId;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;
}
