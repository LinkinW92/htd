package com.skeqi.mes.service.fqz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.mapper.qh.CmesCrud;
import com.skeqi.mes.mapper.qh.CmesCrudL;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleT;

public interface RoleService {

	// 查询所有角色
	public List<CMesRoleT> findRoleList();

	public List<CMesMenuT> findMenu(Integer id);

	public List<CMesMenuCrudT> findCrud(Integer id, Integer mid);

	public void deleteMenu(Integer id);

	public void addRoleMenu(Integer rid, Integer mid, Integer status);

	//按钮表添加数据
	public Integer addCrud(CmesCrud crud);

	public Integer addMenuCrud(Integer menuId , Integer crudId, Integer roleId);

	//根据用户Id查下按钮信息
	public List<CmesCrudL> findByCrud(Integer userId);

	//根据角色Id查下按钮信息
	public List<	CmesCrudL>  toupdateCrud(@Param("roleId")Integer roleId);

	public List<CmesCrudL> findByMenuId(Integer roleId);

	public void delMenCrud(Integer roleId);

	public void delCrud(Integer menuId);

	public List<Integer> findByMid(Integer roleId);

	public Integer findByRoleName(String name);

	public Integer findCidMenuidRoleid(Integer menuId, Integer roleId, int cid);
}
