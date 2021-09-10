package com.skeqi.mes.mapper.fqz;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.skeqi.mes.mapper.qh.CmesCrud;
import com.skeqi.mes.mapper.qh.CmesCrudL;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleT;

public interface RoleDAO {

	//查询所有角色
	public List<CMesRoleT> findRoleList();

	//根据角色id查询他的模块权限
	public List<CMesMenuT> findMenu(Integer id);

	//根据角色查看可以看到的增伤改按钮
	public List<CMesMenuCrudT> findCrud(@Param("id")Integer id,@Param("mid")Integer mid);

	//删除此角色的所有增删改按钮的权限
	public void deleteMenu(Integer id);

	//添加角色权限
	public void addRoleMenu(@Param("rid")Integer rid,@Param("mid")Integer mid,@Param("status")Integer status);

	//添加按钮表数据
	public Integer addCrud(CmesCrud crud);

	//向中间表添加数据
	@Insert("insert into  c_mes_menu_crud_t(MENU_ID,MENU_CRUD_ID,ROLE_ID) values(#{menuId},#{crudId},#{roleId}) ")
	public Integer addMenuCrud(  @Param("menuId")Integer menuId,@Param("roleId")Integer roleId, @Param("crudId")Integer crudId);

	public List<	CmesCrudL> findByCrud(@Param("userId")Integer userId);

	public List<CmesCrudL> toupdateCrud(@Param("roleId")Integer roleId);

	public List<CmesCrudL> findByMenuId(@Param("roleId")Integer roleId);

	//根据角色id删除菜单按钮中间表
	@Delete("delete from   c_mes_menu_crud_t  where ROLE_ID=#{roleId}")
	public void delMenCrud(@Param("roleId")Integer roleId);

	//根据菜单id删除按钮表
	@Delete("delete from   c_mes_crud_t   where id=#{menuId}")
	public void delCrud(@Param("menuId")Integer menuId);

	//根据角色id查询中间表获取菜单id
	@Select(" select MENU_ID from  c_mes_menu_crud_t  where   ROLE_ID = #{roleId}   GROUP BY MENU_ID")
	public List<Integer> findByMid(@Param("roleId")Integer roleId);

	//根据用户名查询角色id
	@Select("select ROLE_ID from c_mes_user_t u LEFT JOIN c_mes_role_t  r on u.ROLE_ID=r.id  where USER_NAME=#{name} ")
	public Integer findByRoleName(@Param("name")String name);

	//查询中间表是否存在此数据
	@Select("SELECT COUNT(*) FROM c_mes_menu_crud_t WHERE MENU_ID=#{menuId}  AND MENU_CRUD_ID=#{roleId} AND  ROLE_ID=#{cid}")
	public Integer findCidMenuidRoleid(@Param("menuId")Integer menuId,@Param("roleId") Integer roleId,@Param("cid")  int cid);






}
