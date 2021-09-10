package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.qh.CmesCrud;
import com.skeqi.mes.mapper.qh.CmesCrudL;
import com.skeqi.mes.pojo.CMesCrud;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuTl;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.pojo.project.Scheduling;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.fqz.RoleService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Scope;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "api")
@Api(value = "角色管理", description = "角色管理")
public class CMesRoleManagerControllerl {

	@Autowired
	CMesRoleTService roleService;
	@Autowired
	private RoleService service;

	// 角色列表
	@RequestMapping(value = "/role/queryAll", method = RequestMethod.POST)
	@ApiOperation(value = "角色列表", notes = "角色列表")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int") })
	public JSONObject findAllRole(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		RoleT roleT = new RoleT();
		roleT.setRoleName(null);
		List<RoleT> RoleT = roleService.findAllRole(roleT);
		PageInfo<RoleT> pageInfo = new PageInfo<>(RoleT);

		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 模板列表
	@RequestMapping(value = "/role/findCMesMenuT", method = RequestMethod.POST)
	@ApiOperation(value = "模块列表 ", notes = "模块列表 ")
	@ApiImplicitParam(name = "MENU_TYPE", value = "Menu_Type", required = false, paramType = "query", dataType = "String")
	@ResponseBody
	public JSONObject findCMesMenuT(HttpServletRequest request, String MENU_TYPE) throws ServicesException {

		JSONObject json = new JSONObject();
		try {
			List<CMesMenuT> menu = roleService.findMenuByMenu_Type(MENU_TYPE);
			json.put("code", 0);
			json.put("msg", menu);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;

	}

	// 添加角色
	@RequestMapping(value = "/role/addRole", method = RequestMethod.POST)
	@ApiOperation(value = "添加角色", notes = "添加角色")
	@ResponseBody
	@OptionalLog(module="基础建模", module2="角色管理", method="添加角色")
	public JSONObject lineManager(HttpServletRequest request, @ModelAttribute CMesRoleMenuTl cMesRoleMenuT) {

		JSONObject json = new JSONObject();
		try {
			RoleT roler = new RoleT();
			roler.setRoleName(cMesRoleMenuT.getRoleName());
			roler.setRoleDis(cMesRoleMenuT.getRoleDis());
			// 添加角色数据
			roleService.insertRole(roler);
			Integer id = roler.getId();
			Integer[] arr = cMesRoleMenuT.getMenuId();
			for (Integer integer : arr) {
				CMesRoleMenuT menu = new CMesRoleMenuT();
				menu.setRoleId(id);
				menu.setMenuId(integer);
				// 向中间表添加数据
				roleService.insertRoleMenu(menu);
			}
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 根据ID查询角色信息
	@RequestMapping(value = "/role/toUpdateRole", method = RequestMethod.POST)
	@ApiOperation(value = "根据ID查询角色信息", notes = "根据ID查询角色")
	@ResponseBody
	@ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "query", dataType = "int")
	public JSONObject toUpdateRole(HttpServletRequest request, Integer id) {

		JSONObject json = new JSONObject();
		try {
			List<CMesRoleMenuT> cMesRoleMenuTs = roleService.toupdate(id);
			json.put("code", 0);
			json.put("msg", cMesRoleMenuTs);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 修改角色信息
	@RequestMapping(value = "/role/editRole", method = RequestMethod.POST)
	@ApiOperation(value = "修改角色", notes = "修改角色")
	@ResponseBody
	@OptionalLog(module="基础建模", module2="角色管理", method="修改角色")
	public JSONObject editRole(@ModelAttribute CMesRoleMenuTl cMesRoleMenuT) {
		JSONObject json = new JSONObject();
		try {
			RoleT roler = new RoleT();
			roler.setRoleName(cMesRoleMenuT.getRoleName());
			roler.setRoleDis(cMesRoleMenuT.getRoleDis());
			roler.setId(cMesRoleMenuT.getRoleId());
			// 根据角色ID删除中间表数据
			roleService.delRoleMenu(roler.getId());
			// 添加角色数据
			roleService.update(roler);
			Integer id = roler.getId();
			Integer[] arr = cMesRoleMenuT.getMenuId();
			for (Integer integer : arr) {
				CMesRoleMenuT menu = new CMesRoleMenuT();
				menu.setRoleId(id);
				menu.setMenuId(integer);
				// 向中间表添加数据
				roleService.insertRoleMenu(menu);
			}
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "修改失败");
		}
		return json;
	}

	// 根据ID查询角色权限
	@RequestMapping(value = "/role/findByidCrud", method = RequestMethod.POST)
	@ApiOperation(value = "查询角色权限", notes = "根据角色ID查询权限")
	@ResponseBody
	@ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "query", dataType = "int")
	public JSONObject findByidCrud(HttpServletRequest request, Integer id) {
		// 修改角色
		JSONObject json = new JSONObject();
		try {
			List<CMesRoleMenuT> cMesRoleMenuT = roleService.findByidCrud(id);

			json.put("code", 0);
			json.put("msg", cMesRoleMenuT);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 删除角色信息
	@RequestMapping(value = "/role/delRole", method = RequestMethod.POST)
	@ApiOperation(value = "删除角色", notes = "根据ID删除角色信息")
	@ResponseBody
	@ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "query", dataType = "int")
	@OptionalLog(module="基础建模", module2="角色管理", method="删除角色")
	public JSONObject delRole(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			// 删除角色信息
			roleService.delRole(id);
			// 删除中间表数据
			roleService.delRoleMenu(id);
			// 删除按钮表
			List<CmesCrudL> mids = service.findByMenuId(id);// 根据角色id获取模板id
			for (CmesCrudL m : mids) {
				int mid = Integer.parseInt(m.getId());
				service.delCrud(mid);
			}
			// 删除按钮权限表
			service.delMenCrud(id);

			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 添加按钮权限
	@RequestMapping(value = "/role/addCrud", method = RequestMethod.POST)
	@ApiOperation(value = "添加按钮权限", notes = "添加按钮权限")
	@ResponseBody
	@OptionalLog(module="基础建模", module2="角色管理", method="添加按钮权限")
	public JSONObject addCrud(@RequestBody List<CmesCrud> slist, HttpServletRequest request) throws ServicesException {
		JSONObject json = new JSONObject();
		Integer roleId = null;
		try {
			for (CmesCrud cmesCrud : slist) {
				Integer menuId = cmesCrud.getId();// 菜单id
				roleId = cmesCrud.getRoleId();// 模块ID
				List<CmesCrudL> children = cmesCrud.getChildren();
				for (CmesCrudL cmesCrudL : children) {
					String url = cmesCrudL.getId();// 按钮url
					String name = cmesCrudL.getLabel();// 按钮权限名
					CmesCrud crud = new CmesCrud();
					crud.setLabel(name);
					crud.setUrl(url);
					crud.setId(menuId);
					service.addCrud(crud);// 按钮表（crud）添加数据
					int cid = crud.getCid();// 按钮id
					Integer a = service.findCidMenuidRoleid(menuId, roleId, cid);// 查询当前权限是否添加了防止重复添加
					if (a == 0) {
						service.addMenuCrud(menuId, roleId, cid);// 中间表添加数据
					}
				}
			}

			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "添加失败");
		}
		return json;
	}

	// 修改按钮权限
	@RequestMapping(value = "/role/updateCrud", method = RequestMethod.POST)
	@ApiOperation(value = "修改按钮权限", notes = "修改按钮权限")
	@ResponseBody
	@OptionalLog(module="基础建模", module2="角色管理", method="修改按钮权限")
	public JSONObject updateCrud(@RequestBody List<CmesCrud> slist) throws ServicesException {
		JSONObject json = new JSONObject();
		Integer roleId = null;
		for (CmesCrud cmesCrud : slist) {
			Integer roleId2 = cmesCrud.getRoleId();
			service.delCrud(cmesCrud.getId());
			service.delMenCrud(roleId2);
		}
		try {
			for (CmesCrud cmesCrud : slist) {
				Integer menuId = cmesCrud.getId();// 菜单id
				roleId = cmesCrud.getRoleId();// 模块ID
				List<CmesCrudL> children = cmesCrud.getChildren();
				for (CmesCrudL cmesCrudL : children) {
					String url = cmesCrudL.getId();// 按钮url
					String name = cmesCrudL.getLabel();// 按钮权限名
					CmesCrud crud = new CmesCrud();
					crud.setLabel(name);
					crud.setUrl(url);
					crud.setId(menuId);
					service.addCrud(crud);// 按钮表（crud）添加数据
					int cid = crud.getCid();// 按钮id
					Integer a = service.findCidMenuidRoleid(menuId, roleId, cid);// 查询当前权限是否添加了防止重复添加
					if (a == 0) {
						service.addMenuCrud(menuId, roleId, cid);// 中间表添加数据
					}
				}
			}

			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "修改失败");
		}
		return json;
	}

	@RequestMapping(value = "/role/findByCrud", method = RequestMethod.POST)
	@ApiOperation(value = "查询按钮权限", notes = "查询按钮权限")
	@ResponseBody
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
	public JSONObject findByCrud(Integer userId) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			List<CmesCrudL> crud = service.findByCrud(userId);// 查询按钮权限
			List<Object> list = new ArrayList<Object>();
			for (CmesCrudL c : crud) {
				list.add(c.getId());
			}
			json.put("code", 0);
			json.put("msg", list);
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "查询失败");
		}
		return json;
	}

	// 根据角色ID查询按钮信息
	@RequestMapping(value = "/role/toUpdateCrud", method = RequestMethod.POST)
	@ApiOperation(value = "根据角色ID查询按钮信息", notes = "根据ID查询按钮信息")
	@ResponseBody
	@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, paramType = "query", dataType = "int")
	public JSONObject toUpdateCrud(HttpServletRequest request, Integer roleId) {

		JSONObject json = new JSONObject();
		List<Object> list = new ArrayList<Object>();
		try {
			List<CmesCrudL> crud = service.toupdateCrud(roleId);// 查询按钮权限
			List<CmesCrudL> mid = service.findByMenuId(roleId);// 根据角色id获取模板id
			for (CmesCrudL m : mid) {
				list.add(Integer.parseInt(m.getId()));
			}
			for (CmesCrudL c : crud) {
				list.add(c.getId());
			}
			json.put("code", 0);
			json.put("msg", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}


}
