package com.skeqi.mes.service.all;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.Exception.util.UnknownException;
import com.skeqi.mes.mapper.all.CMesRoleTDAO;
import com.skeqi.mes.pojo.CMesCrud;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.RoleT;

@Service
@Transactional
public class CMesRoleTServiceImpl implements CMesRoleTService {

	@Autowired
	private CMesRoleTDAO dao;

	@Override
	public List<RoleT> findAllRole(RoleT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRole(t);
	}

	@Override
	public List<CMesMenuT> findAllMenu(CMesMenuT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMenu(t);
	}

	@Override
	public Integer addRole(String roleName, String roleDis, String menu) throws ServicesException {
		// TODO Auto-generated method stub
		if (roleName == null || roleName == "") {
			throw new ParameterNullException("角色名称不能为空", 200);
		}
		RoleT t = new RoleT();
		t.setRoleName(roleName);
		List<RoleT> findAllRole = dao.findAllRole(t);
		if (findAllRole.size() > 0) {
			throw new NameRepeatException("角色名称重复", 100);
		}
		t.setRoleDis(roleDis);
		Integer addRole = dao.addRole(t); // 添加角色
		if (addRole <= 0) {
			throw new UnknownException("未知错误", 300);
		}

		String[] split = menu.split(","); // 分割子菜单
		Set<String> set = new HashSet<>();
		Set<String> set1 = new HashSet<>();
		for (String string : split) { // 所有模块ID
			set.add(string); // 去除重复的ID
		}

		Integer findMaxRoleId = dao.findMaxRoleId(); // 获取添加的角色id

		for (String string : set) {
			CMesRoleMenuT rmenu = new CMesRoleMenuT();
			rmenu.setRoleId(findMaxRoleId);
			CMesMenuT m = new CMesMenuT();
			m.setId(Integer.parseInt(string));
			List<CMesMenuT> findAllMenu = dao.findAllMenu(m); // 获取模块信息
			for (CMesMenuT cMesMenuT : findAllMenu) {
				set1.add(cMesMenuT.getMenuType()); // 获取子模块的父类模块ID
			}
			Integer addMenuRole = dao.addMenuRole(rmenu); // 添加子类模块与角色ID
			if (addMenuRole <= 0) {
				throw new UnknownException("未知错误", 300);
			}
		}

		for (String string : set1) {
			CMesRoleMenuT rmenu = new CMesRoleMenuT();
			rmenu.setRoleId(findMaxRoleId);
			rmenu.setMenuId(Integer.parseInt(string));
			Integer addMenuRole = dao.addMenuRole(rmenu); // 添加子类模块与角色ID
			if (addMenuRole <= 0) {
				throw new UnknownException("未知错误", 300);
			}
		}
		return 1;
	}

	@Override
	public Integer delRole(Integer id) throws ServicesException {
		// TODO Auto-generated method stub、

		if (id == null) {
			throw new ParameterNullException("id不能为空", 200);
		}

//		Integer findUser = mapper.findUser(id); // 查询该角色是否正在被使用
//		if (findUser > 0) {
//			throw new ParameterNullException("该角色正在被使用,不能删除", 200);
//		}

		return dao.delRole(id);
	}

	@Override
	public RoleT findRoleByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findRoleByid(id);
	}

	@Override
	public CMesMenuT findMenuByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null) {
			throw new ParameterNullException("id不能为空", 200);
		}
		return dao.findMenuByid(id);
	}

	@Override
	public Integer updateRole(RoleT r, String menu) throws ServicesException {
		// TODO Auto-generated method stub
		if (r.getRoleName() == null || r.getRoleName() == "") {
			throw new ParameterNullException("角色不能为空", 200);
		} else if (r.getId() == 0) {
			throw new ParameterNullException("id不能为0", 200);
		}
		RoleT findRoleByid = dao.findRoleByid(r.getId());
		if (!findRoleByid.getRoleName().equals(r.getRoleName())) {
			List<RoleT> findAllRole = dao.findAllRole(r);
			if (findAllRole.size() > 0) {
				throw new NameRepeatException("角色名称重复", 100);
			}
		}
		dao.updateRole(r); // 修改角色00
		dao.delMenuRole(r.getId()); // 删除之前的r角色模块中间表
		String[] split = menu.split(","); // 分割子菜单
		Set<String> set = new HashSet<>();
		Set<String> set1 = new HashSet<>();
		for (String string : split) { // 所有模块ID
			set.add(string); // 去除重复的ID
		}

		Integer findMaxRoleId = dao.findMaxRoleId(); // 获取添加的角色id

		for (String string : set) {
			CMesRoleMenuT rmenu = new CMesRoleMenuT();
			rmenu.setRoleId(findMaxRoleId);
			CMesMenuT m = new CMesMenuT();
			m.setId(Integer.parseInt(string));
			List<CMesMenuT> findAllMenu = dao.findAllMenu(m); // 获取模块信息
			for (CMesMenuT cMesMenuT : findAllMenu) {
				set1.add(cMesMenuT.getMenuType()); // 获取子模块的父类模块ID
			}
			Integer addMenuRole = dao.addMenuRole(rmenu); // 添加子类模块与角色ID
			if (addMenuRole <= 0) {
				throw new UnknownException("未知错误", 300);
			}
		}

		for (String string : set1) {
			CMesRoleMenuT rmenu = new CMesRoleMenuT();
			rmenu.setRoleId(findMaxRoleId);
			rmenu.setMenuId(Integer.parseInt(string));
			Integer addMenuRole = dao.addMenuRole(rmenu); // 添加子类模块与角色ID
			if (addMenuRole <= 0) {
				throw new UnknownException("未知错误", 300);
			}
		}
		return 1;
	}

	@Override
	public List<CMesMenuT> findMenuByMenu_Type(String MENU_TYPE) {
		return dao.findMenuByMenu_Type(MENU_TYPE);
	}

	@Override
	public Integer insertRole(RoleT roler) throws Exception {
		RoleT t = new RoleT();
		t.setRoleName(roler.getRoleName());
		List<RoleT> findAllRole = dao.findAllRole(t);
		if (findAllRole.size() > 0) {
			throw new NameRepeatException("角色名称重复", 100);
		} else {
			return dao.addRole(roler);
		}
	}

	@Override
	public Integer insertRoleMenu(CMesRoleMenuT menu) {
		return dao.addMenuRole(menu);

	}

	// 回显角色信息
	@Override
	public List<CMesRoleMenuT> toupdate(Integer id) {
		// TODO Auto-generated method stub
		return dao.toupdate(id);
	}

	// 修改角色信息
	@Override
	public Integer update(RoleT roler) {
		// TODO Auto-generated method stub
		return dao.updateRole(roler);
	}

	// 根据角色ID删除中间表数据
	@Override
	public Integer delRoleMenu(int id) {
		// TODO Auto-generated method stub
		return dao.delMenuRole(id);
	}

	// 根据ID查询角色权限
	@Override
	public List<CMesRoleMenuT> findByidCrud(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByidCrud(id);
	}

	// 根据c_mes_crud_t表ID删除关联表数据
	@Override
	public Integer delCrud(Integer id) {
		// TODO Auto-generated method stub
		return dao.delCrud(id);
	}

	// 向关联表添加数据
	@Override
	public int insertCrud(CMesCrud crud) {
		// TODO Auto-generated method stub
		return dao.insertCrud(crud);
	}


}
