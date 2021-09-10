package com.skeqi.mes.controller.yin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesInterface;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.CMesTrayT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.gmg.UserService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;

/**
 * @author SKQ-LYH
 */
@Controller
@RequestMapping("skq")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UsersService usersService;

	@Autowired
	CMesLineTService lineservice;

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	/**
	 * 查询集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "user/findList", method = RequestMethod.POST)
	public void findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		Integer roleId = Integer.parseInt(request.getParameter("roleId")); // 部门编号
		String department = request.getParameter("dmId"); // 部门名称

		CMesUserT dx = new CMesUserT();
		dx.setRoleId(roleId);
		dx.setDepartment(department);

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {
			List<CMesUserT> list = usersService.findUserList(dx);

			data.put("msg", "查询成功！");
			data.put("code", 0);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", "查询失败，数据异常！");
			data.put("code", -1);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	/**
	 * 登录
	 */
	@RequestMapping("confirmUserLogin")
	public @ResponseBody Object confirmUserLogin(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String userName = request.getParameter("username").trim();
		String pwd = request.getParameter("password").trim();
		int a = userService.GetUserInfoByCondition(userName, pwd);
		if (a > 0) {
			Cookie nameCookie = new Cookie("userName", URLEncoder.encode(userName, "UTF-8"));
			nameCookie.setPath("/");
			// nameCookie.setMaxAge(-1);// 设置Cookie失效时间 负数永久不失效
			response.addCookie(nameCookie);
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		return map;
	}

	/**
	 * 登出
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpServletResponse response) {
		Cookie newCookie = new Cookie("userName", null);
		newCookie.setMaxAge(0);
		newCookie.setPath("/");
		// 重新写入，将覆盖之前的
		response.addCookie(newCookie);
		return "redirect:login";
	}

	@RequestMapping("getAllUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 用户列表
	 */
	/*
	 * @RequestMapping("userList") public String userList(HttpSession session,
	 * HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page")
	 * Integer page) { Map<String, Object> map = new HashMap<String, Object>();
	 * PageHelper.startPage(page, 8); List<CMesUserT> userList =
	 * usersService.userList(map); PageInfo<CMesUserT> pageInfo = new
	 * PageInfo<>(userList, 5);
	 *
	 * PageHelper.startPage(page, 8); List<RoleT> roleList =
	 * usersService.roleList(map); PageInfo<RoleT> pageInfo2 = new
	 * PageInfo<>(roleList, 5); map.put("grade", 1); List<CMesMenuT> menuList =
	 * usersService.menuList(map); session.setAttribute("pageInfo2", pageInfo2);
	 * session.setAttribute("roleList", roleList);
	 * session.setAttribute("menuList", menuList);
	 * request.setAttribute("pageInfo", pageInfo); return
	 * "base_control/userManager"; }
	 */

	@RequestMapping("roleManager")
	public String roleManager(HttpSession session, HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, 8);
		List<CMesUserT> userList = usersService.userList(map);
		PageInfo<CMesUserT> pageInfo = new PageInfo<>(userList, 5);

		PageHelper.startPage(page, 8);
		List<RoleT> roleList = usersService.roleList(map);
		PageInfo<RoleT> pageInfo2 = new PageInfo<>(roleList, 5);
		map.put("grade", 1);
		List<CMesMenuT> menuList = usersService.menuList(map);
		request.setAttribute("pageInfo2", pageInfo2);
		request.setAttribute("roleList", roleList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("pageInfo", pageInfo);
		return "base_control/roleManager";
	}

	/**
	 * 添加用户
	 */
	/*
	 * @RequestMapping("addUser")
	 *
	 * @ResponseBody public Map<String, Object> addUser(HttpServletRequest
	 * request) { Map<String, Object> map = new HashMap<String, Object>();
	 * String userName = request.getParameter("userName").trim(); String userPwd
	 * = request.getParameter("userPwd").trim(); String role =
	 * request.getParameter("role"); String status =
	 * request.getParameter("isChecked"); String i = "1"; if (i.equals(status))
	 * { map.put("status", 1); } else { map.put("status", 0); }
	 * map.put("userName1", userName); map.put("userPwd",
	 * Encryption.getPassWord(userPwd + userName + 666666 + userPwd, 555));
	 * map.put("roleId", role); List<CMesUserT> userList =
	 * usersService.userList(map); if (userList.size() > 0) { map.put("msg",
	 * -1); return map; } try { int number = usersService.getMaxNumber();
	 * map.put("userNumber", number + 1); usersService.addUser(map);
	 * map.put("msg", 0); } catch (Exception e) { } return map; }
	 */

	/**
	 * 删除用户
	 */
	/*
	 * @RequestMapping("delUser") public @ResponseBody Map<String, Object>
	 * delUser(HttpServletRequest request) { String id =
	 * request.getParameter("id"); Map<String, Object> map = new HashMap<String,
	 * Object>(); try { map.put("id", id); usersService.delUser(map);
	 * map.put("msg", "ok"); } catch (Exception e) { map.put("msg", 0); } return
	 * map; }
	 */

	/**
	 * 删除角色
	 */
	@RequestMapping("delRole")
	public @ResponseBody Map<String, Object> delRole(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("roleId", id);
			int count = usersService.countUserByRoleId(map);
			if (count > 0) {
				map.put("msg", -1);
				return map;
			}
			usersService.removeRoleByRoleId(map);
			usersService.removeRoleAndMenuByRoleId(map);
			map.put("msg", 0);
		} catch (Exception e) {
			map.put("msg", 1);
		}
		return map;
	}

	/*	*//**
			 * 将要修改的用户信息查询出来 显示在输入框
			 *//*
			 * @RequestMapping("toUpdateUser") public @ResponseBody Map<String,
			 * Object> toUpdateUser(HttpServletRequest request) { String id =
			 * request.getParameter("id"); Map<String, Object> map = new
			 * HashMap<String, Object>(); map.put("id", id); List<CMesUserT>
			 * users = usersService.userList(map); map.put("user",
			 * users.get(0)); return map; }
			 */

	@RequestMapping("toUpdateRoles")
	public @ResponseBody Map<String, Object> toUpdateRole(HttpServletRequest request) throws Exception {
		StringBuilder str = new StringBuilder();
		String roleId = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("flag", 1);
		List<RoleT> roleList = usersService.roleList(map); // 当前角色
		List<CMesRoleMenuT> roleMenuList = usersService.roleAndMenuList(map); // 当前角色拥有的模块
		/*
		 * List<CMesMenuCrudT> listMenuCrudByRoleId =
		 * usersService.listMenuCrudByRoleId(map); //当前角色可以执行的增删改
		 * map.put("listMenuCrudByRoleId", listMenuCrudByRoleId);
		 */
		for (int i = 0; i < roleMenuList.size(); i++) {
			str.append("<li value='" + roleMenuList.get(i).getMenuId() + "'>" + roleMenuList.get(i).getMenuName()
					+ "</li>");
		}
		System.err.println("hahhhhhhhhhhhhh");
		map.put("roleMenuList", roleMenuList);
		map.put("role", roleList.get(0));
		map.put("menuList", str);
		System.err.println("lllllllllllllllll" + map);
		return map;
	}

	/**
	 * 修改用户
	 */
	/*
	 * @RequestMapping("updateUser") public @ResponseBody Map<String, Object>
	 * updateUser(HttpServletRequest request) { String id =
	 * request.getParameter("id"); String userName1 =
	 * request.getParameter("userName1").trim(); String userPwd1 =
	 * request.getParameter("userPwd1").trim(); String role1 =
	 * request.getParameter("role1"); String status =
	 * request.getParameter("status"); Map<String, Object> map = new
	 * HashMap<String, Object>(); Map<String, Object> map1 = new HashMap<String,
	 * Object>(); map.put("id", id); map1.put("userName1", userName1);
	 * map.put("userName", userName1); map.put("userPwd",
	 * Encryption.getPassWord(userPwd1 + userName1 + 666666 + userPwd1, 555));
	 * map.put("roleId", role1); map.put("status", status); List<CMesUserT>
	 * users = usersService.userList(map); if
	 * (!users.get(0).getUserName().equals(userName1)) { List<CMesUserT>
	 * userList = usersService.userList(map1); if (userList.size() > 0) {
	 * map.put("msg", 1); return map; } } String oldPwd =
	 * users.get(0).getUserPwd(); // 如果新密码和原密码一样，不修改密码 if
	 * (userPwd1.equals(oldPwd)) { map.put("userPwd", oldPwd); } try {
	 * usersService.updateUser(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 */

	/**
	 * 添加角色/配置权限
	 */
	@RequestMapping("addRole")
	public @ResponseBody Map<String, Object> addRole(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName").trim();
		String roleDis = request.getParameter("roleDis");
		String menuIds = request.getParameter("menuIds");
		Set<String> set = new HashSet<>();
		Set<String> set1 = new HashSet<>();
		map.put("roleName", roleName);
		int countRoleByName = usersService.countRoleByName(map);
		if (countRoleByName > 0) {
			map.put("msg", -1);
			return map;
		}
		String[] str = menuIds.split(",");
		// 去重复
		for (String string : str) { // 所有模块id
			set.add(string);
		}
		map.put("roleName", roleName);
		map.put("roleDis", roleDis);
		try {
			usersService.addRole(map); // 添加角色
			Integer maxId = usersService.finMaxIdByRole(); // 获取最大的角色ID
			for (String menuId : set) {
				map.put("roleId", maxId);
				map.put("menuId", Integer.parseInt(menuId));
				List<CMesMenuT> menuList = usersService.menuList(map);
				for (CMesMenuT cMesMenuT : menuList) {
					set1.add(cMesMenuT.getMenuType()); // 父类模块id
				}
				usersService.addRoleAndMenu(map);
			}
			for (String string : set1) {
				map.put("roleId", maxId);
				map.put("menuId", string);
				usersService.addRoleAndMenu(map);
			}
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("editRole")
	public @ResponseBody Map<String, Object> editRole(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName").trim();
		String roleDis = request.getParameter("roleDis");
		String menuIds = request.getParameter("menuIds");
		String id = request.getParameter("id");
		map.put("roleId", id);
		map.put("id", id);
		map.put("roleName", roleName);
		map.put("roleDis", roleDis);
		// 通过ID获取角色信息
		List<RoleT> roleList = usersService.roleList(map);
		if (!roleList.get(0).getRoleName().equals(roleName)) {
			int countRoleByName = usersService.countRoleByName(map);
			if (countRoleByName > 0) {
				map.put("msg", -1);
				return map;
			}
		}
		Set<String> set = new HashSet<>();
		Set<String> set1 = new HashSet<>();
		String[] str = menuIds.split(",");
		// 去重复
		for (String string : str) {
			set.add(string);
		}
		try {
			usersService.editRole(map);
			map.put("roleId", id);
			// 移除原有的角色权限
			usersService.removeRoleAndMenuByRoleId(map);
			for (String menuId : set) {
				map.put("menuId", Integer.parseInt(menuId));
				// 添加新的角色权限
				usersService.addRoleAndMenu(map);
				List<CMesMenuT> menuList = usersService.menuList(map);
				for (CMesMenuT cMesMenuT : menuList) {
					set1.add(cMesMenuT.getMenuType());
				}
			}
			for (String string : set1) {
				map.put("roleId", id);
				map.put("menuId", string);
				usersService.addRoleAndMenu(map);
			}
			map.put("msg", 0);
		} catch (Exception e) {
		}
		System.err.println("mapr " + map);
		return map;
	}

	/**
	 * 查询子菜单
	 */
	@RequestMapping("findChildrenMenu")
	public @ResponseBody Map<String, Object> findChildrenMenu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		map.put("menuType", id);
		List<CMesMenuT> menuList = usersService.menuList(map);
		StringBuilder str = new StringBuilder();
		StringBuilder str1 = new StringBuilder();
		for (int i = 0; i < menuList.size(); i++) {
			str.append(
					"<option value='" + menuList.get(i).getId() + "'>" + menuList.get(i).getMenuName() + "</option>");
			str1.append("<li value='" + menuList.get(i).getId() + "'>" + menuList.get(i).getMenuName() + "</li>");
		}
		map.put("str", str);
		map.put("str1", str1);
		return map;
	}

	/*	*//**
			 * 工位信息
			 */
	/*
	 * @RequestMapping("productionLine") public String
	 * productionLineList(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page")
	 * Integer page) { Map<String, Object> map = new HashMap<String, Object>();
	 * String lineId = request.getParameter("lineId"); map.put("lineId",
	 * lineId); PageHelper.startPage(page, 8); List<CMesStationT> stationList =
	 * usersService.stationList(map); PageInfo<CMesStationT> pageInfo = new
	 * PageInfo<>(stationList, 5);
	 *
	 * List<CMesLineT> lineList = usersService.lineList(map); try { if (lineId
	 * == null || lineId == "") { lineId = lineList.get(0).getId().toString(); }
	 * } catch (Exception e) { }
	 *
	 * request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("lineList", lineList);
	 * request.setAttribute("lineId", lineId); return
	 * "base_control/productionLine"; }
	 */
	/*	*//**
			 * 产线管理
			 *
			 * @throws ServicesException
			 *//*
			 * @RequestMapping("lineManager") public String
			 * lineManager(HttpServletRequest request,@RequestParam(required =
			 * false,defaultValue = "1",value = "page")Integer page) throws
			 * ServicesException{ // Map<String, Object> map = new
			 * HashMap<String, Object>(); CMesLineT c = new CMesLineT(); String
			 * lineId = request.getParameter("lineId"); if(lineId!=null){
			 * c.setId(Integer.parseInt(lineId)); } map.put("lineId", lineId);
			 * PageHelper.startPage(page,8); List<CMesStationT> stationList =
			 * usersService.stationList(map); PageInfo<CMesStationT> pageInfo =
			 * new PageInfo<>(stationList,5);
			 *
			 * PageHelper.startPage(page,8); List<CMesLineT> lineList =
			 * lineservice.findAllLine(c); PageInfo<CMesLineT> pageInfo2 = new
			 * PageInfo<>(lineList,5); // request.setAttribute("pageInfo",
			 * pageInfo); request.setAttribute("pageInfo2", pageInfo2);
			 * request.setAttribute("lineId", lineId); return
			 * "base_control/lineManager"; }
			 */

	/**
	 * 添加产线
	 *
	 * @throws ServicesException
	 */
	/*
	 * @RequestMapping("addLine") public @ResponseBody Map<String, Object>
	 * addLine(HttpServletRequest request){ Map<String, Object> map = new
	 * HashMap<String, Object>(); CMesLineT c = new CMesLineT(); String lineName
	 * = request.getParameter("lineName").trim(); String lineDsc =
	 * request.getParameter("lineDsc"); String codeType =
	 * request.getParameter("codeType"); c.setName(lineName); c.setDsc(lineDsc);
	 * c.setCodeType(Integer.parseInt(codeType)); // map.put("lineName",
	 * lineName); // map.put("lineDsc", lineDsc); // map.put("codeType",
	 * codeType); // Integer count = usersService.countLineByLineName(map); try
	 * { Integer count = lineservice.addLine(c); } catch (ServicesException e) {
	 * e.printStackTrace(); if(e.getCode()>=100 && e.getCode()<200){
	 * map.put("msg",1); } } return map; }
	 */
	// 开班
	/*
	 * @RequestMapping("/toeditstatus")
	 *
	 * @ResponseBody public Map<String, Object> toeditstatus(HttpServletRequest
	 * request) { Map<String, Object> map = new HashMap<String, Object>();
	 * String id = request.getParameter("id"); String status =
	 * request.getParameter("status"); try { usersService.toeditstatus(id,
	 * status); map.put("msg", 1); } catch (Exception e) { // TODO: handle
	 * exception e.printStackTrace(); map.put("msg", 2); } return map; }
	 */

	/**
	 * 修改产线信息
	 */
	/*
	 * @RequestMapping("editLine") public @ResponseBody Map<String, Object>
	 * editLine(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<String, Object>(); String id = request.getParameter("id4");
	 * String lineName = request.getParameter("lineName4").trim(); String
	 * lineDsc = request.getParameter("lineDsc4"); String codeType =
	 * request.getParameter("codeType"); map.put("codeType", codeType);
	 * map.put("id", id); map.put("lineName", lineName); map.put("lineDsc",
	 * lineDsc); List<CMesLineT> lineList = usersService.lineList(map); if
	 * (!lineList.get(0).getName().equals(lineName)) { Integer count =
	 * usersService.countLineByLineName(map); if (count>0) { map.put("msg", 1);
	 * return map; } } usersService.editLine(map); return map; }
	 */
	/**
	 * 修改工位信息
	 *//*
		 * @RequestMapping("editStation") public @ResponseBody Map<String,
		 * Object> editStation(HttpServletRequest request) { Map<String, Object>
		 * map = new HashMap<String, Object>(); String id =
		 * request.getParameter("id"); String stationName =
		 * request.getParameter("stationName").trim();// 工位名称 String
		 * stationIndex = request.getParameter("stationIndex").trim();// 工位下标
		 * String stationTime = request.getParameter("stationTime").trim();//
		 * 工位节拍 String lineId = request.getParameter("lineId");// 所属产线 String
		 * stationType = request.getParameter("stationType");// 工位类型（1.线内站，2线外站）
		 * String stationReviewornot =
		 * request.getParameter("stationReviewornot");// 是否追溯 （1.是 ，0.否） String
		 * stationPrintornot = request.getParameter("stationPrintornot");// 是否打印
		 * String stationEndornot = request.getParameter("stationEndornot");//
		 * 是否末站 String stationAutoornot =
		 * request.getParameter("stationAutoornot");// 站业务属性（1.手动站，2.自动站，3.测试站）
		 * String stationProcessok = request.getParameter("stationProcessok");//
		 * 是否流程检查 String stationDataok =
		 * request.getParameter("stationDataok");// 是否数据检查 String
		 * stationRecipeornot = request.getParameter("stationRecipeornot");//
		 * 是否需要配方 String stationAgvornot =
		 * request.getParameter("stationAgvornot");// 是否需要AGV String
		 * stationRequstoutline =
		 * request.getParameter("stationRequstoutline");// 是否出站校验 String
		 * stationRequstin = request.getParameter("stationRequstin");// 是否请求进站
		 * String stationUploadmes = request.getParameter("stationUploadmes");//
		 * 是否上传MES String stationGunornot =
		 * request.getParameter("stationGunornot");// 是否有拧紧枪 String
		 * stationLightornot = request.getParameter("stationLightornot");//
		 * 是否点亮放行灯
		 *
		 * String stationScanderFlag =
		 * request.getParameter("stationScanderFlag"); String stationCcdFlag =
		 * request.getParameter("stationCcdFlag"); String stationRubberFlag =
		 * request.getParameter("stationRubberFlag"); String stationLeakageFlag
		 * = request.getParameter("stationLeakageFlag"); String stationEolFlag =
		 * request.getParameter("stationEolFlag"); String stationAdamFlag =
		 * request.getParameter("stationAdamFlag"); String stationPlcFlag =
		 * request.getParameter("stationPlcFlag"); map.put("stationScanderFlag",
		 * stationScanderFlag); map.put("stationCcdFlag", stationCcdFlag);
		 * map.put("stationRubberFlag", stationRubberFlag);
		 * map.put("stationLeakageFlag", stationLeakageFlag);
		 * map.put("stationEolFlag", stationEolFlag); map.put("stationAdamFlag",
		 * stationAdamFlag); map.put("stationPlcFlag", stationPlcFlag);
		 * map.put("stationName", stationName); map.put("stationIndex",
		 * stationIndex); map.put("stationTime", stationTime);
		 * map.put("stationType", stationType); map.put("lineId", lineId);
		 * List<CMesStationT> stationList1 = usersService.stationList(map);
		 * map.put("stationReviewornot", stationReviewornot);
		 * map.put("stationPrintornot", stationPrintornot);
		 * map.put("stationEndornot", stationEndornot);
		 * map.put("stationAutoornot", stationAutoornot);
		 * map.put("stationProcessok", stationProcessok);
		 * map.put("stationDataok", stationDataok);
		 * map.put("stationRecipeornot", stationRecipeornot);
		 * map.put("stationAgvornot", stationAgvornot);
		 * map.put("stationRequstoutline", stationRequstoutline);
		 * map.put("stationRequstin", stationRequstin);
		 * map.put("stationUploadmes", stationUploadmes);
		 * map.put("stationGunornot", stationGunornot);
		 * map.put("stationLightornot", stationLightornot); map.put("id", id);
		 * map.put("stationId", id); List<CMesStationT> stationList =
		 * usersService.stationList(map); // 验证工位下标是否存在 if
		 * (stationList.get(0).getStationIndex() !=
		 * Integer.parseInt(stationIndex)) { int countIndexById =
		 * usersService.countIndexById(map); if (countIndexById > 0) {
		 * map.put("msg", 2); return map; } } if
		 * (!stationList.get(0).getStationName().equals(stationName)) { Integer
		 * count = usersService.countStationByStationName(map); if (count > 0) {
		 * map.put("msg", 1); return map; } } if
		 * (stationList1.get(0).getStationEndornot() != stationEndornot) { for
		 * (CMesStationT cMesStationT : stationList1) { if
		 * (cMesStationT.getStationEndornot().equals("1")) { if
		 * (stationEndornot.equals("1")) { map.put("msg", 3); return map; } } }
		 * } try { usersService.editStation(map); map.put("msg", 0); } catch
		 * (Exception e) { e.printStackTrace(); } return map; }
		 */

	/**
	 * 通过id查询产线信息
	 */
	/*
	 * @RequestMapping("toEditLine") public @ResponseBody Map<String, Object>
	 * toEditLine(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<String, Object>(); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesLineT> lineList = usersService.lineList(map);
	 * map.put("line", lineList.get(0)); return map; }
	 */
	/*	*//**
			 * 通过id查询工位信息
			 *//*
			 * @RequestMapping("toEditStation") public @ResponseBody Map<String,
			 * Object> toEditStation(HttpServletRequest request) { Map<String,
			 * Object> map = new HashMap<String, Object>(); String id =
			 * request.getParameter("id"); map.put("stationId", id);
			 * List<CMesStationT> stationList = usersService.stationList(map);
			 * map.put("station", stationList.get(0)); return map; }
			 */

	/*	*//**
			 * 删除工位
			 *//*
			 * @RequestMapping("delStation")
			 *
			 * @ResponseBody public Map<String, Object>
			 * delStation(HttpServletRequest request) { Map<String, Object> map
			 * = new HashMap<String, Object>(); String id =
			 * request.getParameter("id"); map.put("id", id); // 验证程序注册是否正关联此工位
			 * int countRegisterByStationId =
			 * usersService.countRegisterByStationId(map); // 验证料单部件明细是否正关联此工位
			 * int countMaterialListDetailByStationId =
			 * usersService.countMaterialListDetailByStationId(map); //
			 * 验证设备管理是否正关联此工位 int countDeviceByStationId =
			 * usersService.countDeviceByStationId(map); // 验证物料信息是否正关联此工位 int
			 * countMaterialMsgByStationId =
			 * usersService.countMaterialMsgByStationId(map); // 验证螺栓信息是否正关联此工位
			 * int countBoltByStationId =
			 * usersService.countBoltByStationId(map); // 验证气密性信息是否正关联此工位 int
			 * countLeakageByStationId =
			 * usersService.countLeakageByStationId(map); // 验证其他信息是否正关联此工位 int
			 * countOtherByStationId = usersService.countOtherByStationId(map);
			 * // 验证产品配方中间表是否正关联此工位 int countProductionRecipeByStationId =
			 * usersService.countProductionRecipeByStationId(map);配置权限
			 *
			 * StringBuilder str = new StringBuilder(); if
			 * (countRegisterByStationId > 0) { str.append("程序注册,"); } if
			 * (countMaterialListDetailByStationId > 0) { str.append("料单部件明细,");
			 * } if (countDeviceByStationId > 0) { str.append("设备管理,"); } if
			 * (countMaterialMsgByStationId > 0) { str.append("物料信息,"); } if
			 * (countBoltByStationId > 0) { str.append("螺栓信息,"); } if
			 * (countLeakageByStationId > 0) { str.append("气密性信息,"); } if
			 * (countOtherByStationId > 0) { str.append("其他信息,"); } if
			 * (countProductionRecipeByStationId > 0) { str.append("产品配方,"); }
			 * if (countRegisterByStationId > 0 ||
			 * countMaterialListDetailByStationId > 0 || countDeviceByStationId
			 * > 0 || countMaterialMsgByStationId > 0 || countBoltByStationId >
			 * 0 || countLeakageByStationId > 0 || countOtherByStationId > 0 ||
			 * countProductionRecipeByStationId > 0) { map.put("str", str);
			 * map.put("msg", -1); return map; } try {
			 * usersService.delStation(map); map.put("msg", 0); } catch
			 * (Exception e) { } return map; }
			 */

	/**
	 * 删除产线
	 *//*
		 * @RequestMapping("delLine") public @ResponseBody Map<String, Object>
		 * delLine(HttpServletRequest request) { Map<String, Object> map = new
		 * HashMap<String, Object>(); String id = request.getParameter("id");
		 * map.put("id", id); // 验证工位是否关联此产线 int countStationByLineId =
		 * usersService.countStationByLineId(map); // 验证程序注册是否正关联此产线 int
		 * countRegisterByLineId = usersService.countRegisterByLineId(map); //
		 * 验证载具格式管理是否正关联此产线 int countTrayByLineId =
		 * usersService.countTrayByLineId(map); // 验证工艺配置是否正关联此产线 int
		 * countProductionProcessByLineId =
		 * usersService.countProductionProcessByLineId(map); // 验证料单管理是否正关联此产线
		 * int countMaterialListByLineId =
		 * usersService.countMaterialListByLineId(map); // 验证制造参数清单管理是否正关联此产线
		 * int countManufactureParametersByLineId =
		 * usersService.countManufactureParametersByLineId(map); //
		 * 验证总成类型管理是否正关联此产线 int countAssemblyTypeByLineId =
		 * usersService.countAssemblyTypeByLineId(map); // 验证设备管理是否正关联此产线 int
		 * countDeviceByLineId = usersService.countDeviceByLineId(map); //
		 * 验证标签管理是否正关联此产线 int countLabelManagerByLineId =
		 * usersService.countLabelManagerByLineId(map); // 验证计划管理是否正关联此产线 int
		 * countPlanByLineId = usersService.countPlanByLineId(map); //
		 * 验证班次管理是否正关联此产线 int countShiftsTeamByLineId =
		 * usersService.countShiftsTeamByLineId(map); // 验证员工管理是否正关联此产线 int
		 * countEmpByLineId = usersService.countEmpByLineId(map); //
		 * 验证生成条码是否正关联此产线 int countBarCodeByLineId =
		 * usersService.countBarCodeByLineId(map); StringBuilder str = new
		 * StringBuilder(); if (countStationByLineId > 0) { str.append("工位管理,");
		 * } if (countRegisterByLineId > 0) { str.append("程序注册,"); } if
		 * (countTrayByLineId > 0) { str.append("载具格式,"); } if
		 * (countProductionProcessByLineId > 0) { str.append("工艺配置,"); } if
		 * (countMaterialListByLineId > 0) { str.append("料单管理,"); } if
		 * (countManufactureParametersByLineId > 0) { str.append("制造参数清单管理,"); }
		 * if (countAssemblyTypeByLineId > 0) { str.append("总成类型管理,"); } if
		 * (countDeviceByLineId > 0) { str.append("设备管理,"); } if
		 * (countLabelManagerByLineId > 0) { str.append("标签管理,"); } if
		 * (countPlanByLineId > 0) { str.append("计划管理,"); } if
		 * (countShiftsTeamByLineId > 0) { str.append("班次管理,"); } if
		 * (countEmpByLineId > 0) { str.append("员工管理,"); } if
		 * (countBarCodeByLineId > 0) { str.append("生成条码,"); } if
		 * (countRegisterByLineId > 0 || countTrayByLineId > 0 ||
		 * countProductionProcessByLineId > 0 || countMaterialListByLineId > 0
		 * || countManufactureParametersByLineId > 0 ||
		 * countAssemblyTypeByLineId > 0 || countDeviceByLineId > 0 ||
		 * countLabelManagerByLineId > 0 || countPlanByLineId > 0 ||
		 * countShiftsTeamByLineId > 0 || countEmpByLineId > 0 ||
		 * countBarCodeByLineId > 0 || countStationByLineId > 0) {
		 * map.put("msg", -1); map.put("str", str); return map; } try {
		 * usersService.delLine(map); map.put("msg", 0); } catch (Exception e) {
		 * } return map; }
		 */

	/**
	 * 添加工位 27个值
	 */
	/*
	 * @RequestMapping("addStation") public @ResponseBody Map<String, Object>
	 * addStation(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<String, Object>(); String stationName =
	 * request.getParameter("stationName").trim();// 工位名称 String stationIndex =
	 * request.getParameter("stationIndex").trim();// 工位下标 String stationTime =
	 * request.getParameter("stationTime").trim();// 工位节拍 String lineId =
	 * request.getParameter("lineId");// 所属产线 String stationType =
	 * request.getParameter("stationType");// 工位类型（1.线内站，2线外站） String
	 * stationReviewornot = request.getParameter("stationReviewornot");// 是否追溯
	 * （1.是 ，0.否） String stationPrintornot =
	 * request.getParameter("stationPrintornot");// 是否打印 String stationEndornot
	 * = request.getParameter("stationEndornot");// 是否末站 String stationAutoornot
	 * = request.getParameter("stationAutoornot");// 站业务属性（1.手动站，2.自动站，3.测试站）
	 * String stationProcessok = request.getParameter("stationProcessok");//
	 * 是否流程检查 String stationDataok = request.getParameter("stationDataok");//
	 * 是否数据检查 String stationRecipeornot =
	 * request.getParameter("stationRecipeornot");// 是否需要配方 String
	 * stationAgvornot = request.getParameter("stationAgvornot");// 是否需要AGV
	 * String stationRequstoutline =
	 * request.getParameter("stationRequstoutline");// 是否出站校验 String
	 * stationRequstin = request.getParameter("stationRequstin");// 是否请求进站
	 * String stationUploadmes = request.getParameter("stationUploadmes");//
	 * 是否上传MES String stationGunornot =
	 * request.getParameter("stationGunornot");// 是否有拧紧枪 String
	 * stationLightornot = request.getParameter("stationLightornot");// 是否点亮放行灯
	 *
	 * String stationScanderFlag = request.getParameter("stationScanderFlag");
	 * String stationCcdFlag = request.getParameter("stationCcdFlag"); String
	 * stationRubberFlag = request.getParameter("stationRubberFlag"); String
	 * stationLeakageFlag = request.getParameter("stationLeakageFlag"); String
	 * stationEolFlag = request.getParameter("stationEolFlag"); String
	 * stationAdamFlag = request.getParameter("stationAdamFlag"); String
	 * stationPlcFlag = request.getParameter("stationPlcFlag");
	 * map.put("stationScanderFlag", stationScanderFlag);
	 * map.put("stationCcdFlag", stationCcdFlag); map.put("stationRubberFlag",
	 * stationRubberFlag); map.put("stationLeakageFlag", stationLeakageFlag);
	 * map.put("stationEolFlag", stationEolFlag); map.put("stationAdamFlag",
	 * stationAdamFlag); map.put("stationPlcFlag", stationPlcFlag);
	 * map.put("stationName", stationName); map.put("stationIndex",
	 * stationIndex); map.put("stationTime", stationTime);
	 * map.put("stationType", stationType); map.put("lineId", lineId);
	 * List<CMesStationT> stationList = usersService.stationList(map);
	 * map.put("stationReviewornot", stationReviewornot);
	 * map.put("stationPrintornot", stationPrintornot);
	 * map.put("stationEndornot", stationEndornot); map.put("stationAutoornot",
	 * stationAutoornot); map.put("stationProcessok", stationProcessok);
	 * map.put("stationDataok", stationDataok); map.put("stationRecipeornot",
	 * stationRecipeornot); map.put("stationAgvornot", stationAgvornot);
	 * map.put("stationRequstoutline", stationRequstoutline);
	 * map.put("stationRequstin", stationRequstin); map.put("stationUploadmes",
	 * stationUploadmes); map.put("stationGunornot", stationGunornot);
	 * map.put("stationLightornot", stationLightornot); // 验证工位下标是否存在 int
	 * countIndexById = usersService.countIndexById(map); if (countIndexById >
	 * 0) { map.put("msg", 2); return map; } Integer count =
	 * usersService.countStationByStationName(map); if (count > 0) {
	 * map.put("msg", 1); return map; } for (CMesStationT cMesStationT :
	 * stationList) { if (cMesStationT.getStationEndornot().equals("1")) { if
	 * (stationEndornot.equals("1")) { map.put("msg", 3); return map; } } } try
	 * { usersService.addStation(map); map.put("msg", 0); } catch (Exception e)
	 * { e.printStackTrace(); } return map; }
	 */
	/**
	 * 载具格式信息
	 *
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("carrierFormatManager")
	public String carrierFormatManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, 8);
		List<CMesTrayT> carrierList = usersService.carrierList(map);
		PageInfo<CMesTrayT> pageInfo = new PageInfo<>(carrierList, 5);
		List<CMesLineT> lineList = usersService.lineList(map);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("lineList", lineList);
		return "base_control/carrierFormatManager";
	}

	/**
	 * 添加载具格式
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("addCarrier")
	public @ResponseBody Map<String, Object> addCarrier(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String trayName = request.getParameter("trayName").trim();
		String trayVr = request.getParameter("trayVr");
		String dis = request.getParameter("dis");
		String lineId = request.getParameter("lineId");
		map.put("trayName", trayName);
		map.put("lineId", lineId);
		map.put("trayVr", trayVr);
		map.put("dis", dis);
		Integer count = usersService.countCarrierByName(map);
		if (count > 0) {
			map.put("msg", 1);
			return map;
		}
		try {
			usersService.addCarrier(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("toUpdateCarrier")
	public @ResponseBody Map<String, Object> toUpdateCarrier(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesTrayT> carrierList = usersService.carrierList(map);
		map.put("carrier", carrierList.get(0));
		return map;
	}

	/**
	 * 修改载具格式
	 */
	@RequestMapping("editCarrier")
	public @ResponseBody Map<String, Object> editCarrier(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String trayName = request.getParameter("trayName").trim();
		String trayVr = request.getParameter("trayVr");
		String dis = request.getParameter("dis");
		String lineId = request.getParameter("lineId");
		String id = request.getParameter("id");
		map.put("id", id);
		map.put("trayName", trayName);
		map.put("lineId", Integer.parseInt(lineId));
		map.put("trayVr", trayVr);
		map.put("dis", dis);
		map.put("dt", new Date());
		List<CMesTrayT> carrierList = usersService.carrierList(map);
		if (!carrierList.get(0).getTrayName().equals(trayName)) {
			Integer count = usersService.countCarrierByName(map);
			if (count > 0) {
				map.put("msg", 1);
				return map;
			}
		}
		try {
			usersService.editCarrier(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	/**
	 * 删除载具
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("delCarrier")
	public @ResponseBody Map<String, Object> delCarrier(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		try {
			usersService.delCarrier(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	/**
	 * 配置角色
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("configRole")
	public @ResponseBody Map<String, Object> configRole(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String roleId = request.getParameter("id");
			String chkValue = request.getParameter("chk_value");
			map.put("roleId", roleId);
			usersService.delMenuCrudByRoleId(map);
			for (int i = 0; i < UserController.getInts(chkValue).size(); i++) {
				map.put("menuId", UserController.getInts(chkValue).get(i).get(0));
				map.put("menuCrudId", UserController.getInts(chkValue).get(i).get(1));
				map.put("roleId", roleId);
				usersService.addMenuCrud(map);
			}
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

	private static List<List<Integer>> getInts(String str) {
		String[] strs = str.split(",");
		List<Integer> intList = new ArrayList<Integer>();
		List<Integer> intList2 = new ArrayList<Integer>();
		List<List<Integer>> list3 = new ArrayList<>();
		List<List<Integer>> list4 = new ArrayList<>();
		for (String string : strs) {
			intList.add(Integer.parseInt(string));
		}
		for (int i = 0; i < intList.size(); i++) {
			if (intList.get(i) > 11) {
				intList2.add(i);
			}
		}
		int j = 0;
		for (int i = 0; i < intList2.size(); i++) {
			List<Integer> list = intList.subList(j, intList2.get(i));
			j = intList2.get(i);
			list3.add(list);
		}
		List<Integer> list = intList.subList(j, intList.size());
		list3.add(list);
		for (int i = 0; i < list3.size(); i++) {
			List<Integer> numberList = list3.get(i);
			for (int k = 1; k < numberList.size(); k++) {
				List<Integer> numberList2 = new ArrayList<Integer>();
				numberList2.add(numberList.get(0));
				numberList2.add(numberList.get(k));
				list4.add(numberList2);
			}
		}
		return list4;
	}

	/*************************************
	 * 接口管理
	 *****************************************************/
	@RequestMapping("interfaceManager")
	public String interfaceManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CMesInterface> interfaceList2 = usersService.interfaceList(map);
		String mark = request.getParameter("mark");
		List<CMesInterface> interfaceList1 = usersService.interfaceList(map);
		if (mark == null) {
			if (interfaceList1.size() > 0) {
				mark = interfaceList1.get(0).getRemark();
			} else {
				mark = "";
			}
		}
		map.put("mark", mark);
		PageHelper.startPage(page, 8);
		List<CMesInterface> interfaceList = usersService.interfaceList(map);
		PageInfo<CMesInterface> pageInfo = new PageInfo<>(interfaceList, 5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("interfaceList", interfaceList2);
		request.setAttribute("mark", mark);
		return "tool_control/interfaceManager";
	}

	@RequestMapping("addInterface")
	public @ResponseBody Map<String, Object> addInterface(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("name").trim();
		String mark = request.getParameter("remark");
		String valu = request.getParameter("valu").trim();
		String dis = request.getParameter("dis");
		map.put("name", name);
		map.put("remark", mark);
		map.put("val", valu);
		map.put("dis", dis);
		Integer count = usersService.countInterfaceByName(map);
		if (count > 0) {
			map.put("msg", 1);
			return map;
		}
		try {
			usersService.addInterface(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("toEditInterface")
	public @ResponseBody Map<String, Object> toEditInterface(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesInterface> interfaceList = usersService.interfaceList(map);
		map.put("interfaceList", interfaceList.get(0));
		return map;
	}

	@RequestMapping("delInterface")
	public @ResponseBody Map<String, Object> delInterface(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		try {
			usersService.delInterface(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("editInterface")
	public @ResponseBody Map<String, Object> editInterface(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("name").trim();
		String remark = request.getParameter("remark").trim();
		String val = request.getParameter("val").trim();
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		map.put("name", name);
		map.put("id", id);
		List<CMesInterface> interfaceList = usersService.interfaceList(map);
		map.put("remark", remark);
		map.put("val", val);
		map.put("dis", dis);
		if (!interfaceList.get(0).getName().equals(name)) {
			Integer count = usersService.countInterfaceByName(map);
			if (count > 0) {
				map.put("msg", 1);
				return map;
			}
		}
		try {
			usersService.editInterface(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
}
