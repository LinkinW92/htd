package com.skeqi.mes.service.fqz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.RoleDAO;
import com.skeqi.mes.mapper.qh.CmesCrud;
import com.skeqi.mes.mapper.qh.CmesCrudL;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleT;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDAO dao;

	@Override
	public List<CMesRoleT> findRoleList() {
		// TODO Auto-generated method stub
		return dao.findRoleList();
	}

	public List<CMesMenuT> findMenu(Integer id){
		return dao.findMenu(id);
	}


	@Override
	public List<CMesMenuCrudT> findCrud(Integer id,Integer mid) {
		// TODO Auto-generated method stub
		return dao.findCrud(id,mid);
	}


	@Override
	public void deleteMenu(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteMenu(id);
	}


	@Override
	public void addRoleMenu(Integer rid, Integer mid, Integer status) {
		// TODO Auto-generated method stub
		dao.addRoleMenu(rid, mid, status);
	}

	@Override
	public Integer addCrud(CmesCrud crud) {
	return	dao.addCrud( crud);
	}

	@Override
	public Integer addMenuCrud(Integer  menuId, Integer crudId, Integer roleId) {
		// TODO Auto-generated method stub
		return dao.addMenuCrud(menuId, crudId, roleId);
	}

	@Override
	public List<CmesCrudL> findByCrud(Integer userId) {
		// TODO Auto-generated method stub
		return dao.findByCrud( userId);
	}

	@Override
	public List<CmesCrudL> toupdateCrud(Integer roleId) {
		// TODO Auto-generated method stub
		return dao. toupdateCrud( roleId);
	}

	@Override
	public List<CmesCrudL> findByMenuId(Integer roleId) {
		// TODO Auto-generated method stub
		return dao.findByMenuId( roleId);
	}

	@Override
	public void delMenCrud(Integer roleId) {
		dao.delMenCrud( roleId);

	}

	@Override
	public void delCrud(Integer menuId) {
		dao.delCrud( menuId);

	}

	@Override
	public List<Integer> findByMid(Integer roleId) {
		// TODO Auto-generated method stub
		return dao.findByMid( roleId);
	}

	@Override
	public Integer findByRoleName(String name) {
		// TODO Auto-generated method stub
		return dao.findByRoleName( name);
	}

	@Override
	public Integer findCidMenuidRoleid(Integer menuId, Integer roleId, int cid) {
		// TODO Auto-generated method stub
		return dao.findCidMenuidRoleid( menuId,  roleId,  cid);
	}



}
