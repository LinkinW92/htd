package com.skeqi.finance.pojo.bo.TBdAccount;

import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryAddBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 科目信息添加对象 t_bd_account
 *
 * @author toms
 * @date 2021-07-19
 */
@Data
@ApiModel("科目信息添加对象")
public class TBdAccountAddBo {

	/** 科目核算维度组分录添加对象 */
	@Valid
	@ApiModelProperty("科目核算维度组")
	private List<TBdAccountFlexentryAddBo> tBdAccountFlexentryAddBoList;

    /** 科目代码 */
    @ApiModelProperty("科目代码")
	@NotBlank(message = "科目代码不能为空")
    private String fNumber;

    /** 名称 */
    @ApiModelProperty("名称")
	@NotBlank(message = "名称不能为空")
    private String fName;

    /** 描述 */
    @ApiModelProperty("描述")
    private String fDescription;

    /** 上级科目内码 */
    @ApiModelProperty("上级科目内码")
    private Integer fParentId;

    /** 助记码 */
    @ApiModelProperty("助记码")
	@NotBlank(message = "助记码不能为空")
    private String fHelpErcode;

    /** 科目类别内码 ，引用会计要素表  */
    @ApiModelProperty("科目类别内码 ，引用会计要素表 ")
	@NotNull(message = "科目类别不能为空")
    private Integer fGroupId;

    /** 余额方向 1 ：借方 ； -1 ：贷方  */
    @ApiModelProperty("余额方向 1 ：借方 ； -1 ：贷方 ")
	@NotNull(message = "余额方向不能为空")
    private Integer fDc;

    /** 科目表内码 */
    @ApiModelProperty("科目表内码")
//	@NotNull(message = "科目表不能为空")
    private Integer fAccttblid;

    /** 是否现金科目0：非现金科目 ，1：现金科目 ，默认0 */
    @ApiModelProperty("是否现金科目0：非现金科目 ，1：现金科目 ，默认0")
	@NotBlank(message = "现金科目不能为空")
    private String fIscash;

    /** 是否银行科目 0：非银行科目 ，1：银行科目 ，默认0 */
    @ApiModelProperty("是否银行科目 0：非银行科目 ，1：银行科目 ，默认0")
	@NotBlank(message = "银行科目不能为空")
    private String fIsbank;

    /** 是否期末调汇 */
    @ApiModelProperty("是否期末调汇")
	@NotBlank(message = "期末调汇不能为空")
    private String fIsallocate;

    /** 是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0 */
    @ApiModelProperty("是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0")
	@NotBlank(message = "现金等价物不能为空")
    private String fIscashFlow;

    /** 核算项目组 */
    @ApiModelProperty("核算项目组")
    private Long fItemDetailid;


    /** 	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0 */
    @ApiModelProperty("	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0")
	private String fIsquantities;

    /** 单位组 引用计量单位组 */
    @ApiModelProperty("单位组 引用计量单位组")
    private Long fUnitGroupid;

    /** 默认单位 引用计量单位 */
    @ApiModelProperty("默认单位 引用计量单位")
    private Long fUnitId;

    /** 是否明细科目0=非明细科目 1=明细科目 默认为明细科目 */
    @ApiModelProperty("是否明细科目0=非明细科目 1=明细科目 默认为明细科目")
    private String fIsdetail;

    /** 科目级次从一级开始 */
    @ApiModelProperty("科目级次从一级开始")
    private Integer fLevel;

    /** 创建组织 */
    @ApiModelProperty("创建组织")
	@NotNull(message="创建组织不能为空")
    private Integer fCreateOrgid;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Integer fCreatorid;

    /** 创建日期 */
    @ApiModelProperty("创建日期")
    private Date fCreateDate;

    /** 使用组织 */
    @ApiModelProperty("使用组织")
    private Integer fUseOrgid;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private Integer fModifierid;

    /** 修改日期 */
    @ApiModelProperty("修改日期")
    private Date fModifyDate;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    private String fDocumentStatus;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private Integer fAuditorid;

    /** 审核日期 */
    @ApiModelProperty("审核日期")
    private Date fAuditDate;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    private Integer fForbidderid;

    /** 禁用日期 */
    @ApiModelProperty("禁用日期")
    private Date fForbidDate;

    /** 是否系统预设1 系统预设0 非系统预设默认0 */
    @ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
    private String fIssysPreset;

    /** 主表项目（流入) */
    @ApiModelProperty("主表项目（流入)")
    private Integer fCfitemId;

    /** 主表项目（流出) */
    @ApiModelProperty("主表项目（流出)")
    private Integer fOcfitemId;

    /** 附表项目（流入） */
    @ApiModelProperty("附表项目（流入）")
    private Integer fCfindirectitemId;

    /** 附表项目（流出） */
    @ApiModelProperty("附表项目（流出）")
    private Integer fOcfindirectitemId;

    /** 核算所有币别 */
    @ApiModelProperty("核算所有币别")
    private String fAllcurrency;

    /** 外币核算 逗号分割id列表 */
    @ApiModelProperty("外币核算 逗号分割id列表")
    private String fCurrencys;

    /** 是否出日记账 0：不出日记账 ，1：出日记账，默认0 */
    @ApiModelProperty("是否出日记账 0：不出日记账 ，1：出日记账，默认0")
    private String fIsshowJournal;


}
