package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 货币符号视图对象 t_bd_coin_symbol
 *
 * @author toms
 * @date 2021-07-12
 */
@Data
@ApiModel("货币符号视图对象")
public class TBdCoinSymbolVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String fRemark;

	/** 排序 */
	@Excel(name = "排序")
	@ApiModelProperty("排序")
	private Integer fSort;

	/** 类别ID */
	@Excel(name = "类别ID")
	@ApiModelProperty("类别ID")
	private Integer fTypeId;

	/** 名字 */
	@Excel(name = "名字")
	@ApiModelProperty("名字")
	private String fTypeName;

}
