package com.skeqi.finance.pojo.bo.basicinformation.cashflow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.skeqi.common.core.domain.BaseEntity;

/**
 * 现金流量项目类别-2分页查询对象 t_gl_cash_flow_type
 *
 * @author toms
 * @date 2021-07-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("现金流量项目类别-2分页查询对象")
public class TGlCashFlowTypeQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 编码 */
	@ApiModelProperty("编码")
	private String fNumber;
	/** 名称 */
	@ApiModelProperty("名称")
	private String fName;
	/** 类别属性 主表项目 = 1，附表项目 = 2 */
	@ApiModelProperty("类别属性 主表项目 = 1，附表项目 = 2")
	private Integer fItemGroupid;
	/** 项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4   */
	@ApiModelProperty("项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4  ")
	private Integer fGroupTypeid;
	/** 描述 */
	@ApiModelProperty("描述")
	private String fDescription;
	/** 现金流量项目表  */
	@ApiModelProperty("现金流量项目表 ")
	private Integer fCashFlowItemTable;
	/** 创建组织 */
	@ApiModelProperty("创建组织")
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
	/** 组织隔离字段 */
	@ApiModelProperty("组织隔离字段")
	private Integer fMasterId;

	/** 账簿id */
	@ApiModelProperty("账簿id")
	private Integer bookId;

	/** 币别id */
	@ApiModelProperty("币别id")
	private Integer currencyId;

}
