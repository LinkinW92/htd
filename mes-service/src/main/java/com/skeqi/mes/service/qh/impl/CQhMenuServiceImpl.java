package com.skeqi.mes.service.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhMenuDao;
import com.skeqi.mes.pojo.qh.CQhMenuT;
import com.skeqi.mes.service.qh.CQhMenuService;

/**
 * @author yinp
 * @explain 菜单
 * @date 2020-9-7
 */
@Service
public class CQhMenuServiceImpl implements CQhMenuService {

	@Autowired
	CQhMenuDao dao;

	@Override
	public List<JSONObject> findMenuList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.findMenuList(json);
	}

	@Override
	public Integer addMenu(JSONObject dx) throws Exception {
		// 查询是否有重名
		Integer count = dao.findMenuBYIdAndName(null, dx.getString("menuName"));
		if (count>0) {
			throw new Exception("菜单名称已存在");
		}

		return dao.addMenu(dx);
	}

	@Override
	public Integer updateMenu(JSONObject dx) throws Exception {
		// 查询是否有重名
		Integer count = dao.findMenuBYIdAndName(dx.getInteger("id"), dx.getString("menuName"));
		if (count>0) {
			throw new Exception("菜单名称已存在");
		}
		return dao.updateMenu(dx);
	}

	@Override
	public Integer deleteMenu(Integer id) {

		//删除当前选中的菜单
		dao.deleteMenuById(id);
		//查询是否还有下级菜单
		List<CQhMenuT> menuList = dao.findMenuBySuperiorMenuIdList(id);
		//如果没有下级菜单了直接完成
		if(menuList==null || menuList.size()==0) {
			return 1;
		}
		//如果有就遍历下级菜单
		for (CQhMenuT cQhMenuT : menuList) {
			//同递归删除下级 制止删除 所有下级
			deleteMenu(cQhMenuT.getId());
		}

		return 1;
	}

	@Override
	public List<CQhMenuT> findMenuBySuperiorMenuIdList(Integer superiorMenuId) {
		// TODO Auto-generated method stub
		return dao.findMenuBySuperiorMenuIdList(superiorMenuId);
	}

}
