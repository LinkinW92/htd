package com.skeqi.mes.service.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhRoleDao;
import com.skeqi.mes.pojo.qh.CQhRoleT;
import com.skeqi.mes.service.qh.CQhRoleService;

/**
 * @author yinp
 * @explain 角色管理
 * @date 2020-9-3
 */
@Service
public class CQhRoleServiceImpl implements CQhRoleService {

	@Autowired
	CQhRoleDao dao;

	@Override
	public List<CQhRoleT> queryRoleList(String roleName,Integer id) {
		// TODO Auto-generated method stub
		return dao.queryRoleList(roleName,id);
	}

	@Override
	public Integer addRole(CQhRoleT dx) throws Exception {

		Integer count = dao.queryForDuplicateName(dx.getId(), dx.getRoleName());
		if (count > 0) {
			throw new Exception("角色名称已存在 ");
		}

		return dao.addRole(dx);
	}

	@Override
	public Integer updateRole(CQhRoleT dx) throws Exception {
		Integer count = dao.queryForDuplicateName(dx.getId(), dx.getRoleName());
		if (count > 0) {
			throw new Exception("角色名称已存在 ");
		}
		return dao.updateRole(dx);
	}

	@Override
	public Integer deleteRole(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteRole(id);
	}

}
