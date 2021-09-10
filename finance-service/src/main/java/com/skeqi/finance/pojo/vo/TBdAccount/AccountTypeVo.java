package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 科目类别视图对象 t_bd_flex_item_property
 * @Created by DZB
 * @Date 2021/7/24 10:18
 * @Description TODO
 */
@Data
@ApiModel("科目类别-被会计要素信息关联-被科目表关联")
public class AccountTypeVo {
	private static final long serialVersionUID = 1L;


	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fAcctTypeId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;


}
