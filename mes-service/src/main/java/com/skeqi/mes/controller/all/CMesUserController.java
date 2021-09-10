package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.all.CMesUserTService;
import com.skeqi.mes.util.ToolUtils;


/***
 *
 * @author ENS 用户管理 1
 *
 */
@Controller
@RequestMapping(value = "skq")
public class CMesUserController {

	@Autowired
	CMesUserTService userService;

	@Autowired
	CMesRoleTService roleService;

	// @Autowired
	// UsersService usersService;

	// 添加user
	@RequestMapping("addUser")
	@ResponseBody
	public JSONObject addUser(HttpServletRequest request) {
		String userName = request.getParameter("userName").trim();
		String userPwd = request.getParameter("userPwd").trim();
		String role = request.getParameter("role");
		String status = request.getParameter("isChecked");
		String department = request.getParameter("department");

		CMesUserT user = new CMesUserT();
		user.setUserName(userName);
		user.setUserPwd(Encryption.getPassWord(userPwd + userName + 666666 + userPwd, 555));
		user.setRoleId(Integer.parseInt(role));
		user.setStatus(status);
		user.setDepartment(department);
		user.setUserNumber(2);

		JSONObject json = new JSONObject();

		try {
			userService.addUser(user);
			json.put("code", 0);

		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	// 通过id 查询user
	@RequestMapping("toUpdateUser")
	@ResponseBody
	public JSONObject toUpdateUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();

		try {
			json.put("user", userService.findByidUser(Integer.parseInt(id)));
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}

	@RequestMapping("toUpdateRole")
	@ResponseBody
	public JSONObject toUpdateRole(HttpServletRequest request) throws ServicesException {
		JSONObject json = new JSONObject();

		StringBuilder str = new StringBuilder();
		String roleId = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("roleId", roleId);
		map.put("flag", 1);

		RoleT role = new RoleT();
		role.setId(Integer.parseInt(roleId));

		List<RoleT> roleList = roleService.findAllRole(role);
        System.err.println("userCotroller========");
		// List<CMesRoleMenuT> roleMenuList = usersService.roleAndMenuList(map);
		// //
		//
		// List<CMesMenuCrudT> listMenuCrudByRoleId =
		// usersService.listMenuCrudByRoleId(map); // 当前角色可以执行的增删改
		// map.put("listMenuCrudByRoleId", listMenuCrudByRoleId);
		//
		// for (int i = 0; i <roleMenuList.size(); i++) {
		//
		// str.append("<li value='" + roleMenuList.get(i).getMenuId() + "'>" +
		// roleMenuList.get(i).getMenuName() +"</li>");
		//
		// }
		//
		// map.put("roleMenuList", roleMenuList);

		map.put("role", roleList.get(0));
		map.put("menuList", str);

		return json;
	}

	@RequestMapping("updateUser")
	@ResponseBody
	public JSONObject updateUser(HttpServletRequest request) throws Exception, ServicesException {
		String id = request.getParameter("id");
		String userName = request.getParameter("userName1").trim();
		String userPwd = request.getParameter("userPwd1").trim();
		String role = request.getParameter("role1");
		String status = request.getParameter("status");

		CMesUserT user = new CMesUserT();
		user.setId(Integer.parseInt(id));
		user.setUserName(userName);
		CMesUserT findByidUser = userService.findByidUser(Integer.parseInt(id));
		if (findByidUser.getUserPwd().equals(userPwd)) {
			user.setUserPwd(findByidUser.getUserPwd());
		} else {
			user.setUserPwd(Encryption.getPassWord(userPwd + userName + 666666 + userPwd, 555));
		}
		user.setRoleId(Integer.parseInt(role));
		user.setStatus(status);
		JSONObject json = new JSONObject();
		try {
			userService.updateUser(user);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("delUser")
	@ResponseBody
	public JSONObject delUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			userService.delUser(Integer.parseInt(id));
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}

	@RequestMapping("userList")
	public String userList(HttpSession session, HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		CMesUserT user = new CMesUserT();
		try {
			PageHelper.startPage(page, 8);
			List<CMesUserT> userList = userService.findAllUser(user);
			PageInfo<CMesUserT> pageInfo = new PageInfo<>(userList, 5);
			request.setAttribute("pageInfo", pageInfo);
			RoleT role = new RoleT();
			CMesMenuT menu = new CMesMenuT();
			List<RoleT> roleList = roleService.findAllRole(role);
			List<CMesMenuT> menuList = roleService.findAllMenu(menu);
			session.setAttribute("roleList", roleList);
			session.setAttribute("menuList", menuList);
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "base_control/userManager";
	}

	@RequestMapping(value = "user/findUserList", method = RequestMethod.POST)
	public void findUserList(HttpServletResponse response) throws ServicesException, IOException {
		List<CMesUserT> list = userService.findAllUser(null);

		JSONObject result = new JSONObject();
		result.put("data", list);
		response.getWriter().append(result.toJSONString());
	}


}
