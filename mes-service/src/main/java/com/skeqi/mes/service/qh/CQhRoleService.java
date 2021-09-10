package com.skeqi.mes.service.qh;

import java.util.List;

import com.skeqi.mes.pojo.qh.CQhRoleT;

/**
 * @author yinp
 * @explain 角色管理
 * @date 2020-9-3
 */
public interface CQhRoleService {

	/**
	 * @explain 查询角色集合
	 * @param json
	 * @return com.skeqi.mes.pojo.qh.CQhRoleT
	 */
	public List<CQhRoleT> queryRoleList(String  roleName,Integer id);

	/**
	 * @explain 新增角色
	 * @param dx
	 * @return
	 */
	public Integer addRole(CQhRoleT dx) throws Exception;

	/**
	 * @explain 更新角色
	 * @param dx
	 * @return
	 */
	public Integer updateRole(CQhRoleT dx) throws Exception;

	/**
	 * @explain 删除角色
	 * @param id
	 * @return
	 */
	public Integer deleteRole(Integer id);

}
