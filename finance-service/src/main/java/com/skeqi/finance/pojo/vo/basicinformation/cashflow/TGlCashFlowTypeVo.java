package com.skeqi.finance.pojo.vo.basicinformation.cashflow;

import com.skeqi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 现金流量项目类别-2视图对象 t_gl_cash_flow_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("现金流量项目类别-2视图对象")
public class TGlCashFlowTypeVo {

	private static final long serialVersionUID = 1L;

	/** 现金流量项目类别内码 */
	@ApiModelProperty("现金流量项目类别内码")
	private Integer fItemTypeid;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 类别属性 主表项目 = 1，附表项目 = 2 */
	@Excel(name = "类别属性 主表项目 = 1，附表项目 = 2")
	@ApiModelProperty("类别属性 主表项目 = 1，附表项目 = 2")
	private Integer fItemGroupid;

	/** 项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4   */
	@Excel(name = "项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4  ")
	@ApiModelProperty("项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4  ")
	private Integer fGroupTypeid;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String fDescription;

	/** 现金流量项目表  */
	@Excel(name = "现金流量项目表 ")
	@ApiModelProperty("现金流量项目表 ")
	private Integer fCashFlowItemTable;

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

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/** 组织隔离字段 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;

	/** 现金流量项目id */
	@Excel(name = "现金流量项目id")
	@ApiModelProperty("现金流量项目id")
	private Integer fId;

	/** 流量项目编码 */
	@Excel(name = "流量项目编码")
	@ApiModelProperty("流量项目编码")
	private String tfNumber;

	/** 流量项目名称 */
	@Excel(name = "流量项目名称")
	@ApiModelProperty("流量项目名称")
	private String tfName;

	/** 上级流量项目id */
	@Excel(name = "上级流量项目id")
	@ApiModelProperty("上级流量项目id")
	private Integer fParentId;

	/** 本表父级id */
	@Excel(name = "本表父级id")
	@ApiModelProperty("本表父级id")
	private Integer fParentDirectoryId;


	/** 总数 */
	@Excel(name = "总数")
	@ApiModelProperty("总数")
	private BigDecimal amount;

	/** 比重 */
	@Excel(name = "比重")
	@ApiModelProperty("比重")
	private BigDecimal proportion;
}
