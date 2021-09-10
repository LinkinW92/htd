package com.skeqi.finance.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 组织管理对象 sys_organization
 *
 * @author toms
 * @date 2021-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_organization")
public class SysOrganization implements Serializable {

    private static final long serialVersionUID=1L;


    /** 组织ID */
    @TableId(value = "id")
    private Integer id;

    /** 组织名称 */
    private String name;

    /** 组织编码 */
    private String orgCode;

    /** 组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他 */
    private String cmpType;

    /** 地址 */
    private String address;

    /** 描述 */
    private String description;

    /** 负责人 */
    private String leader;

    /** 联系人电话 */
    private String phone;

    /** 邮编 */
    private String postCode;

    /** 核算组织 1法人 2利润中心 */
    private String orgType;

    /** 所属法人ID */
    private Integer legalParentId;

    /** 所属法人名称 */
    private String legalParentName;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 数据状态 */
    private Integer status;

    /** 创建人 */
    private Integer createUser;

    /** 更新人 */
    private Integer updateUser;

}
