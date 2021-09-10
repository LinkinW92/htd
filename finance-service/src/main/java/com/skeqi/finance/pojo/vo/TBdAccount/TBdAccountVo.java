package com.skeqi.finance.pojo.vo.TBdAccount;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 科目信息视图对象 t_bd_account_table
 *
 * @author toms
 * @date 2021-07-19
 */
@Data
@ApiModel("科目信息视图对象")
public class TBdAccountVo {

	private static final long serialVersionUID = 1L;


	//region 关联拆线呢
	/** 科目类别名称 ，引用会计要素表  */
	@Excel(name = "科目类别名称")
	@ApiModelProperty("科目类别名称")
	private String fGroupName;

	/** 外币核算 逗号分割id列表 */
	@Excel(name = "外币核算")
	@ApiModelProperty("外币核算")
	private String fCurrencyName;

	/** 核算项目组 */
	@Excel(name = "核算项目组名称")
	@ApiModelProperty("核算项目组名称")
	private String fItemDetailName;

	@Excel(name = "核算项目组集合")
	@ApiModelProperty("核算项目组集合")
	private List<Map> fItemDetailList;
	@Excel(name = "外币核算集合")
	@ApiModelProperty("外币核算集合")
	private List<Map> fCurrencyList;
	//endregion

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fAcctId;

	/** 科目代码 */
	@Excel(name = "科目代码")
	@ApiModelProperty("科目代码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 上级科目内码 */
	@Excel(name = "上级科目内码")
	@ApiModelProperty("上级科目内码")
	private Integer fParentId;

	/** 助记码 */
	@Excel(name = "助记码")
	@ApiModelProperty("助记码")
	private String fHelpErcode;

	/** 科目类别内码 ，引用会计要素表  */
	@Excel(name = "科目类别内码 ，引用会计要素表 ")
	@ApiModelProperty("科目类别内码 ，引用会计要素表 ")
	private Integer fGroupId;

	/** 余额方向 1 ：借方 ； -1 ：贷方  */
	@Excel(name = "余额方向 1 ：借方 ； -1 ：贷方 ")
	@ApiModelProperty("余额方向 1 ：借方 ； -1 ：贷方 ")
	private Integer fDc;

	/** 科目表内码 */
	@Excel(name = "科目表内码")
	@ApiModelProperty("科目表内码")
	private Integer fAccttblid;

	/** 是否现金科目0：非现金科目 ，1：现金科目 ，默认0 */
	@Excel(name = "是否现金科目0：非现金科目 ，1：现金科目 ，默认0")
	@ApiModelProperty("是否现金科目0：非现金科目 ，1：现金科目 ，默认0")
	private String fIscash;

	/** 是否银行科目 0：非银行科目 ，1：银行科目 ，默认0 */
	@Excel(name = "是否银行科目 0：非银行科目 ，1：银行科目 ，默认0")
	@ApiModelProperty("是否银行科目 0：非银行科目 ，1：银行科目 ，默认0")
	private String fIsbank;

	/** 是否期末调汇 */
	@Excel(name = "是否期末调汇")
	@ApiModelProperty("是否期末调汇")
	private String fIsallocate;

	/** 是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0 */
	@Excel(name = "是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0")
	@ApiModelProperty("是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0")
	private String fIscashFlow;

	/** 核算项目组 */
	@Excel(name = "核算项目组")
	@ApiModelProperty("核算项目组")
	private Integer fItemDetailid;

	/** 	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0 */
	@Excel(name = "	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0")
	@ApiModelProperty("	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0")
	private String fIsquantities;

	/** 单位组 引用计量单位组 */
	@Excel(name = "单位组 引用计量单位组")
	@ApiModelProperty("单位组 引用计量单位组")
	private Integer fUnitGroupid;

	/** 默认单位 引用计量单位 */
	@Excel(name = "默认单位 引用计量单位")
	@ApiModelProperty("默认单位 引用计量单位")
	private Integer fUnitId;

	/** 是否明细科目0=非明细科目 1=明细科目 默认为明细科目 */
	@Excel(name = "是否明细科目0=非明细科目 1=明细科目 默认为明细科目")
	@ApiModelProperty("是否明细科目0=非明细科目 1=明细科目 默认为明细科目")
	private String fIsdetail;

	/** 科目级次从一级开始 */
	@Excel(name = "科目级次从一级开始")
	@ApiModelProperty("科目级次从一级开始")
	private Integer fLevel;

	/** 创建组织 */
	@Excel(name = "创建组织")
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/** 创建日期 */
	@Excel(name = "创建日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/** 使用组织 */
	@Excel(name = "使用组织")
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/** 修改人 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/** 修改日期 */
	@Excel(name = "修改日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/** 审核人 */
	@Excel(name = "审核人")
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/** 审核日期 */
	@Excel(name = "审核日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/** 禁用人 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/** 禁用日期 */
	@Excel(name = "禁用日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private String fIssysPreset;

	/** 主表项目（流入) */
	@Excel(name = "主表项目" , readConverterExp = "主表项目（流入)")
	@ApiModelProperty("主表项目（流入)")
	private Integer fCfitemId;

	/** 主表项目（流出) */
	@Excel(name = "主表项目" , readConverterExp = "主表项目（流出)")
	@ApiModelProperty("主表项目（流出)")
	private Integer fOcfitemId;

	/** 附表项目（流入） */
	@Excel(name = "附表项目" , readConverterExp = "流=入")
	@ApiModelProperty("附表项目（流入）")
	private Integer fCfindirectitemId;

	/** 附表项目（流出） */
	@Excel(name = "附表项目" , readConverterExp = "流=出")
	@ApiModelProperty("附表项目（流出）")
	private Integer fOcfindirectitemId;

	/** 核算所有币别 */
	@Excel(name = "核算所有币别")
	@ApiModelProperty("核算所有币别")
	private String fAllcurrency;

	/** 外币核算 逗号分割id列表 */
	@Excel(name = "外币核算 逗号分割id列表")
	@ApiModelProperty("外币核算 逗号分割id列表")
	private String fCurrencys;

	/** 是否出日记账 0：不出日记账 ，1：出日记账，默认0 */
	@Excel(name = "是否出日记账 0：不出日记账 ，1：出日记账，默认0")
	@ApiModelProperty("是否出日记账 0：不出日记账 ，1：出日记账，默认0")
	private String fIsshowJournal;


}
