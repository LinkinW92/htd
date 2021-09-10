package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCrud;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.RoleT;

public interface CMesRoleTDAO {

	//查询角色列表
	public List<RoleT> findAllRole(RoleT t);

	//根据ID查询角色列表
	public RoleT findRoleByid(Integer id);

	//查询模块列表
	public List<CMesMenuT> findAllMenu(CMesMenuT t);

	//获取最大的角色id
	public Integer findMaxRoleId();

	//添加角色
	public Integer addRole(RoleT r);

	//修改角色
	public Integer updateRole(RoleT r);

	//添加角色模块中间表
	public Integer addMenuRole(CMesRoleMenuT t);

	//根据角色ID查询用户ID
	public Integer findUser(Integer id);

	//删除角色
	public Integer delRole(Integer id);

	//删除角色模块中间表
	public Integer delMenuRole(Integer id);

	//查询角色拥有 的模块
	public CMesMenuT findMenuByid(Integer id);

	//根据Menu_Type查询模板信息
	public List<CMesMenuT> findMenuByMenu_Type(@Param("MENU_TYPE")String MENU_TYPE);

	//根据ID回显角色信息
	public List<CMesRoleMenuT> toupdate(Integer id);

	//根据ID查询角色权限
	public List<CMesRoleMenuT> findByidCrud(Integer id);

	//根据c_mes_crud_t表ID删除关联表数据
	public Integer delCrud(Integer id);

	//向关联表添加数据
	public int insertCrud(CMesCrud crud);

}
