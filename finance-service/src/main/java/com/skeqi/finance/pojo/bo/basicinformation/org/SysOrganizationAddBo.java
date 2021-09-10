package com.skeqi.finance.pojo.bo.basicinformation.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 组织管理添加对象 sys_organization
 *
 * @author toms
 * @date 2021-07-16
 */
@Data
@ApiModel("组织管理添加对象")
public class SysOrganizationAddBo {


    /** 组织名称 */
    @ApiModelProperty("组织名称")
    @JsonProperty(value = "name")
    private String name;

    /** 组织编码 */
    @ApiModelProperty("组织编码")
    @JsonProperty(value = "orgCode")
    private String orgCode;

    /** 组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他 */
    @ApiModelProperty("组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他")
    @JsonProperty(value = "cmpType")
    private String cmpType;

    /** 地址 */
    @ApiModelProperty("地址")
    @JsonProperty(value = "address")
    private String address;

    /** 描述 */
    @ApiModelProperty("描述")
    @JsonProperty(value = "description")
    private String description;

    /** 负责人 */
    @ApiModelProperty("负责人")
    @JsonProperty(value = "leader")
    private String leader;

    /** 联系人电话 */
    @ApiModelProperty("联系人电话")
    @JsonProperty(value = "phone")
    private String phone;

    /** 邮编 */
    @ApiModelProperty("邮编")
    @JsonProperty(value = "postCode")
    private String postCode;

    /** 核算组织 1法人 2利润中心 */
    @ApiModelProperty("核算组织 1法人 2利润中心")
    @JsonProperty(value = "orgType")
    private String orgType;

    /** 所属法人ID */
    @ApiModelProperty("所属法人ID")
    @JsonProperty(value = "legalParentId")
    private Integer legalParentId;

    /** 所属法人名称 */
    @ApiModelProperty("所属法人名称")
    @JsonProperty(value = "legalParentName")
    private String legalParentName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonProperty(value = "createTime")
    private Date createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @JsonProperty(value = "updateTime")
    private Date updateTime;

    /** 数据状态 */
    @ApiModelProperty("数据状态")
    @JsonProperty(value = "status")
    private Integer status;

    /** 创建人 */
    @ApiModelProperty("创建人")
    @JsonProperty(value = "createUser")
    private Integer createUser;

    /** 更新人 */
    @ApiModelProperty("更新人")
    @JsonProperty(value = "updateUser")
    private Integer updateUser;
}
