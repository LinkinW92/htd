package com.skeqi.finance.pojo.bo.basicinformation.depr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 折旧方法添加对象 t_fa_depr_method
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧方法添加对象")
public class TFaDeprMethodAddBo {


	/**
	 * 资产类别分组编码
	 */
	@ApiModelProperty("资产类别分组编码")
	private String fNumber;

	/**
	 * 折旧依据
	 * 1.最后一期提完折旧
	 * 2.最后一期剩余折旧额不处理
	 * 3.最后一期剩余折旧额大于2倍当期折旧额则继续提取，否则当期提完
	 */
	@ApiModelProperty("折旧依据 1.最后一期提完折旧 2.最后一期剩余折旧额不处理 3.最后一期剩余折旧额大于2倍当期折旧额则继续提取，否则当期提完")
	private String fDeprOption;

	/**
	 * 剩余年限计算方法
	 * 1、向下取整
	 * 2、向上取整
	 * 3、四舍五入
	 * 4、精确计算
	 */
	@ApiModelProperty("剩余年限计算方法 1、向下取整2、向上取整3、四舍五入4、精确计算")
	private String fCalcWay;

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
