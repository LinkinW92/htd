package com.skeqi.finance.pojo.vo.basicinformation.depr;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 折旧政策视图对象 t_fa_depr_policy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧政策视图对象")
public class TFaDeprPolicyVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fPolicyId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	/** 新增、清理折旧政策
1：新增当期计提折旧，清理当期不提折旧
2：新增当期计提折旧，清理当期计提折旧
3：新增当期不提折旧，清理当期计提折旧
4：新增当期不提折旧，清理当期不提折旧 */
	@Excel(name = "新增、清理折旧政策")
	@ApiModelProperty("新增、清理折旧政策")
	private String fClearDeprPolicy;

	/** 变动当期即影响折旧
0：不选中
1：选中 */
	@Excel(name = "变动当期即影响折旧")
	@ApiModelProperty("变动当期即影响折旧")
	private String fIsaffectDepr;

	/** 折旧要素变动时按静态折旧方法计算 */
	@Excel(name = "折旧要素变动时按静态折旧方法计算")
	@ApiModelProperty("折旧要素变动时按静态折旧方法计算")
	private String fAffectprPolicy;

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
	private Integer fIssysPreset;

	/** 组织隔离字段 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;


}
