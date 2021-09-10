package com.skeqi.finance.pojo.vo.basicinformation.depr;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 折旧方法元素视图对象 t_fa_depr_method_item
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧方法元素视图对象")
public class TFaDeprMethodItemVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 字段类型 1：文本 2：金额  */
	@Excel(name = "字段类型 1：文本 2：金额 ")
	@ApiModelProperty("字段类型 1：文本 2：金额 ")
	private Integer fFieldType;

	/** 对应的取数字段  */
	@Excel(name = "对应的取数字段 ")
	@ApiModelProperty("对应的取数字段 ")
	private String fFormulaType;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;


}
