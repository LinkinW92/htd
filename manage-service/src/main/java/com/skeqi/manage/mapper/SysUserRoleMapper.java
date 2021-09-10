package com.skeqi.manage.mapper;

import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.manage.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与角色关联表 数据层
 *
 * @author skeqi
 */
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRole> {

	int updateStatus(@Param("list") List<SysUserRole> batchList);
}
