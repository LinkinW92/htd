package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesCrud;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.RoleT;

public interface CMesRoleTService {

	//查询角色列表
	public List<RoleT> findAllRole(RoleT t) throws ServicesException;

	//根据ID查询角色列表
	public RoleT findRoleByid(Integer id) throws ServicesException;

	//查询模块列表
	public List<CMesMenuT> findAllMenu(CMesMenuT t) throws ServicesException;

	//添加角色
	public Integer addRole(String roleName,String roleDis,String menu) throws ServicesException;

	//删除角色
	public Integer delRole(Integer id) throws ServicesException;

	//查询角色拥有的模块
	public CMesMenuT findMenuByid(Integer id) throws ServicesException;

	//修改角色
	public Integer updateRole(RoleT r,String menu) throws ServicesException;

	//根据Menu_Type查询模板信息
	public List<CMesMenuT> findMenuByMenu_Type(String MENU_TYPE);

	//添加角色
	public Integer insertRole(RoleT roler) throws Exception;

	//向中间表添加数据
	public Integer insertRoleMenu(CMesRoleMenuT menu);

	//回显
	public List<CMesRoleMenuT> toupdate(Integer id);

	//修改角色表
	public Integer update(RoleT roler);

	//根据角色ID删除中间表数据
	public Integer delRoleMenu(int id);

	//根据ID查询角色权限
	public List<CMesRoleMenuT> findByidCrud(Integer id);

	//删除关联表数据
	public Integer delCrud(Integer id);

	//向关联表添加数据
	public int insertCrud(CMesCrud crud);
}
