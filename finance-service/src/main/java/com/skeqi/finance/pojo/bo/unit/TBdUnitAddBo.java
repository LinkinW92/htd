package com.skeqi.finance.pojo.bo.unit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计量单位添加对象 t_bd_unit
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("计量单位添加对象")
public class TBdUnitAddBo {


    /** 编码 */
    @ApiModelProperty("编码")
	@NotNull(message = "编码不能为空")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
	@NotNull(message = "名称不能为空")
    private String fName;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;

    /** 单位组内码  */
    @ApiModelProperty("单位组内码 ")
	@NotNull(message = "单位组不能为空")
    private Integer fUnitGroupId;

    /** 基准计量单位 */
    @ApiModelProperty("基准计量单位")
    private String fIsbaseUnit;

    /** 精度 */
    @ApiModelProperty("精度")
    private Integer fPrecision;

    /** 舍入类型 */
    @ApiModelProperty("舍入类型")
	@NotNull(message = "舍入类型不能为空")
    private String fRoundType;


	@ApiModelProperty("单位换算")
	TBdUnitConvertRateAddBo convertRate;
}
