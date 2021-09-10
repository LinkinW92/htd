package com.skeqi.finance.pojo.bo.TBdAccountFlexentry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 科目核算维度组分录编辑对象 t_bd_account_flexentry
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("科目核算维度组分录编辑对象")
public class TBdAccountFlexentryEditBo {


    /** 分录内码  */
    @ApiModelProperty("分录内码 ")
    private Integer fEntryId;

    /** 科目主表内码 */
    @ApiModelProperty("科目主表内码")
    private Integer fAcctId;

    /** 维度内码 */
    @ApiModelProperty("维度内码")
    private Integer fFlexitempropertyId;

    /** 是否启用 '0'未启用 '1'启用 */
    @ApiModelProperty("是否启用 '0'未启用 '1'启用")
    private String fAcctitemisvalid;

    /** 必录类型需要有分类：1 必录 2 可选 */
    @ApiModelProperty("必录类型需要有分类：1 必录 0 可选")
    private String fInputType;

    /** 维度数据表中的字段名称 */
    @ApiModelProperty("维度数据表中的字段名称")
    private String fDataFieldname;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    private String fSeq;

    /** 组织隔离字段 */
    @ApiModelProperty("组织隔离字段")
    private Integer fMasterId;
}
