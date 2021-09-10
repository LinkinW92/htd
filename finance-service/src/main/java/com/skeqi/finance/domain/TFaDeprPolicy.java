package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 折旧政策对象 t_fa_depr_policy
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_depr_policy")
public class TFaDeprPolicy implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 内码
	 */
	@TableId(value = "f_policy_id")
	private Integer fPolicyId;

	/**
	 * 编码
	 */
	private String fNumber;

	/**
	 * 新增、清理折旧政策
	 * 1：新增当期计提折旧，清理当期不提折旧
	 * 2：新增当期计提折旧，清理当期计提折旧
	 * 3：新增当期不提折旧，清理当期计提折旧
	 * 4：新增当期不提折旧，清理当期不提折旧
	 */
	private String fClearDeprPolicy;

	/**
	 * 变动当期即影响折旧
	 * 0：不选中
	 * 1：选中
	 */
	private String fIsaffectDepr;

	/**
	 * 折旧要素变动时按静态折旧方法计算
	 */
	private String fAffectprPolicy;

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
	private Integer fIssysPreset;

	/**
	 * 组织隔离字段
	 */
	private Integer fMasterId;

}
