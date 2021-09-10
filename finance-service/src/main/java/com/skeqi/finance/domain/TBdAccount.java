package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 科目信息对象 t_bd_account
 *
 * @author toms
 * @date 2021-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_bd_account")
public class TBdAccount extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 内码
	 */
	@TableId(value = "f_acct_id")
	private Integer fAcctId;

	/**
	 * 科目代码
	 */
	private String fNumber;

	/**
	 * 名称
	 */
	private String fName;

	/**
	 * 描述
	 */
	private String fDescription;

	/**
	 * 上级科目内码
	 */
	private Integer fParentId;

	/**
	 * 助记码
	 */
	private String fHelpErcode;

	/**
	 * 科目类别内码 ，引用会计要素表
	 */
	private Integer fGroupId;

	/**
	 * 余额方向 1 ：借方 ； -1 ：贷方
	 */
	private Integer fDc;

	/**
	 * 科目表内码
	 */
	private Integer fAccttblid;

	/**
	 * 是否现金科目0：非现金科目 ，1：现金科目 ，默认0
	 */
	private String fIscash;

	/**
	 * 是否银行科目 0：非银行科目 ，1：银行科目 ，默认0
	 */
	private String fIsbank;

	/**
	 * 是否期末调汇
	 */
	private String fIsallocate;

	/**
	 * 是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0
	 */
	private String fIscashFlow;

	/**
	 * 核算项目组
	 */
	private Integer fItemDetailid;

	/**
	 * 是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0
	 */
	private String fIsquantities;

	/**
	 * 单位组 引用计量单位组
	 */
	private Long fUnitGroupid;

	/**
	 * 默认单位 引用计量单位
	 */
	private Long fUnitId;

	/**
	 * 是否明细科目0=非明细科目 1=明细科目 默认为明细科目
	 */
	private String fIsdetail;

	/**
	 * 科目级次从一级开始
	 */
	private Integer fLevel;

	/**
	 * 创建组织
	 */
	private Integer fCreateOrgid;

	/**
	 * 创建人
	 */
	private Integer fCreatorid;

	/**
	 * 创建日期
	 */
	private Date fCreateDate;

	/**
	 * 使用组织
	 */
	private Integer fUseOrgid;

	/**
	 * 修改人
	 */
	private Integer fModifierid;

	/**
	 * 修改日期
	 */
	private Date fModifyDate;

	/**
	 * 数据状态
	 */
	private String fDocumentStatus;

	/**
	 * 审核人
	 */
	private Integer fAuditorid;

	/**
	 * 审核日期
	 */
	private Date fAuditDate;

	/**
	 * 禁用状态
	 */
	private String fForbidStatus;

	/**
	 * 禁用人
	 */
	private Integer fForbidderid;

	/**
	 * 禁用日期
	 */
	private Date fForbidDate;

	/**
	 * 是否系统预设1 系统预设0 非系统预设默认0
	 */
	private String fIssysPreset;

	/**
	 * 主表项目（流入)
	 */
	private Integer fCfitemId;

	/**
	 * 主表项目（流出)
	 */
	private Integer fOcfitemId;

	/**
	 * 附表项目（流入）
	 */
	private Integer fCfindirectitemId;

	/**
	 * 附表项目（流出）
	 */
	private Integer fOcfindirectitemId;

	/**
	 * 核算所有币别
	 */
	private String fAllcurrency;

	/**
	 * 外币核算 逗号分割id列表
	 */
	private String fCurrencys;

	/**
	 * 是否出日记账 0：不出日记账 ，1：出日记账，默认0
	 */
	private String fIsshowJournal;

}
