package com.skeqi.finance.pojo.bo.basicinformation.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * 基础-币别编辑对象 t_bd_currency
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("基础-币别编辑对象")
public class TBdCurrencyEditBo {


    /** 内码  */
    @ApiModelProperty("内码 ")
    private Integer fCurrencyId;

	/** 货币符号 */
	@ApiModelProperty("货币符号")
	private String fSysmbol;


	/** 是否中间币  0不是 1是 */
	@ApiModelProperty("是否中间币  0不是 1是")
	private String fIstrans;

	/** 显示货币符号  */
	@ApiModelProperty("显示货币符号 ")
	private String fIsshowCsymbol;

	/** 货币符号内码 */
	@ApiModelProperty("货币符号内码")
	private String fCurrencySymbolid;

	/** 显示顺序 */
	@ApiModelProperty("显示顺序")
	private String fFormatOrder;


	/** 数据状态 */
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;



	/** 禁用状态 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;


	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;


	/** 单价精度  */
	@ApiModelProperty("单价精度 ")
	private Integer fPricedigits;

	/** 金额精度  */
	@ApiModelProperty("金额精度 ")
	private Integer fAmountdigits;

	@ApiModelProperty("舍入类型 1四舍五入  2四舍六入五双")
	private String fSettleType;

}
