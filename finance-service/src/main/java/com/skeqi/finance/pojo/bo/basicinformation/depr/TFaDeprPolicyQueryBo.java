package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.skeqi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 折旧政策分页查询对象 t_fa_depr_policy
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("折旧政策分页查询对象")
public class TFaDeprPolicyQueryBo extends BaseEntity {

	/**
	 * 分页大小
	 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/**
	 * 排序列
	 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/**
	 * 排序的方向desc或者asc
	 */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/**
	 * 编码
	 */
	@ApiModelProperty("编码")
	private String fNumber;
	/**
	 * 新增、清理折旧政策
	 * 1：新增当期计提折旧，清理当期不提折旧
	 * 2：新增当期计提折旧，清理当期计提折旧
	 * 3：新增当期不提折旧，清理当期计提折旧
	 * 4：新增当期不提折旧，清理当期不提折旧
	 */
	@ApiModelProperty("新增、清理折旧政策1：新增当期计提折旧，清理当期不提折旧2：新增当期计提折旧，清理当期计提折旧3：新增当期不提折旧，清理当期计提折旧4：新增当期不提折旧，清理当期不提折旧")
	private String fClearDeprPolicy;
	/**
	 * 变动当期即影响折旧
	 * 0：不选中
	 * 1：选中
	 */
	@ApiModelProperty("变动当期即影响折旧0：不选中1：选中")
	private String fIsaffectDepr;
	/**
	 * 折旧要素变动时按静态折旧方法计算
	 */
	@ApiModelProperty("折旧要素变动时按静态折旧方法计算")
	private String fAffectprPolicy;
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
