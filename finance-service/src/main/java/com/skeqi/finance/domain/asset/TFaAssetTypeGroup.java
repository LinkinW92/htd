package com.skeqi.finance.domain.asset;

import com.baomidou.mybatisplus.annotation.*;
import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 资产类别组对象 t_fa_asset_type_group
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_asset_type_group")
public class TFaAssetTypeGroup implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_id")
    private Integer fId;

    /** 资产类别分组编码 */
    private String fNumber;

	/** 资产类别分组名称 */

	private String fName;

	/** 描述 */
	private String fDescribe;

	/** 上级id */
	private Integer fParentId;

}
