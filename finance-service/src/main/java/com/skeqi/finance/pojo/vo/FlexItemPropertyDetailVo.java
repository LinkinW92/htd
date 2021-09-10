package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel("核算维度明细分类账对象")
public class FlexItemPropertyDetailVo {




	/**
	 * 日期
	 */
	@Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("日期")
	private Date fDate;
	/**
	 * 期间
	 */
	@Excel(name = "期间")
	@ApiModelProperty("期间")
	private Integer fPeriod;

	/**
	 * 年
	 */
	@Excel(name = "年")
	@ApiModelProperty("年")
	private Integer fYear;

	/**
	 * 核算维度组合内码
	 */
	@Excel(name = "核算维度")
	@ApiModelProperty("核算维度")
	private String fDetailCodeStr;
	/**
	 * 科目内码
	 */
	@Excel(name = "科目编码")
	@ApiModelProperty("科目编码")
	private String fAccountNumber;

	/**
	 * 科目名称
	 */
	@Excel(name = "科目名称")
	@ApiModelProperty("科目名称")
	private String fAccountName;


	/**
	 * 凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制
	 */
	@Excel(name = "凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制")
	@ApiModelProperty("凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制")
	private Integer fVoucherGroupId;

	/**
	 * 凭证号
	 */
	@Excel(name = "凭证号")
	@ApiModelProperty("凭证号")
	private Integer fVoucherGroupNo;

	/**摘要*/
	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String Summary;

	/**
	 * 余额方向 1 ：借方 ； -1 ：贷方
	 */
	@Excel(name = "科目余额方向 1 ：借方 ； -1 ：贷方 ")
	@ApiModelProperty("科目余额方向 1 ：借方 ； -1 ：贷方 ")
	private Integer fAccountDc;


	/**
	 * 计量单位
	 */
	@Excel(name = "计量单位(借方)")
	@ApiModelProperty("计量单位(借方)")
	private String fMeasureUnitName;

	/**
	 * 单价
	 */
	@Excel(name = "单价(借方)")
	@ApiModelProperty("单价(借方)")
	private BigDecimal fUnitPrice;

	/**
	 * 数量
	 */
	@Excel(name = "数量(借方)")
	@ApiModelProperty("数量(借方)")
	private BigDecimal fQuantity;

	/**余额*/
	@Excel(name = "余额(借方)")
	@ApiModelProperty("余额(借方)")
	private BigDecimal balance;


	/**
	 * 计量单位
	 */
	@Excel(name = "计量单位(贷方)")
	@ApiModelProperty("计量单位(贷方)")
	private String fMeasureUnitName2;

	/**
	 * 单价
	 */
	@Excel(name = "单价(贷方)")
	@ApiModelProperty("单价(贷方)")
	private BigDecimal fUnitPrice2;

	/**
	 * 数量
	 */
	@Excel(name = "数量(贷方)")
	@ApiModelProperty("数量(贷方)")
	private BigDecimal fQuantity2;

	/**余额*/
	@Excel(name = "余额(贷方)")
	@ApiModelProperty("余额(贷方)")
	private BigDecimal balance2;


	/**
	 * 计量单位
	 */
	@Excel(name = "计量单位(余额)")
	@ApiModelProperty("计量单位(余额)")
	private String fMeasureUnitName3;

	/**
	 * 单价
	 */
	@Excel(name = "单价(余额)")
	@ApiModelProperty("单价(余额)")
	private BigDecimal fUnitPrice3;

	/**
	 * 数量
	 */
	@Excel(name = "数量(余额)")
	@ApiModelProperty("数量(余额)")
	private BigDecimal fQuantity3;

	/**余额*/
	@Excel(name = "余额(余额)")
	@ApiModelProperty("余额(余额)")
	private BigDecimal balance3;
}
