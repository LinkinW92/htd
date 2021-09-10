package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 科目表视图对象 t_bd_account
 * @Created by DZB
 * @Date 2021/7/24 10:18
 * @Description TODO
 */
@Data
@ApiModel("科目表-关联会计要素信息-关联科目类别")
public class AccountTableVo {
	private static final long serialVersionUID = 1L;


	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fId;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 名称 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 会计要素表内码  */
	@Excel(name = "会计要素表内码 ")
	@ApiModelProperty("会计要素表内码 ")
	private Integer fAcctGroupTblid;

	/** 名称 */
	@Excel(name = "会计要素信息集合")
	@ApiModelProperty("会计要素信息集合")
	private List<AccountGroupVo> list;
}
