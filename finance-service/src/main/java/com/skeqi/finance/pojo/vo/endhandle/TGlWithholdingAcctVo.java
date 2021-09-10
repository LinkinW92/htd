package com.skeqi.finance.pojo.vo.endhandle;

import com.skeqi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.List;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingAcctDimension;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 凭证预提-预提科目视图对象 t_gl_withholding_acct
 *
 * @author toms
 * @date 2021-07-27
 */
@Data
@ApiModel("凭证预提-预提科目视图对象")
public class TGlWithholdingAcctVo {

	private static final long serialVersionUID = 1L;

	/** 自增ID */
	@ApiModelProperty("自增ID")
	private Integer fId;

	/** 方案内码 */
	@Excel(name = "方案内码")
	@ApiModelProperty("方案内码")
	private Integer fSchemeId;

	/** 预提科目 */
	@Excel(name = "预提科目")
	@ApiModelProperty("预提科目")
	private Integer fProvisionAccount;

	/** 待摊销科目 */
	@Excel(name = "待摊销科目")
	@ApiModelProperty("待摊销科目")
	private String fAccountName;

	/** 核算维度 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private Integer fDetailId;

	/** 比例 */
	@Excel(name = "比例")
	@ApiModelProperty("比例")
	private BigDecimal fProvisionRatio;

	/** 固定 */
	@Excel(name = "固定")
	@ApiModelProperty("固定")
	private String fProvisionFixed;

	private List<TGlWithholdingAcctDimension> dimensions;


}
