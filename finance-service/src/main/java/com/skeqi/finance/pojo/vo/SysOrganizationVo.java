package com.skeqi.finance.pojo.vo;

import com.skeqi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 组织管理视图对象 sys_organization
 *
 * @author toms
 * @date 2021-07-16
 */
@Data
@ApiModel("组织管理视图对象")
public class SysOrganizationVo {

	private static final long serialVersionUID = 1L;

	/** 组织ID */
	@ApiModelProperty("组织ID")
	private Integer id;

	/** 组织名称 */
	@Excel(name = "组织名称")
	@ApiModelProperty("组织名称")
	private String name;

	/** 组织编码 */
	@Excel(name = "组织编码")
	@ApiModelProperty("组织编码")
	private String orgCode;

	/** 组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他 */
	@Excel(name = "组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他")
	@ApiModelProperty("组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他")
	private String cmpType;

	/** 地址 */
	@Excel(name = "地址")
	@ApiModelProperty("地址")
	private String address;

	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String description;

	/** 负责人 */
	@Excel(name = "负责人")
	@ApiModelProperty("负责人")
	private String leader;

	/** 联系人电话 */
	@Excel(name = "联系人电话")
	@ApiModelProperty("联系人电话")
	private String phone;

	/** 邮编 */
	@Excel(name = "邮编")
	@ApiModelProperty("邮编")
	private String postCode;

	/** 核算组织 1法人 2利润中心 */
	@Excel(name = "核算组织 1法人 2利润中心")
	@ApiModelProperty("核算组织 1法人 2利润中心")
	private String orgType;

	/** 所属法人ID */
	@Excel(name = "所属法人ID")
	@ApiModelProperty("所属法人ID")
	private Integer legalParentId;

	/** 所属法人名称 */
	@Excel(name = "所属法人名称")
	@ApiModelProperty("所属法人名称")
	private String legalParentName;

	/** 数据状态 */
	@Excel(name = "数据状态")
	@ApiModelProperty("数据状态")
	private Integer status;

	/** 创建人 */
	@Excel(name = "创建人")
	@ApiModelProperty("创建人")
	private Integer createUser;

	/** 更新人 */
	@Excel(name = "更新人")
	@ApiModelProperty("更新人")
	private Integer updateUser;


}
