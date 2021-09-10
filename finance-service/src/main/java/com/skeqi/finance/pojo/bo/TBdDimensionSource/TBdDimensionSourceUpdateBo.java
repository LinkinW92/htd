package com.skeqi.finance.pojo.bo.TBdDimensionSource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 维度来源编辑对象 t_bd_dimension_source
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("维度来源编辑对象")
public class TBdDimensionSourceUpdateBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fId;

    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
    private String fName;

    /** 类型 1基础资料 2辅助资料 */
    @ApiModelProperty("类型 1基础资料 2辅助资料")
    private String fType;

    /** 策略类型 */
    @ApiModelProperty("策略类型")
    private String fStrategyType;


    /** 修改人 */
    @ApiModelProperty("修改人")
    private Integer fModifierid;

    /** 修改日期 */
    @ApiModelProperty("修改日期")
    private Date fModifyDate;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    private String fDocumentStatus;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    @ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
    private String fIssysPreset;

}
