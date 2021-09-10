package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.GetCookie;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.all.CMesUserTService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "/api")
@Api(value = "用戶管理", description = "用戶管理")
public class CMesUserControllerl {

	@Autowired
	CMesUserTService userService;

	@Autowired
	CMesRoleTService roleService;

	// 用戶列表
	@RequestMapping(value = "/user/userList", method = RequestMethod.POST)
	@ApiOperation(value = "用戶列表", notes = "用戶列表")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "id", value = "编号", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "userName", value = "用户名", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "权限等级", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "department", value = "部门", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "roleId", value = "角色id", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "userNumber", value = "编号", required = false, paramType = "query", dataType = "Integer"),
	})
	public JSONObject userList(HttpServletRequest request) throws Exception {
		System.out.println(GetCookie.getUser());
		Integer pageNum= EqualsUtil.integer(request,"pageNum","页码",false);
		Integer pageSize= EqualsUtil.integer(request,"pageSize","每页记录数",false);
		Integer id= EqualsUtil.integer(request,"id","id",false);
		String userName= EqualsUtil.string(request,"userName","用户名",false);
		Integer status= EqualsUtil.integer(request,"status","权限等级",false);
		Integer roleId= EqualsUtil.integer(request,"roleId","角色id",false);
		String department= EqualsUtil.string(request,"department","部门",false);
		Integer userNumber= EqualsUtil.integer(request,"userNumber","编号",false);
		PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
		CMesUserT userT=new CMesUserT();
		userT.setUserNumber(userNumber);
		userT.setUserName(userName);
		userT.setStatus(String.valueOf(status));
		userT.setDepartment(department);
		userT.setRoleId(roleId);

		List<CMesUserT> userList = userService.findAllUser(userT);
		JSONObject json = new JSONObject();
		try {
			PageInfo<CMesUserT> pageInfo = new PageInfo<>(userList);
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

	// 新增用戶
	@ResponseBody
	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	@ApiOperation(value = "添加用户", notes = "添加用户")
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 202, message = "添加失败") })
	@OptionalLog(module="基础建模", module2="用户管理", method="添加用户")
	public JSONObject addUser(@ModelAttribute @Valid CMesUserT cMesUserT) throws ServicesException {
		CMesUserT user = new CMesUserT();
		user.setUserName(cMesUserT.getUserName());
		user.setUserPwd(Encryption
				.getPassWord(cMesUserT.getUserPwd() + cMesUserT.getUserName() + 666666 + cMesUserT.getUserPwd(), 555));
		user.setRoleId(cMesUserT.getRoleId());
		user.setStatus(cMesUserT.getStatus());
		user.setPosition(cMesUserT.getPosition());
		user.setDepartment(cMesUserT.getDepartment());
		user.setUserNumber(2);
		user.setName(cMesUserT.getName());
		JSONObject json = new JSONObject();

		try {
			userService.addUser(user);
			json.put("code", 0);
			json.put("msg", "添加成功");

		} catch (ServicesException e) {
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;

	}

	// 角色信息
	@ResponseBody
	@RequestMapping(value = "/user/findByRole", method = RequestMethod.POST)
	@ApiOperation(value = "角色信息", notes = "查詢角色信息")
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 202, message = "查询失败") })
	public JSONObject findByRole(HttpServletRequest request) throws ServicesException {

		JSONObject json = new JSONObject();
		try {
			List<RoleT> findAllRole = roleService.findAllRole(null);
			json.put("code", 0);
			json.put("findAllRole", findAllRole);
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;

	}

	// 刪除用戶
	@RequestMapping(value = "/user/delUser", method = RequestMethod.POST)
	@ApiOperation(value = "删除用戶信息", notes = "根据id删除用戶")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "Integer")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 202, message = "删除失败") })
	@OptionalLog(module="基础建模", module2="用户管理", method="删除用户")
	public JSONObject delUser(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			userService.delUser(id);
			json.put("code", 0);
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 修改用戶
	@ResponseBody
	@RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
	@ApiOperation(value = "修改用戶", notes = "修改用戶")
	@OptionalLog(module="基础建模", module2="用户管理", method="修改用户")
	public JSONObject updateUser(@ModelAttribute CMesUserT cMesUserT) throws ServicesException {
		CMesUserT user = new CMesUserT();
		user.setId(cMesUserT.getId());
		user.setUserName(cMesUserT.getUserName());
		CMesUserT findByidUser = userService.findByidUser(cMesUserT.getId());
		if (findByidUser.getUserPwd().equals(cMesUserT.getUserPwd())) {
			user.setUserPwd(findByidUser.getUserPwd());
		} else {
			user.setUserPwd(Encryption.getPassWord(
					cMesUserT.getUserPwd() + cMesUserT.getUserName() + 666666 + cMesUserT.getUserPwd(), 555));
		}
		user.setPosition(cMesUserT.getPosition());
		user.setRoleId(cMesUserT.getRoleId());
		user.setStatus(cMesUserT.getStatus());
		user.setDepartment(cMesUserT.getDepartment());
		JSONObject json = new JSONObject();
		try {
			userService.updateUser(user);
			json.put("code", 0);
			json.put("msg", "修改成功");

		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = "/user/updatePwd", method = RequestMethod.POST)
	@ApiOperation(value = "修改密码", notes = "修改密码")
	@OptionalLog(module="基础建模", module2="用户管理", method="修改密码")
	public JSONObject updatePwd(Integer id, String userName, String passWord)
			throws ServicesException {
     	JSONObject json = new JSONObject();
		try {
			String pwd = Encryption.getPassWord(passWord + userName + 666666 + passWord, 555);
			System.err.println("pwd===="+pwd);
			userService.updatePwd(pwd, id);
			json.put("code", 0);
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "修改失败");
		}
		return json;
	}

	// 重制密码
	@ResponseBody
	@RequestMapping(value = "/user/resetPwd", method = RequestMethod.POST)
	@ApiOperation(value = "重制密码", notes = "重制密码")
	@OptionalLog(module="基础建模", module2="用户管理", method="重制密码")
	public Rjson resetPwd(HttpServletRequest request)
			throws ServicesException {
		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("userName")) || StringUtils.isEmpty(map.get("oldPassword")) || StringUtils.isEmpty(map.get("newPassword"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Rjson rj=userService.resetPwd(map);

			return rj;
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	/**
	 * 查询所有部门
	 * @return
	 */
	@RequestMapping("/user/findDepartment")
	@ResponseBody
	public Rjson findDepartment() {
		try {
			List<JSONObject> list = userService.findDepartment();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询直属主管
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/findReportsTo")
	@ResponseBody
	public Rjson findReportsTo(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			JSONObject json = userService.findReportsTo(userId);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改直属主管
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/updateReportsTo")
	@ResponseBody
	public Rjson updateReportsTo(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			Integer reportsToId = EqualsUtil.integer(request, "reportsToId", "直属主管ID", true);

			userService.updateReportsTo(userId, reportsToId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
