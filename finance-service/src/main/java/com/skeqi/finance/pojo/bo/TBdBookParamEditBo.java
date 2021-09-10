package com.skeqi.finance.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 总账管理参数-账簿参数编辑对象 t_bd_book_param
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("总账管理参数-账簿参数编辑对象")
public class TBdBookParamEditBo {


	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fId;

	/**
	 * 利润分配科目
	 */
	@ApiModelProperty("利润分配科目")
	private Integer fProfitDistributionAccount;

	/**
	 * 基本选项本年利润科目
	 */
	@ApiModelProperty("基本选项本年利润科目")
	private Integer fCurrentYearProfitAccount;

	/**
	 * 账表余额方向与科目设置的余额方向相同
	 */
	@ApiModelProperty("账表余额方向与科目设置的余额方向相同")
	private String fBaseOne;

	/**
	 * 明细账(表)摘要自动继承上条分录摘要
	 */
	@ApiModelProperty("明细账(表)摘要自动继承上条分录摘要")
	private String fBaseTwo;

	/**
	 * 明细账科目显示所有科目名称
	 */
	@ApiModelProperty("明细账科目显示所有科目名称")
	private String fBaseThree;

	/**
	 * 多栏账成本类科目期初余额从余额表取数
	 */
	@ApiModelProperty("多栏账成本类科目期初余额从余额表取数")
	private String fBaseFour;

	/**
	 * 多栏账损益类科目期初余额从余额表取数
	 */
	@ApiModelProperty("多栏账损益类科目期初余额从余额表取数")
	private String fBaseFive;

	/**
	 * 核算维度余额表非明细级的核算维度的余额合并在一个方向
	 */
	@ApiModelProperty("核算维度余额表非明细级的核算维度的余额合并在一个方向")
	private String fBaseSix;

	/**
	 * 往来科目必须输入业务编号（影响凭证及科目初始数据录入）
	 */
	@ApiModelProperty("往来科目必须输入业务编号（影响凭证及科目初始数据录入）")
	private String fBaseSeven;

	/**
	 * 结账时要求损益类科目余额为零
	 */
	@ApiModelProperty("结账时要求损益类科目余额为零")
	private String fCheckoutOptionsOne;

	/**
	 * 不允许跨财务年度的反结账
	 */
	@ApiModelProperty("不允许跨财务年度的反结账")
	private String fCheckoutOptionsTwo;

	/**
	 * 业务系统结账可与总账结账期间不一致
	 */
	@ApiModelProperty("业务系统结账可与总账结账期间不一致")
	private String fCheckoutOptionsThree;

	/**
	 * 结账时不检查业务单据的凭证生成情况（总账结账慢时建议勾选）
	 */
	@ApiModelProperty("结账时不检查业务单据的凭证生成情况（总账结账慢时建议勾选）")
	private String fCheckoutOptionsFour;

	/**
	 * 总账结账检查对账结果包含调整期凭证
	 */
	@ApiModelProperty("总账结账检查对账结果包含调整期凭证")
	private String fCheckoutOptionsFive;

	/**
	 * 账簿ID
	 */
	@ApiModelProperty("账簿ID")
	private Integer fBookId;

	/**
	 * 创建组织
	 */
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/**
	 * 使用组织
	 */
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/**
	 * 修改人
	 */
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/**
	 * 修改日期
	 */
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/**
	 * 数据状态
	 */
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/**
	 * 审核人
	 */
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/**
	 * 审核日期
	 */
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/**
	 * 禁用状态
	 */
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/**
	 * 禁用人
	 */
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/**
	 * 禁用日期
	 */
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/**
	 * 是否系统预设1 系统预设0 非系统预设默认0
	 */
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/**
	 * 组织隔离字段
	 */
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;
}
