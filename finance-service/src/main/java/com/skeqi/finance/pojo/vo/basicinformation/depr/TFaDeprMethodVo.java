package com.skeqi.finance.pojo.vo.basicinformation.depr;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 折旧方法视图对象 t_fa_depr_method
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("折旧方法视图对象")
public class TFaDeprMethodVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	private Integer fId;

	/**
	 * 资产类别分组编码
	 */
	@Excel(name = "资产类别分组编码")
	@ApiModelProperty("资产类别分组编码")
	private String fNumber;

	/**
	 * 折旧依据
	 * 1.最后一期提完折旧
	 * 2.最后一期剩余折旧额不处理
	 * 3.最后一期剩余折旧额大于2倍当期折旧额则继续提取，否则当期提完
	 */
	@Excel(name = "折旧依据")
	@ApiModelProperty("折旧依据")
	private String fDeprOption;

	/**
	 * 剩余年限计算方法
	 * 1、向下取整
	 * 2、向上取整
	 * 3、四舍五入
	 * 4、精确计算
	 */
	@Excel(name = "剩余年限计算方法")
	@ApiModelProperty("剩余年限计算方法")
	private String fCalcWay;

	/**
	 * 创建组织
	 */
	@Excel(name = "创建组织")
	@ApiModelProperty("创建组织")
	private Integer fCreateOrgid;

	/**
	 * 创建人
	 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer fCreatorid;

	/**
	 * 创建日期
	 */
	@Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("创建日期")
	private Date fCreateDate;

	/**
	 * 使用组织
	 */
	@Excel(name = "使用组织")
	@ApiModelProperty("使用组织")
	private Integer fUseOrgid;

	/**
	 * 修改人
	 */
	@Excel(name = "修改人")
	@ApiModelProperty("修改人")
	private Integer fModifierid;

	/**
	 * 修改日期
	 */
	@Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("修改日期")
	private Date fModifyDate;

	/**
	 * 数据状态
	 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private String fDocumentStatus;

	/**
	 * 审核人
	 */
	@Excel(name = "审核人")
	@ApiModelProperty("审核人")
	private Integer fAuditorid;

	/**
	 * 审核日期
	 */
	@Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("审核日期")
	private Date fAuditDate;

	/**
	 * 禁用状态
	 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;

	/**
	 * 禁用人
	 */
	@Excel(name = "禁用人")
	@ApiModelProperty("禁用人")
	private Integer fForbidderid;

	/**
	 * 禁用日期
	 */
	@Excel(name = "禁用日期", width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("禁用日期")
	private Date fForbidDate;

	/**
	 * 是否系统预设1 系统预设0 非系统预设默认0
	 */
	@Excel(name = "是否系统预设1 系统预设0 非系统预设默认0")
	@ApiModelProperty("是否系统预设1 系统预设0 非系统预设默认0")
	private Integer fIssysPreset;

	/**
	 * 组织隔离字段
	 */
	@Excel(name = "组织隔离字段")
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;


}
