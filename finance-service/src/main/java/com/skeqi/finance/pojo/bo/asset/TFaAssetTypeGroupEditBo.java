package com.skeqi.finance.pojo.bo.asset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 资产类别组编辑对象 t_fa_asset_type_group
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("资产类别组编辑对象")
public class TFaAssetTypeGroupEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fId;

    /** 资产类别分组编码 */
    @ApiModelProperty("资产类别分组编码")
    private String fNumber;

	/** 资产类别分组名称 */
	@ApiModelProperty("资产类别分组名称")
	private String fName;

	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescribe;

	/** 上级id */
	@ApiModelProperty("上级id")
	private Integer fParentId;
}
