package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.qh.CQhRoleT;

/**
 * @author yinp
 * @explain 角色管理
 * @date 2020-9-3
 */
public interface CQhRoleDao {

	/**
	 * @explain 查询角色集合
	 * @param json
	 * @return com.skeqi.mes.pojo.qh.CQhRoleT
	 */
	public List<CQhRoleT> queryRoleList(@Param("roleName")String  roleName,@Param("id")Integer  id);

	/**
	 * @explain 查询是否重名
	 * @param json
	 * @return
	 */
	public Integer queryForDuplicateName(@Param("id")Integer id,@Param("roleName")String  roleName);

	/**
	 * @explain 新增角色
	 * @param dx
	 * @return
	 */
	public Integer addRole(CQhRoleT dx);

	/**
	 * @explain 更新角色
	 * @param dx
	 * @return
	 */
	public Integer updateRole(CQhRoleT dx);

	/**
	 * @explain 删除角色
	 * @param id
	 * @return
	 */
	public Integer deleteRole(@Param("id")Integer id);

}
