package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 会计要素信息视图对象 t_bd_account_group
 * @Created by DZB
 * @Date 2021/7/24 10:18
 * @Description TODO
 */
@Data
@ApiModel("-会计要素信息-关联科目类别&&被科目表关联")
public class AccountGroupVo {
	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fAcctgroupId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	@Excel(name = "科目类别集合")
	@ApiModelProperty("科目类别集合")
	private List<AccountTypeVo> list;

}
