package com.skeqi.finance.pojo.bo.basicinformation.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 组织管理分页查询对象 sys_organization
 *
 * @author toms
 * @date 2021-07-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("组织管理分页查询对象")
public class SysOrganizationQueryBo extends BaseEntity {

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


	/** 组织名称 */
    @JsonProperty(value = "name")
	@ApiModelProperty("组织名称")
	private String name;
	/** 组织编码 */
    @JsonProperty(value = "orgCode")
	@ApiModelProperty("组织编码")
	private String orgCode;
	/** 组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他 */
    @JsonProperty(value = "cmpType")
	@ApiModelProperty("组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他")
	private String cmpType;
	/** 地址 */
    @JsonProperty(value = "address")
	@ApiModelProperty("地址")
	private String address;
	/** 描述 */
    @JsonProperty(value = "description")
	@ApiModelProperty("描述")
	private String description;
	/** 负责人 */
    @JsonProperty(value = "leader")
	@ApiModelProperty("负责人")
	private String leader;
	/** 联系人电话 */
    @JsonProperty(value = "phone")
	@ApiModelProperty("联系人电话")
	private String phone;
	/** 邮编 */
    @JsonProperty(value = "postCode")
	@ApiModelProperty("邮编")
	private String postCode;
	/** 核算组织 1法人 2利润中心 */
    @JsonProperty(value = "orgType")
	@ApiModelProperty("核算组织 1法人 2利润中心")
	private String orgType;
	/** 所属法人ID */
    @JsonProperty(value = "legalParentId")
	@ApiModelProperty("所属法人ID")
	private Integer legalParentId;
	/** 所属法人名称 */
    @JsonProperty(value = "legalParentName")
	@ApiModelProperty("所属法人名称")
	private String legalParentName;
	/** 数据状态 */
    @JsonProperty(value = "status")
	@ApiModelProperty("数据状态")
	private Integer status;
	/** 创建人 */
    @JsonProperty(value = "createUser")
	@ApiModelProperty("创建人")
	private Integer createUser;
	/** 更新人 */
    @JsonProperty(value = "updateUser")
	@ApiModelProperty("更新人")
	private Integer updateUser;

}
