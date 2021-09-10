package com.skeqi.finance.pojo.bo.voucher;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 凭证录入主添加对象 t_gl_voucher
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("凭证录入主添加对象")
public class TGlVoucherAddBo {


	/**
	 * 账簿内码
	 */
	@ApiModelProperty("账簿内码")
	@NotNull(message = "账簿不能为空")
	private Integer fAccountBookId;

	/**
	 * 核算组织内码
	 */
	@ApiModelProperty("核算组织内码")
	@NotNull(message = "核算组织不能为空")
	private Integer fAcctOrgid;

	/**
	 * 日期
	 */
	@ApiModelProperty("日期")
	@NotNull(message = "日期不能为空")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date fDate;

	@ApiModelProperty("业务日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date fBusDate;

	/**
	 * 年：凭证所在年度，根据凭证日期FDATE字段，结合会计日历进行计算所得
	 */
	@ApiModelProperty("年：凭证所在年度，根据凭证日期FDATE字段，结合会计日历进行计算所得 ")
	private Integer fYear;

	/**
	 * 期 :凭证所在期间，根据凭证日期FDATE及会计日历计算所得
	 */
	@ApiModelProperty("期 :凭证所在期间，根据凭证日期FDATE及会计日历计算所得")
	private Integer fPeriod;

	/**
	 * 期号
	 */
	@ApiModelProperty("期号")
	private String fBillNo;

	/**
	 * 凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制
	 */
	@ApiModelProperty("凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制")
	@NotNull(message = "凭证字不能为空")
	private Integer fVoucherGroupId;

	/**
	 * 凭证号
	 */
	@ApiModelProperty("凭证号")
	@NotNull(message = "凭证号不能为空")
	private Integer fVoucherGroupNo;

	/**
	 * 附件数：凭证附件数
	 */
	@ApiModelProperty("附件数：凭证附件数")
	private Integer fAttachments;

	/**
	 * 凭证来源：
	 * 1、一般凭证：手工录入
	 * 2、业务系统凭证：由业务系统单据生成
	 * 3、机制凭证：总账系统通过凭证模板系统自动生成
	 */
	@ApiModelProperty("凭证来源： 1、一般凭证：手工录入 2、业务系统凭证：由业务系统单据生成 3、机制凭证：总账系统通过凭证模板系统自动生成")
	private String fInternalind;

	/**
	 * 参考信息
	 */
	@ApiModelProperty("参考信息")
	private String fReference;

	/**
	 * 结算方式：来源于基础资料结算方式，当科目为银行科目时必录
	 */
	@ApiModelProperty("结算方式：来源于基础资料结算方式，当科目为银行科目时必录")
	private Integer fSettleTypeId;

	/**
	 * 结算号
	 */
	@ApiModelProperty("结算号")
	private String fSettleNo;

	/**
	 * 本位币
	 */
	@ApiModelProperty("本位币")
	private Integer fBaseCurrencyId;

	/**
	 * 借方总金额：凭证分录上借方金额的总和
	 */
	@ApiModelProperty("借方总金额：凭证分录上借方金额的总和")
	private BigDecimal fDebitTotal;

	/**
	 * 贷方总金额：凭证分录上贷方金额的汇总
	 */
	@ApiModelProperty("贷方总金额：凭证分录上贷方金额的汇总")
	private BigDecimal fCreditTotal;

	/**
	 * 出纳：来源于用户
	 */
	@ApiModelProperty("出纳：来源于用户")
	private Integer fCashierId;

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
	 * 数据状态A：创建B：审核中C：已审核D：重新审核Z：暂存
	 */
	@ApiModelProperty("数据状态A：创建B：审核中C：已审核D：重新审核Z：暂存")
	private String fDocumentStatus;

	/**
	 * 是否审核
	 */
	@ApiModelProperty("是否审核")
	private String fChecked;

	/**
	 * 审核人ID
	 */
	@ApiModelProperty("审核人ID")
	private Integer fCheckerId;

	/**
	 * 审核日期
	 */
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/**
	 * 是否过账：0 未过账 1 已过账
	 */
	@ApiModelProperty("是否过账：0 未过账 1 已过账")
	private String fPosted;

	/**
	 * 过账：来源于用户
	 */
	@ApiModelProperty("过账：来源于用户")
	private Integer fPosterId;

	/**
	 * 过账日期
	 */
	@ApiModelProperty("过账日期")
	private Date fPostDate;

	/**
	 * 调整期=0表示正常期间凭证，非调整期 >0则表示对应的调整期
	 */
	@ApiModelProperty("调整期=0表示正常期间凭证，非调整期 >0则表示对应的调整期")
	private Integer fAdjustPeriod;

	/**
	 * 是否作废凭证：0正常凭证，1作废凭证
	 */
	@ApiModelProperty("是否作废凭证：0正常凭证，1作废凭证")
	private String fInvalid;

	/**
	 * 作废人
	 */
	@ApiModelProperty("作废人")
	private Integer fInvaliderId;

	/**
	 * 关联凭证ID
	 */
	@ApiModelProperty("关联凭证ID")
	private Integer fMapvchId;

	/**
	 * 来源单据标识，记录该凭证的数据来源于哪种类型的单据（记录对应单据的FormID）
	 */
	@ApiModelProperty("来源单据标识，记录该凭证的数据来源于哪种类型的单据（记录对应单据的FormID）")
	private String fSourceBillKey;

	/**
	 * 是否调整期凭证
	 */
	@ApiModelProperty("是否调整期凭证")
	private String fIsadjustVoucher;

	/**
	 * 对应业务对象：BOS_SubSystem中fid
	 */
	@ApiModelProperty("对应业务对象：BOS_SubSystem中fid")
	private String fSystemId;

	/**
	 * 已指定流量 1是 0否
	 */
	@ApiModelProperty("已指定流量 1是 0否")
	private String fIscashFlow;

	/**
	 * 是否拆分 0:未拆分1:按金额接近拆分2:按金额比例拆分
	 */
	@ApiModelProperty("是否拆分 0:未拆分1:按金额接近拆分2:按金额比例拆分")
	private Integer fIssplit;

	/**
	 * 是否虚拟过账 0否 1是
	 */
	@ApiModelProperty("是否虚拟过账 0否 1是")
	private Integer fIsvirPost;

	/**
	 * 打印次数
	 */
	@ApiModelProperty("打印次数")
	private Integer fPrintTimes;

	@Valid
	List<TGlVoucherEntryAddBo> voucherEntry;
}
