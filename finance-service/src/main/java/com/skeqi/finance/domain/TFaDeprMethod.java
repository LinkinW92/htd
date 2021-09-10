package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 折旧方法对象 t_fa_depr_method
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fa_depr_method")
public class TFaDeprMethod implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 内码
	 */
	@TableId(value = "f_id")
	private Integer fId;

	/**
	 * 资产类别分组编码
	 */
	private String fNumber;

	/**
	 * 折旧依据
	 * 1.最后一期提完折旧
	 * 2.最后一期剩余折旧额不处理
	 * 3.最后一期剩余折旧额大于2倍当期折旧额则继续提取，否则当期提完
	 */
	private String fDeprOption;

	/**
	 * 剩余年限计算方法
	 * 1、向下取整
	 * 2、向上取整
	 * 3、四舍五入
	 * 4、精确计算
	 */
	private String fCalcWay;

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
